/**
 * Created by tianyi on 2018/3/15.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Mydb {
    Connection con;

    public Connection getCon(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/newsfeed", "root", "19930807");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return con;
    }
}
