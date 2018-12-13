package iqb.testcase.test;

public class Test {
	private int x,y; 
    public void setX(int i){ x=i;} 
    public void setY(int i){y=i;} 
    public synchronized void setXY(int i){ 
         setX(i); setY(i); 
    } 
    public synchronized boolean check(){ 
         return x!=y;   
    } 
}
