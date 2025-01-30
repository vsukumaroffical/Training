//$Id$
package thread;

public class MethodSyn {
	public static void main(String[] args) {
		Table table = new Table();
		MyThread1 thread1 = new MyThread1(table);
		MyThread2 thread2 = new MyThread2(table);
		thread1.start();
		thread2.start();
	}
}
class MyThread1 extends Thread{
	Table t;
	MyThread1(Table t){
		this.t = t;
	}
	
	
	@Override
	public void run() {
		t.print(3);
	}
}

class MyThread2 extends Thread{
	Table t;
	MyThread2(Table t){
		this.t = t;
	}
	
	
	@Override
	public void run() {
		t.print(7);
	}
}

class Table{
	 void print(int n) {
		synchronized(this) {
			for(int i=1;i<=5;i++) {
				System.out.println(i*n);
				try {
					Thread.sleep(1000);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}