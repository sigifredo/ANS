package grammarp;

public class Production {
	String noterminal;
	String rightpart;
	public Production(String noterminal, String rightpart) {
		super();
		this.noterminal = noterminal;
		this.rightpart = rightpart;
	}
	public String getNoterminal() {
		return noterminal;
	}
	public void setNoterminal(String noterminal) {
		this.noterminal = noterminal;
	}
	public String getRightpart() {
		return rightpart;
	}
	public void setRightpart(String rightpart) {
		this.rightpart = rightpart;
	}
	@Override
	public String toString() {
		return noterminal + "->" + rightpart;
	}
	
}
