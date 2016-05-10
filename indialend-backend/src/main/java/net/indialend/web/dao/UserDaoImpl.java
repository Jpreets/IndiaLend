package net.indialend.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.indialend.web.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    public User findById(int id) {
        User user = getByKey(id);
        return user;
    }

    public User findByPhone(String phone) {
        System.out.println("phone : " + phone);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("phone", phone));
        User user = (User) crit.uniqueResult();

        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();

        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByPhone(String phone) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("phone", phone));
        User user = (User) crit.uniqueResult();
        delete(user);
    }

}
