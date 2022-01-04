package member;

import java.util.ArrayList;
import java.util.Scanner;

import com.njy.util.Util;

import dao.MemberDao;
import dao.MemberDaoImpl;
import dto.MemberDto;

public class MainClass {

	public static void main(String[] args) {
		System.out.println("Hello! It's MemberDB");
		// 멤버 정보 DB 활용
		

		// 파일에 있는 데이터를 읽어오기
		String filePath = "C:/Users/pc/Desktop/eclipse_workspace/전공정보.txt";
		Member member = new Member();
		ArrayList<MemberDto> fList = member.getMemberList(filePath);
//		for (MemberDto d : list) {
//			System.out.println(d.getName());
//		}
				
		// 디비 연결
		MemberDaoImpl dao = new MemberDaoImpl();
//		System.out.println("쉼표로 구분되는 데이터 갯수: " + spData.length);
		// 읽어온 데이터를 DB에 넣기
		dao.insert("이름이", "aid", "1234");
				
				
				// 데이터 보기
		MemberDto dto = dao.selectOne(5);
		System.out.println(dto.getName());
				
		// 전체 데이터 보기
		ArrayList<MemberDto> dList = dao.selectAll();
				
		// 데이터 수정
		// dto에 수정할 데이터 셋팅
//		MemberDto updateDto = new MemberDto();
		// 수정해야 할 이메일
//		updateDto.setEmail("sumin@gmail.com");
		// 어떤 데이터를 변경해야 되는지 알아야 된다
//		updateDto.setNum(dao.selectNameEmail("유수민", "na"));
//		dao.update(updateDto);   // 셋팅된 dto 전달
				
				// 변경된 내용 확인
//		dto = dao.selectOne(updateDto.getNum());
//		System.out.println(dto.getName());
//		System.out.println(dto.getMajor());
//		System.out.println(dto.getEmail());
				
		// 1. 회원 가입
		// 이름, 전공, 이멜,  패스워드
		dto.setName("나는비");
		dto.setUserid("bbb");
		dto.setUserpw("1234");
		dto.setMajor("전공공");
		dto.setEmail("bbb@aaa.com");
		dao.insert(dto);
		// 다 입력하면 회원 가입이 완료 되었습니다 메세지 출력
		// 2. 로그인
		// 이멜, 패스를 입력 하면 만약 맞다면 로긴 성공 / 틀리면 실패
		// 성공하면 회원 정보 출력
		Scanner sc = new Scanner(System.in);
		System.out.print("id 입력: ");
		String id = sc.next();
		System.out.print("pw 입력: ");
		String pw = sc.next();
		if (dao.login(id, pw)) {
			System.out.println("로긴 성공");
			for (MemberDto d : dList) {
				System.out.println(d.getNum() + " " + d.getName() + " " + d.getMajor() + " " + d.getEmail());
//				System.out.println(d.getName());
//				System.out.println(d.getMajor());
//				System.out.println(d.getEmail());
			}
		} else {
			System.out.println("아이디 또는 패스워드 확인");
		}
				
		filePath = "C:/Users/pc/Desktop/eclipse_workspace/mytok.txt";
		String myTok = Util.readLineFile(filePath);
		myTok = myTok.replaceAll("\n", "");   // 개행 문자 제거
		String dbTok = dao.getTok(id);
		System.out.println(myTok);
		System.out.println(dbTok);
		if (myTok.equals(dbTok)) {
			System.out.println("로그인 성공!!!!!!!");
		} else {
			System.out.println("실패!!!!!!!!!!!");
		}
				// 3. 회원정보 수정(패스워드)
				// 회원 정보가 출력 되는 것을 보고 그 번호의 패스워드를 변경을 해보자
				
				// 로그인 기능을 활용해서 로그인 성공한 유저는 네이버 검색 프로그램을 할 수 있게 해보자
				
				
				// 네이버 검색어 DB저장을 한번 해보자
				// 토요일 예고(특집)
				// 멜론 음악 100을 우리 디비로 가지고 와 볼까?		
				
	}

}
