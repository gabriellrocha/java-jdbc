import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Consultas {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:mysql://localhost:3306/java_university";
        Connection connection = DriverManager.getConnection(URL, "root", "123456");

        String sql = "SELECT * FROM user";
        String insert = "INSERT INTO user (id, name, level, created_date) VALUES (36,'marcos', 98, '2024-04-05')";

        Statement statement = connection.createStatement();

        // Para fazer apenas um SELECT este método é suficiente -> return: ResultSet
        statement.executeQuery(sql);

        // Para INSERT, UPDATE, DELETE -> return: int, informa quantas linha na tabela foram alteradas
        int x = statement.executeUpdate(insert);



    }
}
