import java.util.*;
import java.io.*;

/**
 * Reads students' marks from file, calculates total and prints highest and lowest
 *
 * @author (reema)
 * @version (18/09/2023)
 */
public class StudentMarksStatistics
{
    private String unitName;
    private List<Student> studentList = new ArrayList<>();

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String name) {
        this.unitName = name;
    }
    
     public void calculateMarksStatistics() {
        readDataFromTextFile();
        calculateAndPrintTotalMarks();
        printStudentBelowThresholdMark();
        calculateAndPrintTopFiveHighLowStudents();
    }
    
    /**
     * F1: Read unit name and students mark from text file
     */
     public void readDataFromTextFile() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file name (with extension): ");
        String fileName = scanner.nextLine();
        scanner.close();

        try {
            File file = new File(fileName);
            Scanner scanner1 = new Scanner(file);
            
            while (scanner1.hasNextLine()) {
                String line = scanner1.nextLine();

                String[] columns = line.split(",");
                int totalColumns = columns.length;

                if (totalColumns == 1) {
                    setUnitName(columns[0]);
                } else {
                    // if first record is heading ignore
                    if (columns[0].equals("Last Name")) {
                        continue;
                    }
                    String studentLastName = columns[0];
                    String studentFirstName = columns[1];
                    String studentId = columns[2];
                    double assignmentOneMark = 0;
                    double assignmentTwoMark = 0;
                    double assignmentThreeMark = 0;
                    
                    if (totalColumns > 3 && !columns[3].isEmpty()) {
                        assignmentOneMark = Double.parseDouble(columns[3]);
                    }

                    if (totalColumns > 4 && !columns[4].isEmpty()) {
                        assignmentTwoMark = Double.parseDouble(columns[4]);
                    }

                    if (totalColumns > 5 && !columns[5].isEmpty()) {
                        assignmentThreeMark = Double.parseDouble(columns[5]);
                    }

                    String studentFullName = studentFirstName + " " + studentLastName;

                    Student student = new Student(studentId, studentFullName, assignmentOneMark, assignmentTwoMark, assignmentThreeMark);
                    studentList.add(student);
                }
            }
            scanner1.close();
        } catch (IOException e) {
            System.out.println("Cannot open the file");
        }
    }
    
     /**
     * F2: Calculate and print total marks for each student
     */
    public void calculateAndPrintTotalMarks() {

        System.out.println(getUnitName());
        System.out.println("--------------------------");

        for (Student student: studentList) {

            double total = student.getAssignmentOneMark() + student.getAssignmentTwoMark() + student.getAssignmentThreeMark();
            student.setTotalMarks(total);

            System.out.println("Student Name: " + student.getName());
            System.out.println("Student Id: " + student.getId());
            System.out.println("Assignment One Mark: " + student.getAssignmentOneMark());
            System.out.println("Assignment Two Mark: " + student.getAssignmentTwoMark());
            System.out.println("Assignment Three Mark: " + student.getAssignmentTwoMark());
            System.out.println("Total Marks: " + student.getTotalMarks());
            System.out.println("----------------------------");
        }
    }
    
     /**
     * F3: print the list of students with total marks less than a certain threshold
     */
    public void printStudentBelowThresholdMark() {
        System.out.print("Enter threshold mark: ");
        Scanner scanner = new Scanner(System.in);

        double threshold;
        while (true) {
            String input = scanner.nextLine();
            try {
                double mark = Double.parseDouble(input);

                if (mark <= 0) {
                    System.out.print("Please enter a valid threshold: ");
                } else {
                    threshold = mark;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid threshold: ");
            }
        }

        for (Student student: studentList) {
            if (student.getTotalMarks() < threshold) {
                System.out.println(student.getId() + "-" + student.getName() + ", " + "Total Marks: " + student.getTotalMarks());
            }
        }
    }
    
    /**
     * F4: calculate and print top five highest and lowest students
     */
    public void calculateAndPrintTopFiveHighLowStudents() {
        Student[] highestStudentList = new Student[5];
        Student[] lowestStudentList = new Student[5];

        double[] highestTotals = {0, 0, 0, 0, 0};
        double[] lowestTotals = {90, 90, 90, 90, 90};

        for (Student student : studentList) {

            for (int i = 0; i < 5; i++) {
                if (student.getTotalMarks() > highestTotals[i]) {
                    // find and shift student with lower marks in the list
                    for (int j = 4; j > i; j--) {
                        highestStudentList[j] = highestStudentList[j - 1];
                        highestTotals[j] = highestTotals[j - 1];
                    }

                    // add student into the highest list
                    highestStudentList[i] = student;
                    highestTotals[i] = student.getTotalMarks();
                    break;
                }
            }

            for (int i = 0; i < 5; i++) {
                if (student.getTotalMarks() < lowestTotals[i]) {
                    // find and shift student with higher marks in the list
                    for (int j = 4; j > i; j--) {
                        lowestStudentList[j] = lowestStudentList[j - 1];
                        lowestTotals[j] = lowestTotals[j - 1];
                    }

                    // add student into the highest list
                    lowestStudentList[i] = student;
                    lowestTotals[i] = student.getTotalMarks();
                    break;
                }
            }
        }

        System.out.println("Top 5 students with highest marks");
        for (Student student: highestStudentList) {
            System.out.println(student.getName() + ": " + student.getTotalMarks());
        }

        System.out.println("Top 5 students with lowest marks");
        for (Student student: lowestStudentList) {
            System.out.println(student.getName() + ": " + student.getTotalMarks());
        }
    }
}
