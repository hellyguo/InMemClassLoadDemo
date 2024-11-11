package demo.inmem.load.jdk17;

import demo.base.impl.HiddenClassHolder;
import demo.inmem.load.InMemClassLoader;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 2:48 PM
 */
public class InMemClassLoaderHiddenImpl implements InMemClassLoader {

    @Override
    public Class<?> loadClass(String className, byte[] classBytes) {
        return HiddenClassHolder.loadClass(className, classBytes);
    }
}
