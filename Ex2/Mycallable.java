package Ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

public class Mycallable implements Callable<Integer> {

    private String fileNames;

    public Mycallable(String fileNames) {
        this.fileNames = fileNames;
    }


    @Override
    public Integer call() throws Exception {
        int sumOfLine = 0 ;

        try {
            sumOfLine += Files.lines(Path.of(fileNames)).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sumOfLine;


    }
}
