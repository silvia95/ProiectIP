package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Journal;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScientificActivityService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Journal getJournal(String journalISSN) {

        Journal journal = new Journal();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Journals WHERE ISSN = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, journalISSN);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                journal.setISSN(resultSet.getString("ISSN"));
                journal.setName(resultSet.getString("JOURNAL_NAME"));
                journal.setScore(resultSet.getInt("SCORE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journal;
    }
}
