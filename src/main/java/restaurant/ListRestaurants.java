package restaurant;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListRestaurants {

    public static void main(String[] args) throws SQLException {
        Connection conn = DB.getConnection();

        ResultSet restaurants = DB.listData("restaurant");
        while (restaurants.next()){
            System.out.println(restaurants.getInt("id") + ": " + restaurants.getString("name"));
        }

        DB.closeConnection();

    }

}
