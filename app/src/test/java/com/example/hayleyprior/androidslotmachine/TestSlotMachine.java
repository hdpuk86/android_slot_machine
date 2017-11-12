package com.example.hayleyprior.androidslotmachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class TestSlotMachine {

    private Symbols lion;
    private Symbols alligator;
    private Wheel wheel;
    private SlotMachine slotMachine;
    private SlotMachine spy;

    @Before
    public void setUp() throws Exception {
        lion = Symbols.LION;
        alligator = Symbols.ALLIGATOR;
        wheel = new Wheel();
        slotMachine = new SlotMachine(wheel, 3);
        spy = Mockito.spy((new SlotMachine(wheel, 3)));
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

    @Test
    public void canGetLineImages() {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(lion);
        line.add(alligator);
        ArrayList<String> images = slotMachine.getLineImages(line);
        assertEquals("@drawable/lion", images.get(0));
    }

    @Test
    public void canCheckWinTrue() throws Exception {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        assertEquals(true, slotMachine.checkWin(line));
    }

    @Test
    public void canCheckWinFalse() throws Exception {
        ArrayList<Symbols> line = new ArrayList<>();
        line.add(Symbols.RHINO);
        line.add(Symbols.RHINO);
        line.add(Symbols.LION);
        assertEquals(false, slotMachine.checkWin(line));
    }
}
