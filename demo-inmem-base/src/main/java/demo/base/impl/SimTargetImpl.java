package demo.base.impl;

import demo.base.LambdaHolder;
import demo.base.inf.SimTarget;

import java.util.Arrays;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 00:54
 */
public class SimTargetImpl implements SimTarget {

    private static final ThreadLocal<int[]> LOCAL_DATA = ThreadLocal.withInitial(LambdaHolder.SUPPLIER);

    @Override
    public void outputData(String data) {
        int[] array = LOCAL_DATA.get();
        Arrays.fill(array, 11);
        array[0] = Arrays.stream(array).sum();
    }

}
