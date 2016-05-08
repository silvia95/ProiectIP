package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.users.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LogInService implements AuthenticationProvider {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         /* prepare user object */
        User user = new User();
        user.setEmail(authentication.getName());
        user.setPassword(authentication.getCredentials().toString());

        /* check if user object is valid*/
        if (this.isValidUser(user)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), grantedAuths);
        } else return null;
    }

    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isValidUser(User user) {
        try {
            String query = "SELECT COUNT(*) FROM USERS WHERE email = ? AND password = ?";
            PreparedStatement statement = getDataSource().getConnection().prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int counter = resultSet.getInt(1);
            return counter != 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
