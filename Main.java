import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        HashMap<Integer, CourseTree> courses = new HashMap<Integer, CourseTree>();
        HashMap<Integer, List<Integer>> students = new HashMap<Integer, List<Integer>>();
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
                        addClass(courses,students, studentAdd);
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
    public static void addClass(HashMap<Integer, CourseTree> courses, HashMap<Integer, List<Integer>> students, int studentId){
            for(int courseCode : courses.keySet()){
                if((int)(Math.random() * 2) == 0){
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
                }
            }
        }
    public static void removeClass(HashMap<Integer, List<Integer>> students, int studentId){
        List<Integer> enrolledClasses = students.get(studentId);
        if(!enrolledClasses.isEmpty()){
            int removeIndex = (int)(Math.random()*enrolledClasses.size());
            int removeCourseCode = enrolledClasses.remove(removeIndex);
            System.out.println("Successfully removed course");
        }else{
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
