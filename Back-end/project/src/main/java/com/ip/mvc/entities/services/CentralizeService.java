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

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @param userID
     * @return
     */
    public Centralization articleCentralization(int userID) {

        int abArticleScore = 0;
        int totalArticleScore = 0;
        int abNumerator = 0;
        int numerator, denominator;
        Centralization cent = new Centralization();

        try (Connection connection = dataSource.getConnection()) {
            String query1 = "SELECT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setInt(1, userID);

            ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet1.next()) {
                numerator = 0;
                denominator = 0;
                String articleID = resultSet1.getString("ARTICLE_ID");
                String query2 = "SELECT * FROM ARTICLE_AUTHORS WHERE ARTICLE_ID = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, articleID);

                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()) {
                    denominator++;
                }
                denominator = (1 > denominator - 2) ? 1 : denominator - 2;

                String query3 = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON J.ISSN = A.JOURNAL_ISSN WHERE A.ARTICLE_ID = ?";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setString(1, articleID);

                ResultSet resultSet3 = statement3.executeQuery();
                if (resultSet3.next()) {
                    numerator = resultSet3.getInt("SCORE");
                    if (resultSet3.getInt("SCORE") >= 4)
                        abNumerator += resultSet3.getInt("SCORE");
                }
                totalArticleScore += numerator / denominator;
                if (abNumerator != 0) {
                    abArticleScore += abNumerator / denominator;
                }
            }
            cent.setABScore(abArticleScore);
            cent.setTotalScore(totalArticleScore);


            // get current&set type
            String type = "";
            String actualTypeQuery = "SELECT T.TYPE FROM TEACHERS T JOIN USERS U ON T.EMAIL = U.EMAIL WHERE U.USER_ID = ?";
            PreparedStatement actualTypeStatement = connection.prepareStatement(actualTypeQuery);
            actualTypeStatement.setInt(1, userID);
            ResultSet actualTypeResultSet1 = actualTypeStatement.executeQuery();
            while (actualTypeResultSet1.next()) {
                type = actualTypeResultSet1.getString("TYPE");
            }
            cent.setActualType(type);


            // set future type
            if (type.startsWith("Lect")) {
                //criteriile pentru trecere la conferentiar
                if (abArticleScore >= 16 && totalArticleScore >= 32) {
                    cent.setPass(true);
                    cent.setFutureType("Conferentiar");
                } else
                    cent.setPass(false);
            } else {
                if (type.startsWith("Conf")) {
                    //criteriile pentru trecere la profesor
                    if (abArticleScore >= 56 && totalArticleScore >= 24) {
                        cent.setPass(true);
                        cent.setFutureType("Profesor");
                    } else
                        cent.setPass(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return cent;
    }


    /**
     *
     * @param userID
     * @return
     */
    public Centralization quotationsCentralization(int userID) {

        int abQuotationsScore = 0;
        int totalQuotationsScore = 0;
        int abNumerator = 0;
        int numerator, denominator;
        String type = "";
        Centralization cent = new Centralization();

        try (Connection connection = dataSource.getConnection()) {
            String query1 = "SELECT ARTICLE_ID FROM ARTICLE_AUTHORS WHERE USER_ID = ?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setInt(1, userID);

            ResultSet resultSet1 = statement1.executeQuery();
            while (resultSet1.next()) {
                numerator = 0;
                denominator = 0;
                String articleID = resultSet1.getString("ARTICLE_ID");
                String query2 = "SELECT * FROM ARTICLE_AUTHORS WHERE ARTICLE_ID = ?";
                PreparedStatement statement2 = connection.prepareStatement(query2);
                statement2.setString(1, articleID);

                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()) {
                    denominator++;
                }
                denominator = (1 > denominator - 2) ? 1 : denominator - 2;

                String query3 = "SELECT J.SCORE FROM JOURNALS J JOIN ARTICLES A ON A.JOURNAL_ISSN = J.ISSN JOIN QUOTATIONS Q ON Q.ARTICLE_ID = A.ARTICLE_ID WHERE Q.ARTICLE_ID = ?";
                PreparedStatement statement3 = connection.prepareStatement(query3);
                statement3.setString(1, articleID);

                ResultSet resultSet3 = statement3.executeQuery();
                while (resultSet3.next()) {
                    numerator += resultSet3.getInt("SCORE");
                    if (resultSet3.getInt("SCORE") >= 4)
                        abNumerator += resultSet3.getInt("SCORE");
                }
                if (abNumerator != 0) {
                    abQuotationsScore += abNumerator / denominator;
                }
                totalQuotationsScore += numerator / denominator;
            }
            cent.setABScore(abQuotationsScore);
            cent.setTotalScore(totalQuotationsScore);


            // get&set actual type
            String actualTypeQuery = "SELECT T.TYPE FROM TEACHERS T JOIN USERS U ON T.EMAIL = U.EMAIL WHERE U.USER_ID = ?";
            PreparedStatement actualTypeStatement = connection.prepareStatement(actualTypeQuery);
            actualTypeStatement.setInt(1, userID);
            ResultSet actualTypeResultSet = actualTypeStatement.executeQuery();
            while (actualTypeResultSet.next()) {
                type = actualTypeResultSet.getString("TYPE");
            }
            cent.setActualType(type);


            // set the future type
            if (type.startsWith("Lect")) {
                //criteriile pentru trecere la conferentiar
                if (abQuotationsScore >= 12 && totalQuotationsScore >= 48) {
                    cent.setPass(true);
                    cent.setFutureType("Conferentiar");
                } else
                    cent.setPass(false);
            } else {
                if (type.startsWith("Conf")) {
                    //criteriile pentru trecere la profesor
                    if (abQuotationsScore >= 40 && totalQuotationsScore >= 120) {
                        cent.setPass(true);
                        cent.setFutureType("Profesor");
                    } else
                        cent.setPass(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cent;
    }
}
