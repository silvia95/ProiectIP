package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.pdfImport.Jurnal;
import com.ip.mvc.entities.model.users.Teacher;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Teacher> getAccounts() {
        List<Teacher> accounts = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM TEACHERS";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher account = new Teacher(resultSet);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void deleteAccount(String email) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "DELETE FROM TEACHERS WHERE EMAIL = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJournal(Jurnal jurnal) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "INSERT INTO JOURNALS(ISSN, JOURNAL_NAME, SCORE) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, jurnal.getISSN());
            statement.setString(2, jurnal.getName());
            statement.setInt(3, jurnal.getScore());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
