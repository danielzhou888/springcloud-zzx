package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author zhouzhixiang
 * @Date 2020-04-18
 */
public class Proxy {

    public static Object newProxyInstance(Class infce, InvocationHandler h) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String methodStr = "";
        String rt = "\r\n";

        Method[] methods = infce.getMethods();
        for (Method m : methods) {
            methodStr += "@Override" + rt +
                    "public void " + m.getName() + "() {" + rt +
                    "   try {" + rt +
                    "       Method md = " + infce.getName() + ".class.getMethod(\"" + m.getName() + "\");" + rt +
                    "       h.invoke(this, md);" + rt +
                    "   } catch(Exception e) {" + rt +
                    "       e.printStackTrace();" + rt +
                    "   }" + rt +
                    "}" + rt;
        }

        String classStr = "package com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2;" + rt +
                "import java.lang.reflect.Method;" + rt +
                "public class $Proxy1 implements " + infce.getName() + "{" + rt +
                "   com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h;" + rt +
                "   public $Proxy1(com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h) {" + rt +
                "       this.h = h;" + rt +
                "   }" + rt +
                methodStr +
                "}";
        String fileName = "/Users/daniel/zzx/springcloud-zzx/zzx-design-pattern/src/main/java/com/zzx/design/pattern/zzxdesignpattern/proxy/chapt2/$Proxy1.java";
        File f = new File(fileName);
        FileWriter fw = new FileWriter(f);
        fw.write(classStr);
        fw.flush();
        fw.close();

        //compile
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iterable = fileManager.getJavaFileObjects(fileName);
        JavaCompiler.CompilationTask t = compiler.getTask(null, fileManager, null, null, null, iterable);
        t.call();
        fileManager.close();

        // load into memory and create an instance
        URL[] urls = new URL[]{new URL("file:/Users/daniel/zzx/springcloud-zzx/zzx-design-pattern/src/")};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass("com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.$Proxy1");
        Constructor<?> constructor = c.getConstructor(InvocationHandler.class);
        Object o = constructor.newInstance(h);
        return o;
    }

    class Test implements Moveable {

        com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h;

        public Test(com.zzx.design.pattern.zzxdesignpattern.proxy.chapt2.InvocationHandler h) {
            this.h = h;
        }

        @Override
        public void move() {


        }
    }
}
