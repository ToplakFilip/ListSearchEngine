import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SearchingContext {

    private SearchStrategy search;

    SearchingContext(SearchStrategy search) {
        this.search = search;
    }

    int doSearch(ArrayList<String> readFile, HashMap<String, HashSet<Integer>> invertedIndexes, String[] words) {
        return search.searchResult(readFile, invertedIndexes, words);
    }
}
