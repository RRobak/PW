package lab14.zad2;

public class Results {
private int yes=0;
private int no=0;
public int getYes() {
	return yes;
}

public int getNo() {
	return no;
}
public void yes() {
	this.yes++;
}

public void no() {
	this.no++;
}

public void setYes(int yes) {
	this.yes = yes;
}

public void setNo(int no) {
	this.no = no;
}

public void addYes(int yes) {
	this.yes = this.yes+yes;
}

public void addNo(int no) {
	this.no = this.no+ no;
}


}
