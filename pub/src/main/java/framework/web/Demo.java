package framework.web;

public class Demo {
	public static void main(String[] args) {
		String a = "aaa";
		String b = new Test().set(a);
		//System.out.println(a);

		User user = new User();
		user.setAge("11");
		new Test1().changeUser(user);
		System.out.println(user.getAge());

	}

}
