package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sim_cells")
public class SIMCell {

    @Id
    @Column(name = "sim_cell_id")
    private String id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private SIMProvider simProvider;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SIMProvider getSimProvider() {
        return simProvider;
    }

    public void setSimProvider(SIMProvider simProvider) {
        this.simProvider = simProvider;
    }
}
