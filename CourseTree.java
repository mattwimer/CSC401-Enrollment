public class CourseTree extends AVLTree{
    private int startTime;
    private int endTime;
    
    
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
    public boolean timeConflict(int time1, int time2){
        if(startTime < time1 && time1 < endTime)
            return true;
        else if(startTime < time2 && time2 < endTime)
            return true;
        return false;
    }
    
}
