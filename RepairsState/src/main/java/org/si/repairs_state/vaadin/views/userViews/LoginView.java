package org.si.repairs_state.vaadin.views.userViews;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.si.repairs_state.domain.Admin;
import org.si.repairs_state.domain.User;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import static org.si.repairs_state.vaadin.ViewNavigator.navigator;
import static org.si.repairs_state.vaadin.ViewPaths.*;

@SpringUI
@SpringComponent
@UIScope
public class LoginView extends CustomComponent implements View {

    private UserAuthenticationDAO userAuthenticationDAOSQL;

    private Binder<User> userBinder = new Binder<>();
    private Binder<Admin> adminBinder = new Binder<>();
    private User user = new User("", "");
    private Admin admin = new Admin("", "");
    private TextField userNameTextField = new TextField("Telefonas");
    private PasswordField passwordTextField = new PasswordField("Slaptažodis");
    private Button signInButton = new Button("Prisijungti", e ->  signIn(user, admin));
    private Label newUserLabel = new Label("<span style='cursor: pointer; color:blue'>Registracija</span>",
            ContentMode.HTML);



    @Autowired
    public void setUserAuthenticationDAOSQL(UserAuthenticationDAO userAuthenticationDAOSQL){

        this.userAuthenticationDAOSQL = userAuthenticationDAOSQL;
    }

    public LoginView(){


        HorizontalLayout signUpLayout = new HorizontalLayout(newUserLabel);

        FormLayout logInFormLayout = new FormLayout(userNameTextField, passwordTextField,
                                                    signUpLayout, signInButton);
        VerticalLayout logInPageLayout = new VerticalLayout(logInFormLayout);
        logInFormLayout.setSizeUndefined();
        logInPageLayout.setSizeFull();
        logInPageLayout.setComponentAlignment(logInFormLayout, Alignment.TOP_CENTER);


        userBinder.bind(userNameTextField, User::getUserName, User::setUserName);
        userBinder.bind(passwordTextField, User::getPassword, User::setPassword);
        userBinder.setBean(user);
        adminBinder.bind(userNameTextField, Admin::getUserName, Admin::setUserName);
        adminBinder.bind(passwordTextField, Admin::getPassword, Admin::setPassword);
        adminBinder.setBean(admin);

        signInButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        signUpLayout.addLayoutClickListener(e -> navigator.navigateTo(SIGN_UP.getViewPath()));


        setCompositionRoot(logInPageLayout);
    }

    /**
     * @param userRequest
     * @param adminRequest
     *
     */
    private void signIn(User userRequest, Admin adminRequest){

        if(userAuthenticationDAOSQL.checkAuthentication(userRequest)){
            getUI().getSession().setAttribute("UserName", userRequest.getUserName());
            navigator.navigateTo(MAIN.getViewPath());
        }else if (userAuthenticationDAOSQL.checkAuthenticationAdmin(adminRequest)) {
            navigator.navigateTo(REGISTR.getViewPath());
        }else {

            Notification.show("Blogai įvesti duomenys", Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void beforeLeave (ViewBeforeLeaveEvent event){
        userNameTextField.setValue("");
        passwordTextField.setValue("");
        event.navigate();
    }
}
