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

    public boolean addQuotation(Quotation quotation) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO Quotations(user_id, article_id, text, year) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, quotation.getUserID());
            statement.setString(2, quotation.getArticleID());
            statement.setString(3, quotation.getText());
            statement.setString(4, quotation.getYear());

            statement.executeQuery();
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

}
