package uestc.learning.entity;

public class Grade {

	private String sid;
	private String cid;
	private int score;
	private String note;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Grade [sid=" + sid + ", cid=" + cid + ", score=" + score + ", note=" + note + "]";
	}
	
	
}	
