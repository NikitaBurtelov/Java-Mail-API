package database;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Connector {
    private static String userName = "root";
    private static String password = "root";
    private static String connectionUrl = "jdbc:mysql://localhost:3306/email?useSSL=false";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static void initDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionUrl, userName, password);
            statement = (Statement) connection.createStatement();
        }
        catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    private static ArrayList<String> getEmailDatabase() {
        try {
            int i = 0;
            Class.forName("com.mysql.jdbc.Driver");
            String query = "select emailadress from emailaccount";
            ArrayList<String> arrayList = new ArrayList<>();

            connection = DriverManager.getConnection(connectionUrl, userName, password);
            statement = (Statement) connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
                System.out.println(arrayList.get(i));
                i++;
            }
            return arrayList;

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
    public static ArrayList<String> runConnect() {
        initDatabase();
        return getEmailDatabase();
    }
}
