import java.util.HashMap;
import java.util.Map;

public class utils {
    
    public static final String HEAD_HTML = "<!DOCTYPE html><html>"
    	+ "<head><meta charset=\"UTF-8\"><meta name=\"viewport\" "
    	+ "content=\"width=device-width, initial-scale=1.0\">"
    	+ "<meta name=\"description\" content=\"\"><meta name"
    	+ "=\"author\" content=\"\"><title>Testing, Yo</title>"
    	+ "<link rel=\"shortcut icon\" href=\"\"><link rel=\"stylesheet\""
    	+ " href=\"/bootstrap/css/bootstrap.css\"></head>\n\n";
    
    public static final String TWITTER_WIDGET = "";
    
    public static final Map<String, String> widgets = new HashMap<String, String>();
    
    public utils() {
	widgets.put("twitter", TWITTER_WIDGET);
    }
}
