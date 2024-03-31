package com.javawide.book.blackbook1.ch11.board;


public class TSQLBoard {
	IBehavior behavior = new NewWriting();
	public enum BEHAVIOR { newwrite, listwrite, updatewrite, deletewrite, listcontent }
	public enum BEHAVIOR_CLASS { NewWriting, GetWriting, UpdateWriting, DeleteWriting, GetContent }
	public String toString() {
		behavior.execute();
		return behavior.toString();
	}
	public void setBehavior(IBehavior behavior) {
		this.behavior = behavior;
	}
	public static void main(String[] args) {
		TSQLBoard board = new TSQLBoard();
		GetWriting writing = new GetWriting();
		writing.setSearchtype("writer");
		writing.setKeyword("Storm");
		board.setBehavior(writing);
		System.out.println(board);
	}
}
