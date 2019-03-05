/**
 * Principal.java
 * @author Julia Zhao
 * @date March 09 2018
 * Principal class to use for schedule project
 */

public class Principal extends Person {
private String schoolName;
private Teacher [] teachers = new Teacher [10];
private int i=0;

Principal (){
}

/* addTeacherToSchool method
 * Adds a teacher to the school
 * @param teacher - the teacher to be added
 */
public void addTeacherToSchool(Teacher teacher){
  teachers[i] = teacher;
  i++;
}
/* setSchoolName method
 * Sets the school name
 * @param name - the name of the school
 */
public void setSchoolName (String name){
  schoolName=name;
}

/* getTeacherName method
 * Gets the teacher's name
 * @return teacher's name
 */
public String getTeacherName (){
  return teachers[i-1].firstName;
}

/* getTeachers method
 * Gets the teacher array
 * @return teachers array
 */
public Teacher[] getTeachers(){
  return teachers;
}

/* setCourses
 * Required because of the abstract class Person
 */
public void setCourses(String courseName, int a){};

}//end of class
