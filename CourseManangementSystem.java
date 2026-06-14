import java.util.*;

interface Enrollment {
    void enroll(String cId, String sId);
}

interface Submission {
    void submit(String aId, String cId, String sId, String sub);
}

class Course {
    private String cId;
    private String cName;
    private String desc;
    private String instName;

    public Course(String courseId, String courseName, String description, String instructorName) {
        this.cId = courseId;
        this.cName = courseName;
        this.desc = description;
        this.instName = instructorName;
    }

    public String getCourseId() {
        return cId;
    }

    public String getCourseName() {
        return cName;
    }

    public String getDescription() {
        return desc;
    }

    public String getInstructorName() {
        return instName;
    }
}

class Assignment {
    private String aId;
    private String cId;
    private String sId;
    private String sub;

    public Assignment(String assignmentId, String courseId, String studentId, String submission) {
        this.aId = assignmentId;
        this.cId = courseId;
        this.sId = studentId;
        this.sub = submission;
    }

    public String getAssignmentId() {
        return aId;
    }

    public String getCourseId() {
        return cId;
    }

    public String getStudentId() {
        return sId;
    }

    public String getSubmission() {
        return sub;
    }
}

class Student {
    private String studentId;
    private String studentName;

    public Student(String studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }
}

public class CourseManangementSystem implements Enrollment, Submission {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<Assignment> assignments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void createCourse() {
        System.out.println("Enter course details:");
        System.out.println("+----------------------------------+");
        System.out.print("| Course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("| Course Name: ");
        String courseName = scanner.nextLine();
        System.out.print("| Description: ");
        String description = scanner.nextLine();
        System.out.print("| Instructor Name: ");
        String instructorName = scanner.nextLine();
        System.out.println("+----------------------------------+");

        Course course = new Course(courseId, courseName, description, instructorName);
        courses.add(course);
        System.out.println("+----------------------------------+");
        System.out.println("| Course created successfully.      |");
        System.out.println("+----------------------------------+");
    }

    public void enroll(String courseId, String studentId) {
        boolean courseExists = false;
        boolean studentExists = false;
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courseExists = true;
                break;
            }
        }
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                studentExists = true;
                break;
            }
        }
        if (!courseExists) {
            System.out.println("+---------------------+");
            System.out.println("| Course not found.   |");
            System.out.println("+---------------------+");
            return;
        }
        if (!studentExists) {
            System.out.println("+----------------------+");
            System.out.println("| Student not found.   |");
            System.out.println("+----------------------+");
            return;
        }
        System.out.println("+----------------------------+");
        System.out.println("| Enrollment confirmed, course access granted. |");
        System.out.println("+----------------------------+");
    }

    public void submit(String assignmentId, String courseId, String studentId, String submission) {
        boolean courseExists = false;
        boolean studentExists = false;
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courseExists = true;
                break;
            }
        }
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                studentExists = true;
                break;
            }
        }
        if (!courseExists) {
            System.out.println("+---------------------+");
            System.out.println("| Course not found.   |");
            System.out.println("+---------------------+");
            return;
        }
        if (!studentExists) {
            System.out.println("+----------------------+");
            System.out.println("| Student not found.   |");
            System.out.println("+----------------------+");
            return;
        }
        Assignment assignment = new Assignment(assignmentId, courseId, studentId, submission);
        assignments.add(assignment);
        System.out.println("+------------------------------------+");
        System.out.println("| Assignment submitted, feedback provided. |");
        System.out.println("+------------------------------------+");
    }

    public static void addStudent() {
        System.out.println("Enter student details:");
        System.out.println("+-------------------------------+");
        System.out.print("| Student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("| Student Name: ");
        String studentName = scanner.nextLine();
        System.out.println("+-------------------------------+");

        Student student = new Student(studentId, studentName);
        students.add(student);
        System.out.println("+--------------------------------------+");
        System.out.println("| Student added successfully.          |");
        System.out.println("+--------------------------------------+");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nOnline Course Management System Menu:");
            System.out.println("+----------------------------------------+");
            System.out.println("| 1. Create a course                    |");
            System.out.println("| 2. Enroll in a course                 |");
            System.out.println("| 3. Submit an assignment               |");
            System.out.println("| 4. Add a student                      |");
            System.out.println("| 5. Exit                               |");
            System.out.println("+----------------------------------------+");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createCourse();
                    break;
                case 2:
                    System.out.println("Enter enrollment details:");
                    System.out.println("+---------------------------+");
                    System.out.print("| Course ID: ");
                    String courseId = scanner.nextLine();
                    System.out.print("| Student ID: ");
                    String studentId = scanner.nextLine();
                    new CourseManangementSystem().enroll(courseId, studentId);
                    break;
                case 3:
                    System.out.println("Enter assignment details:");
                    System.out.println("+----------------------------+");
                    System.out.print("| Assignment ID: ");
                    String assignmentId = scanner.nextLine();
                    System.out.print("| Course ID: ");
                    courseId = scanner.nextLine();
                    System.out.print("| Student ID: ");
                    studentId = scanner.nextLine();
                    System.out.print("| Submission: ");
                    String submission = scanner.nextLine();
                    new CourseManangementSystem().submit(assignmentId, courseId, studentId, submission);
                    break;
                case 4:
                    addStudent();
                    break;
                case 5:
                    System.out.println("+----------------------+");
                    System.out.println("| Exiting program.     |");
                    System.out.println("+----------------------+");
                    break;
                default:
                    System.out.println("+----------------------------------------+");
                    System.out.println("| Invalid choice. Please enter a number between 1 and 5. |");
                    System.out.println("+----------------------------------------+");
            }
        } while (choice != 5);
        scanner.close();
    }
}
