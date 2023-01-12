package Ex2;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Ex2_1  {




    public static String[] createTextFiles(int n, int seed, int bound) throws IOException {
        String[] arr = new String[n];
        Random rnd = new Random();
        rnd.setSeed(seed);

        for (int i = 0; i < n; i++) {
            String fileName = "file_" + (i) + ".txt";
            arr[i] = fileName;

            int numOfLines = rnd.nextInt(bound);
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (int j=0; j<numOfLines; j++){
                writer.write("Hello world "+ j + "\n");

            }
            writer.close();
        }

        return arr;
    }

    public static int getNumOfLines(String[] fileNames){
        int sumOfLine = 0;

        for (String fileName : fileNames) {
            try {
                sumOfLine += Files.lines(Path.of(fileName)).count();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sumOfLine;
    }


    public static int getNumOfLinesThreads(String[] fileNames){
        int sum=0;
        myThread[] gThread = new myThread[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {

            myThread newThread = new myThread(fileNames[i]);
            newThread.start();

            gThread[i]= newThread;


        }
        for (int i = 0; i < fileNames.length; i++) {

            try {
                gThread[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sum += gThread[i].getSumOfarr();

        }


        return sum;
    }



    public static int getNumOfLinesThreadPool(String[] fileNames){
        int sum=0;

        ExecutorService threadpool1 = Executors.newFixedThreadPool(fileNames.length);
        Future<Integer>[] list = new Future[fileNames.length];


        for (int i = 0; i < fileNames.length; i++) {
            Mycallable newtask = new Mycallable(fileNames[i]);
            list[i] = threadpool1.submit(newtask);

        }

        for (int i = 0; i < fileNames.length; i++) {

            try {
                sum += list[i].get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        threadpool1.shutdown();

        return sum;
    }




    public static void main(String[] args) throws IOException {
        long s = System.currentTimeMillis();




        System.out.println("#######################################################################");

        s = System.currentTimeMillis();
        String[] arr = createTextFiles(500, 2,200);
        int sum = getNumOfLines(arr);

        System.out.println("number of line: "+sum+" time in ms: "+ (System.currentTimeMillis() - s));



        System.out.println("#######################################################################");
        s = System.currentTimeMillis();
        int sum2 = getNumOfLinesThreads(arr);

        System.out.println("number of line: "+sum2+" time in ms: "+ (System.currentTimeMillis() - s));

        System.out.println("#######################################################################");
        s = System.currentTimeMillis();
        int sum3 = getNumOfLinesThreadPool(arr);
        System.out.println("number of line: "+sum3+" time in ms: "+ (System.currentTimeMillis() - s));

    }

}

