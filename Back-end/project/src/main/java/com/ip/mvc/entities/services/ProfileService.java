package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.forms.ProfileForm;
import com.ip.mvc.entities.model.users.Teacher;

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

    public Teacher getTeacherInfo(String userID) {
        Teacher teacherInfo = new Teacher();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT u.USER_ID, u.EMAIL, u.PASSWORD, u.FIRST_NAME, u.LAST_NAME, t.TYPE  FROM USERS u JOIN TEACHERS t ON u.EMAIL = t.EMAIL WHERE u.USER_ID = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                teacherInfo.setUserID(resultSet.getString("USER_ID"));
                teacherInfo.setEmail(resultSet.getString("EMAIL"));
                teacherInfo.setPassword(resultSet.getString("PASSWORD"));
                teacherInfo.setFirstname(resultSet.getString("FIRST_NAME"));
                teacherInfo.setLastname(resultSet.getString("LAST_NAME"));
                teacherInfo.setType(resultSet.getString("TYPE"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacherInfo;
    }

    public List<String> getDepartments(String email) {
        List<String> departments = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT DEPARMENTNAME FROM DEPARTMENTS WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                departments.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public List<String> getOtherEmails(String email) {
        List<String> emails = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT OTHER_EMAIL FROM EMAILS WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                emails.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public boolean editProfile(ProfileForm profileForm, Teacher teacher) {

        if(!profileForm.getPassword().equals(teacher.getPassword()) ||
                !profileForm.getConfirmedPassword().equals(profileForm.getPassword())) return false;
        else {
            if (profileForm.getFirstname().equals("")) profileForm.setFirstname(teacher.getFirstname());
            if (profileForm.getLastname().equals("")) profileForm.setLastname(teacher.getLastname());

            try (Connection connection = dataSource.getConnection()) {
                String query = "UPDATE Users SET FIRST_NAME = ? WHERE EMAIL = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, profileForm.getFirstname());
                statement.setString(2, teacher.getEmail());
                statement.executeUpdate();

                query = "UPDATE Users SET LAST_NAME = ? WHERE EMAIL = ? ";
                statement = connection.prepareStatement(query);
                statement.setString(1, profileForm.getLastname());
                statement.setString(2, teacher.getEmail());
                statement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

}
