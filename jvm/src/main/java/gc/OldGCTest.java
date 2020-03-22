package gc;

/**
 * 模拟OldGC测试
 */
public class OldGCTest {

    public static void main(String[] args) {
        byte[] arr1 = new byte[2*1024*1024];
        arr1 = new byte[2*1024*1024];
        arr1 = new byte[2*1024*1024];
        arr1 = null;

        byte[] arr2 = new byte[128*1024];
        byte[] arr3 = new byte[2*1024*1024];
        arr3 = new byte[2*1024*1024];
        arr3 = new byte[2*1024*1024];
        arr3 = new byte[128*1024];
        arr3 = null;

        byte[] arr4 = new byte[2*1024*1024];
    }

}
