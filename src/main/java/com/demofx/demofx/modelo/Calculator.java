package com.demofx.demofx.modelo;

/**
 * @author Ing. Mateo Cortes Lopez;
 * Email: mateo.corteslopez@gmail.com
 * @version Id: <b>ms_simulation_track</b> 12/12/2022 11:38 a. m.
 **/
public class Calculator {

    private int operatorOne;
    private int operatorTwo;

    public Calculator(int operatorOne, int operatorTwo) {
        this.operatorOne = operatorOne;
        this.operatorTwo = operatorTwo;
    }

    public int getOperatorOne() {
        return operatorOne;
    }

    public void setOperatorOne(int operatorOne) {
        this.operatorOne = operatorOne;
    }

    public int getOperatorTwo() {
        return operatorTwo;
    }

    public void setOperatorTwo(int operatorTwo) {
        this.operatorTwo = operatorTwo;
    }

    public int sumar() {
        return this.operatorOne + this.operatorTwo;
    }

    public int restar(){
        return this.operatorOne - this.operatorTwo;
    }

    public int  multiplicar(){
        return this.operatorOne * this.operatorTwo;
    }

    public double dividir(){
        return (double) this.operatorOne / this.operatorTwo;
    }

}
