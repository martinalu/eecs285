/*NOTE: I am aware a log of this code is unneccessarily (sp?) written twice, 
I will make prettier tomorrow, just needed to get to work*/

/*ALSO NOTE: this class will not work when you try to login or add a new user 
 * if you have not downloaded a driver for the database
 package eecs285.proj4.DreamTeam.GUI; If you have and it's 
 still not working, check mamp for your local username and password info*/
package eecs285.proj4.DreamTeam.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class login_window extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;


  public static void main(String[] args)
  {
    JFrame frame = new JFrame("Welcome!");
    frame.setSize(320, 200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);
    userGuide(panel);

    frame.setVisible(true);
    frame.setResizable(false);
  }

  // ---------USER WINDOW-----------------------//
  private static void userGuide(JPanel panel)
  {
    panel.setLayout(null);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(60, 30, 200, 35);
    panel.add(loginButton);

    JButton addUserButton = new JButton("New User");
    addUserButton.setBounds(60, 70, 200, 35);
    panel.add(addUserButton);

    JButton localButton = new JButton("Use Locally");
    localButton.setBounds(60, 110, 200, 35);
    panel.add(localButton);

    // BUTTON FUNCTIONS

    // login button
    loginButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JFrame frame = new JFrame("Login");
        frame.setSize(320, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        initLogin(panel);
        frame.setVisible(true);
        frame.setResizable(false);
      }
    });

    // addUser button
    addUserButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JFrame frame = new JFrame("Create New User");
        frame.setSize(320, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        newUser(panel);
        frame.setVisible(true);
        frame.setResizable(false);
      }
    });

    // local button
    localButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        main_window win;
        win = new main_window();
        win.setMinimumSize(new Dimension(1200, 700));
        win.pack();
        win.setVisible(true);
        win.setResizable(false);
        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      }
    });

  }

  // ---------LOGIN WINDOW-----------------------//
  private static void initLogin(JPanel panel)
  {

    panel.setLayout(null);
    JTextField userText = new JTextField("Username");
    userText.setBounds(73, 30, 175, 35);
    panel.add(userText);

    JPasswordField passwordText = new JPasswordField("Password");
    passwordText.setBounds(73, 70, 175, 35);
    panel.add(passwordText);

    JButton loginButton = new JButton("login");
    loginButton.setBounds(190, 145, 60, 24);
    panel.add(loginButton);

    JButton backButton = new JButton("back");
    backButton.setBounds(250, 145, 60, 24);
    panel.add(backButton);

    // login button
    loginButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent o)
      {
        String uCheck = userText.getText();
        @SuppressWarnings("deprecation")
        String pCheck = passwordText.getText();

        Connection conn = null;
        Statement stmt = null;
        try
        {

          Class.forName("com.mysql.jdbc.Driver").newInstance();

          System.out.println("Connecting to database...");

          conn = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/285Proj4", "root", "root");

          System.out.println("Creating database...");
          stmt = conn.createStatement();

          ResultSet rs = stmt
              .executeQuery("SELECT * FROM Users WHERE username = '" + uCheck
                  + "'" + " AND password = '" + pCheck + "'");
          System.out.println("Database created successfully...");
          if( rs.next() )
          {
            main_window win;
            /* ASSUMED I WOULD NEED THESE VARIABLES LATER FOR RETRIEVING INFO IN
             ATTRIBUTES DB and for saving*/
            // String first = rs.getString("username");
            // String second = rs.getString("password");
            // System.out.println(first + second);
            win = new main_window();
            win.setMinimumSize(new Dimension(1200, 700));
            win.pack();
            win.setVisible(true);
            win.setResizable(false);
            win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          }
          else
          {
            JOptionPane.showMessageDialog(null,
                "Invalid username or password!!!", "Try Again",
                JOptionPane.ERROR_MESSAGE);
          }

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
      }
    });

    //back button
    backButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        JFrame frame = new JFrame("Welcome!");
        frame.setSize(320, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        userGuide(panel);
        frame.setVisible(true);
        frame.setResizable(false);
      }
    });
  }


  // ---------CREATE NEW USER-----------------------//

  private static void newUser(JPanel panel)
  {
    panel.setLayout(null);

    JLabel newUname = new JLabel("Username:");
    newUname.setBounds(15, 30, 80, 30);
    panel.add(newUname);

    JLabel newPword = new JLabel("Password:");
    newPword.setBounds(15, 65, 80, 30);
    panel.add(newPword);

    JLabel confPword = new JLabel("Confirm");
    confPword.setBounds(15, 95, 80, 30);
    panel.add(confPword);

    JLabel confPword2 = new JLabel("Password:");
    confPword2.setBounds(15, 110, 80, 30);
    panel.add(confPword2);


    JTextField userText = new JTextField();
    userText.setBounds(120, 30, 190, 30);
    panel.add(userText);

    JPasswordField passwordText = new JPasswordField();
    passwordText.setBounds(120, 65, 190, 30);
    panel.add(passwordText);

    JPasswordField passwordText2 = new JPasswordField();
    passwordText2.setBounds(120, 100, 190, 30);
    panel.add(passwordText2);

    JButton submitButton = new JButton("Submit");
    submitButton.setBounds(245, 145, 70, 24);
    panel.add(submitButton);

    //submit button
    submitButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent o)
      {
        String addUser = userText.getText();
        @SuppressWarnings("deprecation")
        String addPword = passwordText.getText();
        String check = passwordText2.getText();

        System.out.println(addPword + ":" + check);

        if( addPword.equals(check) )
        {
          Connection conn = null;
          Statement stmt = null;
          try
          {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/285Proj4", "root", "root");

            stmt = conn.createStatement();

            String sql = "INSERT INTO Users " + "VALUES ('" + addUser + "', '"
                + addPword + "')";
            System.out.println(sql);

            stmt.executeUpdate(sql);


            ResultSet rs = stmt
                .executeQuery("SELECT * FROM Users WHERE username = '"
                    + addUser + "'" + " AND password = '" + addPword + "'");

            main_window win;
            win = new main_window();
            win.setMinimumSize(new Dimension(1200, 700));
            win.pack();
            win.setVisible(true);
            win.setResizable(false);
            win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        }
        else
        {
          JOptionPane.showMessageDialog(null, "Passwords don't match.",
              "Try Again", JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

}
