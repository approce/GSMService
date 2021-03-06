package sms.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private Long ID;

    @Column(name = "aggregator_id")
    private String aggregator_id;

    @Column(name = "create_date")
    private Calendar create_date;

    @Column(name = "start_date")
    private Calendar start_date;

    @Column(name = "finish_date")
    private Calendar finish_date;

    @OneToMany
    @JoinColumn(name = "request_id")
    private List<Message> messageList;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Calendar getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Calendar create_date) {
        this.create_date = create_date;
    }

    public Calendar getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Calendar finish_date) {
        this.finish_date = finish_date;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void addMessage(Message message) {
        if(messageList == null) {
            messageList = new LinkedList<>();
        }
        messageList.add(message);
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Request otherRequest = (Request) obj;
        return Objects.equals(this.ID, otherRequest.ID);
    }

    public String getAggregator_id() {
        return aggregator_id;
    }

    public void setAggregator_id(String aggregator_id) {
        this.aggregator_id = aggregator_id;
    }

    public Calendar getStart_date() {
        return start_date;
    }

    public void setStart_date(Calendar start_date) {
        this.start_date = start_date;
    }

    public static enum RequestStatus {
        AVAILABLE, EXECUTING
    }
}
