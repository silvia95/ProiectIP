package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Book;
import com.ip.mvc.entities.model.contents.ScientificEvent;
import com.ip.mvc.entities.model.contents.Project;
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
            String query = "SELECT A.ARTICLE_ID FROM ARTICLES A " +
                    "JOIN ARTICLE_AUTHORS AU ON A.ARTICLE_ID = AU.ARTICLE_ID " +
                    "JOIN USERS U ON AU.USER_ID = U.USER_ID " +
                    "JOIN TEACHERS T ON U.EMAIL = T.EMAIL " +
                    "JOIN JOURNALS j ON A.JOURNAL_ISSN = j.ISSN WHERE U.USER_ID = ?";

            query = buildQuery(scientificProduction, bindParameters, query);

            System.out.println(query);
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

    public List<Article> getScientificImpact(String userID, ScientificProduction scientificProduction) {
        List<Article> articleList = new ArrayList<>();

        List<String> bindParameters = new ArrayList<>();
        bindParameters.add(userID);

        try (Connection connection = getDataSource().getConnection()) {
            String query = "SELECT A.*, Q.* FROM Articles A\n" +
                    "JOIN Quotations Q On Q.article_id = A.article_id\n" +
                    "JOIN Article_Authors au ON au.article_id = A.article_id\n" +
                    "WHERE au.user_id = ?";

            query = buildQuery(scientificProduction, bindParameters, query);

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

    /**
     * Get scientific events
     *
     * @param userID
     * @param scientificProduction
     * @return
     */
    public List<ScientificEvent> getScientificEvents(String userID, ScientificProduction scientificProduction) {
        List<ScientificEvent> eventsList = new ArrayList<>();

        List<String> bindParameters = new ArrayList<>();
        bindParameters.add(userID);

        try (Connection connection = getDataSource().getConnection()) {
            String query = "SELECT * FROM SCIENTIFIC_EVENTS se " +
                    "JOIN SCIENTIFIC_EVENTS_ATTENDING sea On sea.event_id = se.event_id\n" +
                    "JOIN USERS u ON u.user_id = sea.user_id\n" +
                    "WHERE u.user_id = ?";

            query = buildEventsQuery(scientificProduction, bindParameters, query);

            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < bindParameters.size(); i++) {
                statement.setString(i + 1, bindParameters.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ScientificEvent event = new ScientificEvent(resultSet);
                eventsList.add(event);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventsList;
    }

    /**
     * Get scientific events
     *
     * @param userID
     * @param scientificProduction
     * @return
     */
    public List<Book> getBooks(String userID, ScientificProduction scientificProduction) {
        List<Book> booksList = new ArrayList<>();

        List<String> bindParameters = new ArrayList<>();
        bindParameters.add(userID);

        try (Connection connection = getDataSource().getConnection()) {
            String query = "SELECT * FROM BOOKS b " +
                    "JOIN BOOK_AUTHORS ba On b.book_id = ba.book_id\n" +
                    "JOIN USERS u ON u.user_id = ba.user_id\n" +
                    "WHERE u.user_id = ?";

            query = buildBooksQuery(scientificProduction, bindParameters, query);

            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < bindParameters.size(); i++) {
                statement.setString(i + 1, bindParameters.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book(resultSet);
                booksList.add(book);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booksList;
    }



    private String buildQuery(ScientificProduction scientificProduction, List<String> bindParameters, String query) {
        if (scientificProduction.getName().length() > 0) {
            query += " AND TITLE LIKE ?";
            bindParameters.add("%" + scientificProduction.getName() + "%");
        }

        if (scientificProduction.getAuthors().size() > 0) {
            List<Teacher> authors = scientificProduction.getAuthors();
            for (Teacher author : authors) {
                query += " AND (LAST_NAME = ? OR FIRST_NAME = ?)";
                bindParameters.add(author.getLastname());
                bindParameters.add(author.getFirstname());
            }
        }
        if (scientificProduction.getJournalName().length() > 0) {
            System.out.println("journal not null");
            query += " AND JOURNAL_NAME LIKE ?";
            bindParameters.add("%" + scientificProduction.getJournalName() + "%");
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
            query += "AND SCORE LIKE ?";
            bindParameters.add(String.valueOf(score));
        }
        if (scientificProduction.getFromScore() != 0 || scientificProduction.getToScore() != 0) {
            query += " AND (? <= SCORE AND  SCORE <= ?)";
            bindParameters.add(String.valueOf(scientificProduction.getFromScore()));
            bindParameters.add(String.valueOf(scientificProduction.getToScore()));
        }
        if (scientificProduction.getFromYear().length() > 0 &&
                scientificProduction.getToYear().length() > 0) {
            query += " AND ( ? <= YEAR AND YEAR <= ?)";
            bindParameters.add(scientificProduction.getFromYear());
            bindParameters.add(scientificProduction.getToYear());
        }
        return query;
    }

    /**
     * Builds events query
     *
     *
     * @param scientificProduction
     * @param bindParameters
     * @param query
     * @return
     */
    private String buildEventsQuery(ScientificProduction scientificProduction, List<String> bindParameters, String query) {
        if (scientificProduction.getFromScore() != 0 || scientificProduction.getToScore() != 0) {
            query += " AND (? <= SEA.SCORE AND  SEA.SCORE <= ?)";
            bindParameters.add(String.valueOf(scientificProduction.getFromScore()));
            bindParameters.add(String.valueOf(scientificProduction.getToScore()));
        }
        if (scientificProduction.getFromYear().length() > 0 &&
                scientificProduction.getToYear().length() > 0) {
            query += " AND ( ? <= SE.EVENT_YEAR AND SE.EVENT_YEAR <= ?)";
            bindParameters.add(scientificProduction.getFromYear());
            bindParameters.add(scientificProduction.getToYear());
        }
        return query;
    }

    /**
     * Builds books query
     *
     * @param scientificProduction
     * @param bindParameters
     * @param query
     * @return
     */
    private String buildBooksQuery(ScientificProduction scientificProduction, List<String> bindParameters, String query) {
        if (scientificProduction.getFromScore() != 0 || scientificProduction.getToScore() != 0) {
            query += " AND (? <= B.SCORE AND  B.SCORE <= ?)";
            bindParameters.add(String.valueOf(scientificProduction.getFromScore()));
            bindParameters.add(String.valueOf(scientificProduction.getToScore()));
        }
        if (scientificProduction.getFromYear().length() > 0 &&
                scientificProduction.getToYear().length() > 0) {
            query += " AND ( ? <= B.BOOK_YEAR AND B.BOOK_YEAR <= ?)";
            bindParameters.add(scientificProduction.getFromYear());
            bindParameters.add(scientificProduction.getToYear());
        }
        return query;
    }


    public List<Project> filterProjects(String userID, ScientificProduction scientificProduction) {
        List<Project> projectList = new ArrayList<>();

        List<String> bindParameters = new ArrayList<>();
        bindParameters.add(userID);

        try (Connection connection = getDataSource().getConnection()) {
            String query = "SELECT p.PROJECT_ID FROM PROJECTS p " +
                    "JOIN PROJECT_AUTHORS a ON a.PROJECT_ID = p.PROJECT_ID " +
                    "WHERE a.USER_ID = ?";

            query = buildQuery(scientificProduction, bindParameters, query);

            PreparedStatement statement = connection.prepareStatement(query);
            for(int i = 0; i < bindParameters.size(); i++) {
                statement.setString(i + 1, bindParameters.get(i));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectID(resultSet.getString(1));
                projectList.add(project);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projectList;
    }

}
