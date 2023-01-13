package Ex2;

import java.util.concurrent.Callable;

public class CustomExecutor implements Comparable<CustomExecutor> {


    private Task task;
    private int priory;
    private CustomExecutor currentMax;



    private CustomExecutor(Task task, int priory) {
        this.task = task;
        if (priory>=0 && priory<=10){
            this.priory = priory;
        }
        else {
            this.priory = 5;
        }
    }

    public static CustomExecutor createCustomExecutor(Task task, int priory) {
        return new CustomExecutor(task, priory);
    }

    @Override
    public int compareTo(CustomExecutor o) {

        return Integer.compare(this.getPriory(),o.getPriory());
    }

    public int getPriory() {
        return priory;
    }

    public CustomExecutor getCurrentMax() {

        return currentMax;
    }


    public static void gracefullyTerminate(){

    }


}
