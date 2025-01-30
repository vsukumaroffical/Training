//$Id$
package thread;

public class ThreadExample {
	public static void main(String[] args) {
		MyThread thread = new MyThread("Suku");
//		thread.setName("sukumar");
		
		thread.start();
		for(int i =0 ;i<1000;i++) {
			System.out.println("main thread");
		}
		System.out.println(thread.getName());
		
	}
}
class MyThread extends Thread{
	MyThread(String name){
		super(name);
	}
	public MyThread() {
		
	}
	@Override
	public void run() {
		for(int i =0 ;i<1000;i++) {
			System.out.println("sub thread");
		}
	}
}