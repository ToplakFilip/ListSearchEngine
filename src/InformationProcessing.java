import java.util.ArrayList;

public class InformationProcessing {

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

}
