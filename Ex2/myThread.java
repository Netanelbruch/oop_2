package Ex2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class myThread extends Thread  {


    private final String file;
    private int sumOfarr;

    public myThread( String file) {
        this.file = file;
    }


    @Override
    public void run() {

        sumOfarr += getNumOfLines(file);
    }


    public static int getNumOfLines(String fileNames){
        int sumOfLine = 0;

            try {
                sumOfLine += Files.lines(Path.of(fileNames)).count();

            } catch (IOException e) {
                e.printStackTrace();
            }

        return sumOfLine;
    }

    public int getSumOfarr() {
        return sumOfarr;
    }
}
