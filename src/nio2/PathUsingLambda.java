package nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * - Stream on nio2 API
 * Java 8 allows complex operations n nio2 to be done easier and shorter.
 * New methods are added to rely on streams
 *
 */
public class PathUsingLambda {
    public static void main(String[] args) throws IOException {
        // Files.walk() - returns stream of all child file/dir (lazy loading - loaded on-demand)
        // by default, it penetrates till level, Integer.MAX_VALUE
        // it avoids circular search, so avoid go till symbolic's target
        Path usrBin = Paths.get("/home/bbn");
        Stream<Path> childPaths = Files.walk(usrBin);

        // Print all java files
        Files.walk(usrBin)
                .filter(path -> path.toString()
                .endsWith(".class"))
                .forEach(System.out::println);

        // Print all directories
        Files.walk(usrBin)
                .filter(path -> Files.isDirectory(path))
                .map(path -> path.toAbsolutePath())
                .forEach(System.out::println);

        // Listing one-level down child directories
        System.out.println("Listing child directories");
        Files.list(usrBin).forEach(System.out::println);

        System.out.println("Listing child files/directories: ");
        File file = usrBin.toFile(); // only accepts directory
        file.listFiles();

        // Print file contents
        Path path = Paths.get("input.txt");
        if (!Files.exists(path))
            Files.createFile(path);
        // Files.lines(path) - returns all lines in stream (lazy loading),
        // more efficient than Files.readAllLines(Path) when working with large file,
        // but doesn't necessarily mean faster always
        Files.lines(path).forEach(System.out::println);
        Files.readAllLines(path); // reads all lines into memory at once

    }
}
