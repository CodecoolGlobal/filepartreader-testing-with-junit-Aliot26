import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.filePath = "test_data.txt";
        this.fromLine = 1;
        this.toLine = 1;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFromLine() {
        return fromLine;
    }

    public Integer getToLine() {
        return toLine;
    }

    public void setFromLine(Integer fromLine) {
        this.fromLine = fromLine;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setToLine(Integer toLine) {
        this.toLine = toLine;
    }

    public void setup(String filepath, Integer fromLine, Integer toLine) {
        if (fromLine > toLine || fromLine < 1) {
            throw new IllegalArgumentException("parameters are wrong");
        }
        setFromLine(fromLine);
        setToLine(toLine);
        setFilePath(filepath);
    }

    public String read() throws IOException {
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(ls);
            }

            return stringBuilder.toString();
        }
    }

    public String readLines() throws IOException {
        StringBuilder output = new StringBuilder();
        String[] lines = read().split("\\r?\\n");
        if (lines.length < getToLine()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        for (Integer line = getFromLine() - 1; line < getToLine();
             line++) {
            output.append(lines[line]).append("\n");
        }
        return output.toString();
    }

}
