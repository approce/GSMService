package com.controller;

import com.DAO.AggregatorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private AggregatorDAO aggregatorDAO;

    @RequestMapping("/")
    String home() {
        return String.valueOf(aggregatorDAO.getAggregators().size());
    }
}
