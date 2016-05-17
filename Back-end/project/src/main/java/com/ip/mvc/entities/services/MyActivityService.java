package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Quotation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyActivityService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Article> getArticles(String userID) {

        List<Article> articleList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT Articles.article_id, Articles.title, Articles.year, Articles.journal_issn FROM Articles " +
                    "JOIN Article_Authors ON Article_Authors.article_id = Articles.article_id WHERE Article_Authors.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setArticleID(resultSet.getString("ARTICLE_ID"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setYear(resultSet.getString("YEAR"));
                article.setJournalISSN(resultSet.getString("JOURNAL_ISSN"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public List<Quotation> getQuotations(String userID) {
        List<Quotation> quotations = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT q.article_id, q.text, q.year, q.articleName, q.location, q.authors, ar.user_id FROM Quotations q " +
                    "JOIN Article_Authors ar ON ar.article_id = q.article_id " +
                    "WHERE ar.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Quotation quotation = new Quotation();
                quotation.setArticleID(resultSet.getString(1));
                quotation.setText(resultSet.getString(2));
                quotation.setYear(resultSet.getString(3));
                quotation.setArticleName(resultSet.getString(4));
                quotation.setLocation(resultSet.getString(4));
                quotation.setAuthors(Integer.parseInt(resultSet.getString("AUTHORS")));
                quotation.setUserID(resultSet.getString(6));
                quotations.add(quotation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotations;
    }

    public boolean addQuotation(Quotation quotation) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Quotations(ARTICLE_ID, text, year, articleName, location, authors) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, quotation.getArticleID());
            statement.setString(2, quotation.getText());
            statement.setString(3, quotation.getYear());
            statement.setString(4, quotation.getArticleName());
            statement.setString(5, quotation.getLocation());
            statement.setInt(6, quotation.getAuthors());

            statement.executeQuery();
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

}
