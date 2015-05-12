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
    public final Long NUMBER;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    public final SIMProvider SIM_PROVIDER;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sim")
    private List<Message> messageList;

    public SIM(Long NUMBER, SIMProvider SIM_PROVIDER) {
        this.NUMBER = NUMBER;
        this.SIM_PROVIDER = SIM_PROVIDER;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
