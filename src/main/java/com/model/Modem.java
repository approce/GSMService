package com.model;

import org.smslib.AGateway;
import org.smslib.modem.SerialModemGateway;
import javax.persistence.*;

@Entity
@Table(name = "modem")
public class Modem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "imei")
    private String IMEI;

    @Column(name = "port")
    private String port;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "gatewaySpeed")
    private Integer gatewaySpeed;

    @Column(name = "init_commant")
    private String initCommand;

    transient private SerialModemGateway serialModemGateway;

    transient private SIM sim;

    public void initGateway() {
        serialModemGateway = new SerialModemGateway("modem_" + id, port, gatewaySpeed, brand, model);
        serialModemGateway.setProtocol(AGateway.Protocols.PDU);
        serialModemGateway.setInbound(true);
        serialModemGateway.setCustomInitString(initCommand);
    }
}
