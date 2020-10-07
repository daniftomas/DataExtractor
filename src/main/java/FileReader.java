import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public String readFile(String path){
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e ) {
            e.printStackTrace();
            return "Path not valid";
        }
    }
}
