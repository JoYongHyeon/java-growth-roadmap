package thread._14_executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static util.ThreadLogger.log;

public class _3_ExecutorService_InvokeAny_Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService es = Executors.newFixedThreadPool(10);

        _1_ExecutorService_CallableTask task1 = new _1_ExecutorService_CallableTask("task1", 1000);
        _1_ExecutorService_CallableTask task2 = new _1_ExecutorService_CallableTask("task2", 2000);
        _1_ExecutorService_CallableTask task3 = new _1_ExecutorService_CallableTask("task3", 3000);

        List<_1_ExecutorService_CallableTask> tasks = List.of(task1, task2, task3);

        Integer value = es.invokeAny(tasks);
        log("value = " + value);
        es.close();
    }
}
