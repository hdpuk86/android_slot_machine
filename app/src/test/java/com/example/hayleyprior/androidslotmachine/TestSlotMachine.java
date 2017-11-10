package com.example.hayleyprior.androidslotmachine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class TestSlotMachine {

    private Wheel wheel;
    private SlotMachine slotMachine;

    @Before
    public void setUp() throws Exception {
        wheel = new Wheel();
        slotMachine = new SlotMachine(wheel, 3);
    }

    @Test
    public void canGenerate3Wheels() throws Exception {
        assertEquals(3, slotMachine.countSlots());
    }

    @Test
    public void canGenerate5Wheels() throws Exception {
        SlotMachine fiveSlotMachine = new SlotMachine(wheel, 5);
        assertEquals(5, fiveSlotMachine.countSlots());
    }
}
