import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SearchingContext {

    private final SearchStrategy search;

    SearchingContext(SearchStrategy search) {
        this.search = search;
    }

    void doSearch(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        search.searchResult(readFile, invertedIndexes, words);
    }
}
