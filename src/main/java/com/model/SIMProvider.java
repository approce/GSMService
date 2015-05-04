package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sim_provider")
public class SIMProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "provider_id")
    public final Integer ID;

    @Column(name = "name")
    public final String NAME;

    @Column(name = "get_number_ussd")
    public final String GET_NUMBER_COMMAND;

    @Column(name = "description")
    public final String DESCRIPTION;

    public SIMProvider(Integer ID, String NAME, String GET_NUMBER_COMMAND, String DESCRIPTION) {
        this.ID = ID;
        this.NAME = NAME;
        this.GET_NUMBER_COMMAND = GET_NUMBER_COMMAND;
        this.DESCRIPTION = DESCRIPTION;
    }
}
