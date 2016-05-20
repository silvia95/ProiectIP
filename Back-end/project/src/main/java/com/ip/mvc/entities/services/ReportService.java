package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.forms.ScientificProduction;
import com.ip.mvc.entities.model.users.Teacher;

import javax.sql.DataSource;
import java.sql.Connection;
    import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrei on 5/20/2016.
 */
public class ReportService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Article> getScientificProduction(String userID, ScientificProduction scientificProduction) {
        List<Article> articleList = new ArrayList<>();

        List<String> bindParameters = new ArrayList<>();
        bindParameters.add(userID);

        try (Connection connection = getDataSource().getConnection()) {
            String query = "SELECT A.ARTICLE_ID FROM ARTICLES A JOIN ARTICLE_AUTHORS AU ON A.ARTICLE_ID = AU.ARTICLE_ID JOIN USERS U ON AU.USER_ID = U.USER_ID JOIN TEACHERS T ON U.EMAIL = T.EMAIL JOIN JOURNALS j ON A.JOURNAL_ISSN = j.ISSN WHERE U.USER_ID = ?";
            if (scientificProduction.getName().length() > 0) {
                query += " AND A.TITLE = ?";
                bindParameters.add(scientificProduction.getName());
            }

            if (scientificProduction.getAuthors().size() > 0) {
                List<Teacher> authors = scientificProduction.getAuthors();
                for (Teacher author : authors) {
                    query += " AND (T.LAST_NAME = ? OR T.FIRST_NAME = ?)";
                    bindParameters.add(author.getLastname());
                    bindParameters.add(author.getFirstname());
                }
            }
            if (scientificProduction.getJournalName().length() > 0) {
                System.out.println("journal not null");
                query += " AND J.JOURNAL_NAME = ?";
                bindParameters.add(scientificProduction.getJournalName());
            }
            if (scientificProduction.getClassification().length() > 0) {
                String classification = scientificProduction.getClassification();
                int score ;
                switch (classification) {
                    case "A":
                        score = 8;
                        break;
                    case "B":
                        score = 4;
                        break;
                    case "C":
                        score = 2;
                        break;
                    case "D":
                        score = 1;
                        break;
                    default:
                        score = 0;
                        break;
                }
                query += "AND J.SCORE LIKE ?";
                bindParameters.add(String.valueOf(score));
            }
            if (scientificProduction.getFromScore() != 0 && scientificProduction.getToScore() != 0) {
                query += " AND (A.SCORE BETWEEN ? AND ?)";
                bindParameters.add(String.valueOf(scientificProduction.getFromScore()));
                bindParameters.add(String.valueOf(scientificProduction.getToScore()));
            }
            if (scientificProduction.getFromYear().length() > 0 &&
                    scientificProduction.getToYear().length() > 0) {
                query += " AND ( ? <= A.YEAR AND A.YEAR <= ?)";
                bindParameters.add(scientificProduction.getFromYear());
                bindParameters.add(scientificProduction.getToYear());
            }

//            System.out.println(query);
//            System.out.println(bindParameters);
            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < bindParameters.size(); i++) {
                statement.setString(i + 1, bindParameters.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setArticleID(resultSet.getString(1));
                articleList.add(article);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }

}
