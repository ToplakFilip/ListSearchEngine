import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class InformationProcessing {

    private final HashMap<String, HashSet<Integer>> invertedIndexes = new HashMap<>();

    void invertedIndexing(ArrayList<String> productList){
        for (int i = 0; i < productList.size(); i++) {
            sliceAndMark(productList.get(i), i);
        }
    }

    private void sliceAndMark(String setOfWords, int row){
        setOfWords = setOfWords.toLowerCase();
        String[] parts = setOfWords.split(" ");
        for (String part : parts) {
            //ADD NEW HASHSET IF IT DOESN'T EXIST, PUT THE ROW NUMBER IN IT
            invertedIndexes.computeIfAbsent(part, k -> new HashSet<>());
            invertedIndexes.get(part).add(row);
        }
    }

    HashMap<String, HashSet<Integer>> getInvertedIndexes(){
        return invertedIndexes;
    }

//printing inverted indexing
    void printIt(){
        for(String i : invertedIndexes.keySet()){
            System.out.println(i + " appears at: " + invertedIndexes.get(i));
        }
    }
}
