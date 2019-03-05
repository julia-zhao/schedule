/**
 * Person.java
 * @author Julia Zhao
 * @date March 02 2018
 * @version 1.0.0
 * Abstract class Person to use for the schedule project
 */

public abstract class Person {
  String firstName="none",lastName="none",streetAddress="none",postalCode="none",phoneNumber="none",email="none";
  
  public Person(){ //first constructor
  }
  
  public Person(String firstName, String lastName){
  }
  
  //abstract method
  public abstract void setCourses(String courseName, int a);
}
