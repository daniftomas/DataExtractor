import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public String readFile(String path) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e ) {
            throw new IOException("Path not valid");
        }
    }
}
