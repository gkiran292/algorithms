import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class HuffManEncoding {

    public static void main(String[] args) throws IOException, URISyntaxException {

        System.out.println("Reading input from the file " + args[0]);
        List<String> lines = readDataFromFile(args[0]);
        int[] characterCountMap = getCharacterCount(lines);
        PriorityQueue<HTreeNode> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < characterCountMap.length; i++) {

            if (characterCountMap[i] < 1) {
                continue;
            }
            HTreeNode hTreeNode = new HTreeNode((char)(i+32), characterCountMap[i]);

            priorityQueue.add(hTreeNode);
        }

        while (priorityQueue.size() > 1) {

            HTreeNode hTreeNode1 = priorityQueue.poll();
            HTreeNode hTreeNode2 = priorityQueue.poll();

            if (hTreeNode1 != null && hTreeNode2 != null) {
                HTreeNode hTreeNode = new HTreeNode((hTreeNode1.frequency + hTreeNode2.frequency));
                hTreeNode.left = hTreeNode1;
                hTreeNode.right = hTreeNode2;
                priorityQueue.add(hTreeNode);
            }
        }

        Map<Character, String> outputMap = new HashMap<>();

        HTreeNode hTreeNode = priorityQueue.poll();
        traverseTree(hTreeNode, "", outputMap);

        for (Map.Entry<Character, String> entry : outputMap.entrySet()) {
            System.out.println(entry.getKey() + " ===> " + entry.getValue());
        }

        int sumWithoutEncoding = 0;
        int sumWithEncoding = 0;
        for (int i = 0; i < characterCountMap.length; i++) {
            if (characterCountMap[i] < 1) {
                continue;
            }

            sumWithoutEncoding += characterCountMap[i] * 7;
            sumWithEncoding += characterCountMap[i] * outputMap.get((char)(i+32)).length();
        }

        System.out.println("Sum without encoding: " + sumWithoutEncoding);
        System.out.println("Sum with encoding: " + sumWithEncoding);
    }

    private static void traverseTree(HTreeNode root, String s, Map<Character, String> outputMap) {

        if (root == null) {
            return;
        }

        if (root.aChar != (char)0) {
            outputMap.put(root.aChar, s);
            return;
        }

        traverseTree(root.left, s + "0", outputMap);
        traverseTree(root.right, s + "1", outputMap);
    }

    private static int[] getCharacterCount(List<String> totalList) {

        int[] charArray = new int[97];
        for (String s : totalList) {

            for (int i = 0; i < s.length(); i++) {
                char aChar = s.charAt(i);
                int index = aChar - 32;
                if (index < 0 || index > 96) {
                    continue;
                }
                charArray[aChar - 32]++;
            }
        }

        return charArray;
    }

    private static List<String> readDataFromFile(String uriPath) throws IOException, URISyntaxException {

        List<String> list = new ArrayList<>();
        File file = new File(new URI(uriPath));
        if (!file.exists()) {
            if (!file.createNewFile()) {
                System.out.println("Couldn't create a new file " + uriPath);
                throw new IOException("Couldn't create a new file tmp.txt");
            }
        }

        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));

        String line;
        while ((line = br.readLine()) != null) {
            list.add(line);
        }

        br.close();
        return list;
    }
}

class HTreeNode implements Comparable<HTreeNode> {

    char aChar;
    int frequency;
    HTreeNode left;
    HTreeNode right;

    HTreeNode(char aChar, int frequency) {
        this.aChar = aChar;
        this.frequency = frequency;
    }

    HTreeNode(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HTreeNode o) {

        if (this.frequency != o.frequency) {
            return this.frequency - o.frequency;
        }

        return -1;
    }
}
