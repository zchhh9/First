package com.swufestu.week2_hello;

public class Item {
    private String cnama;
    private String cval;

    public Item(String cnama, String cval) {
        this.cnama = cnama;
        this.cval = cval;
    }

    public String getCnama() {
        return cnama;
    }

    public void setCnama(String cnama) {
        this.cnama = cnama;
    }

    public String getCval() {
        return cval;
    }

    public void setCval(String cval) {
        this.cval = cval;
    }
}
