package org.si.repairs_state.vaadin;

import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;

import static org.si.repairs_state.vaadin.ViewPaths.*;



@SpringUI(path="repairs_state")
@PushStateNavigation
public class ViewNavigator extends UI {

    public static SpringNavigator navigator;

    private final View loginView;
    private final View signUpView;
    private final View mainView;
    private final View custRegistrationView;
    private final View repairsView;
    private final View stateView;
    private final UserAuthenticationDAO userAuthenticationDAOSQL;

    @Autowired
    public ViewNavigator(SpringNavigator navigator, View loginView, View custRegistrationView,
                         View signUpView, View mainView, View repairsView,View stateView, UserAuthenticationDAO userAuthenticationDAOSQL){

        ViewNavigator.navigator = navigator;
        this.stateView = stateView;

        this.custRegistrationView = custRegistrationView;

        this.loginView = loginView;
        this.signUpView = signUpView;
        this.mainView = mainView;
        this.repairsView = repairsView;
        this.userAuthenticationDAOSQL = userAuthenticationDAOSQL;
    }

    public void init(VaadinRequest request){

        navigator.init(this, this);


        navigator.addView(LOGIN.getViewPath(), loginView);
        navigator.addView(REPAIRS.getViewPath(), repairsView);
        navigator.addView(REGISTR.getViewPath(), custRegistrationView);
        navigator.addView(SIGN_UP.getViewPath(), signUpView);
        navigator.addView(MAIN.getViewPath(), mainView);
        navigator.addView(VABALS.getViewPath(), stateView);

        navigator.addViewChangeListener(viewChangeEvent -> {

            boolean accessPermission = false;

            if (viewChangeEvent.getNewView().equals(mainView) &&
                    userAuthenticationDAOSQL.getAuthenticatedUserName() == null){

                Notification.show("Prisijunkite", Notification.Type.ERROR_MESSAGE);
                navigator.navigateTo(LOGIN.getViewPath());
            } else{

                accessPermission = true;
            }

            return accessPermission;
        });
    }

}
