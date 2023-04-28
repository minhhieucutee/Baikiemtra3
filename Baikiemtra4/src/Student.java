import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String studentID;
    String name;
    String address;
    String phone;

    Student(String studentID, String name, String address, String phone) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-20s %-12s", studentID, name, address, phone);
    }
}

 class SchoolManagement {
    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add student records");
            System.out.println("2. Display student records");
            System.out.println("3. Save records");
            System.out.println("4. Exit");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    saveRecords();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        scanner.nextLine(); // consume newline character from previous input

        System.out.println("Enter student ID:");
        String studentID = scanner.nextLine();

        System.out.println("Enter student name:");
        String name = scanner.nextLine();

        System.out.println("Enter student address:");
        String address = scanner.nextLine();

        System.out.println("Enter student phone:");
        String phone = scanner.nextLine();

        students.add(new Student(studentID, name, address, phone));
        System.out.println("Student record added successfully!");
    }

    private static void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.printf("%-10s %-20s %-20s %-12s\n", "Student ID", "Student Name", "Address", "Phone");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void saveRecords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student student : students) {
                writer.write(String.format("%s,%s,%s,%s\n", student.studentID, student.name, student.address, student.phone));
            }
            System.out.println("Student records saved to file 'students.txt' successfully!");
        } catch (IOException e) {
            System.out.println("Error occurred while saving student records.");
            e.printStackTrace();
        }
    }
}