package eecs285.proj4.GUI;

import java.awt.*;
import javax.swing.*;
import eecs285.proj4.GUI.main_window;

public class Main
{
  public static main_window win;
  
  public static void main( String[] args )  {
    win = new main_window();
    win.setMinimumSize(new Dimension(690, 530));
    win.pack();
    win.setVisible(true);
    win.setResizable(false);
    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    win.pack();
  }

}
