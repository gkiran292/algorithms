import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxProfitJobSchedule {

    /*
    Reference: https://www.geeksforgeeks.org/weighted-job-scheduling/
     */

    /*
    To run the program: javac MaxProfitJobSchedule.java
    java MaxProfitJobSchedule <input-file-name>
     */
    public static void main(String[] args) throws IOException {

        List<Job> jobs = readInput(args[0]);
        Collections.sort(jobs);
        int[] table = new int[jobs.size()];
        table[0] = jobs.get(0).getWeight();

        for (int i = 1; i < jobs.size(); i++) {

            int localProfit = jobs.get(i).getWeight();
            int compatibleJobIndex = binarySearch(jobs, i);

            if (compatibleJobIndex != Integer.MIN_VALUE) {
                localProfit += table[compatibleJobIndex];
            }

            table[i] = Math.max(localProfit, table[i-1]);
        }

        System.out.println(table[jobs.size()-1]);
    }

    private static int binarySearch(List<Job> jobs, int index) {

        int start = 0;
        int end = index-1;

        int mid;

        while (start <= end) {
            mid = (start + end)/2;

            if (jobs.get(mid).getFinishTime() <= jobs.get(index).getStartTime()) {
                if (jobs.get(mid+1).getFinishTime() <= jobs.get(index).getStartTime()) {
                    start = mid+1;
                } else {
                    return mid;
                }
            } else {
                end = mid - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    private static List<Job> readInput(String fileName) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));

        String inputLine;
        List<Job> jobs = new ArrayList<>();

        while ((inputLine = bf.readLine()) != null) {
            String[] elements = inputLine.split("\\s");

            jobs.add(new Job(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]),
                    Integer.parseInt(elements[2])));
        }

        return jobs;
    }
}


class Job implements Comparable<Job> {
    private int startTime;
    private int finishTime;
    private int weight;

    Job(int startTime, int finishTime, int weight) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.weight = weight;
    }

    int getStartTime() {
        return startTime;
    }

    int getFinishTime() {
        return finishTime;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Job o) {
        return Integer.compare(this.finishTime, o.finishTime);
    }

    @Override
    public String toString() {
        return "Job{" +
                "startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", weight=" + weight +
                '}';
    }
}
