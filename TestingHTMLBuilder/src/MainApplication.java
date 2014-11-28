
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	Button btn = new Button();
	btn.setText("Refresh");
	final String html_location = "file://localhost/Users/theProfessional/Documents/gitRepos/eecs285/resources/demo_website/index.html";
	
	WebView browser = new WebView();
	WebEngine webEngine = browser.getEngine();
	
	webEngine.load(html_location);

	btn.setOnAction(new EventHandler<ActionEvent>() {

	    @Override
	    public void handle(ActionEvent event) {
		System.out.println("RAWR");
	    }
	});

	StackPane root = new StackPane();
	root.getChildren().add(btn);
	browser.setPrefSize(40, 40);
	root.getChildren().add(browser);

	Scene scene = new Scene(root, 1300, 500);
	
	
	primaryStage.setTitle("HTMLBuilderTesting");
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

}
