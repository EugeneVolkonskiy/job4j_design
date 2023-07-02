package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s(%s, %s);", tableName,
                "id SERIAL PRIMARY KEY",
                "name TEXT"
        );
        createStatement(sql);
    }

    public void dropTable(String tableName) throws SQLException {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;", tableName
        );
        createStatement(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type
        );
        createStatement(sql);
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;", tableName, columnName
        );
        createStatement(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName
            );
            statement.execute(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        try (TableEditor tableEditor = new TableEditor(new Properties())) {
            tableEditor.createTable("new_table_name");
            System.out.println(tableEditor.getTableScheme("new_table_name"));
            tableEditor.addColumn("new_table_name", "new_column", "VARCHAR");
            System.out.println(tableEditor.getTableScheme("new_table_name"));
            tableEditor.renameColumn("new_table_name", "new_column", "rename_column");
            System.out.println(tableEditor.getTableScheme("new_table_name"));
            tableEditor.dropColumn("new_table_name", "rename_column");
            System.out.println(tableEditor.getTableScheme("new_table_name"));
            tableEditor.dropTable("new_table_name");
        }
    }
}
