import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

public class UI {

    private final Scanner scanner = new Scanner(System.in);
    private final InformationProcessing infoProc = new InformationProcessing();
    private final ArrayList<String> readFile = new ArrayList<>();
    private HashMap<String, HashSet<Integer>> indexedRows;

    void start(String fileName) {
        File file = new File(fileName);
        try (Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNext()) {
                readFile.add(fileScan.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        infoProc.invertedIndexing(readFile);
        menu();
    }

    void menu() {
        label:
        while (true) {
            System.out.println("\n-== MENU ==-");
            System.out.println("1 - Find specific rows");
            System.out.println("2 - Print every row of a file");
            System.out.println("0 - Exit");
            String input = scanner.nextLine();

            this.indexedRows = infoProc.getInvertedIndexes();

            switch (input) {
                case "1":
                    searchingStrategy();
                    break;
                case "2":
                    System.out.println("\n-== List of all found rows that contain typed words ==-");
                    for (String i : readFile) {
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

    private void searchingStrategy() {
        System.out.println("-= Select searching method =-");
        System.out.println("1 - Rows contain ANY inputted keyword");
        System.out.println("2 - Rows contain NO inputted keyword");
        System.out.println("3 - Rows contain EVERY inputted keyword");
        System.out.println("4 - Rows contain PARTS OF inputted keyword");
        String answer = scanner.nextLine();
        String[] parts;
        switch (answer) {
            case "1":
                parts = typeWords();
                SearchingContext searchEngine = new SearchingContext(new anySearch());
                searchEngine.doSearch(readFile, indexedRows, parts);
                break;
            case "2":
                parts = typeWords();
                searchEngine = new SearchingContext(new noneSearch());
                searchEngine.doSearch(readFile, indexedRows, parts);
                break;
            case "3":
                parts = typeWords();
                searchEngine = new SearchingContext(new allSearch());
                searchEngine.doSearch(readFile, indexedRows, parts);
                break;
            case "4":
                parts = typeWords();
                searchEngine = new SearchingContext(new partialSearch());
                searchEngine.doSearch(readFile, indexedRows, parts);
                break;
            default:
                System.out.println("Wrong input");
                break;
        }
    }

    private String[] typeWords() {
        System.out.println("Search for specific words in a row:");
        String searchTerm = scanner.nextLine();
        searchTerm = searchTerm.toLowerCase();
        return searchTerm.split(" ");
    }
}
