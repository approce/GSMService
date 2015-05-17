package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @Column(name = "offer_id")
    private String offer_id;

    @Column(name = "originator")
    private String originator;

    @Column(name = "priority")
    private Double priority;

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public Double getPriority() {
        return priority;
    }

    public void setPriority(Double priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Offer offer = (Offer) obj;
        return this.getOffer_id().equals(offer.getOffer_id());
    }
}