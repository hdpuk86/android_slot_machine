package com.example.hayleyprior.androidslotmachine;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

    @Test
    public void playerCanAddMoreMoney() throws Exception {
        slotMachine.setPlayerFunds(10);
        slotMachine.addPlayerFunds(20);
        assertEquals(30, slotMachine.checkPlayerFunds());
    }

    @Test
    public void spinGetsArrayList() throws Exception {
        ArrayList<Symbols> line = slotMachine.spin();
        assertEquals(3, line.size());
    }

    @Test
    public void playFundsDecreaseAfterSpin() throws Exception {
        slotMachine.setPlayerFunds(10);
        slotMachine.spin();
        assertEquals(9, slotMachine.checkPlayerFunds());
    }

    @Test
    public void gameBankIncreasesAfterSpin() throws Exception {
        slotMachine.spin();
        assertEquals(1, slotMachine.checkGameBank());
    }
}
