package net.indialend.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.indialend.web.dao.UserDao;
import net.indialend.web.dao.UserLocationDao;
import net.indialend.web.model.User;
import net.indialend.web.model.UserLocations;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserLocationDao userLocationDao;

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByPhone(String phone) {
        User user = userDao.findByPhone(phone);
        return user;
    }

    public void saveUser(User user) {

        User u = userDao.findByPhone(user.getPhone());
        if (u != null) {
            u.setEmail(user.getEmail());
            u.setGender(user.getGender());
            u.setName(user.getName());
            u.setLatitude(user.getLatitude());
            u.setLongitute(user.getLongitute());
            u.setGcmToken(user.getGcmToken());
            u.setActive(user.isActive());
            userDao.update(u);
            
           
            System.out.println("u.isService()"+u.isService());
            if (user.isService()) {
                
                UserLocations locations = new UserLocations();
                locations.setUser(u);
                locations.setLatitude(user.getLatitude());
                locations.setLongitute(user.getLongitute());
                
                userLocationDao.save(locations);
                System.out.println("save");
            }
             user =u;
        } else {
            userDao.save(user);
        }
    }

    /*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
     */
    public void updateUser(User user) {
        User entity = userDao.findByPhone(user.getPhone());
        if (entity != null) {
            entity.setName(user.getName());
            entity.setEmail(user.getEmail());
            entity.setPhone(user.getPhone());
            entity.setGender(user.getGender());
            entity.setLatitude(user.getLatitude());
            entity.setLongitute(user.getLongitute());
        }
    }

    public void deleteUserByPhone(String phone) {
        userDao.deleteByPhone(phone);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean isUserPhoneUnique(Integer id, String phone) {
        User user = findByPhone(phone);
        return (user == null);
    }

    public User login(String phoneEmail,String password) {
        return userDao.login(phoneEmail,password);
    }

    public User findByPhoneAndDate(String phone, String selectedDate) {
        User user = userDao.findByPhone(phone);
        List<UserLocations> userLocationses =  userLocationDao.findByUserIdAndDate(user.getUser_id(), selectedDate);
        user.setUserLocations(userLocationses);
        return user;
    }

    public void deactivateUser(String phone) {
       
        User u = userDao.findByPhone(phone);
        if (u != null) {
            u.setActive(false);
            userDao.update(u);
        }

    }

}
