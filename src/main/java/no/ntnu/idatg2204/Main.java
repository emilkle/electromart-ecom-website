package no.ntnu.idatg2204;
import java.sql.*;

public class Main {

    public static void main(String[] args) {
        // Just a test to connect to database and print data
        String url = "jdbc:mysql://localhost:3306/electromart";
        String username = "root";
        String passwd = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, passwd);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from brand");

            while (resultSet.next()){
                System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + resultSet.getString(3));
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}