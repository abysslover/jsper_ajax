

import java.util.*;

class Hello {
	private String greetMessage;
	public Hello(String message) {
		this.greetMessage = message;
	}	
	public String getGreetMessage() {
		return greetMessage;
	}
	public int getLength() {
		return greetMessage.length();
	}
}

class 객체타입 {
}

class 어떤Comparator implements Comparator<객체타입> {
	public int compare(객체타입 obj0, 객체타입 obj1) {
		if(obj0.hashCode() < obj1.hashCode()) {
			return -1;
		} else if(obj0 == obj1) {
			return 0;
		} else {
			return 1;
		}
	}
}

class MessageComparator implements Comparator<Hello> {
	public int compare(Hello obj0, Hello obj1) {
		return obj0.getGreetMessage().compareTo(obj1.getGreetMessage());
	}
}

class LengthComparator implements Comparator<Hello> {
	public int compare(Hello obj0, Hello obj1) {
		if(obj0.getLength() < obj1.getLength()) {
			return -1;
		} else if(obj0.getLength() == obj1.getLength()) {
			return 0;
		} else return 1;
	}
}

public class HelloComparatorTest {

	public static void main(String[] args) {
		
		Vector<Hello> hellos = new Vector<Hello>(10);
		hellos.add(new Hello("새해 복 많이 받으세요."));
		hellos.add(new Hello("Happy New Year"));
		// 스페인어
		hellos.add(new Hello("Feliz Año Nuevo"));
		// 일본어
		hellos.add(new Hello("明けましておめでとう"));
		// 독일어
		hellos.add(new Hello("glückliches neues Jahr"));
		// 이탈리아어
		hellos.add(new Hello("nuovo anno felice"));
		// 중국어
		hellos.add(new Hello("新年好"));
		// 프랑스어
		hellos.add(new Hello("nouvelle année heureuse"));
		// 포르투갈어
		hellos.add(new Hello("ano novo feliz"));
		// 러시아어
		hellos.add(new Hello("S Novim Godom"));
		// 아랍어
		hellos.add(new Hello("Kulu Sanah('aammu) wa antoum bi-khair"));
		for(Hello hello : hellos) {
			System.out.println(hello.getGreetMessage());
		}
		
		System.out.println("======================== 가나다라 순서 비교 ======================");
		Collections.sort(hellos, new MessageComparator());
		for(Hello hello : hellos) {
			System.out.println(hello.getGreetMessage());
		}
		
		System.out.println("========================== 길이 비교  ===========================");
		Collections.sort(hellos, new LengthComparator());
		for(Hello hello : hellos) {
			System.out.println(hello.getGreetMessage());
		}
	}
}
