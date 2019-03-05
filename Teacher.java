/**
 * Teacher.java
 * @author Julia Zhao
 * @date April 2 2018
 * @version 1.0.0
 * Teacher class to use with schedule program
 */

public class Teacher extends Person { 
  private Student [][] students = new Student[4][];
  private String [] courses=new String[4];
  private int a=0, b=0, c=0, d=0;
  
  Teacher(){ //default constructor
  }
  
  Teacher (String firstName, String lastName, String course1, String course2, 
           String course3, String course4){
    this.firstName=firstName;
    this.lastName=lastName;
    this.courses[0]=course1;
    this.courses[1]=course2;
    this.courses[2]=course3;
    this.courses[4]=course4;
  }
  
  /* setCourseMax method
   * Sets the maximum number of students for a course
   * @param courseName - name of course
   * @param courseMax - max number of students
   */
  public void setCourseMax (String courseName, int courseMax){
    if (courseName.equals(courses[0])){
      students[0] = new Student [courseMax];
    }
    else if (courseName.equals(courses[1])){
      students[1] = new Student [courseMax];
    }
    else if (courseName.equals(courses[2])){
      students[2] = new Student [courseMax];
    }
    else if (courseName.equals(courses[3])){
      students[3] = new Student [courseMax];
    }
  }//end of setCourseMax
  
  /* addStudentToClass method
   * Adds a student to the class
   * @param courseName - name of class
   * @param s - student to add
   */
  public void addStudentToClass(String courseName, Student s){
    if (courseName.equals(courses[0]) && a<students[0].length){
      students[0][a] = s;
      a++;
    }
    else if (courseName.equals(courses[1]) && b<students[1].length){
      students[1][b]=s;
      b++;
    }
    else if (courseName.equals(courses[2])&&c<students[2].length){
      students[2][c]=s;
      c++;
    }
    else if (courseName.equals(courses[3])&& d<students[3].length){
      students[3][d]=s;
      d++;
    }
  }//end of addStudentToClass 
  
/* getStudentName method
 * Retrieves student name
 * @param courseName - name of class
 * @return student name
 */
  public String getStudentName (String courseName){
    if (courseName.equals(courses[0])){
      return students[0][a-1].firstName;
    }
    else if (courseName.equals(courses[1])){
      return students[1][b-1].firstName;
    }
    else if (courseName.equals(courses[2])){
      return students[2][c-1].firstName;
    }
    else{
      return students[2][d-1].firstName;
    }
  }
  
  /* setCourses method
   * Sets the teacher's courses
   * @param courseName - name of class
   * @param a - position in array to add
   */
  public void setCourses (String courseName, int a){
    this.courses[a]=courseName;
  }//end of setCourses
  
  /* getStudents method
   * Returns the specified students array
   * @return students array
   */
  public Student[] getStudents (int a){
    return students[a];
  }
  
 /* getCourse method
  * Returns the teacher's course name
  * @param a - which course in the array to return
  * @return course name
  */
  public String getCourse(int a){
    return courses[a];
  }//end of getCourse
}//end of class