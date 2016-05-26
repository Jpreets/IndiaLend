package net.indialend.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.indialend.web.model.User;
import net.indialend.web.model.UserLocations;

@Repository("userLocationDao")
public class UserLocationDaoImpl extends AbstractDao<Integer, UserLocations> implements UserLocationDao {

    public List<UserLocations> findByUserId(int userId) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userId", userId));
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<UserLocations> userLocationses = (List<UserLocations>) crit.list();

        return userLocationses;
    }

    public void save(UserLocations user) {
        persist(user);
    }

    private Date getFormattedFromDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    private Date getFormattedToDateTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    public List<UserLocations> findByUserIdAndDate(Integer user_id, String selectedDate) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user.user_id", user_id));
        try {

             Date date =   new SimpleDateFormat("yyyy/MM/dd").parse(selectedDate);
             
            crit.add(Restrictions.ge("date",
                    getFormattedFromDateTime(date)));

            crit.add(Restrictions.le("date",
                    getFormattedToDateTime(date)));
            
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<UserLocations> userLocationses = (List<UserLocations>) crit.list();

        return userLocationses;
    }

}
