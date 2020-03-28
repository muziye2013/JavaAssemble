package gc;

/**
 * FullGC调优实例
 * BEFORE_JVM_SETTING:-Xms200M -Xmx200M -XX:NewSize=100m -XX:MaxNewSize=100m -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=20m -XX:+UseConcMarkSweepGC -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
 * AFTER_JVM_SETTING: -Xms300M -Xmx300M -XX:NewSize=200m -XX:MaxNewSize=200m -XX:SurvivorRatio=2 -XX:PretenureSizeThreshold=20m -XX:+UseConcMarkSweepGC -Xloggc:gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps
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
