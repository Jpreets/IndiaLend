package net.indialend.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.indialend.web.dao.UserDao;
import net.indialend.web.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findByPhone(String phone) {
        User user = dao.findByPhone(phone);
        return user;
    }

    public void saveUser(User user) {
        dao.save(user);
    }

    /*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setGender(user.getGender());
        }
    }

    public void deleteUserByPhone(String phone) {
        dao.deleteByPhone(phone);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserPhoneUnique(Integer id, String phone) {
        User user = findByPhone(phone);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

}
