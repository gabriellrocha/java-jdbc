import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class InstrucaoPreparada {

    public static void main(String[] args) throws SQLException {

        Connection connection = FactoryConnection.getConnection();

        // Exemplo declaração preparada

        String name = "Gabriel";
        int level = 65;
        String email = "gabr@gmail.com";

        String sql = "INSERT INTO user (id, name, level, created_date, email) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement =  connection.prepareStatement(sql);
        statement.setInt(1, 7);
        statement.setString(2, name);
        statement.setInt(3, level);
        statement.setDate(4, Date.valueOf(LocalDate.now()));
        statement.setString(5, email);

        statement.executeUpdate();

        // Solicitações em Lote

        int id = 121;
        for(int i=1; i<=10; i++) {

            statement.setInt(1, id + i);
            statement.setString(2, name + i);
            statement.setInt(3, level + i);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, email);

            statement.addBatch(); // Agrupando as consultas
        }

        int[] results = statement.executeBatch(); // Enviando todas de uma vez

        for (int result : results) { // Cada célula contém o numero de linhas que a consulta correspondente modificou

            System.out.println(result);
        }
    }
}
