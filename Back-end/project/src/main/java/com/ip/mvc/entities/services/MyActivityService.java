package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.Article;
import com.ip.mvc.entities.model.contents.Conference;
import com.ip.mvc.entities.model.contents.Project;
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
                quotation.setLocation(resultSet.getString(5));
                quotation.setAuthors(Integer.parseInt(resultSet.getString("AUTHORS")));
                quotation.setUserID(resultSet.getString(7));
                quotations.add(quotation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotations;
    }

    public List<Project> getProjects(String userID) {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT p.PROJECT_ID, p.title, p.description, p.domain, p.start_date, p.finish_date, p.budget, p.director FROM Projects p " +
                    "JOIN Project_Authors a ON a.PROJECT_ID = p.PROJECT_ID\n" +
                    "WHERE a.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();

                project.setProjectID(resultSet.getString(1));
                project.setTitle(resultSet.getString(2));
                project.setDescription(resultSet.getString(3));
                project.setDomain(resultSet.getString(4));
                project.setStartDate(resultSet.getString(5));
                project.setFinishDate(resultSet.getString(6));
                project.setBudget(Integer.parseInt(resultSet.getString(7)));
                project.setDirector(resultSet.getString(8));

                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public List<Conference> getConferences(String userID) {
        List<Conference> conferenceList = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT c.CONFERENCE_ID, c.CONFERENCE_NAME, c.YEAR, c.DETAILS, c.LOCATION FROM CONFERENCES c " +
                    "JOIN CONFERENCES_ATTENDING ca ON ca.CONFERENCE_ID = c.CONFERENCE_ID " +
                    "WHERE ca.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Conference conference = new Conference();

                conference.setConferenceID(resultSet.getString(1));
                conference.setName(resultSet.getString(2));
                conference.setYear(resultSet.getString(3));
                conference.setDetails(resultSet.getString(4));
                conference.setLocation(resultSet.getString(5));

                conferenceList.add(conference);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return conferenceList;
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

    public boolean addArticle(Article article) {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT ISSN FROM JOURNALS WHERE JOURNAL_NAME = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, article.getJournalTitle());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String journalISSN = resultSet.getString("ISSN");
                query = "INSERT INTO ARTICLES(TITLE, YEAR, JOURNAL_ISSN) VALUES (?, ?, ?)";

                statement = connection.prepareStatement(query);

                statement.setString(1, article.getTitle());
                statement.setString(2, article.getYear());
                statement.setString(3, journalISSN);

                statement.execute();

                query = "SELECT ARTICLE_ID FROM ARTICLES WHERE TITLE = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, article.getTitle());
                resultSet = statement.executeQuery();

                resultSet.next();
                String articleID = resultSet.getString(1);

                query = "INSERT INTO ARTICLE_AUTHORS(ARTICLE_ID, USER_ID) VALUES (?, ?)";
                statement = connection.prepareStatement(query);

                statement.setString(1, articleID);
                statement.setString(2, article.getUserID());

                statement.execute();

                return true;
            } else return false;

        }
        catch (SQLException e) {
            return false;
        }
    }


    public boolean addProject(Project project) {

        try (Connection connection = getDataSource().getConnection()) {
            String query = "INSERT INTO PROJECTS(DIRECTOR, TITLE, DOMAIN, START_DATE, FINISH_DATE, DESCRIPTION, BUDGET) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, project.getDirector());
            statement.setString(2, project.getTitle());
            statement.setString(3, project.getDomain());
            statement.setString(4, project.getStartDate());
            statement.setString(5, project.getFinishDate());
            statement.setString(6, project.getDescription());
            statement.setInt(7, project.getBudget());

            statement.execute();

            query = "SELECT PROJECT_ID FROM PROJECTS WHERE TITLE = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, project.getTitle());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String projectID = resultSet.getString(1);


            query = "INSERT INTO PROJECT_AUTHORS(PROJECT_ID, USER_ID) VALUES (?, ?)";
            statement = connection.prepareStatement(query);

            statement.setString(1, projectID);
            statement.setString(2, project.getUserID());
            statement.execute();


            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addConference(Conference conference, String userID) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "INSERT INTO CONFERENCES (CONFERENCE_NAME, LOCATION, YEAR, DETAILS) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, conference.getName());
            statement.setString(2, conference.getLocation());
            statement.setString(3, conference.getYear());
            statement.setString(4, conference.getDetails());

            statement.execute();

            sql = "SELECT CONFERENCE_ID FROM CONFERENCES WHERE CONFERENCE_NAME = ?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, conference.getName());

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String conferenceID = resultSet.getString(1);

            sql = "INSERT INTO CONFERENCES_ATTENDING(USER_ID, CONFERENCE_ID) VALUES (?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, userID);
            statement.setString(2, conferenceID);

            statement.execute();


        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public Article getArticle(String articleID) {
        Article article = new Article();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM ARTICLES WHERE ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, articleID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article.setArticleID(resultSet.getString("ARTICLE_ID"));
                article.setJournalISSN(resultSet.getString("JOURNAL_ISSN"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setYear(resultSet.getString("YEAR"));
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Quotation getQuotation(String articleID) {
        Quotation quotation = new Quotation();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT ARTICLE_ID, TEXT, YEAR, ARTICLENAME, LOCATION, AUTHORS FROM QUOTATIONS WHERE ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, articleID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                quotation.setArticleID(resultSet.getString(1));
                quotation.setText(resultSet.getString(2));
                quotation.setYear(resultSet.getString(3));
                quotation.setArticleName(resultSet.getString(4));
                quotation.setLocation(resultSet.getString(5));
                quotation.setAuthors(resultSet.getInt(6));
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quotation;
    }

    public Project getProject(String projectID) {
        Project project = new Project();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM PROJECTS WHERE PROJECT_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                project.setProjectID(resultSet.getString(1));
                project.setDirector(resultSet.getString(2));
                project.setTitle(resultSet.getString(3));
                project.setDomain(resultSet.getString(4));
                project.setStartDate(resultSet.getString(5));
                project.setFinishDate(resultSet.getString(6));
                project.setDescription(resultSet.getString(7));
                project.setBudget(resultSet.getInt(8));

            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public Conference getConference(String conferenceID) {
        Conference conference = new Conference();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM CONFERENCES WHERE CONFERENCE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, conferenceID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                conference.setConferenceID(resultSet.getString(1));
                conference.setName(resultSet.getString(2));
                conference.setYear(resultSet.getString(3));
                conference.setLocation(resultSet.getString(4));
                conference.setDetails(resultSet.getString(5));

            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return conference;
    }

}
