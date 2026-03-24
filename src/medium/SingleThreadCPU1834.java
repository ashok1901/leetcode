package medium;

import java.util.*;

public class SingleThreadCPU1834 {

    private class Task {
        private int index;
        private  int enqueueTime;
        private int processingTime;
        public Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }

    /**
     * Approach : Maintain a priority queue as per the priority criteria
     * @param tasks
     * @return
     */
    public int[] getOrder(int[][] tasks) {
        Queue<Task> queue = new PriorityQueue<>(new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.processingTime == o2.processingTime) {
                    return o1.index - o2.index;
                }
                return o1.processingTime - o2.processingTime;
            }
        });

        // First CPU will pick the first job as it's idle as of now, so push all the jobs with smallest enqueue time
        // to the queue. Then pop one and push those came within it ends this picked up job
        int smallestEnqueueTime = Integer.MAX_VALUE;
        for (int i = 0; i < tasks.length; i++) {
            smallestEnqueueTime = Math.min(smallestEnqueueTime, tasks[i][0]);
        }

        Set<Integer> alreadyVisited = new HashSet<>();
        // Push the smalleset enque time jobs to the priority queue
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i][0] == smallestEnqueueTime) {
                queue.offer(new Task(i, tasks[i][0], tasks[i][1]));
                alreadyVisited.add(i);
            }
        }

        // Now pick the top of the priority queue and add other jobs those start before this job ends
        int[] result = new int[tasks.length];
        int resultIndex = 0;
        int currTime = 0;
        // Already added to the queue

        while (!queue.isEmpty()) {
            Task task = queue.poll();
            int taskEnqueueTime = task.enqueueTime;
            int taskEndTime = taskEnqueueTime + task.processingTime;
            result[resultIndex] = task.index;
            resultIndex++;

            // Now push all those com in before this time but after this
            for (int i = 0; i < tasks.length; i++) {
                int taskArrivalTime = tasks[i][0];
                if (taskArrivalTime >= taskEnqueueTime && taskArrivalTime <= taskEndTime && !alreadyVisited.contains(i)) {
                    queue.offer(new Task(i, tasks[i][0], tasks[i][1]));
                    alreadyVisited.add(i);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SingleThreadCPU1834 singleCore = new SingleThreadCPU1834();
        int[][] tasks = {{1,2},{2,4},{3,2},{4,1}};
        System.out.println("Tasks order "  + Arrays.toString(singleCore.getOrder(tasks)));

        int[][] tasks1 = {{7,10},{7,12},{7,5},{7,4},{7,2}};
        System.out.println("Tasks order "  + Arrays.toString(singleCore.getOrder(tasks1)));
    }
}


