package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sim_cell")
public class SIMCell {

    @Id
    @Column(name = "sim_cell_id")
    private String ID;

    @Column(name = "description")
    public final String DESCRIPTION;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    public final SIMProvider SIM_PROVIDER;

    public SIMCell(String DESCRIPTION, SIMProvider SIM_PROVIDER) {
        this.DESCRIPTION = DESCRIPTION;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
