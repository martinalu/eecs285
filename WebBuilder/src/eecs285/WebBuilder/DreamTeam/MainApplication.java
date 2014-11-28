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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author theProfessional
 */
public class MainApplication extends Application {

    private static WebView webview = new WebView();
    private static WebEngine webEngine = webview.getEngine();
    private static ImageView imageView = new ImageView();

    @Override
    public void start(Stage primaryStage) {

	final BorderPane root = new BorderPane();
	GridPane top = new GridPane();
	ScrollPane main = new ScrollPane();
	main.setMaxSize(600, 600);

	HBox menu = new HBox(addFileMenu());
	GridPane gridLeft = addLeftMenu();
	GridPane gridRight = addTopMenu();

	Scene scene = new Scene(root, 700, 750);

	webEngine.loadContent("<h1> first header </h1>");
	webview.setContextMenuEnabled(true);

	webview.setOnDragOver(new EventHandler<DragEvent>() {
	    public void handle(DragEvent event) {
		/* data is dragged over the target */

		/*
		 * accept it only if it is not dragged from the same node and if
		 * it has a string data
		 */
		if (event.getGestureSource() != webview
			&& event.getDragboard().hasString()) {
		    /* allow for both copying and moving, whatever user chooses */
		    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		}
		event.consume();
	    }

	});

	webview.setOnDragDropped(new EventHandler<DragEvent>() {
	    public void handle(final DragEvent event) {
		/* data dropped */
		/* if there is a string data on dragboard, read it and use it */

		final Dragboard db = event.getDragboard();
		final String dbString = db.getString();

		// Dialog box that will pop up once drop action is targetted
		final Stage secondaryStage = new Stage();
		AnchorPane secondaryLayout = new AnchorPane();
		GridPane grid = new GridPane();

		Label newText = new Label("Text to add: ");
		final TextArea textarea = new TextArea();
		textarea.setMaxSize(180, 8);
		Button ok = new Button("OK");
		Button cancel = new Button("Cancel");
		grid.add(newText, 0, 0);
		grid.add(textarea, 1, 0);
		grid.add(ok, 0, 2);
		grid.add(cancel, 1, 2);
		secondaryLayout.getChildren().add(grid);
		secondaryStage.setScene(new Scene(secondaryLayout, 300, 90));
		secondaryStage.setTitle("Text to Add");
		secondaryStage.show();

		ok.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {

			String add = textarea.getText();
			final boolean success = false;

			if (textarea.getText() == null) {
			    System.out.println(db.getString());
			    event.consume();
			} else {
			    addToHtml(dbString, add);

			    event.setDropCompleted(success);
			    event.consume();
			    secondaryStage.close();
			}

		    }
		});

		cancel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
			secondaryStage.close();
		    }
		});

	    }
	});

	top.add(menu, 0, 0);
	top.add(gridRight, 1, 1);
	root.setTop(top);
	root.setLeft(gridLeft);
	main.setContent(webview);
	root.setCenter(main);

	primaryStage.setTitle("HTML Editor");
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }

    public void addToHtml(String dbString, String add) {

	if (dbString == "paragraph") {
	    add = "<p>" + add + "</p>";
	    webEngine.loadContent(webEngine.executeScript(
		    "document.documentElement.innerHTML").toString()
		    + add);
	} else if (dbString == "header") {

	    add = "<h1>" + add + "</h1>";
	    webEngine.loadContent(webEngine.executeScript(
		    "document.documentElement.innerHTML").toString()
		    + add);
	}

    }

    public MenuBar addFileMenu() {
	MenuBar menu = new MenuBar();
	Menu file = new Menu("File");
	MenuItem open = new MenuItem("Open File");
	MenuItem exit = new MenuItem("Exit Program");
	file.getItems().addAll(open, exit);

	Menu project = new Menu("Project");
	MenuItem properties = new MenuItem("Properties");
	project.getItems().addAll(properties);
	menu.getMenus().addAll(file, project);
	return menu;
    }

    public GridPane addTopMenu() {
	GridPane grid = new GridPane();
	Label font = new Label(" Fonts:");
	final ComboBox<String> fonts = new ComboBox<String>();
	fonts.setItems(FXCollections.observableArrayList("Sans-serif", "Arial",
		"Times New Roman"));
	fonts.getSelectionModel().select("Sans-serif");

	Label color = new Label(" Colors:");
	ComboBox<String> colors = new ComboBox<String>();
	colors.setItems(FXCollections.observableArrayList("Black", "Blue",
		"Red"));
	colors.getSelectionModel().select("Black");

	Label properties = new Label("Element Properties");
	properties.setFont(Font.font("Sans-serif", 14));

	grid.add(properties, 1, 1);
	grid.add(font, 2, 1);
	grid.add(fonts, 3, 1);
	grid.add(color, 4, 1);
	grid.add(colors, 5, 1);
	grid.setHgap(5);
	return grid;
    }

    public GridPane addLeftMenu() {

	GridPane grid = new GridPane();
	final Stage stage = new Stage();

	Button widget = new Button();
	widget.setText("Twitter Widget");

	final Button header = new Button();
	header.setText("Header");

	header.setOnDragDetected(new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture */
		System.out.println("onDragDetected");

		/* allow any transfer mode */
		Dragboard db = header.startDragAndDrop(TransferMode.ANY);

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString("header");
		db.setContent(content);

		event.consume();
	    }
	});

	final Button text = new Button();
	text.setText("Paragraph");

	text.setOnDragDetected(new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent event) {
		/* drag was detected, start drag-and-drop gesture */
		System.out.println("onDragDetected");

		/* allow any transfer mode */
		Dragboard db = text.startDragAndDrop(TransferMode.ANY);

		/* put a string on dragboard */
		ClipboardContent content = new ClipboardContent();
		content.putString("paragraph");
		db.setContent(content);

		event.consume();
	    }
	});

	final Button image = new Button();
	image.setText("Image");

	image.setOnAction(new EventHandler<ActionEvent>() {
	    @Override
	    public void handle(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(stage);
		webEngine.loadContent(webEngine.executeScript(
			"document.documentElement.innerHTML").toString()
			+ "<img src=\"" + file.getPath() + "\"/>");

	    }

	});
	Button background = new Button();
	background.setText("Background");

	Label content = new Label("Content Frames");
	content.setFont(Font.font("Sans-serif", 14));

	grid.add(content, 1, 1);
	grid.add(widget, 1, 2);
	grid.add(header, 1, 3);
	grid.add(text, 1, 4);
	grid.add(image, 1, 5);
	grid.add(background, 1, 6);
	grid.setVgap(18);
	return grid;
    }

    public void dragDrop() {

    }

}
