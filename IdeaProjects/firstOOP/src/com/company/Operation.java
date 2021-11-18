package com.company;

public class Operation {

    private MathOperation mathOperation;

    public Operation(MathOperation mathOperation) {

        this.mathOperation = mathOperation;
    }
    public int doOperation(int a, int b){

        return mathOperation.operation(a,b);
    }
}
