import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnection {

    private static  Connection connection;

    public static Connection getConnection() {

        if(connection == null) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_university", "root", "123456");
            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    public static Boolean closeConnection() {
        boolean closed = false;

        try {
            if (connection != null) {
                connection.close();
                closed = true;
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return closed;
    }
}
