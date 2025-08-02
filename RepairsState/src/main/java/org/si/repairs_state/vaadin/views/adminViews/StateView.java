package org.si.repairs_state.vaadin.views.adminViews;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.si.repairs_state.domain.Customer;
import org.si.repairs_state.domain.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import static org.si.repairs_state.vaadin.ViewNavigator.navigator;
import static org.si.repairs_state.vaadin.ViewPaths.REGISTR;
import static org.si.repairs_state.vaadin.ViewPaths.REPAIRS;


@SpringComponent
@UIScope
public class StateView extends CustomComponent implements View {
    private Long currentRepair;
    private String currentModelis;
    private String currentPrietaisas;
    private String currentTelNr;


    private Devices device;
    private Binder<Devices> binders = new Binder<>(Devices.class);

    private Grid<Devices> grid3 = new Grid(Devices.class);
    private Grid<Devices> grid4 = new Grid(Devices.class);
    private TextField vardas = new TextField("vardas");
    private TextField pavarde = new TextField("pavarde");
    private TextField telnr = new TextField("telnr");
    private TextField adresas = new TextField("adresas");
    private TextField pastas = new TextField("pastas");
    private TextField gedimas = new TextField("gedimas");


    private Binder<Customer> binder = new Binder<>(Customer.class);
    private Customer customer = new Customer("", "", "", "", "", "", "");

    private Grid<Customer> grid = new Grid(Customer.class);
    private Grid<Customer> grid2 = new Grid(Customer.class);
    private TextField busena = new TextField("Būsena");
    private TextArea komentaras = new TextArea("Komentaras");
    private TextField prietaisas = new TextField();
    private TextField modelis = new TextField();
    private TextField rma = new TextField();
   // private TextField telnr = new TextField();
    private TextField laikas = new TextField();
    private Button save = new Button("Save", e -> saveBusKom(customer));

    Date date = new Date();
    String DATE_FORMAT = "yyyy/MM/dd";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);


    private final JdbcTemplate jdbcTemplate;


    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT  Busena, Komentaras,  Prietaisas, Modelis, RMA, Laikas, TelNr FROM remontas WHERE RMA =" + currentRepair + " GROUP BY RMA",
                (rs, rowNum) -> new Customer(rs.getString("Busena"), rs.getString("Komentaras"),
                        currentPrietaisas, currentModelis, rs.getString("RMA"), rs.getString("TelNr")
                        , rs.getString("Laikas")
                ));
    }

    public List<Devices> findAll3() {
        return jdbcTemplate.query("SELECT  RMA, vardas, pavarde, telnr, adresas, pastas, prietaisas, modelis, prietaisoSN, gedimas, laikas FROM customreg WHERE RMA = " + currentRepair + "",
                (rs, rowNum) -> new Devices(
                        rs.getLong("RMA"),
                        rs.getString("vardas"), rs.getString("pavarde"), rs.getString("telnr"), rs.getString("adresas"),
                        rs.getString("pastas"), rs.getString("prietaisas"), rs.getString("modelis"), rs.getString("prietaisoSN"), rs.getString("gedimas"), rs.getString("laikas")));
    }

    public List<Customer> findAll2() {
        return jdbcTemplate.query("SELECT    Busena, Komentaras,  Prietaisas, Modelis, RMA, Laikas, TelNr FROM remontas WHERE RMA =" + currentRepair + "",
                (rs, rowNum) -> new Customer(rs.getString("Busena"), rs.getString("Komentaras"),
                        rs.getString("Prietaisas"), rs.getString("Modelis"), rs.getString("RMA"), rs.getString("TelNr")
                        , rs.getString("Laikas")

                ));
    }

    @Autowired
    public StateView(JdbcTemplate template) {
        jdbcTemplate = template;
        //updateGrid3();
        binder.bind(busena, Customer::getBusena, Customer::setBusena);
        binder.bind(komentaras, Customer::getKomentaras, Customer::setKomentaras);
        binder.bind(modelis, Customer::getModelis, Customer::setModelis);
        binder.bind(prietaisas, Customer::getPrietaisas, Customer::setPrietaisas);
        komentaras.setMaxLength(200);
                busena.setMaxLength(20);
        binder.setBean(customer);
        VerticalLayout vertical = new VerticalLayout(grid2);
        HorizontalLayout HorLayout = new HorizontalLayout(busena, komentaras);
        VerticalLayout veryLayout = new VerticalLayout(HorLayout, save);
        VerticalLayout verLayoutt = new VerticalLayout(grid, grid4, grid3, veryLayout);
        HorizontalLayout stateViewLayout = new HorizontalLayout(verLayoutt, vertical);
        stateViewLayout.setWidth("100.0%");
        stateViewLayout.setSizeUndefined();
        stateViewLayout.setSizeFull();
        grid4.setColumns("vardas", "pavarde", "adresas", "pastas");
        grid4.setHeightMode(HeightMode.UNDEFINED);
        grid4.setWidth("100.0%");
        grid3.setColumns("gedimas");
        grid3.setHeightMode(HeightMode.UNDEFINED);
        grid3.setWidth("100.0%");
        grid.setColumns("RMA", "prietaisas", "modelis");
        grid.setWidth("100.0%");
        grid.getColumn("RMA").setWidth(100);
        grid.setHeightMode(HeightMode.UNDEFINED);

        grid2.setColumns("busena", "komentaras", "laikas");
        grid2.setWidth("100.0%");
        binder.bindInstanceFields(this);

        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        Button view1 = new Button("Remontai", e -> navigator.navigateTo(REPAIRS.getViewPath()));
        view1.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        Button view2 = new Button("Registracija", e -> navigator.navigateTo( REGISTR.getViewPath()));
        view2.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        VerticalLayout menu = new VerticalLayout(title, view1, view2);
        HorizontalLayout MainUIViewLayout = new HorizontalLayout(menu);
        MainUIViewLayout.setSizeFull();
        HorizontalLayout stateMenuLayout = new HorizontalLayout(MainUIViewLayout, stateViewLayout);
        setCompositionRoot(stateMenuLayout);

    }

    private void updateGrid() {
        List<Customer> customers = findAll();


        grid.setItems(customers);

    }

    private void updateGrid2() {
        List<Customer> customers2 = findAll2();
        grid2.setItems(customers2);

    }

    private void updateGrid3() {
        List<Devices> devices = findAll3();
        grid3.setItems(devices);
        grid4.setItems(devices);
    }
    //Duomenų išsaugojimas iš laukų

    /**
     * Register a new user
     *
     * @param customerRequestt User object encapsulating the input form data, namely user_name, email, and password
     */
    private void saveBusKom(Customer customerRequestt) {
        try {
            addBusKom(customerRequestt);
            //Notification.show("Įrašas įsaugotas " + kinta);
            busena.setValue("");
            komentaras.setValue("");
        } catch (Exception e) {
        }
    }

    public void addBusKom(Customer customerRequestt) throws Exception {
        String busena = customerRequestt.getBusena();
        String komentaras = customerRequestt.getKomentaras();
        jdbcTemplate.update(

                "INSERT INTO remontas(Busena, Komentaras, Prietaisas, Modelis, RMA, TelNr, Laikas) VALUES(?, ?, ?, ?, ?, ?, ?)",
                busena, komentaras, currentPrietaisas, currentModelis, currentRepair, currentTelNr, sdf.format(date)
        );
        updateGrid2();
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        currentModelis = (String) getUI().getSession().getAttribute("Modelis");
        currentPrietaisas = (String) getUI().getSession().getAttribute("Prietaisas");
        currentTelNr = (String) getUI().getSession().getAttribute("TelNr");
        currentRepair = (Long) getUI().getSession().getAttribute("RMA");
        updateGrid2();
        updateGrid();
        updateGrid3();
    }

    @Override
    public void beforeLeave(ViewBeforeLeaveEvent event) {

        // userAuthenticationDAOSQL.signOut();
        event.navigate();
    }
}