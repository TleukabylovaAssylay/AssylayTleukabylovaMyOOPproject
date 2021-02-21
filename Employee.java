package Miniproject;

import java.sql.*;
import java.util.Scanner;
public class Employee{
    public static void main(String[] args) {
try{
    int a=0;
    crud c = new crud();
    System.out.println("\n 1-Select Table \n 2-Update salary \n 3-Delete Employee id \n 4-Sum of Salary");
    Scanner  ain=new Scanner(System.in);
    a=ain.nextInt();
    switch(a){
        case 1:
        c.selectEmployee();
        break;
        case 2:
        c.updateEmployee();
        break;
        case 3:
        c.deleteEmployee();
        case 4:
        c.sumSalaryEmployee();
        default:
        System.out.println();
        break;
    }
}
catch(Exception e){
    System.out.println(e);
}
    }
}

class crud{
    private int Employee_id;
    private String FName;
    private String LName;
    private int Salary;
    private String Position;
  public void selectEmployee(){
      try{
        database databaseconncet= new database("jdbc:postgresql://localhost:5432/Javaass5","postgres","1234");
        Connection con= databaseconncet.getConnection();
        System.out.println("Enter  Employee id:");
        Scanner input= new Scanner(System.in);
        int inputid= input.nextInt();
        String sql="SELECT * FROM Employee WHERE Employee_id = ? ; ";
        PreparedStatement stmt= con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        stmt.setInt(1, inputid);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()==false){
            System.out.println("Table do not have this id");
        }
        else{
            rs.previous();
        while (rs.next()){
        System.out.println(rs.getInt("Employee_id") + " " + rs.getString("FName") + " " + rs.getString("LName")+" "+rs.getInt(Salary)+" "+ rs.getString(Position));
      }
    }
    databaseconncet.closeConnection(con, stmt);
}
      catch(Exception e){
        System.out.println(e);
    } 
  }
    public void updateEmployee(){
        try{
        database databaseconncet= new database("jdbc:postgresql://localhost:5432/Javaass5","postgres","1234");
        Connection con= databaseconncet.getConnection();
        Scanner input= new Scanner(System.in);
        System.out.println("Enter  Employee id:");
        int inputid= input.nextInt();
        System.out.println("Enter  Salary:");
        int inputsalary= input.nextInt();
        String sql="UPDATE Employee SET Salary=? WHERE Employee_id = ? ; ";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setInt(1, inputsalary);
        stmt.setInt(2, inputid);
        int x = stmt.executeUpdate();
        if(x>0){
            System.out.println("Update passed successfully");  
        }
        else{
            System.out.println("Update is not passed successfully"); 
        }
        databaseconncet.closeConnection(con, stmt);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void deleteEmployee(){
        try{
        database databaseconncet= new database("jdbc:postgresql://localhost:5432/Javaass5","postgres","1234");
        Connection con= databaseconncet.getConnection();
        Scanner input= new Scanner(System.in);
        System.out.println("Enter  Employee id:");
        int inputid= input.nextInt();
        String sql="DELETE FROM Employee WHERE Employee_id = ?; ";
        PreparedStatement stmt= con.prepareStatement(sql);
        stmt.setInt(1, inputid);
        int x = stmt.executeUpdate();
        if(x>0){
            System.out.println("Delete passed successfully");  
        }
        else{
            System.out.println("Delete is not passed successfully"); 
        }
        databaseconncet.closeConnection(con, stmt);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public void sumSalaryEmployee(){
        try{
          database databaseconncet= new database("jdbc:postgresql://localhost:5432/Javaass5","postgres","1234");
          Connection con= databaseconncet.getConnection();
          String sql="SELECT SUM(Salary) FROM Employee";
          PreparedStatement stmt= con.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
          while (rs.next()){
          System.out.println(rs.getInt("Employee_id") + " " + rs.getString("FName") + " " + rs.getString("LName")+" "+rs.getInt(Salary)+" "+ rs.getString(Position));
    }
      databaseconncet.closeConnection(con, stmt);
  }
        catch(Exception e){
          System.out.println(e);
      } 
    }
}
class database{
    String url;
    String username;
    String password;

    public database(String url, String username, String password) {
        this.url = "jdbc:postgresql://localhost:5432/Javaass5";
        this.username = "postgres";
        this.password = "1234";
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con=null;
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection(url, username, password);
        System.out.println("The connection is established" );
        return con;
    
}
   public void closeConnection(Connection con, Statement stmt) {
        try {
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException throwables) {
            throwables.printStackTrace();
        }
    }
    } 
