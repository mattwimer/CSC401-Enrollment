import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        HashMap<Integer, CourseTree> courses = new HashMap<Integer, CourseTree>();
        HashMap<Integer, List<Integer>> students = new HashMap<Integer, List<Integer>>();
        CourseTree course1 = new CourseTree(1130,1245);
        CourseTree course2 = new CourseTree(1230,1345);
        CourseTree course3 = new CourseTree(1100,1215);
        CourseTree course4 = new CourseTree(1315,1430);
        CourseTree course5 = new CourseTree(1500,1615);
        
        courses.put(24152,course1);
        courses.put(45367,course2);
        courses.put(12663,course3);
        courses.put(45362,course4);
        courses.put(10223,course5);
        Scanner input = new Scanner(System.in);
        int choice;
        do{
            System.out.println("Menu");
            System.out.println("1:Enroll in school");
            System.out.println("2:Add classes");
            System.out.println("3:Remove classes");
            System.out.println("4:Print class schedule");
            System.out.println("-1:Exit");
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            
            switch(choice){
                case 1:
                    int studentId = createStudentId();
                    students.put(studentId, new ArrayList<Integer>());
                    System.out.println("Enrolled in School. Your student ID is: " + studentId);
                    break;
                case 2:
                    System.out.println("Enter your student ID: ");
                    int studentAdd =input.nextInt();
                    input.nextLine();
                    if(students.containsKey(studentAdd)){
                        addClass(courses,students, studentAdd, input);
                    }else{
                        System.out.println("Invalid Student ID");
                    }
                    break;
                case 3:
                    System.out.println("Enter your student ID: ");
                    int studentRemove =input.nextInt();
                    input.nextLine();
                    if(students.containsKey(studentRemove)){
                        removeClass(students,studentRemove);
                    }else{
                        System.out.println("Invalid Student ID");
                    }
                    break;
                case 4:
                    System.out.println("Enter your student ID: ");
                    int studentPrint =input.nextInt();
                    input.nextLine();
                    if(students.containsKey(studentPrint)){
                        printSchedule(students,studentPrint);
                    }else{
                        System.out.println("Invalid Student ID");
                    }
                    break;
                case -1:
                    System.out.println("Exitting program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid number.Enter another choice");
            }
        }while(choice != 0);
        input.close();
    }
    public static int createStudentId(){
        return(int)(Math.random()* 89999) + 10000;
    }
    public static void addClass(HashMap<Integer, CourseTree> courses, HashMap<Integer, List<Integer>> students, int studentId,Scanner input){
            System.out.println("Enter course code to add: ");
             System.out.println("Available courses:");
             for (int courseCode : courses.keySet()) {
                  System.out.println("Course " + courseCode);
             }
           int courseCode = input.nextInt();
            input.nextLine();
                 
                if(courses.containsKey(courseCode)){
                    CourseTree course = courses.get(courseCode);
                    boolean timeConflictExists = false;
                    for(int existingCourseCode : students.get(studentId)){
                        CourseTree existingCourse = courses.get(existingCourseCode);
                        if(existingCourse.timeConflict(course)){
                            timeConflictExists = true;
                            System.out.println("Failed to add course " + courseCode + " due to time conflict with " + existingCourseCode);
                            break;
                        }
                    }
                    if(!timeConflictExists){
                        students.get(studentId).add(courseCode);
                        System.out.println("Successfully added course");
                    }
                }else{
                    System.out.println("Invalid course code");
                }
            }
            
        
    public static void removeClass(HashMap<Integer, List<Integer>> students, int studentId){
        List<Integer> enrolledClasses = students.get(studentId);
        if (!enrolledClasses.isEmpty()) {
        Scanner input = new Scanner(System.in);
        
        // Display the enrolled classes
        System.out.println("Enrolled classes:");
        for (int i = 0; i < enrolledClasses.size(); i++) {
            int courseCode = enrolledClasses.get(i);
            System.out.println((i + 1) + ": Course " + courseCode);
        }
        
        System.out.println("Enter the number of the course to remove (1 to " + enrolledClasses.size() + "): ");
        int choice = input.nextInt();
        
        if (choice >= 1 && choice <= enrolledClasses.size()) {
            int removeIndex = choice - 1;
            int removeCourseCode = enrolledClasses.remove(removeIndex);
            System.out.println("Successfully removed course " + removeCourseCode);
        } else {
            System.out.println("Invalid choice. No course removed.");
        }
    } else {
        System.out.println("Class schedule is empty");
    }
}
        
    
    public static void printSchedule(HashMap<Integer, List<Integer>> students, int studentId){
        List<Integer> enrolledClasses = students.get(studentId);
        if(!enrolledClasses.isEmpty()){
            System.out.print("Your schedule:");
            for(int courseCode : enrolledClasses){
                System.out.println("Course: " + courseCode);
            }
        }else{
            System.out.println("Class Schedule is empty");
        }
        
    }
    
    }
