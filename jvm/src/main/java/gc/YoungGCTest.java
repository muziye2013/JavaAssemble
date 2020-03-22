package gc;

/**
 * 模拟YoungGC测试
 */
public class YoungGCTest {

    public static void main(String[] args) {
        byte[] arr1 = new byte[1024*1024];
        arr1 = new byte[1024*1024];
        arr1 = new byte[1024*1024];
        arr1 = null;

        byte[] arr2 = new byte[2*1024*1024];
    }

}
