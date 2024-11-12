package demo.base;

import java.util.function.Supplier;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 00:54
 */
public class LambdaHolder {

    public static final Supplier<? extends int[]> SUPPLIER =() -> new int[256];

}
