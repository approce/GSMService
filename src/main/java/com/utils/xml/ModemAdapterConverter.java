package com.utils.xml;

import com.model.Modem;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Roman Zayats on 27.03.2015.
 */
public class ModemAdapterConverter extends XmlAdapter<ModemAdapter, Modem> {


    @Override
    public Modem unmarshal(ModemAdapter proxy) throws Exception {
        Modem modem = new Modem(proxy.getIMEI(), proxy.getInitCommand(),
                proxy.getId(), proxy.getPort(), proxy.getBaudRate(),
                proxy.getManufacturer(), proxy.getModel());
        return modem;
    }

    @Override
    public ModemAdapter marshal(Modem modem) throws Exception {
        if (modem != null) {
            ModemAdapter modemAdapter = new ModemAdapter();
            modemAdapter.setId(modem.getId());
            modemAdapter.setIMEI(modem.getIMEI());
            modemAdapter.setPort(modem.getPort());
            modemAdapter.setBaudRate(modem.getBaudRate());
            modemAdapter.setInitCommand(modem.getInitCommand());
            modemAdapter.setManufacturer(modem.getManufacturer());
            modemAdapter.setModel(modem.getModel());
            return modemAdapter;
        } else {
            return null;
        }

    }
}
