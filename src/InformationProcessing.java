import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InformationProcessing {

    private HashMap<String, HashSet<Integer>> invertedIndexes = new HashMap<>();

    void findWords(ArrayList<String> productList, String searchWord) {
        ArrayList<String> foundWords = new ArrayList<>();
        searchWord = searchWord.toLowerCase();
        boolean wordsNotFound = true;

        for (int i = 0; i < productList.size(); i++) {
            if (findWord(productList.get(i), searchWord)) {
                foundWords.add(productList.get(i));
                wordsNotFound = false;
            }
        }
        if (wordsNotFound) {
            System.out.println("No matching clothes found.");
        } else {
            System.out.println("Found clothes:");
            for (int i = 0; i < foundWords.size(); i++) {
                System.out.println(foundWords.get(i));
            }
            System.out.print("\n");
        }
    }

    private boolean findWord(String setOfWords, String searchWord) {
        setOfWords = setOfWords.toLowerCase();
        String[] parts = setOfWords.split(" ");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains(searchWord)) {
                return true;
            }
        }
        return false;
    }

    void findWordsThroughIndex(ArrayList<String> productList, String searchWord){
        if(invertedIndexes.containsKey(searchWord)) {
            invertedIndexes.get(searchWord)
                    .forEach(k -> System.out.println(productList.get(k)));
        }else if(searchWord.length() > 1){
            findWords(productList, searchWord);
        }
    }

    void invertedIndexing(ArrayList<String> productList){
        for (int i = 0; i < productList.size(); i++) {
            sliceAndMark(productList.get(i), i);
        }
    }

    private void sliceAndMark(String setOfWords, int row){
        setOfWords = setOfWords.toLowerCase();
        String[] parts = setOfWords.split(" ");
        for (int i = 0; i < parts.length; i++) {
            //ADD NEW HASHSET IF IT DOESN'T EXIST, PUT THE ROW NUMBER IN IT
            invertedIndexes.computeIfAbsent(parts[i], k -> new HashSet<>());
            invertedIndexes.get(parts[i]).add(row);
        }
    }
//printing inverted indexing
    void printIt(){
        for(String i : invertedIndexes.keySet()){
            System.out.println(i + " appears at: " + invertedIndexes.get(i));
        }
    }
}
