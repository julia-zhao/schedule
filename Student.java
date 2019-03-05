/**
 * Student.java
 * @author Julia Zhao
 * @date April 04 2018
 * @version 1.0.0
 * Student class to use with schedule project
 */

public class Student extends Person{
  
private int studentNumber=0, courseCounter=0;
private String course1="a", course2="b", course3="c", course4="d";
private String[] choices=new String[6];
private boolean alternate=false;

Student(String firstName, String lastName, String course1, String course2, String course3,
        String course4, int studentNumber){
  this.firstName=firstName;
  this.lastName=lastName;
  this.course1=course1;
  this.course2=course2;
  this.course3=course3;
  this.course4=course4;
  this.studentNumber=studentNumber;
}//end of constructor

Student(){
}//end of constructor

/* setStudentNumber method
 * Sets the student number
 * @param number - student number
 */
public void setStudentNumber(int number){
  this.studentNumber=number;
}//end of setStudentNumber

/* setChoices method
 * Sets the student's choices
 * @param choice - course name
 * @param i - which element in the choices array
 */
public void setChoices(String choice, int i){
  this.choices[i]=choice;
}//end of setChoices

/* getChoices method
 * Gets student's choices
 * @param i - which element to return
 * @return course name
 */
public String getChoices (int i){
  return choices[i];
}//end of getChoices 

/* setAlternate method
 * Changes alternate from false to true
 */
public void setAlternate (){
  this.alternate=true;
}//end of setAlternate

/* getAlternate method
 * Gets the value of alternate
 * @return alternate - false or true
 */
public boolean getAlternate(){
  return this.alternate;
}//end of getAlternate

/* addCourseCounter method
 * Adds 1 to the number of courses already decided for the student
 */
public void addCourseCounter(){
  this.courseCounter++;
}//end of addCourseCounter

/* getCourseCounter method
 * Sets the number of courses already decided for the student
 * @return courseCounter
 */
public int getCourseCounter(){
  return this.courseCounter;
}//end of getCourseCounter

/* setCourses
 * Required because of the abstract class Person
 */
public void setCourses(String courseName, int a){};
}//end of class
