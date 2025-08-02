package org.si.repairs_state.vaadin.views.adminViews;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.si.repairs_state.domain.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

import static org.si.repairs_state.vaadin.ViewNavigator.navigator;
import static org.si.repairs_state.vaadin.ViewPaths.REGISTR;
import static org.si.repairs_state.vaadin.ViewPaths.REPAIRS;
import static org.si.repairs_state.vaadin.ViewPaths.VABALS;
@Theme("darktheme")
@SpringComponent
@UIScope

public class RepairsView extends CustomComponent implements View {

    public long busId;
    public String busMod;
    public  String busPrie;
    public  String busTel;
    private Devices device;
    private Binder<Devices> binder = new Binder<>(Devices.class);

    private Grid<Devices> grid = new Grid(Devices.class);
    private TextField RMA = new TextField("RMA");
    private TextField vardas = new TextField("vardas");
    private TextField pavarde = new TextField("pavarde");
    private TextField telnrr = new TextField("telnrr");
    private TextField adresas = new TextField("adresas");
    private TextField pastas = new TextField("pastas");
    private TextField prietaisas = new TextField("prietaisas");
    private TextField modelis = new TextField("modelis");
    private TextField prietaisoSN = new TextField("prietaisoSN");
    private TextField gedimas = new TextField("gedimas");
    private TextField laikas = new TextField("reg. laikas");

    final int page = 1;
    final int limit = 10;
    private TextField filterText = new TextField();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Devices> findAll(String value) {
        return jdbcTemplate.query("SELECT  RMA, vardas, pavarde, telnr, adresas, pastas, prietaisas, modelis, prietaisoSN, gedimas, laikas FROM customreg WHERE RMA like '%" + value + "%' or vardas like '%" + value + "%' or vardas like '%" + value + "%'or prietaisoSN like '%" + value + "%'",
                (rs, rowNum) -> new Devices(
                        rs.getLong("RMA"),
                        rs.getString("vardas"), rs.getString("pavarde"), rs.getString("telnr"), rs.getString("adresas"),
                        rs.getString("pastas"), rs.getString("prietaisas"),rs.getString("modelis"),rs.getString("prietaisoSN"),rs.getString("gedimas"), rs.getString("laikas")));
    }
    private void updateGrid() {
        List<Devices> customers = findAll(filterText.getValue());
        grid.setItems(customers);

    }
    @Autowired
    public RepairsView(JdbcTemplate template){

        jdbcTemplate = template;



        grid.setColumns( "vardas", "pavarde", "telnr", "prietaisas", "modelis", "laikas");
        grid.getColumn("vardas").setExpandRatio(1);
        grid.getColumn("pavarde").setExpandRatio(1);
        grid.getColumn("telnr").setExpandRatio(1);
        grid.getColumn("prietaisas").setExpandRatio(1);
        grid.getColumn("modelis").setExpandRatio(1);
        grid.getColumn("laikas").setExpandRatio(1);

        grid.addComponentColumn(info -> {
            Button button = new Button("Plačiau");

            button.addClickListener(e -> {
                getUI().getSession().setAttribute("RMA", info.gettRMA());
               busId = (Long) getUI().getSession().getAttribute("RMA");
                getUI().getSession().setAttribute("Modelis", info.getmodelis());
                busMod = (String) getUI().getSession().getAttribute("Modelis");
                getUI().getSession().setAttribute("Prietaisas", info.getprietaisas());
                busPrie = (String) getUI().getSession().getAttribute("Prietaisas");
                getUI().getSession().setAttribute("TelNr", info.gettelnr());
                busTel = (String) getUI().getSession().getAttribute("TelNr");

               navigator.navigateTo(VABALS.getViewPath());
            });

            return button;
        }).setExpandRatio(1);

        grid.setSizeFull();
        filterText.setPlaceholder("Paieška");
        filterText.addValueChangeListener(e -> updateGrid());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.setDescription("Paieška pagal RMA, vardą, tel. nr.");
        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);

        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        binder.bindInstanceFields(this);

        VerticalLayout repairLayout = new VerticalLayout(filtering, grid);
        repairLayout.setSizeFull();
        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        Button view1 = new Button("Remontai", e -> navigator.navigateTo(REPAIRS.getViewPath()));
        view1.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        Button view2 = new Button("Registracija", e -> navigator.navigateTo( REGISTR.getViewPath()));
        view2.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        VerticalLayout menu = new VerticalLayout(title, view1, view2);
        HorizontalLayout MainUIViewLayout = new HorizontalLayout(menu);
        MainUIViewLayout.setSizeFull();

HorizontalLayout repairMenuLayout = new HorizontalLayout(MainUIViewLayout, repairLayout);
        setCompositionRoot(repairLayout);
        updateGrid();
    }






}
