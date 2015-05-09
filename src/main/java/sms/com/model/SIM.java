package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sims")
public class SIM {

    @Id
    @Column(name = "sim_number")
    public final Long NUMBER;

    @ManyToOne
    @JoinColumn(name= "provider_id")
    public final SIMProvider SIM_PROVIDER;

    public SIM(Long NUMBER, SIMProvider SIM_PROVIDER) {
        this.NUMBER = NUMBER;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }
}
