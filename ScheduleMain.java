/*
 * ScheduleMain.java
 * @author Julia Zhao
 * @date March 23 2018
 * @version 1.0.0
 * Schedule project (unit 2)
 */

import java.util.Scanner;
import java.io.*;

public class ScheduleMain{
  /* countLines method
   * Counts the number of lines in the text file (how big to make the array)
   * @param file - the Scanner of the file to read
   * @return numElements - the number of elements to set the array
   */
  public static int countLines (Scanner file){
    int count=0;
    //scan text file
    while (file.hasNext()){
      file.nextLine();
      count++;
    }
    return count;
  }//end of countLines
  
  /* createStudentArray
   * Puts students into a Person array
   * @param count - size to make array
   * @return students - array of students
   */
  public static Person[] createStudentArray (int count) throws FileNotFoundException{
    Person[] students;
      //create an array based on how many students there are in the school
      students = new Person[count];
      int index=0;
      
      //read the students into the array
      Scanner readStudents = new Scanner (new File ("students.txt"));
      while (readStudents.hasNextLine()){ //go through the entire text file  
        String temp=readStudents.nextLine();
        if (temp!=null){ //if line is not empty
          Person newStudent = new Student();
          Scanner line = new Scanner (temp);
          //save information into the student
          newStudent.firstName=line.next();
          newStudent.lastName=line.next();
          ((Student)newStudent).setStudentNumber(line.nextInt());
          for (int i=0; i<6; i++){
            ((Student)newStudent).setChoices(line.next(), i); //ordered choices from top choice to last choice (total 6)
          }
          students[index]=newStudent; //add the student into the array of students
          index++;
          line.close();
        }
      }
      readStudents.close();
    return students;
  } //end of createStudentArray
  
  /* createStaffArray
   * Puts teachers into a Person array
   * @param count - size to make array
   * @param courses - array of courses
   * @return staff - array of principal + teachers
   */
  public static Person [] createStaffArray (int count, String [] courses)throws FileNotFoundException{
    Person [] staff = new Person [count];
      int index=0;
      Scanner readStaff = new Scanner (new File ("staff.txt"));
      
      while (readStaff.hasNextLine()){
        if (index==0){ //first line is always the principal
          Person newPrincipal = new Principal();
          newPrincipal.lastName = readStaff.nextLine();
          staff[index]=newPrincipal; //add principal into the array
        }
        else{
          Person newTeacher = new Teacher(); //create new Person object (Teacher)
          String tempStr = readStaff.nextLine(); //save the next line into tempStr
          Scanner readStr = new Scanner (tempStr); //make a Scanner to read tempStr
          newTeacher.lastName=readStr.next();
          //add course to teacher
          int counter=0;
          while (readStr.hasNext()){
            ((Teacher)newTeacher).setCourses(readStr.next(),counter);
            counter++;
          }
          staff[index]=newTeacher;
          readStr.close();
        }
        index++;
      }//go through the entire text file     
      readStaff.close();
      
      //if there is a class but no teacher teaches this class
      for (int i=0; i<courses.length; i++){
        //find the courses who do not have a set teacher
        int isTaught = getTeacher (courses[i], staff);
        if (isTaught==-1){ //no teacher teaches this course
          Person newTeacher = new Teacher();
          newTeacher.lastName="Placeholder"; //create a placeholder teacher
          ((Teacher)newTeacher).setCourses(courses[i],0);
          staff[index]=newTeacher;
          index++;
        }
      }
      return staff;
  }//end of createTeacherArray
  
  /* getTeacher method
   * Returns which Person in the staff array is the relevant teacher
   * @param courseName - name of the course to find the teacher for
   * @param staff - array of staff
   * @return whichTeacher - position in the staff array that contains the relevant teacher
   */
  public static int getTeacher(String courseName, Person[] staff){
    int whichTeacher=-1;
    for (int j=1; j<staff.length; j++){ //goes through the staff array
      if (staff[j]!=null){
        for (int k=0; k<4; k++){ //goes through the four courses of the teacher
          if (((Teacher)staff[j]).getCourse(k)!=null&&((Teacher)staff[j]).getCourse(k).equals(courseName)){
            whichTeacher=j;
          }
        }
      }
    }
    return whichTeacher;
  }//end of getTeacher
  
  /* organizeSchedule method
   * Sorts students into classes on a first come first serve basis
   * @param students - array of students
   * @param staff - array of staff
   * @param courses - array of course names
   * @param courseMax - max number of students per course
   * @param numStudentsInCourse - current number of students in each course
   */
 public static void organizeSchedule (Person [] students, Person [] staff, 
                                       String [] courses, int [] courseMax, int [] numStudentsInCourse){
    int whichTeacher=1;
    boolean assignAlternates=false;
    for (int i=0; i<courses.length; i++){ //go through list of courses
      //find the teacher than teaches this course
      whichTeacher = getTeacher(courses[i], staff);
      
      for (int j=0; j<students.length; j++){ //go through list of students
        for (int k=0; k<4; k++){ //only look at first 4 choices (not alternates)
          //if the student chose this course at one of their top 4
          if (((Student)students[j]).getChoices(k).equals(courses[i])){
            //if the course is not full
            if (numStudentsInCourse[i]<courseMax[i]){
              //add student to the teacher who teaches this class
              ((Teacher)staff[whichTeacher]).addStudentToClass(courses[i], (Student)students[j]);
              ((Student)students[j]).addCourseCounter();
              numStudentsInCourse[i]++;
            }
            else{//course is full
              assignAlternates=true; //the student was not given top 4 choices
              ((Student)students[j]).setAlternate();
            }
          }
        }
      } 
    }
    
    if (assignAlternates==true){
      for (int i=0; i<courses.length; i++){ //go through list of courses
        //find the teacher than teaches this course
        whichTeacher = getTeacher(courses[i], staff);
        
        for (int j=0; j<students.length; j++){ //go through list of students
          if (((Student)students[j]).getAlternate()==true && ((Student)students[j]).getCourseCounter()<4){ //if student needs more courses
            for (int k=4; k<6; k++){ //only look at alternate choices
              if (((Student)students[j]).getChoices(k).equals(courses[i]) && numStudentsInCourse[i]<courseMax[i]){
                //add student to the teacher who teaches this class
                ((Teacher)staff[whichTeacher]).addStudentToClass(courses[i], (Student)students[j]);
                ((Student)students[j]).addCourseCounter();
                numStudentsInCourse[i]++;
              }
            }
          }
        }
      }
    }
  }//end of organizeSchedule method
  
  public static void main (String [] args){
    System.out.println ("Welcome to the Schedule Creator!");
    Person[] students= new Person[0]; //create a person array to hold students
    Person [] staff = new Person[0]; //create a person array to hold principal + teachers
    String [] courses = new String [0]; //array of course names
    int [] courseMax = new int [0]; //max number of students in each course
    int [] numStudentsInCourse = new int [0]; //number of students already in the course
    
    try{ //in case files are missing
      Scanner countStudents = new Scanner (new File ("students.txt"));
      students = createStudentArray(countLines(countStudents));
      
      Scanner countCourses = new Scanner (new File("courses.txt"));
      int courseNumber=countLines(countCourses);
      courses = new String [courseNumber];
      courseMax = new int [courseNumber];
      numStudentsInCourse = new int [courseNumber];
      
      //add courses into the array
      Scanner readCourses=new Scanner (new File("courses.txt"));
      for (int i=0; i<courses.length; i++){
        courses[i] = readCourses.next(); //course name
        courseMax[i] = readCourses.nextInt(); //max number of students in the course
      }
      readCourses.close();
      
      Scanner countStaff = new Scanner (new File ("courses.txt")); 
      staff = createStaffArray((countLines(countStaff)+1), courses); //max amount of staff = # courses + 1 (principal)
    //initialize the course arrays of the teachers
    int whichTeacher;
    for (int i=0; i<courses.length; i++){//go through all the courses
      whichTeacher = getTeacher(courses[i], staff);
      ((Teacher)staff[whichTeacher]).setCourseMax(courses[i], courseMax[i]);
    }
    
    //Organize the schedule
    organizeSchedule(students, staff, courses, courseMax, numStudentsInCourse);
    
    //Print results to file
    File outFile = new File ("schedule.txt");
    PrintWriter printOut = new PrintWriter(outFile);
    
    printOut.print ("PRINCIPAL\r\n"+staff[0].lastName + "\r\n--------");

    for (int i=0; i<courses.length; i++){ //loop through list of courses
      printOut.print ("\r\nCOURSE\r\n"+courses[i] + "\r\n");
      boolean foundTeacher=false;
      int count=1, whichCourse=0;
        //finds the teacher who teaches that course
        while (foundTeacher==false){
          for (int j=0; j<4; j++){
            //if the teacher's course name is the same as the current course in the for loop
            if (((Teacher)staff[count]).getCourse(j)!=null&&((Teacher)staff[count]).getCourse(j).equals(courses[i])){
              foundTeacher=true;
              whichCourse=j;
            }
          }
          count++;
        }
        //print out teacher name
        printOut.print ("\r\nTEACHER\r\n"+staff[count-1].lastName+"\r\n"); 
        printOut.println ("\r\nSTUDENTS");
        
        //print out the list of students in the class
        if (((Teacher)staff[count-1]).getStudents(whichCourse)[0]==null){
          //no students in the class
          printOut.print ("None\r\n");
        }
        else{
          for (int d=0; d<courseMax[i]; d++){ //goes through the array of students for the course
            if (((Teacher)staff[count-1]).getStudents(whichCourse)[d]!=null){
              //print out student's name
              printOut.print (((Teacher)staff[count-1]).getStudents(whichCourse)[d].firstName + " " +
                              ((Teacher)staff[count-1]).getStudents(whichCourse)[d].lastName+"\r\n");
            }
          }
        }
      printOut.print ("---------");
    }
    printOut.close();
    System.out.println ("Schedule successfully printed out to text file! Program finished.");
    }
    catch (Exception e){ //files are missing
      System.out.println ("ERROR. Cannot create schedule.");
    }
  } //end of main
} //end of class