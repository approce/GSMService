package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id")
    private Long ID;

    @Column(name = "short_name")
    private String short_name;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "originator")
    private String originator;

    @Column(name = "priority")
    private Double priority;

}