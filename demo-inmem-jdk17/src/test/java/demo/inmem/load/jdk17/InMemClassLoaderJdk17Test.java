package demo.inmem.load.jdk17;

import demo.base.SimTargetBytes;
import demo.base.inf.SimTarget;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/11/24 12:57 AM
 */
public class InMemClassLoaderJdk17Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemClassLoaderJdk17Test.class);

    @Test
    @SuppressWarnings("unchecked")
    public void test() {
        Class<? extends SimTarget> targetClass =
                (Class<? extends SimTarget>) new InMemClassLoaderImpl().loadClass("demo.base.impl.SimTargetImpl",
                                                                                  SimTargetBytes.SIM_TARGET_BYTES);
        callClass(targetClass);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testUnsafe() {
        Class<? extends SimTarget> targetClass =
                (Class<? extends SimTarget>) new InMemClassLoaderHiddenImpl().loadClass("demo.base.impl.SimTargetImpl",
                                                                                        SimTargetBytes.SIM_TARGET_BYTES);
        callClass(targetClass);
    }

    private void callClass(Class<? extends SimTarget> targetClass) {
        try {
            LOGGER.info("class: {}", targetClass);
            SimTarget target = targetClass.getDeclaredConstructor().newInstance();
            target.outputData("hello");
        } catch (InstantiationException | IllegalAccessException
                 | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
