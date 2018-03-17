/**
 * Created by tianyi on 2018/3/15.
 */
import java.io.*;
import java.sql.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.*;
import com.google.gson.JsonArray;

import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String message;


    @Override
    public void init() throws ServletException {
        message = "Hello world, this message is from servlet!";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
           /* TODO output your page here. You may use following sample code. */
            Mydb db = new Mydb();
            Connection con = db.getCon();
            ArrayList<News> al = new ArrayList<>();

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from news ORDER BY news.date DESC");
                while (rs.next()) {
                    //System.out.println(rs.getDate("date"));
                    News obj = new News(rs.getInt("id"), rs.getString("content"), rs.getDate("date"));
                    al.add(obj);
                }

                String json = new Gson().toJson(al);
                //System.out.println(json);
                response.getWriter().write(json);
            } catch (SQLException ex) {
                Logger.getLogger(JsonServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        String line = null;
        System.out.println("Post request received");
        System.out.println(request.getHeaders("data"));
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);

            System.out.println(jb.toString());
        } catch (Exception e) { /*report an error*/ }


        JsonParser parser = new JsonParser();
        JsonObject o = parser.parse(jb.toString()).getAsJsonObject();
        Mydb db = new Mydb();
        Connection con = db.getCon();
        //Statement stmt = con.createStatement();
        System.out.println(o.get("date").toString());

        try {
            PreparedStatement statement =con.prepareStatement("insert into News values (null, ?, ?) ");
            statement.setString(1, o.get("content").getAsString());
            statement.setDate(2, java.sql.Date.valueOf(o.get("date").getAsString()));
            statement.execute();
            //ResultSet rs = stmt.executeQuery("select * from news ORDER BY news.date DESC");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}


