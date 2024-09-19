package db;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null;

    public static ResultSet listData(String table) throws SQLException {
        String query = "SELECT * FROM " + table;

        Statement st = conn.createStatement();
        st.executeQuery(query);

        return st.getResultSet();

    }

    //função de conexão com o banco de dados
    public static Connection getConnection() {
        if(conn == null){
            try{
                Properties props = loadProperties();
                String url = props.getProperty("url");
            conn = DriverManager.getConnection(url, props);
            } catch (Exception e) {
                throw new DBException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if(conn != null){
            try{
                conn.close();
            }
            catch (SQLException e){
                throw new DBException(e.getMessage());
            }
        }
    }

    //funçao para buscar dados de conexão com o banco no arquivo db.properties
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();

            props.load(fs);
            return props;
        }
        catch (IOException e){
            throw new DBException(e.getMessage());
        }
    }

}
