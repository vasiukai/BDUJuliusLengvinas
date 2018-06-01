package org.si.repairs_state.vaadin.views.userViews;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.si.repairs_state.domain.User;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import static org.si.repairs_state.vaadin.ViewPaths.LOGIN;
import static org.si.repairs_state.vaadin.ViewNavigator.navigator;

@SpringComponent
@UIScope
public class SignUpView extends CustomComponent implements View {

    private UserAuthenticationDAO userAuthenticationDAOSQL;

    private Binder<User> userBinder = new Binder<>();
    private User user = new User("", "", "");
    private TextField userNameTextField = new TextField("Telefono numeris");
    private TextField emailTextField = new TextField("Email");
    private PasswordField passwordTextField = new PasswordField("Slaptažodis");
    private Button signUpButton = new Button("Sukurti", e -> signUp(user));

    @Autowired
    public void setUserAuthenticationDAOSQL(UserAuthenticationDAO userAuthenticationDAOSQL){
        this.userAuthenticationDAOSQL = userAuthenticationDAOSQL;
    }

    public SignUpView(){
        passwordTextField.setMaxLength(20);
        userNameTextField.setMaxLength(20);
        emailTextField.setMaxLength(20);
        FormLayout signUpFormLayout = new FormLayout(userNameTextField, emailTextField,
                                                     passwordTextField, signUpButton);
        VerticalLayout signUpPageLayout = new VerticalLayout(signUpFormLayout);
        signUpFormLayout.setSizeUndefined();
        signUpPageLayout.setSizeFull();
        signUpPageLayout.setComponentAlignment(signUpFormLayout, Alignment.TOP_CENTER);

        userNameTextField.setDescription("Telefono numeris bus naudojas prisijungimui. Įveskite be +370. Pvz 867454745");
        userBinder.bind(userNameTextField, User::getUserName, User::setUserName);
        userBinder.bind(emailTextField, User::getEmail, User::setEmail);
        userBinder.bind(passwordTextField, User::getPassword, User::setPassword);
        userBinder.setBean(user);

        signUpButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        userNameTextField.setRequiredIndicatorVisible(true);
        emailTextField.setRequiredIndicatorVisible(true);
        passwordTextField.setRequiredIndicatorVisible(true);

        setCompositionRoot(signUpPageLayout);
    }

    /**
     * @param userRequest
     */
    private void signUp(User userRequest){

        try {
            userAuthenticationDAOSQL.addNewUser(userRequest);
            navigator.navigateTo(LOGIN.getViewPath());
        } catch (Exception e){

            Notification.show("Klaida: " + e.getMessage(),
                    Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void beforeLeave (ViewBeforeLeaveEvent event){

        userNameTextField.setValue("");
        emailTextField.setValue("");
        passwordTextField.setValue("");
        event.navigate();
    }
}
