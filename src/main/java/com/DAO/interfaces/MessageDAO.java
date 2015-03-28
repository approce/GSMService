package com.DAO.interfaces;

import com.model.Message;

public interface MessageDAO {
    //TODO can I create generic DAO?

    void saveMessage(Message message);

}
