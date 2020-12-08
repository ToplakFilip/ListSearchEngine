import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class UI {

    private Scanner scanner = new Scanner(System.in);
    private InformationProcessing infoProc = new InformationProcessing();
    private ArrayList<String> clothesFromReadFile = new ArrayList<>();

    void start(String fileName) {
        File file = new File(fileName);
        try (Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNext()) {
                clothesFromReadFile.add(fileScan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        infoProc.invertedIndexing(clothesFromReadFile);
        menu();
    }

    void menu() {
        label:
        while (true) {
            System.out.println("-== MENU ==-");
            System.out.println("1 - Find a specific apparel");
            System.out.println("2 - Print all clothing products");
            System.out.println("0 - Exit");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Search for clothing product (type, brand, material):");
                    String searchTerm = scanner.nextLine();
                    //infoProc.findWords(clothesFromReadFile, searchTerm);
                    infoProc.findWordsThroughIndex(clothesFromReadFile, searchTerm);
                    break;
                case "2":
                    System.out.println("\n-== List of all clothing products ==-");
                    for (String i : clothesFromReadFile) {
                        System.out.println(i);
                    }
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Incorrect input");
                    break;
            }
        }
    }
}
