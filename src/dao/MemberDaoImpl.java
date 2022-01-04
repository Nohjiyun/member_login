package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import com.njy.util.Util;

import dto.MemberDto;

public class MemberDaoImpl implements MemberDao {
	private Connection conn; // DB 커넥션 연결 객체
	static String[] code = Util.readLineFile("C:/Users/pc/Desktop/eclipse_workspace/config.txt").split("\\n");

	// 생성자에 연결
	public MemberDaoImpl() {
		// 디비 연결 부분
		String USERNAME = code[0];//DBMS접속 시 아이디
    	String PASSWORD = code[1];//DBMS접속 시 비밀번호
    	String URL = code[2];//DBMS접속할 DB명
        try {
            System.out.println("생성자");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패 ");
            e.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e1) {    }
        }
	}

	@Override
	public void insert(MemberDto dto) {
		String sql = "INSERT INTO member (name, userid, userpw, major, email) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getUserpw());
			pstmt.setString(4, dto.getMajor());
			pstmt.setString(5, dto.getEmail());

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입 성공!");
				UUID uid = UUID.randomUUID();   // 가입 성공하면 고유값 생성
				sql = "UPDATE member SET tok = ? WHERE  userid = ? AND userpw = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uid.toString());
				pstmt.setString(2, dto.getUserid());
				pstmt.setString(3, dto.getUserpw());
				pstmt.executeUpdate();
				System.out.println("이값을 꼭 파일에 저장: " + uid.toString());
			}

		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}

	}
	
	public void insert(String name, String id, String pw) {
		String sql = "INSERT INTO member (name, userid, userpw) VALUES (?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입 성공!");
			}

		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}

	}
	
	public void insert(String name, String id, String pw, String major, String email) {
		String sql = "INSERT INTO member (name, userid, userpw, major, email) VALUES (?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			pstmt.setString(4, major);
			pstmt.setString(5, email);

			int result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입 성공!");
			}

		} catch (Exception e) {
			System.out.println("데이터 삽입 실패!");
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}

	}

	@Override
	public MemberDto selectOne(int n) {
		String sql = "SELECT * FROM member WHERE num = ?";
		MemberDto dto = new MemberDto();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setMajor(rs.getString("major"));
				dto.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {
			System.out.println("SELECT 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return dto;
	}
	
	public int selectNameEmail(String name, String email) {
		String sql = "SELECT num FROM member WHERE name = ? AND email = ?";
		int num = 0;
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (Exception e) {
			System.out.println("SELECT 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return num;
	}

	@Override
	public ArrayList<MemberDto> selectAll() {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "SELECT * FROM member";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setMajor(rs.getString("major"));
				dto.setEmail(rs.getString("email"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.out.println("SELECT 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return list;
	}

	@Override
	public void update(MemberDto dto) {
		String sql = "UPDATE member SET email = ? WHERE  num = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setInt(2, dto.getNum());
			pstmt.executeUpdate();
//			System.out.println("수정된 id: " + id);

		} catch (Exception e) {
			System.out.println("update 예외 발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
	}
	// 로그인
	public boolean login(String id, String pw) {
		boolean isSuccess = false;
		String sql = "SELECT * FROM member WHERE userid = ? AND userpw = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
//				System.out.println(rs.getString("userid"));
				isSuccess = true;
			}

		} catch (Exception e) {
			System.out.println("SELECT 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return isSuccess;
	}
	
	public String getTok(String userid) {
		String sql = "SELECT tok FROM member WHERE userid = ?";
		String retStr = "";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				retStr = rs.getString("tok");
			}

		} catch (Exception e) {
			System.out.println("SELECT 메서드 예외발생");
		} finally {
			try {
				if (pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
			} catch (Exception e2) {
			}
		}
		return retStr;
	}
	

}











