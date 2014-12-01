package eecs285.WebBuilder.DreamTeam.Database;

import java.sql.*;

public class database
{
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/";

  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args)
  {
    Connection conn = null;
    Statement stmt = null;
    try
    {
      Class.forName("com.mysql.jdbc.Driver").newInstance();

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      System.out.println("Creating database...");
      stmt = conn.createStatement();

      //creates database 
      String sql = "CREATE DATABASE 285Proj4";
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");

      //making tables <--- this is where the magic happens
      String sql2 = "CREATE TABLE 285Proj4.Users ( "
          + "username VARCHAR(20) not NULL, "
          + "password varchar(20) NOT NULL, " + "PRIMARY KEY (username))";      
      stmt.executeUpdate(sql2);

      String sql3 = "CREATE TABLE 285Proj4.Attributes ( "
          + "username VARCHAR(20) not NULL) ";
      stmt.executeUpdate(sql3);

      String sql4 = "INSERT INTO 285Proj4.Users ( " + "username, password ) "
          + "VALUES('lauren', 'reeves'), " + "('alex', 'chojnacki'), "
          + "('joe', 'zimmer'), " + "('martina', 'lu'), "
          + "('john', 'doherty'), " + "('taku','rusike') ";
      stmt.executeUpdate(sql4);

    }
    catch( SQLException se )
    {
      se.printStackTrace();
    }
    catch( Exception e )
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        if( stmt != null )
          stmt.close();
      }
      catch( SQLException se2 )
      {
      }
      try
      {
        if( conn != null )
          conn.close();
      }
      catch( SQLException se )
      {
        se.printStackTrace();
      }
    }
    System.out.println("Goodbye!");
  }
}
