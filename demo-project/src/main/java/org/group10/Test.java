package org.group10;

public class Test {
    public static void printSt(Integer a, String... strings){
        System.out.println(a);
        System.out.println(strings[0]);
    }
    public static void main(String[] args){
        String b= "a";
        String c= "z";
        String d = "y";
        printSt(1,b,c,d);
    }
}
