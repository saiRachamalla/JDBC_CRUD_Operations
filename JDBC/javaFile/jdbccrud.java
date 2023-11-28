package com.java.jdbc;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
class crudop{
	public static void insert(Connection con) throws SQLException {
		String s="insert into employee values(?,?,?)";
		PreparedStatement psmt=con.prepareStatement(s);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter employeeid");
		int tempId=sc.nextInt();
		System.out.println("enter employeename");
		String tempName=sc.next();
		System.out.println("enter employeesalary");
		int tempsalary=sc.nextInt();
		psmt.setInt(1, tempId);
		psmt.setString(2, tempName);
		psmt.setInt(3, tempsalary);
		int rows=psmt.executeUpdate();
		if(rows==0) {
			System.out.println("insertion of employee failed");
		}
		else {
			System.out.println("no of rows inserted id"+rows);
		}
	}
	
	public static void display(Connection con) throws SQLException {
		String s="select * from Employee";
		Statement stmt = con.createStatement();
		ResultSet res = stmt.executeQuery(s);
		while(res.next()) {
			int employeeId=res.getInt(1);
			String employeeName=res.getString(2);
			int employeeSalary=res.getInt(3);
			System.out.println(employeeId+"      "+employeeName+"      "+employeeSalary);
		}
	}
	
	public static void search(Connection con) throws SQLException {
		String s1="select * from Employee where employeeId=?";
		PreparedStatement psmt = con.prepareStatement(s1);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter id");
		int tempId=sc.nextInt();
		psmt.setInt(1, tempId);
		ResultSet res=psmt.executeQuery();
		//print table data
		if(res.next()) {
			int id=res.getInt(1);
			String name=res.getString(2);
			int salary=res.getInt(3);			
			System.out.println(id+"     "+name+"     "+salary);
		}
		else {
			System.out.println("employee details not found");
		}

		//		while(res.next()) {
		//			int id=res.getInt(1);
		//			String name=res.getString(2);
		//			int salary=res.getInt(3);
		//
		//			System.out.println(id+"     "+name+"     "+salary);
		//		}
		//		System.out.println("employee details not found");

	}

	public static void update(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String s="update Employee set employeeName=? where employeeId=?";
		PreparedStatement psmt=con.prepareStatement(s);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter employeeid");
		int tempId=sc.nextInt();
		sc.nextLine();
		System.out.println("enter new employeename");
		String tempName=sc.nextLine();
		psmt.setString(1, tempName);
		psmt.setInt(2, tempId);
		int rows=psmt.executeUpdate();
		System.out.println("rows updated "+rows);
	}
	
	public static void delete(Connection con) throws SQLException {
		// TODO Auto-generated method stub
		String s1="delete from Employee where employeeId=?";
		PreparedStatement psmt1=con.prepareStatement(s1);
		Scanner sc=new Scanner(System.in);
		System.out.println("enter employeeid");
		int tempId=sc.nextInt();
		psmt1.setInt(1, tempId);
		int rows1=psmt1.executeUpdate();
		System.out.println("rows deleted "+rows1);
	}

}
public class jdbccrud {
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con=null;
		Statement stmnt=null;
		ResultSet res=null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Driver loaded succesfully");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gqt", "root", "root");
		System.out.println("connection is successfull");
		int choice=0;
		do {
			System.out.println("1.insert");
			System.out.println("2.display");
			System.out.println("3.search");
			System.out.println("4.update");
			System.out.println("5.delete");

			Scanner sc=new Scanner(System.in);
			System.out.println("enter your choice");
			choice=sc.nextInt();
			switch(choice) {
			case 1:
				crudop.insert(con);
				break;
			case 2:
				crudop.display(con);
				break;
			case 3:
				crudop.search(con);
				break;
			case 4:
				crudop.update(con);
			case 5:
				crudop.delete(con);
				break;
			default:
				System.out.println("invalid input ,enter valid input");
				break;
			}
		}while(choice!=0);
	}
}
