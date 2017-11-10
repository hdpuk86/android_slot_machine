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

    Wheel wheel1;

    @Before
    public void setUp() throws Exception {
        wheel1 = new Wheel();
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
        Wheel spy = Mockito.spy(new Wheel());
        Mockito.when(spy.randomInt(spy.countSymbols())).thenReturn(10);
        assertEquals(Symbols.SPIN, spy.getRandomSymbol());
    }
}
