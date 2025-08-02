package org.si.repairs_state.repository;

import org.si.repairs_state.domain.Admin;
import org.si.repairs_state.domain.User;
import org.si.repairs_state.domain.Devices;

public interface UserAuthenticationDAO {



    /**
     * @param userRequest
     * @return
     */
    boolean checkAuthentication(User userRequest);
    /**
     * @param adminRequest
     * @return
     */
    boolean checkAuthenticationAdmin(Admin adminRequest);
    /**
     * @param userRequest
     * @return
     */
    /**
     * @param userRequest
     * @throws Exception
     */
    void addNewUser(User userRequest) throws Exception;
    /**

     * @param userRequestt
     * @throws Exception
     */
    void addNewUser2(Devices userRequestt) throws Exception;
    /**

     * @param devices
     * @throws Exception
     */
    /**
     * @return
     */
    String getAuthenticatedUserName();
    void signOut();


}
