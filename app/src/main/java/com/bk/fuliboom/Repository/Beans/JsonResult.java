package com.bk.fuliboom.Repository.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JsonResult {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("str")
    @Expose
    private String str;

    /**
     *
     * @return
     * The error
     */
    public String getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The str
     */
    public String getStr() {
        return str;
    }

    /**
     *
     * @param str
     * The str
     */
    public void setStr(String str) {
        this.str = str;
    }

}