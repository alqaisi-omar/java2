import java.util.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class FileArchive {
    private String name;
    private LocalDate creationDate;
    private List<String> contents;

    public FileArchive(String name) {
        this.name = name;
        this.creationDate = LocalDate.now();
        this.contents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public List<String> getContents() {
        return contents;
    }

    public void addFile(String fileName) {
        contents.add(fileName);
        System.out.println("File '" + fileName + "' added to archive '" + name + "'");
    }

    public void removeFile(String fileName) {
        if (contents.remove(fileName)) {
            System.out.println("File '" + fileName + "' removed from archive '" + name + "'");
        } else {
            System.out.println("File '" + fileName + "' not found in archive '" + name + "'");
        }
    }
}

class FileManager {
    private List<FileArchive> archives;

    public FileManager() {
        this.archives = new ArrayList<>();
    }

    public void createArchive(String name) {
        FileArchive archive = new FileArchive(name);
        archives.add(archive);
        System.out.println("Archive '" + name + "' created successfully");
    }

    public void deleteArchive(String name) {
        archives.removeIf(archive -> archive.getName().equals(name));
        System.out.println("Archive '" + name + "' deleted successfully");
    }

    public void viewArchiveContents(String name) {
        for (FileArchive archive : archives) {
            if (archive.getName().equals(name)) {
                System.out.println("Archive: " + archive.getName());
                System.out.println("Creation Date: " + archive.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                System.out.println("Contents:");
                for (String file : archive.getContents()) {
                    System.out.println("- " + file);
                }
                return;
            }
        }
        System.out.println("Archive '" + name + "' not found");
    }

    public void addFileToArchive(String archiveName, String fileName) {
        for (FileArchive archive : archives) {
            if (archive.getName().equals(archiveName)) {
                archive.addFile(fileName);
                return;
            }
        }
        System.out.println("Archive '" + archiveName + "' not found");
    }

    public void removeFileFromArchive(String archiveName, String fileName) {
        for (FileArchive archive : archives) {
            if (archive.getName().equals(archiveName)) {
                archive.removeFile(fileName);
                return;
            }
        }
        System.out.println("Archive '" + archiveName + "' not found");
    }
}

public class App {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        Scanner scanner = new Scanner(System.in);

        fileManager.createArchive("Archive1");
        fileManager.addFileToArchive("Archive1", "File1.txt");
        fileManager.addFileToArchive("Archive1", "File2.txt");
        fileManager.viewArchiveContents("Archive1");

        fileManager.createArchive("Archive2");
        fileManager.addFileToArchive("Archive2", "File3.txt");
        fileManager.viewArchiveContents("Archive2");

        fileManager.removeFileFromArchive("Archive1", "File2.txt");
        fileManager.viewArchiveContents("Archive1");

        fileManager.deleteArchive("Archive3"); // Non-existing archive

        scanner.close();
    }
}
