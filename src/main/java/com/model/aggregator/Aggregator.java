package com.model.aggregator;

import com.model.MyModem;

public abstract class Aggregator {

    private Integer id;

    private MyModem myModem;

    private String description;

    public  abstract void initialize();





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MyModem getMyModem() {
        return myModem;
    }

    public void setMyModem(MyModem myModem) {
        this.myModem = myModem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
