package com.utils.xml;

import com.model.MyModem;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by Roman Zayats on 27.03.2015.
 */
public class ModemAdapterConverter extends XmlAdapter<ModemAdapter, MyModem> {


    @Override
    public MyModem unmarshal(ModemAdapter proxy) throws Exception {
        return new MyModem(proxy.getIMEI(), proxy.getInitCommand(),
                proxy.getId(), proxy.getPort(), proxy.getBaudRate(),
                proxy.getManufacturer(), proxy.getModel());
    }

    @Override
    public ModemAdapter marshal(MyModem myModem) throws Exception {
        if (myModem != null) {
            ModemAdapter modemAdapter = new ModemAdapter();
            modemAdapter.setId(myModem.getId());
            modemAdapter.setIMEI(myModem.getIMEI());
            modemAdapter.setPort(myModem.getPort());
            modemAdapter.setBaudRate(myModem.getBaudRate());
            modemAdapter.setInitCommand(myModem.getInitCommand());
            modemAdapter.setManufacturer(myModem.getManufacturer());
            modemAdapter.setModel(myModem.getModel());
            return modemAdapter;
        } else {
            return null;
        }

    }
}
