import java.util.Scanner;

public class StudentGradeCalculator {

    // Method to calculate grade based on average percentage
    public static String calculateGrade(double average) {
        if (average >= 90) {
            return "A+";
        } else if (average >= 80) {
            return "A";
        } else if (average >= 70) {
            return "B";
        } else if (average >= 60) {
            return "C";
        } else if (average >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("STUDENT GRADE CALCULATOR");
        
        // Input: number of subjects
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        double[] marks = new double[numSubjects];
        double totalMarks = 0;

        // Input: marks for each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextDouble();

            // Validate marks input
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid input. Enter marks between 0 and 100: ");
                marks[i] = scanner.nextDouble();
            }

            totalMarks += marks[i];
        }

        // Calculate average
        double averagePercentage = totalMarks / numSubjects;
        String grade = calculateGrade(averagePercentage);

        // Display results
        System.out.println("\n--- RESULT ---");
        System.out.printf("Total Marks       : %.2f / %.0f\n", totalMarks, (double)(numSubjects * 100));
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade             : " + grade);

        scanner.close();
    }
}
