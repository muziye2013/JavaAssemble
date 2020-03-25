package gc;

/**
 * FullGC调优实例
 */
public class FullGCOptmize {

    public static void main(String[] args) throws Exception{
        Thread.sleep(1000);
        while (true) {
            loadData();
        }
    }

    public static void loadData() throws Exception{
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }
}
