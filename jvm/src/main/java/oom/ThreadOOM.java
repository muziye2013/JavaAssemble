package oom;

/**
 * 模拟虚拟机栈溢出
 * 假如虚拟机栈可以动态扩展，当扩展时无法申请到足够的内存时会抛出 OutOfMemoryError 异常
 */
public class ThreadOOM {

    private void cycle(){
        while(true){
        }
    }

    public void stackLeakByThread(){
        while(true){
            new Thread(()->{ cycle(); }).start();
        }
    }

    public static void main(String[] args) {
        ThreadOOM oom = new ThreadOOM();
        oom.stackLeakByThread();

    }
}
