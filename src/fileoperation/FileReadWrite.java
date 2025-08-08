package fileoperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;


/**
 * Utility class for reading from and writing to a file.
 */
public class FileReadWrite {
    private static final String relativePath = "./src/dataStore.txt";
    private static final Path path = Paths.get(relativePath);

    /**
     * Reads the file content into a list of strings.
     * @return a list of strings representing file content.
     */
    public static ArrayList<String> readFile() {
        try{
            if (Files.exists(path)){
                return new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            }else{
                return new ArrayList<>();
            }
        }catch (IOException e){
                System.err.println("Error reading file" + e.getMessage());
                return new ArrayList<>();
        }
    }

    /**
     * Writes the given content to the file.
     * @param lines a ArrayList of strings to write.
     */
    public static void writeFile(ArrayList<String> lines) {
        try{
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing file" + e.getMessage());
        }
    }
}
