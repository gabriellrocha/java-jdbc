import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        String URL = "jdbc:mysql://localhost:3306/java_university";
        Connection connection = DriverManager.getConnection(URL, "root", "123456");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user");

        // Por padrão o ResultSet aponta para linha 0, e o primeiro resultado da consulta é a linha 1

        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            System.out.println(resultSet.getRow() +". " + id + "\t" + name);
        }

        /*
            Pode ser usado para obter metadados do objeto ResultSet como, por exemplo, informações sobre os tipos
            e propriedades das colunas
        */

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println(resultSetMetaData.getColumnClassName(1)); // Classe correspondente ao tipo da coluna (fully qualified name)
        System.out.println(resultSetMetaData.getColumnTypeName(1)); // Tipo da coluna no BD
        System.out.println(resultSetMetaData.getColumnCount()); // O número de colunas
        System.out.println(resultSetMetaData.getColumnLabel(1)); // Descrição da coluna
        System.out.println(resultSetMetaData.getTableName(1)); // Nome da tabela
        System.out.println(resultSetMetaData.getSchemaName(1)); // Nome do schema do BD
        System.out.println(resultSetMetaData.isAutoIncrement(1)); // Suporta Alto incremento
        System.out.println(resultSetMetaData.isNullable(1)); // Pode conter Null - Retorna status

    }

}
