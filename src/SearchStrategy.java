import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

interface SearchStrategy {
    int searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words);
}

class anySearch implements SearchStrategy {
    @Override
    public int searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        HashSet<Integer> rows = new HashSet<>();
        for (String i : words) {
            if (invertedIndexes.containsKey(i)) {
                rows.addAll(invertedIndexes.get(i));
            }
        }
        if (rows.isEmpty()) {
            System.out.println("\n-no valid match -");
        } else {
            System.out.println("\n- matches found -");
            for (int i : rows) {
                System.out.println(readFile.get(i));
            }
        }
        return 0;
    }
}

class allSearch implements SearchStrategy {

    @Override
    public int searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        HashSet<Integer> rows = new HashSet<>();
        boolean firstNumber = true;
        boolean validList = true;
        for (String i : words) {
            if (invertedIndexes.containsKey(i) && firstNumber) {
                rows.addAll(invertedIndexes.get(i));
                firstNumber = false;
            } else if (invertedIndexes.containsKey(i)) {
                rows.retainAll(invertedIndexes.get(i));
            } else {
                System.out.println("\n-no valid match -");
                validList = false;
                break;
            }
        }
        if (rows.isEmpty()) {
            System.out.println("\n-no valid match -");
            validList = false;
        }
        if (validList) {
            System.out.println("\n- matches found -");
            for (int i : rows) {
                System.out.println(readFile.get(i));
            }
        }
        return 0;
    }
}

class noneSearch implements SearchStrategy {

    @Override
    public int searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        HashSet<Integer> rows = new HashSet<>();
        for (String i : words) {
            if (invertedIndexes.containsKey(i)) {
                rows.addAll(invertedIndexes.get(i));
            }
        }
        if (rows.isEmpty()) {
            System.out.println("\n-no valid match -");
        } else {
            System.out.println("\n- matches found -");
            for (int i = 0; i < readFile.size(); i++) {
                if (!rows.contains(i)) {
                    System.out.println(readFile.get(i));
                }
            }
        }
        return 0;
    }
}
