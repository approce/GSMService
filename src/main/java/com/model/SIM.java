package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sims")
public class SIM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sim_id")
    public final Integer ID;

    @Column
    public final Integer NUMBER;

    @ManyToOne()
    @JoinColumn(name= "provider_id")
    public final SIMProvider SIM_PROVIDER;

    public SIM(Integer ID, Integer NUMBER, SIMProvider SIM_PROVIDER) {
        this.ID = ID;
        this.NUMBER = NUMBER;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
