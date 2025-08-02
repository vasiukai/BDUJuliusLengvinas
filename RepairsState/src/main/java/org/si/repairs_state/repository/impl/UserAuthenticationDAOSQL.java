package org.si.repairs_state.repository.impl;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import org.si.repairs_state.domain.Admin;
import org.si.repairs_state.domain.User;
import org.si.repairs_state.repository.UserAuthenticationDAO;
import org.si.repairs_state.repository.exceptions.EmptyFieldException;
import org.si.repairs_state.repository.exceptions.NonUniqueUserNameException;
import org.si.repairs_state.domain.Devices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Repository
public class UserAuthenticationDAOSQL implements UserAuthenticationDAO {

    private String authenticatedUserName;
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenticationDAOSQL(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder){

        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean checkAuthenticationAdmin(Admin adminRequest){

        boolean result = false;

        List<Admin> admins = jdbcTemplate.query(

                "SELECT * FROM admin WHERE user_name = ? and password = ?",
                new String[]{adminRequest.getUserName(), adminRequest.getPassword()},
                (rs, rowNum) -> new Admin(rs.getString("user_name"),
                        rs.getString("password"))
        );

        if(admins.size() != 0){
            Admin admin = admins.get(0);
            authenticatedUserName = admin.getUserName();
            result = true;
        }

        return result;
    }
    @Override
    public boolean checkAuthentication(User userRequest){

        boolean result = false;

        List<User> users = jdbcTemplate.query(

                "SELECT * FROM user WHERE user_name = ?",
                new String[]{userRequest.getUserName()},
                (rs, rowNum) -> new User(rs.getString("user_name"),
                                         rs.getString("password"))
        );

        if(users.size() != 0){


            User user = users.get(0);

            if(passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {

                result = true;
                authenticatedUserName = user.getUserName();
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addNewUser(User userRequest) throws Exception {

        String userName = userRequest.getUserName();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        // All form fields are required; if any of them is empty, throw an exception.
        if(userName.equals("")|| email.equals("") || password.equals("")){

            throw new EmptyFieldException();
        }

        try{
            // If the specified user_name (PK) is available for a new user,
            // insert the user object with encoded password; otherwise, rethrow an exception.
            userRequest.setPassword(passwordEncoder.encode(password));
            jdbcTemplate.update(

                    "INSERT INTO user(user_name, email, password) VALUES(?, ?, ?)",
                    userName, email, userRequest.getPassword()
            );
        } catch(DuplicateKeyException e){

            throw new NonUniqueUserNameException();
        }
    }

    public void addNewUser2(Devices userRequestt) throws Exception {
        String vardas = userRequestt.getvardas();
        String pavarde = userRequestt.getpavarde();
        String telnr = userRequestt.gettelnr();
        String adresas = userRequestt.getadresas();
        String pastas = userRequestt.getpastas();
        String prietaisas = userRequestt.getprietaisas();
        String modelis = userRequestt.getmodelis();
        String prietaisoSN = userRequestt.getprietaisoSN();
        String gedimas = userRequestt.getgedimas();
        String laikas = userRequestt.getgedimas();
        Date date = new Date();
        String DATE_FORMAT = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

        if(vardas.equals("")|| pavarde.equals("") || telnr.equals("")|| prietaisas.equals("")|| modelis.equals("")|| prietaisoSN.equals("")|| gedimas.equals("")){

            throw new EmptyFieldException();
        }


        jdbcTemplate.update(

                "INSERT INTO customreg(vardas, pavarde, telnr, adresas, pastas, prietaisas, modelis, prietaisoSN, gedimas, laikas) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                vardas, pavarde, telnr, adresas, pastas, prietaisas, modelis, prietaisoSN, gedimas, sdf.format(date)
        );

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getAuthenticatedUserName(){

        return authenticatedUserName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signOut(){

        authenticatedUserName = null;
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }
}
