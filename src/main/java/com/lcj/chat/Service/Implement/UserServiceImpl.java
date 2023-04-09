package com.lcj.chat.Service.Implement;

import com.lcj.chat.Entity.User;
import com.lcj.chat.DAO.UserDao;
import com.lcj.chat.Service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User loginService(String uname, String password) {
        // 如果账号密码都对则返回用户对象，若有一个错误则返回null
        User user = userDao.findByUnameAndPassword(uname, password);
        // 清空
        if(user != null){
            user.setPassword("");
        }
        return user;
    }

    @Override
    public User registService(User user) {
        //用户名已存在
        if(userDao.findByUname(user.getUname())!=null){
            // 无法注册
            return null;
        }else{
            //返回创建好的用户对象
            User newUser = userDao.save(user);
            if(newUser != null){
                newUser.setPassword("");
            }
            return newUser;
        }
    }
}
