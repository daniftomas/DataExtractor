import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader();
        System.out.println("======Json Containing DATA=========");
        String pathToDataJson = insertValidJsonPath();
        System.out.println("======Json Containing CONFIGS======");
        String pathToConfigJson = insertValidJsonPath();

        String dataJsonString = fileReader.readFile(pathToDataJson);
        String configJsonString = fileReader.readFile(pathToConfigJson);

        DocumentContext jsonDataContext = JsonPath.parse(dataJsonString);

        JSONObject configJsonObject = null;

        try {
            configJsonObject = (JSONObject) new JSONParser().parse(configJsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Object paths = configJsonObject.get("paths");

        String result = "";

        if (paths instanceof String) {
            String path = "$." + paths;
            result = jsonDataContext.read(path).toString();
        }
        if (paths instanceof JSONArray) {
            String delimiter = (String) configJsonObject.get("delimiter");
            result = "";

            for (Object objectFromJson : (JSONArray) paths) {
                if (objectFromJson instanceof String) {
                    String path = "$." + objectFromJson;
                    result += jsonDataContext.read(path).toString();

                    result += delimiter;
                }
            }
            result = result.substring(0, result.length() - 1);
        }

        //Didn't have the time to finnish this, the third case is missing. The case should include a JSONArray of a JSONArray.

        System.out.println("The result is:");
        System.out.println(result);
    }

    private static String insertValidJsonPath() throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        boolean isInputValid = false;

        while (!isInputValid) {
            System.out.println("Please enter the path of the Json file: ");
            input = reader.readLine();

            if (input.isEmpty()) {
                System.out.println("Path cannot be empty.");
                continue;
            }

            Path path = Paths.get(input);
            if (!Files.exists(path)) {
                System.out.println("File does not exist.");
                continue;
            }
            isInputValid = true;
        }
        return input;
    }

    private boolean getObjectFromConfigs(String path) {


        FileReader fileReader = new FileReader();


        return true;


    }

}
