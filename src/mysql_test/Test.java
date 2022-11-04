package mysql_test;
import java.sql.*;
import java.util.Scanner;

public class Test {
	
	public static void main(String args[])
	{
		Scanner input = new Scanner(System.in);
		Connection con = null;
		PreparedStatement pstmt = null;
		int num = 0;
		System.out.print("데이터 삽입: 1, 데이터 삭제: 2, 데이터 검색: 3 을 입력하시오."); // 실행할 sql 선택
		num = input.nextInt();
		
		// db 연결
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(
					"jdbc:mysql://192.168.56.101:4567/madang",
					"yhshin","1234");
		}catch(Exception e){ System.out.println(e);}
	
		// insert 실행
		if(num == 1) {
			
			String sql = "insert into Book values(?, ?, ?, ?)";
		
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "11");
				pstmt.setString(2, "인공지능");
				pstmt.setString(3, "한빛미디어");
				pstmt.setString(4,"20000");
				
				int result = pstmt.executeUpdate();
				if(result==1) {
					System.out.println("insert 성공");
				}
			}catch(Exception e) {
				System.out.println("e");
			} finally {
				try {
					if(pstmt != null && !pstmt.isClosed()) {
						pstmt.close();
					}
				}catch(Exception e2) {}
			}
		}
		// delete 실행
		else if(num == 2) {
			String sql = "delete from Book where bookid=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "11");
				
				int result = pstmt.executeUpdate();
				if(result==1) {
					System.out.println("delete 성공");
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null && !pstmt.isClosed()) {
						pstmt.close();
					}
				}catch(Exception e2) {}
				}
			}
			// select 실행
		else {
			String sql = "select * from Book where bookid=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "22");
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " " + rs.getString(3)+ " " +rs.getInt(4));
				
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null && !pstmt.isClosed()) {
						pstmt.close();
					}
				}catch(Exception e2) {}
				}
			}
			
		}
		
	}

