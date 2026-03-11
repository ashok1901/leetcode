package medium;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ExclusiveTimeFunction {

    /**
     * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
     * Output: [3,4]
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        Stack<String> fnStack = new Stack<>();
        int[] result = new int[n];
        for (int i = 0; i<n; i++) {
            result[i] = 0;
        }
        for (String log : logs) {
            String[] parts = log.split(":");
            int fnId = Integer.parseInt(parts[0]);
            String fnAction = parts[1];
            int time = Integer.parseInt(parts[2]);
            switch (fnAction) {
                case "start" :
                    if (fnStack.isEmpty()) {
                        fnStack.push(log);
                    } else {
                        String runningFn = fnStack.peek();
                        String[] runningParts = runningFn.split(":");
                        int runningFnId = Integer.parseInt(runningParts[0]);
                        String runningFnAction = runningParts[1];
                        int runningFnTime = Integer.parseInt(runningParts[2]);
                        int fnRanFor = time - runningFnTime;
                        result[runningFnId] += fnRanFor;
                        fnStack.push(log);
                    }
                    break;
                case "end" :
                    String runningFn = fnStack.pop();
                    String[] runningParts = runningFn.split(":");
                    int runningFnId = Integer.parseInt(runningParts[0]);
                    String runningFnAction = runningParts[1];
                    int runningFnTime = Integer.parseInt(runningParts[2]);
                    int fnRanFor = time - runningFnTime;
                    result[runningFnId] += fnRanFor;
                    break;
            }
        }

        return result;

    }

//    private String parseFnAction(String log) {
//        String[] parts = log.split(":");
//        return parts[1];
//    }

    public static void main(String[] args) {
        String[] logs = {"0:start:0","1:start:2","1:end:5","0:end:6"};
        ExclusiveTimeFunction timeFunction = new ExclusiveTimeFunction();
        System.out.println("Run time " + timeFunction.exclusiveTime(2, Arrays.asList(logs)));

    }
}


