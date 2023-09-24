import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args){
        HashMap<Integer, AVLTree> courses = new HashMap<Integer, AVLTree>();
        HashMap<Integer, String> students = new HashMap<Integer, String>();

        // Written for testing purposes
        for(int i = 0; i < 3; i++){
            int courseCode;
            do {
                courseCode = (int)(Math.random()*8999) + 1000; // random number from 1000 to 9999
            } while(!courses.containsKey(courseCode)); // reroll code until a unique course code is generated
            AVLTree tree = new AVLTree();
            courses.put(courseCode, tree);
        }
        
        String[] blah = {"Mackenzie", "Jack", "Ryan", "Laura", "Shivam", "Maria", "Selena", "Mike", "Aaron"};
        List<String> names = Arrays.asList(blah);
    }
    
    
}