package oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 堆外内存空间不足引发OOM
 * -Xmx256m -XX:MaxDirectMemorySize=100M
 */
public class DirectByteBufferOOM {

    public static void main(String[] args) throws InterruptedException{
        //分配128MB直接内存
        ByteBuffer bb = ByteBuffer.allocateDirect(1024*1024*128);
        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}
