package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Centralization;

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
        int abNumerator = 0;
        int numerator = 0;
        int denominator;

        try {
            ResultSet articles = getArticlesForUser();
            while (articles.next()) {
                String articleID = articles.getString("ARTICLE_ID");
                denominator = getDenominator(articleID);
                
                // compute the score
                ResultSet journals = getJournalsForArticles(articleID);
                if (journals.next()) {
                    numerator += journals.getInt("SCORE");
                    if (journals.getInt("SCORE") >= 4)
                        abNumerator += journals.getInt("SCORE");
                }

                totalArticleScore += numerator / denominator;
                if (abNumerator != 0) {
                    abArticleScore += abNumerator / denominator;
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
        int abNumerator = 0;
        int numerator = 0;
        int denominator;

        try {
            ResultSet articles = getArticlesForUser();
            while (articles.next()) {
                String articleID = articles.getString("ARTICLE_ID");
                denominator = getDenominator(articleID);

                ResultSet journals = getJournalsForQuotations(articleID);
                while (journals.next()) {
                    numerator += journals.getInt("SCORE");
                    if (journals.getInt("SCORE") >= 4)
                        abNumerator += journals.getInt("SCORE");
                }
                if (abNumerator != 0) {
                    abQuotationsScore += abNumerator / denominator;
                }
                totalQuotationsScore += numerator / denominator;
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
        int abArticleScore = this.cent.getArticlesABScore();
        int totalArticleScore = this.cent.getArticlesTotalScore();
        int abQuotationsScore = this.cent.getQuotationsABScore();
        int totalQuotationsScore = this.cent.getQuotationsTotalScore();
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
        String query = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON A.JOURNAL_ISSN = J.ISSN JOIN QUOTATIONS Q ON Q.ARTICLE_ID = A.ARTICLE_ID WHERE Q.ARTICLE_ID = ?";
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
    private ResultSet getArticlesForUser() throws SQLException {
        String userID = this.cent.getUserID();

        String query = "SELECT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
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
}
