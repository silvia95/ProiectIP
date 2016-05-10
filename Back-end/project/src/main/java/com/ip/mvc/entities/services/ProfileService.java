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
            String query = "SELECT u.USER_ID, u.EMAIL, u.PASSWORD, t.FIRST_NAME, t.LAST_NAME, t.TYPE  FROM USERS u JOIN TEACHERS t ON u.EMAIL = t.EMAIL WHERE u.USER_ID = ?";

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
                String query = "UPDATE TEACHERS SET FIRST_NAME = ? WHERE EMAIL = ? ";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, profileForm.getFirstname());
                statement.setString(2, teacher.getEmail());
                statement.executeUpdate();

                query = "UPDATE T SET LAST_NAME = ? WHERE EMAIL = ? ";
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

    public int articleScore(String userID){
        int score = 0, numerator, denominator;
        try (Connection connection = dataSource.getConnection()) {
            String query1 = "SELECT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, userID);

            ResultSet resultSet1 = statement1.executeQuery();
            while(resultSet1.next()) {
                numerator = 0;
                denominator = 0;
                String articleID = resultSet1.getString("ARTICLE_ID");
                String query2 = "SELECT * FROM ARTICLE_AUTHORS WHERE ARTICLE_ID = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, articleID);

                ResultSet resultSet2 = statement2.executeQuery();
                while(resultSet2.next()){
                    denominator++;
                }
                denominator = (1 > denominator - 2) ? 1 : denominator - 2;

                String query3 = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON J.ISSN = A.JOURNAL_ISSN WHERE A.ARTICLE_ID = ?";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setString(1, articleID);

                ResultSet resultSet3 = statement3.executeQuery();
                if(resultSet3.next()){
                    numerator = resultSet3.getInt("SCORE");
                }
                score += numerator / denominator;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public int quotationScore(String userID){
        int score = 0, numerator, denominator;
        try (Connection connection = dataSource.getConnection()) {
            String query1 = "SELECT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setString(1, userID);

            ResultSet resultSet1 = statement1.executeQuery();
            while(resultSet1.next()) {
                numerator = 0;
                denominator = 0;
                String articleID = resultSet1.getString("ARTICLE_ID");
                String query2 = "SELECT * FROM ARTICLE_AUTHORS WHERE ARTICLE_ID = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, articleID);

                ResultSet resultSet2 = statement2.executeQuery();
                while(resultSet2.next()){
                    denominator++;
                }
                denominator = (1 > denominator - 2) ? 1 : denominator - 2;

                String query3 = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON A.JOURNAL_ISSN = J.ISSN JOIN QUOTATIONS Q ON Q.ARTICLE_ID = A.ARTICLE_ID WHERE Q.ARTICLE_ID = ?";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setString(1, articleID);

                ResultSet resultSet3 = statement3.executeQuery();
                while(resultSet3.next()){
                    numerator += resultSet3.getInt("SCORE");
                }
                score += numerator / denominator;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

}
