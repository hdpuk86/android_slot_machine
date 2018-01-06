package com.example.hayleyprior.androidslotmachine;

/**
 * Created by hayleyprior on 10/11/2017.
 */



public enum Symbols {
//    LEOPARD(100, "@drawable/leopard"),
//    RHINO(80, "@drawable/rhino"),
//    LION(75, "@drawable/lion"),
//    BUFFALO(60, "@drawable/buffalo"),
//    ELEPHANT(50, "@drawable/elephant"),
//    ALLIGATOR(40, "@drawable/alligator"),
//    HIPPO(30, "@drawable/hippo"),
//    SNAKE(20, "@drawable/snake"),
//    ZEBRA(10, "@drawable/zebra"),
    SPRINGBOK(5, "@drawable/springbok"),
    SPIN(1, "@drawable/freespin");

    public String imageName;
    public int value;

    Symbols(int value, String imageName){
     this.imageName = imageName;
     this.value = value;
    }

    public String getImageName() {
        return imageName;
    }

    public int getValue() {
        return value;
    }

}
