package sbu.testlab.testmachine.tracer.oracle;

public class Tuple {
public int x;
public int y;
public Tuple(int x,int y){
	this.x=x;
	this.y=y;
}
@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Tuple t=new  Tuple(this.x,this.y);
		return t;
	}
}
