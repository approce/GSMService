package com.service;

import com.DAO.ModemDAO;
import com.model.Modem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModemServiceImpl implements ModemService {

    @Autowired
    private ModemDAO modemDAO;

    @Override
    public List<Modem> getModems() {
        return modemDAO.getModems();
    }
}
