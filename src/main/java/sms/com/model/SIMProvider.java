package sms.com.model;

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
    private Integer ID;

    @Column(name = "name")
    public final String NAME;

    @Column(name = "get_number_ussd")
    public final String GET_NUMBER_COMMAND;

    @Column(name = "description")
    private String DESCRIPTION;

    public SIMProvider(String NAME, String GET_NUMBER_COMMAND) {
        this.NAME = NAME;
        this.GET_NUMBER_COMMAND = GET_NUMBER_COMMAND;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }
}
