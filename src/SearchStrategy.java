import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

interface SearchStrategy {
    void searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words);
}

class anySearch implements SearchStrategy {
    @Override
    public void searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
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
    }
}

class allSearch implements SearchStrategy {

    @Override
    public void searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
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
    }
}

class noneSearch implements SearchStrategy {

    @Override
    public void searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
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
    }
}

class partialSearch implements SearchStrategy {
    @Override
    public void searchResult(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        HashSet<Integer> rows = new HashSet<>();
        ArrayList<String> unused = new ArrayList<>();
        for (String word : words) {
            if (invertedIndexes.containsKey(word)) {
                rows.addAll(invertedIndexes.get(word));
            } else {
                unused.add(word);
            }
        }
        if (!unused.isEmpty()) {
            for (String i : unused) {
                rows.addAll(findWords(readFile, i));
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
    }

    private ArrayList<Integer> findWords(ArrayList<String> productList, String searchWord) {
        ArrayList<Integer> foundRows = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            if (findWord(productList.get(i), searchWord)) {
                foundRows.add(i);
            }
        }
        return foundRows;
    }

    private boolean findWord(String setOfWords, String searchWord) {
        setOfWords = setOfWords.toLowerCase();
        String[] parts = setOfWords.split(" ");

        for (String part : parts) {
            if (part.contains(searchWord)) {
                return true;
            }
        }
        return false;
    }
}
