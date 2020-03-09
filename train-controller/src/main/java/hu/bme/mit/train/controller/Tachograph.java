package hu.bme.mit.train.controller;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Tachograph {
    public Table<Long, Integer, Integer> table = HashBasedTable.create();

    public void add(long time, int joystick, int refspeed)
    {
        table.put(time, joystick, refspeed);
    }

}
