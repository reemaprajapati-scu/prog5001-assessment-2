
/**
 * Stores the student data.
 *
 * @author (reema)
 * @version (18/09/2023)
 */
public class Student {

    final private String id;
    final private String name;
    final private double assignmentOneMark;
    final private double assignmentTwoMark;
    final private double assignmentThreeMark;

    public Student(String id, String name, double assignmentOneMark, double assignmentTwoMark, double assignmentThreeMark) {
        this.id = id;
        this.name = name;
        this.assignmentOneMark = assignmentOneMark;
        this.assignmentTwoMark = assignmentTwoMark;
        this.assignmentThreeMark = assignmentThreeMark;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAssignmentOneMark() {
        return assignmentOneMark;
    }

    public double getAssignmentTwoMark() {
        return assignmentTwoMark;
    }

    public double getAssignmentThreeMark() {
        return assignmentThreeMark;
    }

}
