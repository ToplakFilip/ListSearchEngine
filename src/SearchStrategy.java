interface SearchStrategy {
    int searchResult(String[] invertedIndexes, String word);
}

class anySearch implements SearchStrategy {

    @Override
    public int searchResult(String[] invertedIndexes, String word) {
        return 0;
    }
}

class allSearch implements SearchStrategy {

    @Override
    public int searchResult(String[] invertedIndexes, String word) {
        return 0;
    }
}

class noneSearch implements SearchStrategy {

    @Override
    public int searchResult(String[] invertedIndexes, String word) {
        return 0;
    }
}
