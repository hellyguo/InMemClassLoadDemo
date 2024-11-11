package demo.inmem.load.jdk8;

import demo.inmem.load.InMemClassLoader;

import java.net.URL;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 2:48 PM
 */
public class InMemClassLoaderImpl implements InMemClassLoader {

    private static final BytesClassLoader LOADER
            = new BytesClassLoader(new URL[0], InMemClassLoaderImpl.class.getClassLoader());

    @Override
    public Class<?> loadClass(String className, byte[] classBytes) {
        return LOADER.loadClassFromBytes(className, classBytes);
    }
}
