package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * JVM配置参数
 * -Xms20m    JVM初始分配的内存20m
 * -Xmx20m   JVM最大可用内存为20m
 * -XX:+HeapDumpOnOutOfMemoryError 当JVM发生OOM时，自动生成DUMP文件
 * -XX:HeapDumpPath=d:  生成DUMP文件的路径
 * JVM_SETTINGS:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=d:/dump/
 */
public class HeapOOM {
    static class OOMObject {
    }
    public static void main(String[] args) {
        int i = 0;
        List<OOMObject> list = new ArrayList<OOMObject>();
        cycle(list, i);
    }
    public static void cycle(List<OOMObject> list, int i) {
        //在堆中无限创建对象
        while (true) {
            System.out.println("already create objects=" + i++);
            list.add(new OOMObject());
        }
    }
}
