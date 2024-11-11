package demo.inmem.load.jdk11;

import demo.base.impl.AnonClassHolder;
import demo.inmem.load.InMemClassLoader;
import jdk.internal.misc.Unsafe;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 2:48 PM
 */
public class InMemClassLoaderAnonImpl implements InMemClassLoader {

    private static final Unsafe UNSAFE = Unsafe.getUnsafe();
    private static final Object[] EMPTY_OBJECTS = new Object[0];

    @Override
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(AnonClassHolder.class, classBytes, EMPTY_OBJECTS);
    }
}
