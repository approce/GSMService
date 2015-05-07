package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "aggregator_id")
    private String aggregator_id;

    @Column(name = "originator")
    private String originator;

    //TODO validate this to avoid SQL injection
    @Column(name = "body")
    private String body;

    @Column(name = "receipt_date")
    private Calendar receipt_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAggregator_id() {
        return aggregator_id;
    }

    public void setAggregator_id(String aggregator_id) {
        this.aggregator_id = aggregator_id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Calendar getReceipt_date() {
        return receipt_date;
    }

    public void setReceipt_date(Calendar receipt_date) {
        this.receipt_date = receipt_date;
    }
}
