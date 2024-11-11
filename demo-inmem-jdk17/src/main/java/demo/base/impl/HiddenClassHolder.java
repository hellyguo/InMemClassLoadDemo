package demo.base.impl;

import java.lang.invoke.MethodHandles;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/11/24 09:15
 */
public final class HiddenClassHolder {

    private static final String PACKAGE_NAME = HiddenClassHolder.class.getPackageName();

    public static Class<?> loadClass(String className, byte[] classBytes) {
        if (!className.startsWith(PACKAGE_NAME)) {
            throw new RuntimeException("not same package, should failed");
        }
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            return lookup.defineHiddenClass(classBytes, true,
                                            MethodHandles.Lookup.ClassOption.NESTMATE,
                                            MethodHandles.Lookup.ClassOption.STRONG)
                         .lookupClass();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private HiddenClassHolder() {
    }
}
