package com.ip.mvc.entities.services;

import com.ip.mvc.entities.model.contents.*;
import com.ip.mvc.entities.model.users.Teacher;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyActivityService {

    @Autowired
    private ProfileService profileService;

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
            String query = "SELECT DISTINCT Articles.article_id, Articles.title, Articles.year, Articles.journal_issn, J.JOURNAL_NAME FROM Articles " +
                    "JOIN Article_Authors ON Article_Authors.article_id = Articles.article_id " +
                    "JOIN JOURNALS J ON J.ISSN = ARTICLES.JOURNAL_ISSN WHERE Article_Authors.user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Article article = new Article();
                article.setArticleID(resultSet.getString("ARTICLE_ID"));
                article.setTitle(resultSet.getString("TITLE"));
                article.setYear(resultSet.getString("YEAR"));
                article.setJournalISSN(resultSet.getString("JOURNAL_ISSN"));
                article.setJournalTitle(resultSet.getString("JOURNAL_NAME"));
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

            String query = "SELECT ISSN FROM JOURNALS WHERE JOURNAL_NAME = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, quotation.getLocation());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                query = "INSERT INTO Quotations(ARTICLE_ID, text, year, articleName, location, AUTHORS) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);

                statement.setString(1, quotation.getArticleID());
                statement.setString(2, quotation.getText());
                statement.setString(3, quotation.getYear());
                statement.setString(4, quotation.getArticleName());
                statement.setString(5, quotation.getLocation());
                statement.setString(6, quotation.getAuthorsText());

                statement.executeQuery();

            }
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean addArticle(Article article) {
        System.out.println(article.getAuthors());
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT ISSN FROM JOURNALS WHERE JOURNAL_NAME = ?";
            PreparedStatement statement = connection.prepareStatement(query);


            System.out.println(article.getJournalTitle());
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

                /* insert authors */
                List<Teacher> authors = article.getAuthors();
                System.out.println(authors);
                if (authors.size() > 0) {
                    for (Teacher author : authors) {
                        query = "SELECT  u.USER_ID FROM TEACHERS t JOIN USERS u ON t.EMAIL = u.EMAIL WHERE FIRST_NAME = ? AND LAST_NAME = ? ";
                        statement = connection.prepareStatement(query);
                        statement.setString(1, author.getFirstname());
                        statement.setString(2, author.getLastname());

                        resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            /* author is already in db */
                            query = "INSERT INTO ARTICLE_AUTHORS(ARTICLE_ID, USER_ID) VALUES (?, ?)";
                            statement = connection.prepareStatement(query);

                            statement.setString(1, articleID);
                            statement.setString(2, resultSet.getString("USER_ID"));
                            statement.execute();
                        } else {
                            /* author is not in db */
                            query = "INSERT INTO ARTICLE_OTHER_AUTHORS(ARTICLE_ID, NAME) VALUES (?, ?)";
                            statement = connection.prepareStatement(query);

                            statement.setString(1, articleID);
                            statement.setString(2, author.getFirstname() + " " + author.getLastname());

                            statement.execute();
                        }
                    }
                }
                return true;
            } else return false;

        } catch (SQLException e) {
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

            /* insert authors */
            List<Teacher> authors = project.getAuthorsList();
            if (authors.size() > 0) {
                for (Teacher author : authors) {
                    query = "SELECT  u.USER_ID FROM TEACHERS t JOIN USERS u ON t.EMAIL = u.EMAIL WHERE FIRST_NAME = ? AND LAST_NAME = ? ";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, author.getFirstname());
                    statement.setString(2, author.getLastname());

                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                            /* author is already in db */
                        query = "INSERT INTO PROJECT_AUTHORS(PROJECT_ID, USER_ID) VALUES (?, ?)";
                        statement = connection.prepareStatement(query);

                        statement.setString(1, projectID);
                        statement.setString(2, resultSet.getString("USER_ID"));
                        statement.execute();
                    } else {
                            /* author is not in db */

                    }
                }
            }


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

    public Article getArticleInfo(String articleID) {
        Article article = new Article();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT A.ARTICLE_ID, A.JOURNAL_ISSN, A.TITLE, A.YEAR, J.journal_name, J.score FROM Articles A\n" +
                    "JOIN Journals J ON J.ISSN = A.journal_issn WHERE A.ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, articleID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                article.setArticleID(resultSet.getString(1));
                article.setJournalISSN(resultSet.getString(2));
                article.setTitle(resultSet.getString(3));
                article.setYear(resultSet.getString(4));
                article.setJournalTitle(resultSet.getString(5));
                article.setScore(resultSet.getInt(6));
            }

            sql = "SELECT DISTINCT T.first_name, T.last_name FROM Article_Authors A\n" +
                    "JOIN Users U ON U.user_id = A.user_id\n" +
                    "JOIN Teachers T ON T.email = U.email\n" +
                    "WHERE A.article_id = ? ";
            statement = connection.prepareStatement(sql);
            statement.setString(1, articleID);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Teacher author = new Teacher(resultSet.getString(1), resultSet.getString(2));
                article.addAuthor(author);
            }

            sql = "SELECT NAME FROM ARTICLE_OTHER_AUTHORS WHERE ARTICLE_ID = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, articleID);

            resultSet = statement.executeQuery();
            List<String> authors = new ArrayList<>();
            while (resultSet.next()) {
                authors.add(resultSet.getString(1));
            }
            article.setOtherAuthors(authors);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Article getArticleDetails(String articleID) {
        Article article = new Article();

        try (Connection connection = getDataSource().getConnection()) {

            String sql = "SELECT a.*, j.JOURNAL_NAME FROM ARTICLES a " +
                    "JOIN JOURNALS J ON J.ISSN = a.JOURNAL_ISSN WHERE ARTICLE_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, articleID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                article.setArticleID(resultSet.getString(1));
                article.setTitle(resultSet.getString(2));
                article.setYear(resultSet.getString(3));
                article.setJournalISSN(resultSet.getString(4));
                article.setJournalTitle(resultSet.getString(5));

                /* get quotations */
                sql = "SELECT * FROM QUOTATIONS WHERE ARTICLE_ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, articleID);

                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Quotation quotation = new Quotation(resultSet);
                    System.out.println("quot: " + quotation);
                    article.addQuotation(quotation);


                }
                sql = "SELECT DISTINCT aa.ARTICLE_ID, aa.USER_ID, t.FIRST_NAME, t.LAST_NAME FROM ARTICLE_AUTHORS aa " +
                        "FULL OUTER JOIN USERS u ON u.USER_ID = aa.USER_ID " +
                        "FULL OUTER JOIN TEACHERS t ON t.EMAIL = u.EMAIL " +
                        "WHERE aa.ARTICLE_ID = ?";

                statement = connection.prepareStatement(sql);
                statement.setString(1, articleID);

                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Teacher author = new Teacher();
                    author.setFirstname(resultSet.getString(3));
                    author.setLastname(resultSet.getString(4));
                    System.out.println("other: " + author);
                    article.addAuthor(author);
                }


                sql = "SELECT * FROM ARTICLE_OTHER_AUTHORS WHERE ARTICLE_ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, articleID);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    String fullName = resultSet.getString(2);
                    String[] names = fullName.split(" ");
                    Teacher otherAuthor = new Teacher();
                    otherAuthor.setFirstname(names[0]);
                    otherAuthor.setLastname(names[1]);
                    article.addOther(otherAuthor);
                }

            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Project getProjectDetails(String projectID) {

        Project project = new Project();
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM PROJECTS WHERE PROJECT_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectID);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                project = new Project(resultSet);

                sql = "SELECT DISTINCT t.FIRST_NAME, t.LAST_NAME FROM PROJECT_AUTHORS pa " +
                        "JOIN USERS U ON U.USER_ID = pa.USER_ID " +
                        "JOIN TEACHERS t ON t.EMAIL = u.EMAIL " +
                        "WHERE PROJECT_ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, projectID);

                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Teacher author = new Teacher();
                    author.setFirstname(resultSet.getString(1));
                    author.setLastname(resultSet.getString(2));

                    project.addAuthor(author);
                }
            } else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public List<ScientificEvent> getEvents(String userID) {

        List<ScientificEvent> scientificEvents = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            String sql =
                    "SELECT * FROM SCIENTIFIC_EVENTS e " +
                            "JOIN SCIENTIFIC_EVENTS_ATTENDING a ON a.EVENT_ID = e.EVENT_ID " +
                            "WHERE a.USER_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ScientificEvent scientificEvent = new ScientificEvent(resultSet);
                scientificEvents.add(scientificEvent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scientificEvents;
    }

    public void addEvent(ScientificEvent event, String userID) {

        try (Connection connection = getDataSource().getConnection()) {

            int eventID = 0;

            String sql = "SELECT * FROM SCIENTIFIC_EVENTS WHERE TITLE = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, event.getName());

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                sql = "INSERT INTO scientific_events(event_name, event_year, event_link) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(sql, new String[]{"EVENT_ID"});

                statement.setString(1, event.getName());
                statement.setInt(2, event.getYear());
                statement.setString(3, event.getLink());

                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                eventID = rs.getInt(1);
            } else {
                eventID = resultSet.getInt("EVENT_ID");
            }


            sql = "INSERT INTO SCIENTIFIC_EVENTS_ATTENDING(EVENT_ID, USER_ID, SCORE) VALUES (?, ?, ?)";

            statement = connection.prepareStatement(sql);

            statement.setInt(1, eventID);
            statement.setString(2, userID);
            statement.setInt(3, event.getScore());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getBooks(String userID) {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM BOOKS b " +
                    "JOIN BOOK_AUTHORS a ON b.BOOK_ID = a.BOOK_ID " +
                    "WHERE a.USER_ID = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book(resultSet);

                int bookID = resultSet.getInt("BOOK_ID");

                sql = "SELECT * FROM TEACHERS t " +
                        "JOIN USERS u ON u.EMAIL = t.EMAIL " +
                        "JOIN BOOK_AUTHORS b ON b.USER_ID = u.USER_ID " +
                        "WHERE b.BOOK_ID = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, bookID);

                ResultSet resultSet1 = statement.executeQuery();
                while (resultSet1.next()) {
                    Teacher author = new Teacher(resultSet1);
                    book.addAuthor(author);
                }

                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public void addBook(Book book) {
        try (Connection connection = getDataSource().getConnection()) {
            String sql = "INSERT INTO BOOKS(BOOK_NAME, BOOK_YEAR, SCORE) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, new String[]{"BOOK_ID"});
            statement.setString(1, book.getName());
            statement.setInt(2, book.getYear());
            statement.setInt(3, book.getScore());

            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            String bookID = rs.getString(1);

            if (book.getAuthors().size() > 0) {
                List<Teacher> authorsList = book.getAuthors();
                for (Teacher author : authorsList) {
                    sql = "SELECT u.USER_ID FROM TEACHERS t " +
                            "JOIN USERS u ON u.EMAIL = t.EMAIL " +
                            "WHERE t.FIRST_NAME = ? AND t.LAST_NAME = ?";
                    statement = connection.prepareStatement(sql);
                    statement.setString(1, author.getFirstname());
                    statement.setString(2, author.getLastname());

                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String userID = resultSet.getString("USER_ID");
                        sql = "INSERT INTO BOOK_AUTHORS(BOOK_ID, USER_ID) VALUES (?, ?)";
                        statement = connection.prepareStatement(sql);
                        statement.setString(1, bookID);
                        statement.setString(2, userID);

                        statement.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Visitation> getVisitations(String userID) {
        List<Visitation> visitationList = new ArrayList<>();

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "SELECT * FROM VISITATIONS WHERE USER_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userID);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Visitation visitation = new Visitation(resultSet);
                visitationList.add(visitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitationList;
    }

    public void addVisitation(Visitation visitation) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = "INSERT INTO VISITATIONS(USER_ID, UNIVERSITY_NAME, NR_OF_MONTHS, SCORE, rank, purpose) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, visitation.getUserID());
            statement.setString(2, visitation.getUniversityName());
            statement.setInt(3, visitation.getNumberOfMonths());
            statement.setInt(4, visitation.getScore());
            statement.setInt(5, visitation.getTopPosition());
            statement.setString(6, visitation.getPurpose());


            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
