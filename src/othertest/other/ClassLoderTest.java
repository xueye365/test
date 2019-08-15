package src.othertest.other;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 类加载
 * ClassLoader.getSystemClassLoader() static
 * ClassLoader.initSystemClassLoader() static
 * Launcher.getLauncher() static 加载的时候直接创建对应的对象
 * Launcher.ExtClassLoader.getExtClassLoader()
 * Launcher.AppClassLoader.getAppClassLoader(var1)
 * ClassLoader.loadClass()
 */
public class ClassLoderTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        // 根加载器
        URL[] urls=sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }
        // jdk1.8.0_111/jre/lib


        // extClassLoder
        System.out.println(System.getProperty("java.ext.dirs"));
        // D:\Program Files\Java\jdk1.8.0_111\jre\lib\ext


        // appClassLoader
        System.out.println(System.getProperty("java.class.path")) ;
        // D:\Program Files\Java\jdk1.8.0_111\jre\lib\ ;D:\test2\test\out (ext中的也会加载)


        MyClassLoader m = new MyClassLoader("C:\\Users\\xueye\\Desktop\\", "myClassLoader");
        Class c = m.loadClass("Test");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        System.out.println(c.getClassLoader().getParent().getParent().getParent());
        c.newInstance();
        System.out.println("123");
    }

}

class MyClassLoader extends ClassLoader {
    private String path;
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    //用于寻找类文件
    @Override
    public Class findClass(String name) {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    //用于加载类文件
    private byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}

