package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Conference;
import com.ip.mvc.entities.model.contents.Project;
import com.ip.mvc.entities.model.contents.Quotation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void editArticle(Article article) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "UPDATE IPUSER.ARTICLES SET TITLE = ?, year = ?, JOURNAL_ISSN = ? WHERE ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, article.getTitle());
            statement.setString(2, article.getYear());
            statement.setString(3, article.getJournalISSN());
            statement.setString(4, article.getArticleID());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editQuotation(Quotation quotation) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "UPDATE QUOTATIONS SET TEXT = ?, YEAR = ?, ARTICLENAME = ?, LOCATION = ?, AUTHORS = ? WHERE ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, quotation.getText());
            statement.setString(2, quotation.getYear());
            statement.setString(3, quotation.getArticleName());
            statement.setString(4, quotation.getLocation());
            statement.setString(6, quotation.getArticleID());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editProject(Project project) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "UPDATE PROJECTS SET DIRECTOR = ?, TITLE = ?, DOMAIN = ?, START_DATE = ?, FINISH_DATE = ?, DESCRIPTION = ?, BUDGET = ? " +
                    "WHERE PROJECT_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, project.getDirector());
            statement.setString(2, project.getTitle());
            statement.setString(3, project.getDomain());
            statement.setString(4, project.getStartDate());
            statement.setString(5, project.getFinishDate());
            statement.setString(6, project.getDescription());
            statement.setInt(7, project.getBudget());
            statement.setString(8, project.getProjectID());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editConference(Conference conference) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "UPDATE CONFERENCES SET CONFERENCE_NAME = ?, YEAR = ?, LOCATION = ?, DETAILS = ? WHERE CONFERENCE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, conference.getName());
            statement.setString(2, conference.getYear());
            statement.setString(3, conference.getLocation());
            statement.setString(4, conference.getDetails());
            statement.setString(5, conference.getConferenceID());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
