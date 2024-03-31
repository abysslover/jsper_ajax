package com.javawide.book.blackbook1.ch01;
import java.lang.reflect.Method;
import java.util.Vector;

public class TestReflection {
	private String start_day;
	private String yolamana;
	private String test1;
	private int age;
	private float weight;

	public int getAge() {	return age;		}
	public void setAge(int age) {	this.age = age;	}

	public String getStart_day() {	return start_day;	}
	public void setStart_day(String start_day) {	this.start_day = start_day;	}
	
	public String getTest1() {	return test1;	}
	public void setTest1(String test1) {	this.test1 = test1;		}

	public float getWeight() {	return weight;	}
	public void setWeight(float weight) {	this.weight = weight;	}

	public String getYolamana() {	return yolamana;	}
	public void setYolamana(String yolamana) {	this.yolamana = yolamana;	}

	public static Vector<? extends String> getGetSetMethods() {
		Method[] ms = TestReflection.class.getMethods();
		Vector<String> methods = new Vector<String>(8);
		for (Method m : ms) {
			String mName = m.getName();
			if (mName.toLowerCase().contains("get")
					|| mName.toLowerCase().contains("set"))
				methods.add(mName);
		}
		return methods;
	}

	public static void main(String[] args) {
		for (String s : TestReflection.getGetSetMethods()) {
			System.out.println(s);
		}
	}
}
