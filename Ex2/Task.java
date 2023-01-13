package Ex2;

import java.util.concurrent.Callable;

    public class Task<T> implements Callable<T>, Comparable<Task<T>> {
        private Callable<T> task;

        private TaskType taskType;

        public Task(Callable<T> callable) {
            this.task = callable;

        }
        public Task(Callable<T> callable, TaskType taskType) {
            this.task = callable;
            this.taskType = taskType;
        }


        public static <T> Task<T> createTask(Callable<T> task) {
            return new Task<T>(task);
        }

        public static <T> Task<T> createTask(Callable<T> task,TaskType taskType) {
            return new Task<T>(task, taskType);
        }

        public Callable<T> getTask() {
            return task;
        }





        @Override
        public T call() throws Exception {
            if (this.getTask() != null){
                return  this.task.call();
            }

            return null;
        }

        @Override
        public int compareTo(Task<T> o) {
            return Integer.compare(this.getTaskType().getPriorityValue(), o.getTaskType().getPriorityValue());
        }

        public TaskType getTaskType() {
            return taskType;
        }

        public void setTaskType(TaskType taskType) {
            this.taskType = taskType;
        }
    }