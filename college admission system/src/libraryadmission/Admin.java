/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryadmission;
import java.sql.*;
import java.util.Scanner;
public class Admin {
    
     public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        
        try
        {    int choice=0;
            student s = new student();
            do
            {
                System.out.println("Select an operation \n 1- Registration \n 2- Password Update \n 3- Delete ur application \n 4- Search your application \n 5- Exit");
                Scanner choicein = new Scanner(System.in);
                choice=choicein.nextInt();
                switch(choice)
                {
                    case 1:
                        s.getStudentDetails();
                        s.insertStudent();
                        break;
                    case 2:
                        s.updateStudentPassword();
                        break;
                    case 3:
                        s.deleteStudentRecord();
                        break;
                    case 4:
                        s.searchStudent();
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Select the correct choice");
                }
            }while(choice!=5);
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }        
     }
}

