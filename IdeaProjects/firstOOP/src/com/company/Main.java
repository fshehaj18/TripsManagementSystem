package com.company;

public class Main {

    public static void main(String[] args) {
        int value = 0;
       Operation operation = new Operation(new Add());
       value = operation.doOperation(value,5);
       operation = new Operation(new Multiply());
        value = operation.doOperation(value,2);

        System.out.println(value);


    }
    public static int doSomething(MathOperation m, int a, int b){
        return m.operation(a,b);
    }
}
