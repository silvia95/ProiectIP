package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Centralization;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by cristian on 26.05.2016.
 */
public class CentralizeService {

    private Centralization cent;

    private Connection connection;

    private DataSource dataSource;

    private ProfileService profileService;

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Computes all info for the user
     *
     * @param userID
     * @return
     */
    public Centralization compute(String userID){
        try {
            this.connection = dataSource.getConnection();
            this.cent = new Centralization(userID);
            this.setActualType();
            this.articleCentralization();
            this.quotationsCentralization();
            this.mainCheck();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return this.cent;
    }

    /**
     * Computes the points for the articles
     * owned by user
     * @return
     */
    public void articleCentralization() {
        int abArticleScore = 0;
        int totalArticleScore = 0;
        int jabNumerator = 0;
        int jNumerator = 0;
        int denominator;

        try {
            ResultSet articles = getArticlesForUser();
            while (articles.next()) {
                jNumerator = jabNumerator = 0;
                String articleID = articles.getString("ARTICLE_ID");
                denominator = getDenominator(articleID);

                // compute the score
                ResultSet journals = getJournalsForArticles(articleID);
                if (journals.next()) {
                    jNumerator += journals.getInt("SCORE");
                    if (journals.getInt("SCORE") >= 4)
                        jabNumerator += journals.getInt("SCORE");
                }

                totalArticleScore += jNumerator / denominator;
                if (jabNumerator != 0) {
                    abArticleScore += jabNumerator / denominator;
                }
            }
            this.cent.setArticlesABScore(abArticleScore);
            this.cent.setArticlesTotalScore(totalArticleScore);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Computes the points for the user quotations
     * @return
     */
    public void quotationsCentralization() {
        int abQuotationsScore = 0;
        int totalQuotationsScore = 0;
        int jabNumerator = 0;
        int jnumerator = 0;
        int denominator;

        try {
            ResultSet articles = getArticlesForUser();
            while (articles.next()) {
                jabNumerator = jnumerator = 0;
                String articleID = articles.getString("ARTICLE_ID");
                denominator = getDenominator(articleID);

                ResultSet journals = getJournalsForQuotations(articleID);
                while (journals.next()) {
                    jnumerator += journals.getInt("SCORE");
                    if (journals.getInt("SCORE") >= 4)
                        jabNumerator += journals.getInt("SCORE");
                }
                if (jabNumerator != 0) {
                    abQuotationsScore += jabNumerator / denominator;
                }
                totalQuotationsScore += jnumerator / denominator;
            }
            this.cent.setQuotationsABScore(abQuotationsScore);
            this.cent.setQuotationsTotalScore(totalQuotationsScore);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Compute the main check
     */
    private void mainCheck() {
        String type = this.cent.getActualType();
        boolean articlePass, quotationsPass;

        // set future type
        if (type.startsWith("Lect")) {
            this.cent.setFutureType("Conferentiar");
        } else {
            this.cent.setFutureType("Profesor");
        }

        // check and set the pass
        articlePass = this.cent.passArticlesAB() && this.cent.passArticlesTotal();
        quotationsPass = this.cent.passQuotationsAB() && this.cent.passQuotationsTotal();
        if (articlePass && quotationsPass) {
            this.cent.setPass(true);
        } else {
            this.cent.setPass(false);
        }

    }


    /**
     * Sets the current type of the user
     * @throws SQLException
     */
    private void setActualType() throws SQLException {
        String type = "";
        String actualTypeQuery = "SELECT T.TYPE FROM TEACHERS T JOIN USERS U ON T.EMAIL = U.EMAIL WHERE U.USER_ID = ?";
        PreparedStatement actualTypeStatement = connection.prepareStatement(actualTypeQuery);
        actualTypeStatement.setString(1, this.cent.getUserID());
        ResultSet actualTypeResultSet1 = actualTypeStatement.executeQuery();
        while (actualTypeResultSet1.next()) {
            type = actualTypeResultSet1.getString("TYPE");
        }

        cent.setActualType(type);
    }


    /**
     * Gets journals with articles info
     *
     * @param articleID
     * @return
     * @throws SQLException
     */
    private ResultSet getJournalsForArticles(String articleID) throws SQLException {
        String query = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON J.ISSN = A.JOURNAL_ISSN WHERE A.ARTICLE_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, articleID);

        return stmt.executeQuery();
    }

    /**
     * Gets journals with quotations info
     *
     * @param articleID
     * @return
     * @throws SQLException
     */
    private ResultSet getJournalsForQuotations(String articleID) throws SQLException {
        String query = "SELECT DISTINCT * FROM JOURNALS J JOIN ARTICLES A ON A.JOURNAL_ISSN = J.ISSN JOIN QUOTATIONS Q ON Q.ARTICLE_ID = A.ARTICLE_ID WHERE Q.ARTICLE_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, articleID);

        return stmt.executeQuery();
    }


    /**
     *
     * @param articleID
     * @return
     * @throws SQLException
     */
    private int getDenominator(String articleID) throws SQLException {
        int denominator = 0;

        ResultSet authors = getAuthorsForArticle(articleID);
        while (authors.next()) {
            denominator++;
        }
        denominator = (1 > denominator - 2) ? 1 : denominator - 2;

        return denominator;
    }

    /**
     * Returns articles for an user
     *
     * @return
     * @throws SQLException
     */
    private ResultSet   getArticlesForUser() throws SQLException {
        String userID = this.cent.getUserID();

        String query = "SELECT DISTINCT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, userID);

        return stmt.executeQuery();
    }

    /**
     * Get the authors for an article
     *
     * @param articleID
     * @return
     * @throws SQLException
     */
    private ResultSet getAuthorsForArticle(String articleID) throws SQLException {
        String query = "SELECT * FROM ARTICLE_AUTHORS WHERE ARTICLE_ID = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, articleID);

        return stmt.executeQuery();
    }

    public int getBooksScore(String userID) {
        int score = 0;
        try (Connection connection = getDataSource().getConnection()) {

            String sql = "SELECT SUM(score) FROM books b " +
                    "JOIN book_authors a ON b.book_id = a.book_id " +
                    "WHERE a.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public int getEventsScore(String userID) {
        int score = 0;
        try (Connection connection = getDataSource().getConnection()) {

            String sql = "SELECT SUM(score) FROM scientific_events e " +
                    "JOIN scientific_events_attending a ON e.event_id= a.event_id " +
                    "WHERE a.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public int getVisitationsScore(String userID) {
        int score = 0;
        try (Connection connection = getDataSource().getConnection()) {

            String sql = "SELECT SUM(score) FROM visitations " +
                    "WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public int getPerformanceScore(String userID) {
        int projectsScore = profileService.getTotalProjectScore(userID);
        int booksScore = this.getBooksScore(userID);
        int eventsScore = this.getEventsScore(userID);
        int visitationsScore = this.getVisitationsScore(userID);

        return projectsScore + booksScore + eventsScore + visitationsScore;
    }

}
