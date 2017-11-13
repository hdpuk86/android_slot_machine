package com.example.hayleyprior.androidslotmachine;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by hayleyprior on 10/11/2017.
 */

public class TestWheel {

    private Wheel wheel1;
    private Wheel spy;

    @Before
    public void setUp() throws Exception {
        wheel1 = new Wheel();
        spy = Mockito.spy(new Wheel());
    }

    @Test
    public void canGenerateSymbols() throws Exception {
        assertEquals(11, wheel1.countSymbols());
    }

    @Test
    public void canGetSymbolAtIndex() throws Exception {
        assertEquals(Symbols.LEOPARD, wheel1.getSymbolAtIndex(0));
    }

    @Test
    public void canGetRandomSymbol() throws Exception {
        Mockito.when(spy.randomInt(spy.countSymbols())).thenReturn(10);
        assertEquals(Symbols.SPIN, spy.getRandomSymbol());
    }

    @Test
    public void canGetIndexOfSymbol() throws Exception {
        assertEquals(1, wheel1.getSymbolIndex(Symbols.RHINO));
    }

    @Test
    public void nudgeSetsCurrentSymbolToPrevious() throws Exception {
        wheel1.setCurrentSymbol(Symbols.ELEPHANT);
        assertEquals(Symbols.BUFFALO, wheel1.nudge());
    }

    @Test
    public void nudgeFromIndexZeroSetsLastSymbol() throws Exception {
        wheel1.setCurrentSymbol(Symbols.LEOPARD);
        assertEquals(Symbols.SPIN, wheel1.nudge());
    }

    @Test
    public void nudgeAvailableIfNumIs10() throws Exception {
        Mockito.when(spy.randomInt(30)).thenReturn(10);
        spy.randomAssignNudgeAvailable();
        assertEquals(true, spy.getNudgeAvailable());
    }

    @Test
    public void nudgeNotAvailableIfNumIsNot10() throws Exception {
        Mockito.when(spy.randomInt(30)).thenReturn(8);
        spy.randomAssignNudgeAvailable();
        assertEquals(false, spy.getNudgeAvailable());
    }

    @Test
    public void nudgeNotAvailableAfterNudging() throws Exception {
        wheel1.setNudgeAvailable(true);
        wheel1.nudge();
        assertEquals(false, wheel1.getNudgeAvailable());
    }

    @Test
    public void holdAvailableIfNumIs10() throws Exception {
        Mockito.when(spy.randomInt(30)).thenReturn(10);
        spy.randomAssignHoldAvailable();
        assertEquals(true, spy.getHoldAvailable());
    }

    @Test
    public void holdNotAvailableIfNumIsNot10() throws Exception {
        Mockito.when(spy.randomInt(30)).thenReturn(8);
        spy.randomAssignHoldAvailable();
        assertEquals(false, spy.getHoldAvailable());
    }

    @Test
    public void canGetImageFromSymbolIndex() throws Exception {
        assertEquals("@drawable/lion", wheel1.getSymbolImageAtIndex(2));
    }

    @Test
    public void returnsSymbolOnSpin() throws Exception {
        Mockito.when(spy.randomInt(spy.countSymbols())).thenReturn(0);
        assertEquals(Symbols.LEOPARD, spy.spin());
    }

    @Test
    public void setsCurrentSymbolOnSpin() throws Exception {
        spy.setCurrentSymbol(Symbols.ELEPHANT);
        Mockito.when(spy.getRandomSymbol()).thenReturn(Symbols.HIPPO);
        spy.spin();
        assertEquals(Symbols.HIPPO, spy.getCurrentSymbol());
    }

    @Test
    public void returnsCurrentSymbolIfHeld() throws Exception {
        wheel1.setCurrentSymbol(Symbols.SNAKE);
        wheel1.setPlayerHasHeld(true);
        assertEquals(Symbols.SNAKE, wheel1.spin());
    }

    @Test
    public void canReturnTopSymbol() throws Exception {
        wheel1.setCurrentSymbol(Symbols.SNAKE);
        assertEquals(Symbols.HIPPO, wheel1.getTopSymbol());
    }

    @Test
    public void canReturnTopSymbolFromIndex0() throws Exception {
        wheel1.setCurrentSymbol(Symbols.LEOPARD);
        assertEquals(Symbols.SPIN, wheel1.getTopSymbol());
    }
}
