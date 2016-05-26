package net.indialend.web.dao;

import java.util.List;

import net.indialend.web.model.User;

public interface UserDao {

    User findById(int id);

    User findByPhone(String phone);

    void save(User user);
    
    void update(User user);

    void deleteByPhone(String phone);

    List<User> findAllUsers();

    public User login(String phoneEmail, String password);
   

}
