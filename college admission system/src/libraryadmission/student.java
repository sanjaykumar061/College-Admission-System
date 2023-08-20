/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryadmission;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class student
{
    
    private String name;
    private String password;
    private String course;
    private String city;
    private int cutoff;
    
    public void getStudentDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your Name");
        name = input.nextLine();
        System.out.println("Enter your password");
        password = input.nextLine();
        System.out.println("Enter your course");
        course = input.nextLine();
        System.out.println("Enter your city");
        city = input.nextLine();
        System.out.println("Enter the cutoff");
        cutoff = input.nextInt();
    }
    
    public void insertStudent() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        //here we are going to work with a database
        //we need to open a database connection
        try{
        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/project","root","2002");
        Connection con = dbmsconnect.getConnection();
        String sql = "insert into student values (?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setString(2, password);
        stmt.setString(3, course);
        stmt.setString(4,city );
        stmt.setInt(5,cutoff);
        int i = stmt.executeUpdate();
        System.out.println("Record  inserted successfully");
        dbmsconnect.closeConnection(con, stmt);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void updateStudentPassword() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        try{
        dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/project","root","2002");
        Connection con = dbmsconnect.getConnection();
        System.out.println("Enter Your Name");
        Scanner input = new Scanner(System.in);
        String inputname=input.nextLine();
        System.out.println("Enter the new Password");
        String inputpass=input.nextLine();
        String sql = "update student set password = ? where name = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, inputpass);
        stmt.setString(2, inputname);
        int i = stmt.executeUpdate();
        if(i>0)
        {
            System.out.println("Record updated sucessfully");
        }else
        {
            System.out.println("No Such record in the Database");
        }
        dbmsconnect.closeConnection(con, stmt);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


public void deleteStudentRecord() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    try{
    dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/project","root","2002");
    Connection con = dbmsconnect.getConnection();
    System.out.println("Enter the Name of the Student");
    Scanner input = new Scanner(System.in);
    String inputname=input.nextLine();
    String sql = "delete from student where name = ?;";
    PreparedStatement stmt = con.prepareStatement(sql);
    stmt.setString(1, inputname);
    int i = stmt.executeUpdate();
    if(i>0)
    {
        System.out.println("Record Deleted Successfully");
    }
    else
    {
        System.out.println("No Such Record in the Database");
    }
    dbmsconnect.closeConnection(con, stmt);
}

catch(Exception e){
    System.out.println(e.getMessage());
}
}

public void searchStudent() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    try{
    dbmsconnection dbmsconnect = new dbmsconnection("jdbc:mysql://localhost:3306/project","root","2002");
    Connection con = dbmsconnect.getConnection();
    System.out.println("Enter Your Name");
    Scanner input = new Scanner(System.in);
    String inputname=input.nextLine();
    String sql = "select * from student where name=?";
    PreparedStatement stmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
    stmt.setString(1, inputname);
    ResultSet rs = stmt.executeQuery();
    if(rs.next()==false)
    {
        System.out.println("No such record found in the database");
    }
    else
    {    rs.previous();
        while(rs.next()){
        System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
        }
    }
    dbmsconnect.closeConnection(con, stmt);
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
}
}