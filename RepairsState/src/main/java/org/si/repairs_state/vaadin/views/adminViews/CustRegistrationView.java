package org.si.repairs_state.vaadin.views.adminViews;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.si.repairs_state.domain.Customer;
import org.si.repairs_state.domain.Devices;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import static org.si.repairs_state.vaadin.ViewPaths.LOGIN;
import static org.si.repairs_state.vaadin.ViewNavigator.navigator;
import static org.si.repairs_state.vaadin.ViewPaths.REGISTR;
import static org.si.repairs_state.vaadin.ViewPaths.REPAIRS;

@SpringComponent
@UIScope
public class CustRegistrationView extends CustomComponent implements View {
    private UserAuthenticationDAO userAuthenticationDAOSQL;
    @Autowired
    public void setUserAuthenticationDAOSQL(UserAuthenticationDAO userAuthenticationDAOSQL){

        this.userAuthenticationDAOSQL = userAuthenticationDAOSQL;
    }
    private Binder<Customer> binder = new Binder<>(Customer.class);


    private Binder<Devices> devicesBinder = new Binder<>();
    private Devices devices = new Devices("", "", "", "","","","","","", "");
    private TextField vardas = new TextField("Vardas");
    private TextField pavarde = new TextField("Pavarde");
    private TextField telnr = new TextField("Telefono numeris");
    private TextField adresas = new TextField("Adresas");
    private TextField pastas = new TextField("Paštas");
    private TextField prietaisas = new TextField("Prietaisas");
    private TextField modelis = new TextField("Modelis");
    private TextField prietaisoSN = new TextField("Prietaiso S/N");
    private TextArea gedimas = new TextArea("Gedimas");
    private TextField laikas = new TextField("Laikas");
    private Button save = new Button("Išsaugoti", e -> saveDevice(devices));


    public CustRegistrationView(){

        vardas.setMaxLength(20);
        pavarde.setMaxLength(20);
                telnr.setMaxLength(20);
        adresas.setMaxLength(20);
                pastas.setMaxLength(20);
        prietaisas.setMaxLength(20);
                modelis.setMaxLength(20);
        prietaisoSN.setMaxLength(20);
                gedimas.setMaxLength(250);
        devicesBinder.bind(vardas, Devices::getvardas, Devices::setvardas);
        devicesBinder.bind(pavarde, Devices::getpavarde, Devices::setpavarde);
        devicesBinder.bind(telnr, Devices::gettelnr, Devices::settelnr);
        devicesBinder.bind(adresas, Devices::getadresas, Devices::setadresas);
        devicesBinder.bind(pastas, Devices::getpastas, Devices::setpastas);
        devicesBinder.bind(prietaisas, Devices::getprietaisas, Devices::setprietaisas);
        devicesBinder.bind(modelis, Devices::getmodelis, Devices::setmodelis);
        devicesBinder.bind(prietaisoSN, Devices::getprietaisoSN, Devices::setprietaisoSN);
        devicesBinder.bind(gedimas, Devices::getgedimas, Devices::setgedimas);
        devicesBinder.setBean(devices);
        VerticalLayout prietaisa = new VerticalLayout( prietaisas, modelis, prietaisoSN, gedimas);
        VerticalLayout asmuo = new VerticalLayout(vardas, pavarde, telnr, adresas, pastas, save);
        HorizontalLayout RegistDevLayout = new HorizontalLayout(prietaisa, asmuo);


        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        Button view1 = new Button("Remontai", e -> navigator.navigateTo(REPAIRS.getViewPath()));
        view1.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        Button view2 = new Button("Registracija", e -> navigator.navigateTo( REGISTR.getViewPath()));
        view2.addStyleNames(ValoTheme.BUTTON_BORDERLESS);
        VerticalLayout menu = new VerticalLayout(title, view1, view2);




HorizontalLayout RegistrMenuLayout = new HorizontalLayout(menu ,RegistDevLayout);
        setCompositionRoot(RegistrMenuLayout);
    }

    /**
     * Register a new user
     * @param userRequestt User object encapsulating the input form data, namely user_name, email, and password
     */

    private void saveDevice(Devices userRequestt){

        try {
            userAuthenticationDAOSQL.addNewUser2(userRequestt);
            navigator.navigateTo(REPAIRS.getViewPath());
        } catch (Exception e){

        }
    }


    @Override
    public void beforeLeave (ViewBeforeLeaveEvent event){
       vardas.setValue("");
       pavarde.setValue("");
        telnr.setValue("");
       adresas.setValue("");
         pastas.setValue("");
         prietaisas.setValue("");
        modelis.setValue("");
        prietaisoSN.setValue("");
     gedimas.setValue("");

        event.navigate();
    }




}
