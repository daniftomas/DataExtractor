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

public class Main {
    public static void main(String[] args) throws Exception {

        FileReader fileReader = new FileReader();
        System.out.println("======Json Containing DATA=========");
        String pathToDataJson = insertValidJsonPath();
        System.out.println("======Json Containing CONFIGS======");
        String pathToConfigJson = insertValidJsonPath();

        String dataJsonString = fileReader.readFile(pathToDataJson);
        String configJsonString = fileReader.readFile(pathToConfigJson);

        DataHandler dataHandler = new DataHandler(dataJsonString);
        JSONObject configJsonObject = null;

        try {
            configJsonObject = (JSONObject) new JSONParser().parse(configJsonString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Object paths = configJsonObject.get("paths");

        System.out.println("==============");
        System.out.println("Fetching Data based in configs.");
        String result = factory(paths, dataHandler, configJsonObject);

        System.out.println("==============");
        System.out.println("The result is:");
        System.out.println(result);
        System.out.println("==============");
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

    private static String factory(Object paths, DataHandler dataHandler, JSONObject configJsonObject) throws Exception {

        if (paths instanceof String) {
             return dataHandler.extract((String) paths);
        }
        if (paths instanceof JSONArray) {
            String delimiter = (String) configJsonObject.get("delimiter");
            String result = "";

            for (Object objectFromJson : (JSONArray) paths) {
                if (objectFromJson instanceof String) {
                    result += dataHandler.extract((String) objectFromJson);

                    result += delimiter;
                }
            }
            return result.substring(0, result.length() - 1);
        }

        //Didn't have the time to finish this, the third case is missing. The case should handle a JSONArray of a JSONArray.

        throw new Exception(String.format("Unsupported data type for %s: %s", paths.toString(), paths.getClass()));
    }

}
