package eecs285.WebBuilder.DreamTeam;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

import eecs285.WebBuilder.DreamTeam.JOutlookBar;
import eecs285.WebBuilder.DreamTeam.HTMLBuilder;
import eecs285.WebBuilder.DreamTeam.HTMLBuilder.Element;

/**
 *
 * @author theProfessional
 */
public class MainApplication extends Application {

	private static WebView webview = new WebView();
	private static WebEngine webEngine = webview.getEngine();
	private static ImageView imageView = new ImageView();
	private static DefaultListModel elementList;
	private static JOutlookBar outlookbar;
	private static Accordion accordion = new Accordion(); 
	private static HTMLBuilder builder = new HTMLBuilder();
	private static TitledPane[] pane  = new TitledPane[100];

	@Override
	public void start(Stage primaryStage) {

		final BorderPane root = new BorderPane();
		GridPane top = new GridPane();
		ScrollPane main = new ScrollPane();
		main.setMaxSize(600, 600);
		GridPane left = new GridPane();
		GridPane leftmenu = addLeftMenu();

		HBox menu = new HBox(addFileMenu());
		String element = "<head id=\" 0 \">";	
		pane[0] = addButton(element,0);
        accordion.getPanes().add(pane[0]);
		
		Scene scene = new Scene(root, 900, 750);
		webEngine.load(HTMLBuilder.HTML_LOAD_LOCATION);
		elementList = new DefaultListModel();

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

		left.add(accordion,0,0);
		left.add(leftmenu, 0,1);
		top.add(menu, 0, 0);
		
		root.setTop(top);
		root.setLeft(left);
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

		int size = elementList.size();
		if (dbString == "paragraph") {
			System.out.println(builder.getRoot().ID);
			builder.insertElement(builder.getRoot().ID, "p", add, " ");
			builder.build();
			webEngine.reload();
			size = size + 1;
			String element = "<p id=\"" + String.valueOf(size) + " \">";
			elementList.addElement(element);
			pane[size] = addButton(element,size);
			accordion.getPanes().add(pane[size]);
			
			
		} else if (dbString == "header") {

			add = "<h1>" + add + "</h1>";

			builder.insertElement(builder.getElement(size).ID, "h1", add, " ");
			builder.build();
			webEngine.reload();
			size = size + 1;
			String element = "<h1 id=\"" + String.valueOf(size) + " \">";
			elementList.addElement(element);
			pane[size] = addButton(element,size);
			 accordion.getPanes().add(pane[size]);
			
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


	public GridPane addLeftMenu() {

		GridPane grid = new GridPane();
		final Stage stage = new Stage();

		accordion = new Accordion(); 

		Button widget = new Button();
		widget.setText("Twitter Widget");

		final Button header = new Button();
		header.setText("Header");

		header.setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {
				/* drag was detected, start drag-and-drop gesture */

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

		GridPane spPane = new GridPane();

		spPane.add(widget, 1, 1);
		spPane.add(header, 1, 2);
		spPane.add(text, 1, 3);
		spPane.add(image, 1, 4);
		spPane.add(background, 1, 5);
		ScrollPane sp = new ScrollPane();
		sp.setContent(spPane);
		spPane.setVgap(10);

		
		grid.add(accordion, 1, 1);
		grid.add(sp, 1, 3);
		grid.setVgap(10);

		return grid;
	}



	static TitledPane addButton(String element,final int id) {

		TitledPane tpane = new TitledPane();
		GridPane grid = new GridPane();
		grid.add(new Label("Font-style"),0,0);
		final ComboBox<String>style;
		final ObservableList<String> styledata = FXCollections.observableArrayList();
		
		
		
		
		grid.add(style, 0, 1);
	
	
		
		style.getItems().addAll("Normal","Bold", "Italic");
		style.setValue("Normal");
	
		
		
		
//		style.setOnAction(new ChangeListener<>(){
//		
//			
//			public void changed(ObservableValue ov,	String old, String newone) 
//			{
//
//		        String style_string = style.getValue().toString();//get the selected item
//		    	System.out.println(style_string + " " + String.valueOf(id)
//						+ " " + builder.getElement(id).content);
//
//				// do your thing here
//				String change = "";
//				if (style_string == "Bold") {
//					change = "font-weight: bold;";
//				} else if (style_string == "Italic") {
//					change = "font-style: italic;";
//				} else {
//					change = builder.getElement(id).style;
//				}
//
//				builder.updateElement(builder.getElement(id),
//						builder.getElement(id).type, builder.getElement(id).style + change,
//						builder.getElement(id).content);
//				builder.build();
//				webEngine.reload();
//		      
//		    }
//		});
				
			
	
		grid.add(new Label("Font-color"),1,0);
		final JComboBox color = new JComboBox();
		
		SwingNode node = new SwingNode();
		node.setContent(color);
		grid.add(node, 1, 1);
	
//	    color.getItems().addAll("Black","Blue", "Red");	
//	    color.setValue("Black");
		
		color.addItem("Black");
		color.addItem("Blue");
		color.addItem("Red");
		
		color.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				String color_string = color.getSelectedItem().toString();
				// do your thing here
				String change = "";
				if (color_string == "Blue") {
					change = "color: blue;";
				} else if (color_string == "Red") {
					change = "color: red;";
				} else {
					change = builder.getElement(id).style;
				}
				System.out.println(change);
				builder.updateElement(builder.getElement(id),
						builder.getElement(id).type,builder.getElement(id).style,
						builder.getElement(id).content);
				builder.build();
				webEngine.reload();
			}

		});
		
		
		tpane.setContent(grid);
		tpane.setText(element);
		return tpane;
	}

	public void dragDrop() {

	}

}