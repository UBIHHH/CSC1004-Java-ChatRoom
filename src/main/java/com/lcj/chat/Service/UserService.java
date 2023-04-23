package com.lcj.chat.Service;

import com.lcj.chat.Entity.User;

public interface UserService {
    User loginService(String uname, String password);

    User registerService(User user);
}
