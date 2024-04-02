package smarter.common.express.execute;

public class Variable {
	
	public Variable(String name){
		this.type = 3;
		this.name = name;
		this.param = name;
	}
	
	public Variable(String[] names){
		this.type = 4;
		this.param = names;
	}
	
	public Variable(String name,int index){
		this.type = 5;
		this.param = name;
		this.start = index;
	}
	
	public Variable(String name,int start,int end){
		this.name = name;
		this.param = name;
		this.type = 2;
		this.start = start;
		this.end = end;
	}
	
	public Variable(String code,String msg){
		this.type = 1;
		this.param = new Error(code,msg);
	}
	
	private String name;
	
	//1:error 2:集合范围 3:普通 4:集合属性  5:集合追加
	private int type;
	
	private int start = 1;
	
	private int end = -1;

	private Object param;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}
	
	public String toString(){
		return name;
	}
	
}
