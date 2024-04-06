import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class PontoSalvamento {

    public static void main(String[] args) throws SQLException {

        Connection connection = FactoryConnection.getConnection();
        String update1 = "UPDATE user SET level = level+2";
        String update2 = "UPDATE user SET name = 'developer' WHERE id = 78";
        String sqlInvalid = "SQL INVALIDO";

        try {
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.executeUpdate(update1);
            statement.executeUpdate(update2);

            Savepoint save = connection.setSavepoint();  // Adicionando um ponto de salvamento

            try {
                statement.executeUpdate(sqlInvalid);

            } catch (SQLException e){
                connection.rollback(save); // Retornando ao estado salvo
            }

            connection.commit();
            System.out.println("ok");

        } catch (Exception e){
            System.out.println("erro");
            connection.rollback();
        }

        finally {
            FactoryConnection.closeConnection();
        }

    }
}
