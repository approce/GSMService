package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "aggregator_id")
    private Integer aggregator_id;

    @Column(name = "originator")
    private String originator;

    //TODO validate this to avoid SQL injection
    @Column(name = "text")
    private String text;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
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
