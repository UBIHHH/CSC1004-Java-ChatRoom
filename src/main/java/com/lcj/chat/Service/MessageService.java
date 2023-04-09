package com.lcj.chat.Service;

import com.lcj.chat.Entity.Message;

import java.util.List;

public interface MessageService {

    void saveMessage(Message message);

    List<Message> findAll();

}
