package com.javawide.book.blackbook1.ch11.board;

import com.javawide.book.blackbook1.ch11.board.TSQLBoard.BEHAVIOR;
import com.javawide.book.blackbook1.ch11.board.TSQLBoard.BEHAVIOR_CLASS;


public class BoardBehaviorFactory {
	public static void main(String[] args) {
		IBehavior behavior = BoardBehaviorFactory.getInstance("newwrite");
		System.out.println(behavior);
	}

	public static IBehavior getInstance(String behavior) {
		try {
			return (IBehavior)findClass(behavior).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new NewWriting();
	}

	private static Class<?> findClass(String behavior) {
		String className = findClassName(behavior);
		String packageName = BoardBehaviorFactory.class.getPackage().getName();
		try {
			return BoardBehaviorFactory.class.getClassLoader().loadClass(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String findClassName(String behavior) {
		BEHAVIOR candidate = BEHAVIOR.listwrite;
		for(BEHAVIOR b : BEHAVIOR.values()) {
			if(b.toString().equals(behavior)) {
				candidate = b;
				break;
			}
		}
		for(BEHAVIOR_CLASS c : BEHAVIOR_CLASS.values()) {
			if(c.ordinal() == candidate.ordinal()) {
				return c.toString();
			}
		}
		return "NewWriting";
	}
}
