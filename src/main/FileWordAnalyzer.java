import java.io.IOException;
import java.util.*;


public class FileWordAnalyzer {
    private FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public FilePartReader getFilePartReader() {
        return filePartReader;
    }

    public List<String> getWordsOrderedAlphabetically() throws IOException {
        List<String> words = getArrayListFromText();
        Collections.sort(words);
        return words;
    }

    public List getWordsContainingSubstring(String subString) throws IOException {
        List<String> words = getArrayListFromText();
        words.removeIf(word -> !word.contains(subString));
        return words;
    }

    public List getStringsWhichPalindromes() throws IOException {
        List<String> words = getArrayListFromText();
        for (Iterator<String> iterator = words.iterator(); iterator.hasNext(); ) {
            char[] word = iterator.next().toCharArray();
            if (!isPalindrom((word))) {
                iterator.remove();
            }
        }
        return words;
    }

    private List<String> getArrayListFromText() throws IOException {
        String[] arrayString = this.filePartReader.readLines().split("\\r?\\n?\\s+");
        return new ArrayList<>(Arrays.asList(arrayString));
    }

    private Boolean isPalindrom(char[] word) {
        int i1 = 0;
        int i2 = word.length - 1;
        while (i2 > i1) {
            if (word[i1] != word[i2]) {
                return false;
            }
            ++i1;
            --i2;
        }
        return true;
    }

}