package ch6.dao;

import ch6.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlainSingerDao implements SingerDao {
    private static Logger logger =
            LoggerFactory.getLogger(PlainSingerDao.class);

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Problem loading DB Driver!", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://172.17.0.2:3306/musicdb?verifyServerCertificate=false&useSSL=true",
                "prospring5", "prospring5");
    }

    private void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Problem closing connection to the database!", e);
        }
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement =
                    connection.prepareStatement("select * from SINGER");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Singer singer = new Singer();
                singer.setId(resultSet.getLong("id"));
                singer.setFistName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date"));
                result.add(singer);
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when excution SELECT!", e);
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }

    @Override
    public String findFirstNameById(Long id) {
        return null;
    }

    @Override
    public void insert(Singer singer) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "insert into SINGER (first_name, last_name, birth_date)" +
                            "values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, singer.getFistName());
            statement.setString(2, singer.getLastName());
            statement.setDate(3, singer.getBirthDate());
            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getLong(1));
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("Problem when excution INSERT!", e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Singer singer) {

    }

    @Override
    public void delete(Long id) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement
                    ("delete from SINGER where id=?");
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            logger.error("Problem executing DELETE", ex);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public List<Singer> findAllWithDetail() {
        return null;
    }

    @Override
    public void insertWithDetail(Singer singer) {

    }
}
