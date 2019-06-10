import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileWordAnalyzerTest {

    private FileWordAnalyzer fileWordAnalyzer;
    private String filePath = "test_data.txt";

    @BeforeEach
    void createNewFileWordAnaliser() {
        FilePartReader filePartReader = new FilePartReader();
        this.fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
    }

    @Test
    void testGetWordsOrderedAlphabetically() throws IOException {
        this.fileWordAnalyzer.getFilePartReader().setup(filePath, 1, 4);
        String[] exceptedValues = {"1a1", "2a", "2b", "3a", "3b", "3c", "4a", "4bb4", "4cr", "4d"};
        List<String> excepted = new ArrayList<>(Arrays.asList(exceptedValues));

        assertEquals(excepted, fileWordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testGetWordsContainingSubstring() throws IOException {
        this.fileWordAnalyzer.getFilePartReader().setup(filePath, 1, 4);
        String[] exceptedValues = {"3c", "3b", "3a"};
        List<String> excepted = new ArrayList<>(Arrays.asList(exceptedValues));

        assertEquals(excepted, fileWordAnalyzer.getWordsContainingSubstring("3"));
    }

    @Test
    void testGetWordsContainingSubstringWord() throws IOException {
        this.fileWordAnalyzer.getFilePartReader().setup(filePath, 1, 7);
        String[] exceptedValues = {"6ea", "7ea"};
        List<String> excepted = new ArrayList<>(Arrays.asList(exceptedValues));

        assertEquals(excepted, fileWordAnalyzer.getWordsContainingSubstring("ea"));
    }

    @Test
    void testGetStringsWhichPalindromes() throws IOException {
        this.fileWordAnalyzer.getFilePartReader().setup(filePath, 1, 4);
        String[] exceptedValues = {"1a1", "4bb4"};
        List<String> excepted = new ArrayList<>(Arrays.asList(exceptedValues));

        assertEquals(excepted, fileWordAnalyzer.getStringsWhichPalindromes());
    }

    @Test
    void testGetWordsNotContainingSubstring() throws IOException {
        this.fileWordAnalyzer.getFilePartReader().setup(filePath, 1, 4);
        String[] exceptedValues = new String[]{};
        List<String> excepted = new ArrayList<>(Arrays.asList(exceptedValues));

        assertEquals(excepted, fileWordAnalyzer.getWordsContainingSubstring("0"));
    }

}