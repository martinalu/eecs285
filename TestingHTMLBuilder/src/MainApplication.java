import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Button refreshButton = new Button();
	refreshButton.setText("Refresh");

	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();

	webEngine.load(HTMLBuilder.HTML_LOCATION);

	Pane root = new Pane();

	refreshButton.relocate(617.5, 20);

	browser.relocate(50, 60);
	browser.setPrefSize(1200, 600);

	root.getChildren().add(refreshButton);
	root.getChildren().add(browser);

	Scene scene = new Scene(root, 1300, 700);

	primaryStage.setTitle("HTMLBuilderTesting");
	primaryStage.setScene(scene);
	primaryStage.show();

	refreshButton.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		webEngine.reload();
	    }
	});

    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }

}
