package org.si.repairs_state.vaadin.views.userViews;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.si.repairs_state.domain.Customer;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import static org.si.repairs_state.vaadin.ViewPaths.LOGIN;
import static org.si.repairs_state.vaadin.ViewNavigator.navigator;

@SpringComponent
@UIScope
public class MainView extends CustomComponent implements View {

    private Customer customer;
    private Binder<Customer> binder = new Binder<>(Customer.class);
    private Grid<Customer> grid = new Grid(Customer.class);
    private Grid<Customer> grid2 = new Grid(Customer.class);
    private TextField busena = new TextField();
    private TextField komentaras = new TextField();
    private TextField prietaisas = new TextField();
    private TextField modelis = new TextField();
    private TextField rma = new TextField();
    private TextField telnr = new TextField();
    public String selectedId;
    private Customer selectedCustomer;

    private UserAuthenticationDAO userAuthenticationDAOSQL;
    private String currentUserName;
    private Label label = new Label();
    private Button signOut = new Button("Sign out", e -> navigator.navigateTo(LOGIN.getViewPath()));

    @Autowired
    public void setUserAuthenticationDAOSQL(UserAuthenticationDAO userAuthenticationDAOSQL) {

        this.userAuthenticationDAOSQL = userAuthenticationDAOSQL;
    }

    private final JdbcTemplate jdbcTemplate;

    public List<Customer> findAll() {


        return jdbcTemplate.query("SELECT  Busena, Komentaras,  Prietaisas, Modelis, RMA, Laikas, TelNr FROM remontas  WHERE  TelNr =  "+ currentUserName+ " GROUP BY RMA",
                (rs, rowNum) -> new Customer(rs.getString("Busena"), rs.getString("Komentaras"),
                        rs.getString("Prietaisas"), rs.getString("Modelis"), rs.getString("RMA"), rs.getString("TelNr")
                        , rs.getString("Laikas")
                ));
    }

    public List<Customer> findAll2() {
        return jdbcTemplate.query("SELECT   Busena, Komentaras,  Prietaisas, Modelis, RMA, Laikas, TelNr FROM remontas WHERE RMA ="+selectedId+" ",
                (rs, rowNum) -> new Customer( rs.getString("Busena"), rs.getString("Komentaras"),
                        rs.getString("Prietaisas"), rs.getString("Modelis"), rs.getString("RMA"), rs.getString("TelNr")
                        , rs.getString("Laikas")

                ));
    }


    @Autowired
    public MainView(JdbcTemplate template) {
        jdbcTemplate = template;
        HorizontalLayout mainViewLayout = new HorizontalLayout(grid, grid2);
        mainViewLayout.setWidth("100.0%");
        mainViewLayout.setSizeUndefined();
        mainViewLayout.setSizeFull();

        grid.setColumns("RMA", "prietaisas", "modelis");
        grid.setWidth("100.0%");
        grid.getColumn("RMA").setWidth(100);
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(event -> {

            selectedCustomer = event.getFirstSelectedItem().get();
            selectedId = selectedCustomer.getRMA();
            findAll2();
            updateGrid2();
        });

        grid2.setColumns("busena", "komentaras", "laikas");
        grid2.setWidth("100.0%");
        binder.bindInstanceFields(this);


        setCompositionRoot(mainViewLayout);

    }

    private void updateGrid2() {
        List<Customer> customers2 = findAll2();
        grid2.setItems(customers2);

    }

    private void updateGrid() {
        List<Customer> customers = findAll();


        grid.setItems(customers);

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        currentUserName = (String) getUI().getSession().getAttribute("UserName");
        updateGrid();

    }

    @Override
    public void beforeLeave(ViewBeforeLeaveEvent event) {
        getUI().getSession().setAttribute("UserName", "");
        userAuthenticationDAOSQL.signOut();
        event.navigate();
    }

}

