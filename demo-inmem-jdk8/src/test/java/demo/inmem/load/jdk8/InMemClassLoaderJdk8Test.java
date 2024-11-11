package demo.inmem.load.jdk8;

import demo.base.SimTargetBytes;
import demo.base.inf.SimTarget;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/11/24 12:21 AM
 */
public class InMemClassLoaderJdk8Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemClassLoaderJdk8Test.class);

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
                (Class<? extends SimTarget>) new InMemClassLoaderUnsafeImpl().loadClass("demo.base.impl.SimTargetImpl",
                                                                                        SimTargetBytes.SIM_TARGET_BYTES);
        callClass(targetClass);
    }

    private void callClass(Class<? extends SimTarget> targetClass) {
        try {
            LOGGER.info("class: {}", targetClass);
            SimTarget target = targetClass.newInstance();
            target.outputData("hello");
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
