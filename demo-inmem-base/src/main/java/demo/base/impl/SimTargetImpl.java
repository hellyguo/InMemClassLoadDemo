package demo.base.impl;

import demo.base.inf.SimTarget;

import java.util.Arrays;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 00:54
 */
public class SimTargetImpl implements SimTarget {

    @Override
    public void outputData(String data) {
        int[] array = new int[256];
        Arrays.fill(array, 11);
        array[0] = Arrays.stream(array).sum();
    }

}
