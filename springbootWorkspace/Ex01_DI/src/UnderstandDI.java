import java.util.Date;

public class UnderstandDI {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("강한 결합 : " + date);
		
		getDate(date);
	}

	public static void getDate(Date d) {
		Date date = d;
		System.out.println("약한 결합 : " + date);
	}

	// 강한 결합 : 직접 생성
	public static void memberUse1() {
		Member m1 = new Member();
	}

	// 약한 결합 : 생성된 것을 주입 받음 - 의존주입(Dependency Injection)
	public static void memberUse2(Member m) {
		Member m2 = m;
	}
}

//Member를 사용한다 => Member의 기능에 의존한다 라는 의미
class Member {
	String name;
	String nickname;
	public Member() {}
}