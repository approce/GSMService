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
    public final String ID;

    @Column(name = "description")
    public final String DESCRIPTION;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    public final SIMProvider SIM_PROVIDER;

    public SIMCell(String ID, String DESCRIPTION, SIMProvider SIM_PROVIDER) {
        this.ID = ID;
        this.DESCRIPTION = DESCRIPTION;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
