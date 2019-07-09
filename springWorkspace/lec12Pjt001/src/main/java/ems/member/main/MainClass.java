package ems.member.main;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

import ems.member.Student;
import ems.member.StudentAssembler;
import ems.member.service.EMSInformationService;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

public class MainClass {

	public static void main(String[] args) {
		String[] sNums = { 	"H39r8djakndfae32", "H39asdfaelu42o23", "H39iiemamca8w9h4",
							"H39lkmn754fghia7",	"H39plo865cuy8k92", "H39mnbviiaed89q1",
							"H399omjjyv56t3d5", "H39lczaqwg644gj8", "H39ymbcsh74thgh2",
							"H39lesvj7544vf89" };

		String[] sIds = { 	"rabbit", "hippo", "raccoon", "elephant", "lion",
							"tiger", "pig", "horse", "bird", "deer" };

		String[] sPws = { 	"96539", "94875", "15284", "48765", "28661",
							"60915", "30028", "29801", "28645", "28465" };

		String[] sNames = { "agatha", "barbara", "chris", "doris", "elva",
							"fiona", "holly", "jasmin", "lena", "melissa" };

		int[] sAges = { 19, 22, 20, 27, 19, 21, 19, 25, 22, 24 };
		String[] sGenders = { "M", "W", "W", "M", "M", "M", "W", "M", "W", "W" };
		String[] sMajors = { "English Literature", "Korean Language and Literature",
							 "French Language and Literature", "Philosophy", "History",
							 "Law", "Statistics", "Computer", "Economics", "Public Administration" };

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		EMSInformationService informationService = ctx.getBean("informationService", EMSInformationService.class);
		informationService.outputEMSInformation();

		StudentRegisterService registerService = ctx.getBean("registerService", StudentRegisterService.class);
		for (int i = 0; i < sNums.length; i++) {
			Student student = new Student(sNums[i], sIds[i], sPws[i], sNames[i], sAges[i], sGenders[i], sMajors[i]);
			registerService.register(student);
		}

		StudentModifyService modifyService = ctx.getBean("modifyService", StudentModifyService.class);
		modifyService.modify(new Student("H39lesvj7544vf89", "deer", "00000", "melissa", 26, "W", "Vocal Music"));

		StudentSelectService selectService = ctx.getBean("selectService", StudentSelectService.class);
		Student modifiedStudent = selectService.select("H39lesvj7544vf89");
		System.out.println(modifiedStudent.toString());
		System.out.println();

		StudentAllSelectService allSelectService = ctx.getBean("allSelectService", StudentAllSelectService.class);
		Map<String, Student> allStudent = allSelectService.allSelect();
		Set<String> keys = allStudent.keySet();
		Iterator<String> iterator = keys.iterator();

		while (iterator.hasNext()) {
			String key = iterator.next();
			Student student = allStudent.get(key);
			System.out.println(student.toString());
		}

		while (true) {
			Scanner scanner = new Scanner(System.in);
			String str = "";

			System.out.println("\n==================================================================="
					+ "==============================================================================");
			System.out.println("Select number.");
			System.out.println("1. Check student information");
			System.out.println("2. Exit");

			str = scanner.next();
			if (str.contentEquals("2")) {
				System.out.println("Bye");
				break;
			} else {
				System.out.println("Please input your class number.");

				str = scanner.next();
				Student student = selectService.select(str);
				System.out.println(student.toString());
			}
		}

		ctx.close();

	}

}
