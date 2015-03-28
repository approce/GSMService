package com.model;

import java.util.Calendar;

public class Message {

    private Long id;

    private Integer aggregator_id;

    private String originator;

    //TODO validate this to avoid SQL injection
    private String text;

    private String code;

    private Calendar date;

    //TODO join service.


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAggregator_id() {
        return aggregator_id;
    }

    public void setAggregator_id(Integer aggregator_id) {
        this.aggregator_id = aggregator_id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
