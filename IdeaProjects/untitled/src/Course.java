import java.util.ArrayList;

public class Course {
    private String courseName;
    private ArrayList<String> students = new ArrayList<>();
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public void addStudent(String student){
        students.add(student);
    }
    public void removeStudents(){
        for(String s: students)
            students.remove(s);
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<String> getStudents() {
        return students;
    }



    public int getNumberOfStudents() {
        return students.size();
    }

}
