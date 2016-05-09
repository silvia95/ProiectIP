package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.forms.ProfileForm;
import com.ip.mvc.entities.model.users.Teacher;
import com.ip.mvc.entities.model.users.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getUserID(String email) {
        String userID = null;
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT USER_ID FROM USERS WHERE EMAIL = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                userID = resultSet.getString("USER_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }


    public User getUserInfo(String userID) {
        User userInfo = new User();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM USERS WHERE USER_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {

                userInfo.setUserID(resultSet.getString("USER_ID"));
                userInfo.setEmail(resultSet.getString("EMAIL"));
                userInfo.setFirstname(resultSet.getString("FIRST_NAME"));
                userInfo.setLastname(resultSet.getString("LAST_NAME"));
                userInfo.setPassword(resultSet.getString("PASSWORD"));
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    public Teacher getTeacherInfo(User user) {
        Teacher teacherInfo = new Teacher();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT TYPE FROM TEACHERS WHERE EMAIL = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                teacherInfo.setType(resultSet.getString(1));

                /* get departments */
                query = "SELECT DEPARMENTNAME FROM DEPARTMENTS WHERE EMAIL = ?";
                statement = dataSource.getConnection().prepareStatement(query);
                statement.setString(1, user.getEmail());

                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    teacherInfo.addDepartment(resultSet.getString(1));
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherInfo;
    }

    public List<String> getOtherEmails(User user) {
        List<String> emails = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT OTHER_EMAIL FROM EMAILS WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                emails.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public boolean editProfile(ProfileForm profileForm, User user) {

        if(!profileForm.getPassword().equals(user.getPassword()) ||
                !profileForm.getConfirmedPassword().equals(profileForm.getPassword())) return false;
        else {
            if (profileForm.getFirstname().equals("")) profileForm.setFirstname(user.getFirstname());
            if (profileForm.getLastname().equals("")) profileForm.setLastname(user.getLastname());

            try (Connection connection = dataSource.getConnection()) {
                String query = "UPDATE Users SET FIRST_NAME = ? WHERE EMAIL = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, profileForm.getFirstname());
                statement.setString(2, user.getEmail());
                statement.executeUpdate();

                query = "UPDATE Users SET LAST_NAME = ? WHERE EMAIL = ? ";
                statement = connection.prepareStatement(query);
                statement.setString(1, profileForm.getLastname());
                statement.setString(2, user.getEmail());
                statement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }

            return true;
        }
    }

}
