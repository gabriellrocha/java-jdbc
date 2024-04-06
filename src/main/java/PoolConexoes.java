import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoolConexoes {

    public static void main(String[] args) {

        // HikariCP

        try {
           Connection connection =  DataSource.getConnection();

           String sql = "SELECT * FROM user";
           PreparedStatement ps = connection.prepareStatement(sql);
           ResultSet resultSet =  ps.executeQuery();

            while (resultSet.next()){ // move o cursor para linha 1
                String name = resultSet.getString("name");
                System.out.println(name);
            }

        } catch (SQLException e){
            System.out.println("Erro");
        }


    }

    public static class DataSource {

        private static HikariConfig config = new HikariConfig();
        private static HikariDataSource dataSource;

        static {
            // Obrigatórias
            config.setJdbcUrl("jdbc:mysql://localhost:3306/java_university");
            config.setUsername("root");
            config.setPassword("123456");

            // Adicionais

            // Ativa o cache de consultas PreparedStatement
            config.addDataSourceProperty( "cachePrepStmts" , "true" );

            // Define o número de consultas preparadas que o driver irá armazenar em cache para cada conexão
            config.addDataSourceProperty( "prepStmtCacheSize" , "250" );

            // : Especifica o tamanho máximo, em bytes, que uma consulta SQL pode ter para ser armazenada no cache
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );

            config.setMaximumPoolSize(5); // Número máximo de conexões (Default 10)

            dataSource = new HikariDataSource(config);
        }

        private DataSource(){};

        public static Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }
    }
}
