package fileoperation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileReadWrite {
    private static final String relativePath = "./src/dataStore.txt";
    private static final Path path = Paths.get(relativePath);

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

    public static void writeFile(ArrayList<String> lines) {
        try{
            Files.write(path, lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing file" + e.getMessage());
        }
    }
}
