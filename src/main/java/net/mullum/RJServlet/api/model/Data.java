package net.mullum.RJServlet.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    private String data;
    private int ID;
    

    @JsonCreator
    public Data(@JsonProperty("data") String data) {
        this.data = data;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getID() {
        return ID;
    }

    public String getData() {
        return data;
    }
}
