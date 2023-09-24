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
            } while(!courses.containsKey(courseCode)); // reroll code until a unique course code is generated
            CourseTree tree = new CourseTree(times[(int)(i / 3)], times[(int)(i / 3)+1]); // e.g. i=5, ST= 1215, ET= 1330
            courses.put(courseCode, tree);
        }
        
        for(int i = 0; i < 10; i++){ // 10 with prints, 80 in the end
            //make a student id
            int id;
            do{
                id = (int)(Math.random()*89999) + 10000; // random number from 10000 to 99999
            } while(!students.containsKey(id)); // reroll id until a unique student id is generated
            students.put(id, new ArrayList<Integer>());
            //decide which of the 10 classes they will try to add
            for(int courseCode : courses.keySet()){
                if((int)(Math.random()*2) == 0) // 50% chance of trying to enroll
                {
                    CourseTree course = courses.get(courseCode); // course we are attempting to enroll in
                    for(int existingCourseCode : students.get(id))
                    {
                        CourseTree existingCourse = courses.get(existingCourseCode);
                        if(!existingCourse.timeConflict(course))
                            students.get(id).add(courseCode);
                    }
                }
            }
        }
        // String[] blah = {"Mackenzie", "Jack", "Ryan", "Laura", "Shivam", "Maria", "Selena", "Mike", "Aaron"};
        // List<String> names = Arrays.asList(blah);
    }
    
    
}