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
            System.out.println(getUnitName());
            System.out.println(studentList.size());
            
        } catch (IOException e) {
            System.out.println("Cannot open the file");
        }
    }
}
