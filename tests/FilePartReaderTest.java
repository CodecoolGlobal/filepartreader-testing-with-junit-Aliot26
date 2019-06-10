import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FilePartReaderTest {

    private FilePartReader filePartReader;
    private String filePath = "test_data.txt";

    @BeforeEach
    void createNewFilePartReader() {
        this.filePartReader = new FilePartReader();
    }

    @Test
    void testReadLine1() throws IOException {
        filePartReader.setup(filePath, 1, 1);
        assertEquals("1a1\n", filePartReader.readLines());
    }

    @Test
    void testReadLines1_3() throws IOException {
        filePartReader.setup(filePath, 1, 3);
        assertEquals("1a1\n"
                + "2b 2a\n"
                + "3c 3b 3a\n", filePartReader.readLines());
    }

    @Test
    void testSetupReadLinesAll() throws IOException {
        filePartReader.setup(filePath, 1, 7);
        assertEquals("1a1\n"
                + "2b 2a\n"
                + "3c 3b 3a\n"
                + "4d 4cr 4bb4 4a\n"
                + "5e 5d 5c 5b 5ax\n"
                + "6f 6ea 6d 6ca 6bb 6a\n"
                + "7g 7f 7ea\n", filePartReader.readLines());
    }

    @Test
    void testSetupFromLineWrongArgument() {
        assertThrows(IllegalArgumentException.class,
                () -> filePartReader.setup(filePath, -2, 2));
    }

    @Test
    void testToLineWrongArgument(){
        assertThrows(IllegalArgumentException.class,
                ()->filePartReader.setup(filePath, 1, -1));
    }

    @Test
    void testSetupToLineOutOfBounds() throws IOException {
        filePartReader.setup(filePath,1,99);
        assertThrows(ArrayIndexOutOfBoundsException.class, ()->filePartReader.readLines());
    }

    @Test
    void testSetupFilePath() {
        filePartReader.setup("", 1, 2);
        assertThrows(IOException.class, () -> filePartReader.readLines(), "File not found");
    }

}
