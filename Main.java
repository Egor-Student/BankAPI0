package org.example;

import static org.example.JDBC.*;

public class Main {
    public static void main(String[] args) {
    JDBC.putMoney(ID,putSum);
    JDBC.takeMoney(ID,takeSum);
    JDBC.getBalance(ID);
    }
}