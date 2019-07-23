package com.pjt.lec23.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.pjt.lec23.member.Member;

//JdbcTemplate로 연결
@Repository
public class MemberDao2 implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver"; // Oracle Jdbc Driver 메모리
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; // localhost의 url
	private String userId = "c##scott"; // DB계정 ID
	private String userPw = "tiger"; // DB계정 PW

	// private DriverManagerDataSource dataSource;	// DriverManagerDataSource 1)c3p0
	private DriverManagerDataSource dataSource; 	// DriverManagerDataSource 2)spring
	// private DriverManagerDataSource dataSource; //DriverManagerDataSource 3)

	private JdbcTemplate template;

	// JdbcTemplate 초기 설정
	public MemberDao2() {
		/**
		 * DriverManagerDataSource 1)c3p0
		 */
//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClass(driver); 	// Driver 메모리 로딩
//		dataSource.setJdbcUrl(url); 		// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : url
//		dataSource.setUser(userId); 		// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : id
//		dataSource.setPassword(userPw); 	// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : pw


		/**
		 * DriverManagerDataSource 2)spring
		 */
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);	// Driver 메모리 로딩
		dataSource.setUrl(url);					// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : url
		dataSource.setUsername(userId);			// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : id
		dataSource.setPassword(userPw);			// DriverManager로부터 접속할 수 있는 연결객체를 가져옴 : pw



		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}

	@Override
	public int memberInsert(final Member member) {
		int result = 0;
		final String sql = "INSERT INTO member(memId, memPw, memMail) values(?,?,?)";

		/**
		 * 1st) template.update(sql, args)
		 */
//		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());


		/**
		 * 2nd) template.update(PreparedStatementCreator)
		 */
//		result = template.update(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				pstmt.setString(3, member.getMemMail());
//
//				return pstmt;
//			}
//		});

		/**
		 * 3rd) template.query(sql, PreparedStatementSetter())
		 */
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
				pstmt.setString(3, member.getMemMail());
			}
		});


		return result;
	}

	@Override
	public Member memberSelect(final Member member) {
		List<Member> members = null;
		final String sql = "SELECT * FROM member WHERE memId=? AND memPw=?";

		/**
		 * 1st) template.query(sql, PreparedStatementSetter, RowMapper)
		 */
//		members = template.query(sql, new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//			}
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});


		/**
		 * 2nd) template.query(PreparedStatementCreator, RowMapper)
		 */
//		members = template.query(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1,  member.getMemId());
//				pstmt.setString(2,  member.getMemPw());
//				return pstmt;
//			}
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});

		/**
		 * 3rd) template.query(sql, RowMapper, args)
		 */
		members = template.query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				return mem;
			}

		}, member.getMemId(), member.getMemPw());

		if(members.isEmpty()) return null;

		return members.get(0);
	}

	@Override
	public int memberUpdate(final Member member) {
		int result = 0;
		final String sql = "UPDATE member SET memPw=?, memMail=?, memPurcNum=? WHERE memId=?";

		/**
		 * 1st) template.update(sql, args)
		 */
//		result = template.update(sql, member.getMemPw(), member.getMemMail(), member.getMemPurcNum(), member.getMemId());


		/**
		 * 2nd) template.update(PreparedStatementCreator)
		 */
//		result = template.update(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemPw());
//				pstmt.setString(2, member.getMemMail());
//				pstmt.setInt(3, member.getMemPurcNum());
//				pstmt.setString(4, member.getMemId());
//				return pstmt;
//			}
//		});


		/**
		 * 3rd) template.query(sql, PreparedStatementSetter())
		 */
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemPw());
				pstmt.setString(2, member.getMemMail());
				pstmt.setInt(3, member.getMemPurcNum());
				pstmt.setString(4, member.getMemId());
			}

		});


		return result;
	}

	@Override
	public int memberDelete(final Member member) {
		int result = 0;
		final String sql = "DELETE member WHERE memId=? AND memPw=?";

		/**
		 * 1st) template.update(sql, args)
		 */
//		result = template.update(sql, member.getMemId(), member.getMemPw());



		/**
		 * 2nd) template.update(PreparedStatementCreator)
		 */
//		result = template.update(new PreparedStatementCreator() {
//
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				return pstmt;
//			}
//		});


		/**
		 * 3rd) template.query(sql, PreparedStatementSetter())
		 */
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		});

		return result;
	}

}
