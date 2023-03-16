import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class SearchAndMoveFiles {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter folder name: ");
        String folderName = scanner.nextLine();
        File folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("Folder created: " + folder.getAbsolutePath());
        }

        System.out.print("Enter directory path to search: ");
        String directoryPath = scanner.nextLine();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            System.out.println("Directory not found: " + directory.getAbsolutePath());
            return;
        }

        System.out.print("Enter search string: ");
        String searchString = scanner.nextLine();

        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("No files or folders found in directory: " + directory.getAbsolutePath());
            return;
        }

        int numFilesMoved = 0;
        for (File file : files) {
            if (file.getName().contains(searchString)) {
                File newFile = new File(folder, file.getName());
                Files.move(file.toPath(), newFile.toPath());
                System.out.println("Moved: " + file.getAbsolutePath() + " to " + newFile.getAbsolutePath());
                numFilesMoved++;
            }
        }

        System.out.println("Done. Moved " + numFilesMoved + " files/folders.");
    }
}
