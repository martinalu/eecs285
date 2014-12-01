import java.util.ArrayList;

import com.sun.deploy.uitoolkit.impl.fx.ui.FXConsole;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

	Pane root = new Pane();

	// Setup Webpage Preview componants
	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();
	webEngine.load(HTMLBuilder.HTML_LOAD_LOCATION);
	browser.relocate(175, 85);
	browser.setPrefSize(1200, 600);
	root.getChildren().add(browser);

	ListView<String> widgetOptions = new ListView<String>();
	widgetOptions.setEditable(true);
	ObservableList<String> widgetSelection = FXCollections
		.observableArrayList("Header", "Paragraph", "Image",
			"Twitter Feed", "Ordered List", "Unordered List",
			"List Item");
	widgetOptions.setItems(widgetSelection);
	widgetOptions.setPrefSize(145, 575);

	// Widget Drag and Drop Pane
	ScrollPane widgetPane = new ScrollPane(widgetOptions);
	widgetPane.relocate(3, 385);
	widgetPane.setPrefSize(165, 300);
	root.getChildren().add(widgetPane);

	// Create list of elements.
	ListView<String> listOfElements = new ListView<String>();
	
	
	// Element Selection Pane
	ScrollPane elementPane = new ScrollPane();

	Scene scene = new Scene(root, 1400, 700);

	primaryStage.setTitle("HTMLBuilderTesting");
	primaryStage.setScene(scene);
	primaryStage.show();

	// Some Test Code.
	HTMLBuilder builder = new HTMLBuilder();
	builder.createTemplateAlpha();
	builder.build();
	webEngine.reload();

    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }

}
