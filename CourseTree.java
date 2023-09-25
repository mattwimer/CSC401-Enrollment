public class CourseTree extends AVLTree{
    private int startTime;
    private int endTime;
    
    public CourseTree(int a, int b){
        super();
        startTime = a;
        endTime = b;
    }
    
    public int getStartTime() {
        return startTime;
    }
    // public void setStartTime(int startTime) {
    //     this.startTime = startTime;
    // }
    public int getEndTime() {
        return endTime;
    }
    // public void setEndTime(int endTime) {
    //     this.endTime = endTime;
    // }

    
    public boolean timeConflict(int time1, int time2){ // returns true if conflict exists
        if(startTime < time1 && time1 < endTime)
            return true;
        else if(startTime < time2 && time2 < endTime)
            return true;
        return false;
    }
    
    public boolean timeConflict(CourseTree other){
        return timeConflict(other.getStartTime(), other.getEndTime());
    }

    public void displayInfo(){
        System.out.println("The course begins at "+startTime+" and ends at "+endTime+". The following are students enrolled in the course: ");
        super.inOrderTraversal(super.root);
        System.out.println();
    }
}
