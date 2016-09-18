package com.bk.fuliboom.Repository.Beans;

/**
 * Created by Bk on 2016/9/17.
 */
import java.util.ArrayList;
import java.util.List;
public class Data {
    private Boolean error;
    private List<Result> results = new ArrayList<Result>();

    /**
     *
     * @return
     * The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     *
     * @param results
     * The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }
}
