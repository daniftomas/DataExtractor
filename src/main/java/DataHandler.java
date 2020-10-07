import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class DataHandler {
    private DocumentContext jsonDataContext;

    public DataHandler(String raw_file) {
        this.jsonDataContext = JsonPath.parse(raw_file);
    }

    public String extract(String raw_path) {
        String path = "$." + raw_path;
        return this.jsonDataContext.read(path).toString();
    }
}
