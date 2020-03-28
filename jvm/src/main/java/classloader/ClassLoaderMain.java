package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 类加载器
 */
public class ClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        //这个类class的路径
        String classPath = "d/Hello.class";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);

        //类的全称
        String packageNamePath = "classloader.Hello";

        //加载Log这个class文件
        Class<?> Hello = myClassLoader.loadClass(packageNamePath);

        //利用反射获取main方法
        Method method = Hello.getDeclaredMethod("main", String[].class);
        Object object = Hello.newInstance();
        String[] arg = {"ad"};
        method.invoke(object, (Object) arg);
    }
}
