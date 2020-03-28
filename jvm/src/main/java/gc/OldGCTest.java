package gc;

/**
 * 模拟OldGC测试
 * JVM_SETTINGS:-Xms10M -Xmx10M -XX:NewSize=5m -XX:MaxNewSize=5m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=10m -XX:+UseConcMarkSweepGC -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
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
