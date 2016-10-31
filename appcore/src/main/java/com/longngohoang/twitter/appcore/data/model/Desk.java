package com.longngohoang.twitter.appcore.data.model;

import java.io.Serializable;

/**
 * Created by Admin on 23/10/2016.
 */

public class Desk implements Serializable {
    int id;
    String des;
    boolean checked;

    public Desk(int id, String des, boolean checked) {
        this.id = id;
        this.des = des;
        this.checked = checked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
