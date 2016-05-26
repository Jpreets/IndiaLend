package net.indialend.web.dao;

import java.util.List;

import net.indialend.web.model.User;
import net.indialend.web.model.UserLocations;

public interface UserLocationDao {

    List<UserLocations> findByUserId(int userId);

    void save(UserLocations user);

    public List<UserLocations> findByUserIdAndDate(Integer user_id, String selectedDate);

    
}
