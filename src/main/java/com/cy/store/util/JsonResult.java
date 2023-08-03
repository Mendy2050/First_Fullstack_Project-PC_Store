package com.cy.store.util;

import java.io.Serializable;

/**
 * Response result class
 * @param <E> The type of response data
 */
public class JsonResult<E> implements Serializable {
    /** status code */
    private Integer state;

    /** State description information */
    private String message;

    /** data */
    private E data;


    public JsonResult(int error, String s) {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    /** Called when an exception occurs */
    public JsonResult(Throwable e) {
        super();
        // Get the exception information in the exception object
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
