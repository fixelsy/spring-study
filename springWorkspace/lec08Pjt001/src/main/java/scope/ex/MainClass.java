package scope.ex;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");


		InjectionBean injectionBean = ctx.getBean("injectionBean", InjectionBean.class);

		//reference가 달라도 생성된 한개의 객체를 가져다가 사용한다.
		DependencyBean dependencyBean1 = ctx.getBean("dependencyBean", DependencyBean.class);
		DependencyBean dependencyBean2 = ctx.getBean("dependencyBean", DependencyBean.class);

		if (dependencyBean1.equals(dependencyBean2)) {
			System.out.println("dependencyBean1 == dependencyBean2");
		} else {
			System.out.println("dependencyBean1 != dependencyBean2");
		}
		System.out.println("================================================================");

		//bean객체 생성시 scope="prototype"을 선언하면 매번 다른 객체를 생성한다.
		DependencyBean dependencyBeanProto1 = ctx.getBean("dependencyBeanProto", DependencyBean.class);
		DependencyBean dependencyBeanProto2 = ctx.getBean("dependencyBeanProto", DependencyBean.class);

		if (dependencyBeanProto1.equals(dependencyBeanProto2)) {
			System.out.println("dependencyBeanProto1 == dependencyBeanProto2");
		} else {
			System.out.println("dependencyBeanProto1 != dependencyBeanProto2");
		}
	}

}
