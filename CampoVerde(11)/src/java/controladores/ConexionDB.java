package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/campoverde";
    private static final String USER = "root";
    private static final String PASS = "12345678";

    private static ConexionDB instancia;
    private Connection connection;

    private ConexionDB() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static ConexionDB getInstancia() throws SQLException, ClassNotFoundException {
        if (instancia == null) {
            instancia = new ConexionDB();
        }
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
                instancia = null;
            }
        }
    }
}
