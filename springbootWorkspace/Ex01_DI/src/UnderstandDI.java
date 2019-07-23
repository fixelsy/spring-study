import java.util.Date;

public class UnderstandDI {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println("���� ���� : " + date);
		
		getDate(date);
	}

	public static void getDate(Date d) {
		Date date = d;
		System.out.println("���� ���� : " + date);
	}

	// ���� ���� : ���� ����
	public static void memberUse1() {
		Member m1 = new Member();
	}

	// ���� ���� : ������ ���� ���� ���� - ��������(Dependency Injection)
	public static void memberUse2(Member m) {
		Member m2 = m;
	}
}

//Member�� ����Ѵ� => Member�� ��ɿ� �����Ѵ� ��� �ǹ�
class Member {
	String name;
	String nickname;
	public Member() {}
}