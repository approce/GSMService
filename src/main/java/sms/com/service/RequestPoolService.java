package sms.com.service;

import sms.com.model.Request;

public interface RequestPoolService {

    void add(Request request);

    void finish(Request request);
}