package com.model;

public class SIMCellFactory {

    public static SIMCell getSIMCell(String simCell) {
        //TODO add DAO:
        return new SIMCell(simCell, new SIMProvider("Beline", "*161#"));
    }

}
