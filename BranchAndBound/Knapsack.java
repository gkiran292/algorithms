import java.io.*;
import java.util.*;

/**
 * To run the program
 *
 * Sample file:
 * name skill rate
 * Parshurama 25 30
 * Vishwamitra 12 50
 * Bhishma 50 101
 * Krishna 1 5
 * Jesus 45 80
 * java Knapsack <filename> <MaximumSkill>
 */

public class Knapsack {

    private static double maxProfit = 0.0;
    private static Set<Person> addPersonToKnapsack(List<Person> personList, double budget) {

        Node previous = new Node(-1, 0, 0, new HashSet<>());

        Queue<Node> q = new LinkedList<>();
        q.add(previous);

        Set<Person> finalSet = null;

        while (!q.isEmpty()) {

            Node node = q.poll();

            if (node.level > personList.size() - 2) {
                continue;
            }

            Set<Person> nodePersonSet = new HashSet<>(node.personSet);
            nodePersonSet.add(personList.get(node.level+1));
            Node newNode = new Node(node.level+1, node.rate + personList.get(node.level+1).rate,
                    node.skill + personList.get(node.level + 1).skill, nodePersonSet);

            newNode.bound = bound(newNode, budget, personList);

            if (newNode.rate <= budget && newNode.skill > maxProfit) {
                maxProfit = newNode.skill;
                finalSet = new HashSet<>(newNode.personSet);
            }

            if (newNode.bound > maxProfit) {
                q.add(newNode);
            }

            Set<Person> bareNodePersonSet = new HashSet<>(node.personSet);
            Node bareNode = new Node(node.level+1, node.rate, node.skill, bareNodePersonSet);
            bareNode.bound = bound(node, budget, personList);

            if (bareNode.bound > maxProfit) {
                q.add(bareNode);
            }
        }

        return finalSet;
    }

    private static double bound(Node node, double budget, List<Person> personList) {

        int j = node.level + 1;
        double totalRate = node.rate;
        double totalSkill = node.skill;

        while(j < personList.size() && totalRate <= budget) {
            totalRate += personList.get(j).rate;
            totalSkill += personList.get(j).skill;
            j++;
        }

        return totalSkill;
    }

    public static void main(String[] args) throws IOException {

        File file = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
        List<Person> personList = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) {
            String[] value = line.split("\\s");
            personList.add(new Person(value[0], Double.parseDouble(value[2]), Double.parseDouble(value[1])));
        }
        Collections.sort(personList);
        Set<Person> personSet = addPersonToKnapsack(personList, Double.parseDouble(args[1]));

        personSet.forEach(person -> System.out.println(person.name + " 1.000000"));
        System.out.println(maxProfit);
    }
}

class Person implements Comparable<Person> {
    String name;
    double rate;
    double skill;

    Person(String name, double rate, double skill) {
        this.name = name;
        this.rate = rate;
        this.skill = skill;
    }

    @Override
    public int compareTo(Person p) {
        return Double.compare(this.skill/this.rate, p.skill/p.rate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.rate, rate) == 0 &&
                Double.compare(person.skill, skill) == 0 &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, skill);
    }
}

class Node {
    Set<Person> personSet;
    int level;
    double rate;
    double skill;
    double bound;

    Node(int level, double rate, double skill, Set<Person> personSet) {
        this.level = level;
        this.rate = rate;
        this.skill = skill;
        this.personSet = personSet;
    }
}
