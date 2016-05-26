package net.indialend.web.service;

import java.util.List;

import net.indialend.web.model.User;

public interface UserService {

    User findById(int id);

    User findByPhone(String phone);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserByPhone(String phone);

    List<User> findAllUsers();

    boolean isUserPhoneUnique(Integer id, String phone);

    public User login(String phoneEmail, String password);

    public User findByPhoneAndDate(String phone, String selectedDate);

    public void deactivateUser(String phone);

}
