package demo.inmem.load.jdk8;

import demo.base.inf.SimTarget;
import demo.inmem.load.InMemClassLoader;
import sun.misc.Unsafe;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 2:48 PM
 */
public class InMemClassLoaderUnsafeImpl implements InMemClassLoader {

    private static final Unsafe UNSAFE = UnsafeUtil.getTheUnsafe();
    private static final Object[] EMPTY_OBJECTS = new Object[0];

    @Override
    public Class<?> loadClass(String className, byte[] classBytes) {
        return UNSAFE.defineAnonymousClass(SimTarget.class, classBytes, EMPTY_OBJECTS);
    }
}
