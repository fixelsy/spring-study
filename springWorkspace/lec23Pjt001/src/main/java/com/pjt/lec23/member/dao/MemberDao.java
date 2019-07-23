package com.pjt.lec23.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.pjt.lec23.member.Member;

//JDBC로 연결
@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";	//Oracle Jdbc Driver 메모리
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";	//localhost의 url
	private String userId = "c##scott";							//DB계정 ID
	private String userPw = "tiger";							//DB계정 PW

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Override
	public int memberInsert(Member member) {
		int result = 0;

		try {

			//Driver 메모리 로딩
			Class.forName(driver);

			//url과 DB사용자 id, pw를 가지고 DriverManager로부터 접속할 수 있는 연결객체를 가져옴
			conn = DriverManager.getConnection(url, userId, userPw);

			//실제 실행할 Query문을 PreparedStatement로 작성
			String sql = "INSERT INTO member(memId, memPw, memMail) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			pstmt.setString(3, member.getMemMail());

			//Query질의 -> result:성공 횟수
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
					//자원 해제
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return result;
	}

	@Override
	public Member memberSelect(Member member) {
		Member mem = null;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "SELECT * FROM member WHERE memId=? AND memPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String memId = rs.getString("memId");
				String memPw = rs.getString("memPw");
				String memMail = rs.getString("memMail");
				int memPurcNum = rs.getInt("memPurcNum");

				mem = new Member();
				mem.setMemId(memId);
				mem.setMemPw(memPw);
				mem.setMemMail(memMail);
				mem.setMemPurcNum(memPurcNum);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mem;
	}

	@Override
	public int memberUpdate(Member member) {
		int result = 0;

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "UPDATE member SET memPw=?, memMail=?, memPurcNum=? WHERE memId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemPw());
			pstmt.setString(2, member.getMemMail());
			pstmt.setInt(3, member.getMemPurcNum());
			pstmt.setString(4, member.getMemId());
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public int memberDelete(Member member) {
		int result = 0;

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, userId, userPw);
			String sql = "DELETE member WHERE memId=? AND memPw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPw());
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}
