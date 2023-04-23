package com.lcj.chat.DAO;

import com.lcj.chat.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUname(String uname); //通过用户名查找
    User findByUnameAndPassword(String uname, String password);//通过用户名和密码查找
}
