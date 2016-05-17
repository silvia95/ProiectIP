package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by cristian on 17.05.2016.
 */
public class SearchService {

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Returns results for search
     * Based on the select, the user can
     * search in persons, quotations, and by year
     *
     * @param input
     * @param type
     * @return
     */
    public ArrayList<Article> getSearchResults(String input, String type) {

        ArrayList<Article> results = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT a.* FROM Articles a ";
            if(type.equals("persoana")){
                query += "left join Article_Authors aa on aa.article_id = a.article_id\n" +
                        "left join Users u on u.user_id = aa.user_id\n" +
                        "left join Teachers t on t.email = u.email\n" +
                        "where t.last_name = ?";
            } else if(type.equals("citat")){

                query += "join Quotations q on q.article_id = a.article_id\n" +
                        "where q.text LIKE ?";

                input = "%" + input + "%";
            } else if(type.equals("an")){
                query += " WHERE a.year = ?";
            }

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, input);

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Article article = new Article();
                article.setArticleID(resultSet.getString("ARTICLE_ID"));
                article.setJournalISSN(resultSet.getString("JOURNAL_ISSN"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setYear(resultSet.getString("YEAR"));

                results.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


//    private String getTableBySearchType(String type){
//        if(type.equals("persoana")){
//
//        } else if(type.equals("citat")){
//
//        } else if(type.equals("an")){
//
//        }
//    }



}
