package demo.base.impl;

import demo.base.inf.SimTarget;

/**
 * @author Helly Guo
 * <p>
 * Created on 11/10/24 00:54
 */
public class SimTargetImpl implements SimTarget {

    @Override
    public void outputData(String data) {
        System.out.println(data);
    }

}
