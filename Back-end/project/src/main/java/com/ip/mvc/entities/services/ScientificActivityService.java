package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Book;
import com.ip.mvc.entities.model.contents.Journal;
import com.ip.mvc.entities.model.contents.Quotation;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM ARTICLES";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setTitle(resultSet.getString("TITLE"));
                article.setArticleID(resultSet.getString("ARTICLE_ID"));
                article.setYear(resultSet.getString("YEAR"));
                article.setJournalISSN(resultSet.getString("JOURNAL_ISSN"));
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

    public List<Quotation> getAllQuotations() {
        List<Quotation> quotationList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT ARTICLE_ID, ARTICLENAME, AUTHORS, TEXT, YEAR, LOCATION FROM QUOTATIONS";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                Quotation quotation = new Quotation();

                quotation.setArticleID(resultSet.getString(1));
                quotation.setArticleName(resultSet.getString(2));
                quotation.setAuthors(Integer.parseInt(resultSet.getString(3)));
                quotation.setText(resultSet.getString(4));
                quotation.setYear(resultSet.getString(5));
                quotation.setLocation(resultSet.getString(6));
                quotationList.add(quotation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotationList;
    }
}
