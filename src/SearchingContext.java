public class SearchingContext {

    private SearchStrategy search;

    SearchingContext(SearchStrategy search) {
        this.search = search;
    }

    int doSearch(String[] invertedIndexes, String word) {
        return search.searchResult(invertedIndexes, word);
    }
}
