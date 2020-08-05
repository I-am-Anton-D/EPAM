package classes;

import java.util.Arrays;

/**
 * Class for student entity
 */

public class Student {
    private String name;
    private String numberOfGroup;
    int[] performance = new int[5];

    public Student(String name, String numberOfGroup, int[] performance) {
        this.name = name;
        this.numberOfGroup = numberOfGroup;
        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(String numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public int[] getPerformance() {
        return performance;
    }

    public void setPerformance(int[] performance) {
        this.performance = performance;
    }

    @Override
    public String toString() {
        return "Student{" +
            "name='" + name + '\'' +
            ", numberOfGroup='" + numberOfGroup + '\'' +
            ", performance=" + Arrays.toString(performance) +
            '}';
    }
}
