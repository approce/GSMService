package com.controller;

import com.DAO.ModemDAO;
import com.model.Modem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private ModemDAO modemDAO;

    @RequestMapping("/")
    String home() {

        List<Modem> result = modemDAO.getModems();
        System.out.println("asd");


        return "Hello world!";
    }
}
