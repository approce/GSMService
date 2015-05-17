package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "sims")
public class SIM {

    @Id
    @Column(name = "sim_number")
    private Long number;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private SIMProvider simProvider;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sim")
    private List<Message> messageList;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public SIMProvider getSimProvider() {
        return simProvider;
    }

    public void setSimProvider(SIMProvider simProvider) {
        this.simProvider = simProvider;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
