package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;

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

}
