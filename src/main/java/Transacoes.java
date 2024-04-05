import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transacoes {

    public static void main(String[] args) throws SQLException {

        /* No JDBC, por padrão, cada instrução SQL é tratada como uma transação individual e é automaticamente
        *  commitada (confirmada) logo após sua execução */

        Connection connection = FactoryConnection.getConnection();
        connection.setAutoCommit(false); // Desativa o comportamento padrão

        String sql = "INSERT INTO user (id, name, level, created_date) VALUES (78,'dev', 54,'2024-04-03')";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit(); // Operações serão aplicadas no BD permanente

        } catch (SQLException e){ // Tratamento genérico apenas para fins de estudo, não faça isso na realidade
            connection.rollback(); // Se algo der errado durante a execução das operações

        } finally {
            boolean result = FactoryConnection.closeConnection();
            System.out.println("Connection closed? " + result);
        }
    }
}
