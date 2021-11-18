package com.company.exception;

public class Main {

    public static int sum(Integer[] array, int index)
    {
    int sum = 0, i = Math.min(0, index);
    boolean keepGoing = true;

        while(keepGoing) {
            try {
                sum += array[i];
                i++;
            }
            catch (NullPointerException npe) {
                break;
            }
            catch (IndexOutOfBoundsException e) {

            }
            if (i > index || index < 0)
                keepGoing = false;

        }
    return sum;
}
    public static void main(String[] args) {
Integer array[] = {null,null,null,null,null};

System.out.println(sum(array,4));
    }

}
// nje metode qe merr vektor dhe Integer. gjej shumen nga 0 deri ne indexi i dhene (null, -, me i madh, te gjitha)
// krijo nje strukture qe ka nje hierarki dhe e ndryshon algoritmin nga shume ne prodhim