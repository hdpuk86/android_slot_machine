package com.example.hayleyprior.androidslotmachine;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hayleyprior on 14/11/2017.
 */

public class MiniGame {

    private ArrayList<String> game;

    public MiniGame() {
        this.game = new ArrayList<>();
        initiate();
        shuffle();
    }

    public void initiate(){
        game.add("Win");
        game.add("Lose");
        game.add("Lose");
    }

    public void shuffle(){
        Collections.shuffle(this.game);
    }

    public String getPosition1(){
        return this.game.get(0);
    }

    public String getPosition2(){
        return this.game.get(1);
    }

    public String getPosition3(){
        return this.game.get(2);
    }

}
