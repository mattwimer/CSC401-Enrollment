import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args){
        HashMap<Integer, CourseTree> courses = new HashMap<Integer, CourseTree>();
        HashMap<Integer, List<Integer>> students = new HashMap<Integer, List<Integer>>();
        
        // Written for testing purposes
        int[] times = {1130, 1245, 1215, 1330, 1300, 1415, 1345, 1500}; // times are in sets of 2
        for(int i = 0; i < 10; i++){
            int courseCode;
            do {
                courseCode = (int)(Math.random()*8999) + 1000; // random number from 1000 to 9999
            } while(courses.containsKey(courseCode)); // reroll code until a unique course code is generated
            CourseTree tree = new CourseTree(times[(int)(i / 3)], times[(int)(i / 3)+1]); // e.g. i=5, ST= 1215, ET= 1330
            courses.put(courseCode, tree);
        }
        
        // for(int cc : courses.keySet())
        //     System.out.println(cc);

        // testing enroll
        for(int i = 0; i < 10; i++){ // 10 with prints, 80 in the end
            //make a student id
            int id;
            do{
                id = (int)(Math.random()*89999) + 10000; // random number from 10000 to 99999
            } while(students.containsKey(id)); // reroll id until a unique student id is generated
            students.put(id, new ArrayList<Integer>());

            //decide which of the 10 classes they will try to add
            for(int courseCode : courses.keySet()){
                if((int)(Math.random()*2) == 0) // 50% chance of trying to enroll
                {
                    System.out.println("Student "+id+" Trying to enroll in " + courseCode);
                    // **the following code would be in a function called enroll, but its obnoxious with all of the testing to write so I won't
                    CourseTree course = courses.get(courseCode); // course we are attempting to enroll in
                    boolean timeConflictExists = false;
                    for(int existingCourseCode : students.get(id)) // for each course the student is in, check for conflict
                    {
                        CourseTree existingCourse = courses.get(existingCourseCode);
                        if(existingCourse.timeConflict(course)){
                            timeConflictExists = true;
                            System.out.println("Student "+id+" Failed to enroll in class "+courseCode+", conflict with "+existingCourseCode);
                        }
                    }
                    if(!timeConflictExists){
                        students.get(id).add(courseCode);
                        System.out.println("Student "+id+" Successfully enrolled in class "+courseCode);
                    }
                }
            }
        }
        students.put(0, new ArrayList<Integer>()); // to test edge case if a student has no classes

        // testing remove
        for (int id : students.keySet()){
            if((int)(Math.random()*10) == 0 || id == 0){ // 10% chance to remove a class, always test remove on edge case
                List<Integer> enrolledClasses = students.get(id);
                System.out.print("Student "+id+"'s current schedule: [");
                for(int courseCode : enrolledClasses)
                    System.out.print(courseCode+", ");
                System.out.println("]");

                int i = (int)(Math.random()*enrolledClasses.size()); // pick a class to remove from student's schedule
                if(!enrolledClasses.isEmpty())
                    students.get(id).remove(i);

                enrolledClasses = students.get(id);
                System.out.print("Student "+id+"'s new schedule: [");
                for(int courseCode : enrolledClasses)
                    System.out.print(courseCode+", ");
                System.out.println("]");
            }
        }

    }
}