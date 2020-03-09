package hu.bme.mit.train.controller;

import org.junit.Test;
import org.junit.Assert;


public class TachographTest {
    TrainControllerImpl testsubj = new TrainControllerImpl();

    @Test
    public void TachoTest()
    {
        testsubj.followSpeed();
        Assert.assertFalse(testsubj.tachograph.table.size() == 0);
    }

}