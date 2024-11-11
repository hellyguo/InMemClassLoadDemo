package demo.inmem.load.jdk11;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/11/24 12:14 AM
 */
class BytesClassLoader extends URLClassLoader {
    public BytesClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public Class<?> loadClassFromBytes(String className, byte[] bytes) {
        return this.defineClass(className, bytes, 0, bytes.length);
    }
}
