/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eecs285.WebBuilder.DreamTeam;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author theProfessional
 */
public class MainApplication extends Application {
    
    @Override
    public void start(Stage primaryStage) {
   
    	
    	BorderPane root = new BorderPane();
    	GridPane top = new GridPane();
    	HBox menu = new HBox(addFileMenu()); 
        GridPane gridLeft = addLeftMenu();
        GridPane gridRight = addTopMenu();
    	Scene scene = new Scene(root, 700, 750);
    
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//       
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
    	top.add(menu, 0, 0);
    	top.add(gridRight, 1, 1);
    	root.setTop(top);
    	
        root.setLeft(gridLeft);
        TextField text = new TextField();
        text.setMaxSize(600, 600);
        root.setCenter(text);
        
        
        
        primaryStage.setTitle("HTML Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public MenuBar addFileMenu()
    {
    	MenuBar menu = new MenuBar();
    	Menu file = new Menu("File");
    	MenuItem open = new MenuItem("Open File");
    	MenuItem exit = new MenuItem("Exit Program");
    	file.getItems().addAll(open,exit);
    	
    	Menu project = new Menu("Project");
    	MenuItem properties = new MenuItem("Properties");
    	project.getItems().addAll(properties);
    	menu.getMenus().addAll(file,project);
    	return menu;
    }
    
    public GridPane addTopMenu()
    {
    	GridPane grid = new GridPane();
    	Label font = new Label(" Fonts:");
    	ComboBox<String> fonts = new ComboBox<String>();
    	fonts.setItems(FXCollections.observableArrayList("Sans-serif", "Arial", 
    			"Times New Roman"));
    	fonts.getSelectionModel().select("Sans-serif");
    	
    	Label color = new Label(" Colors:");

    	ComboBox<String> colors = new ComboBox<String>();
    	colors.setItems(FXCollections.observableArrayList("Black", "Blue", "Red"));
    	colors.getSelectionModel().select("Black");
    	
    	 Label properties = new Label("Element Properties");
         properties.setFont(Font.font ("Sans-serif", 14));
    	
    	grid.add(properties,1,1);
    	grid.add(font,2,1);
    	grid.add(fonts, 3, 1);
    	grid.add(color, 4, 1);
    	grid.add(colors, 5, 1);
    	grid.setHgap(5);
    	return grid;
    }
    
    public GridPane addLeftMenu()
    {
    	
    	GridPane grid = new GridPane();
    	
		Button widget = new Button();
	    widget.setText("Twitter Widget");
    	
        Button text = new Button();
        text.setText("Text Box");
        
        Button image = new Button();
        image.setText("Image");
      
        Button frame = new Button();
        frame.setText("Frame 4");
        
        Label content = new Label("Content Frames");
        content.setFont(Font.font ("Sans-serif", 14));
        
        grid.add(content,1,1);
        grid.add(widget, 1, 2);
        grid.add(text,1,3);
        grid.add(image, 1, 4);
        grid.add(frame,1, 5);
        grid.setVgap(18);
    	return grid;
    }
    
}
