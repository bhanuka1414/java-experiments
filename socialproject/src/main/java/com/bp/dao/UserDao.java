package com.bp.dao;

import com.bp.functions.Security;
import com.bp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDao extends Security{

    @Autowired
    private SessionFactory sessionFactory;
    //Security security = new Security();
    @Transactional
    public void regUser(User user){
        try {
            Session session = sessionFactory.getCurrentSession();
            user.setPassword(MD5(user.getPassword()));
            session.save(user);
        }catch (Exception e){

        }
    }
    @Transactional
    public User checkUser(User user){
        User user1;
        try {
            Session session = sessionFactory.getCurrentSession();
            Query q = session.createQuery("from User where email = :e and password = :p");
            q.setParameter("e" , user.getEmail());
            q.setParameter("p" , MD5(user.getPassword()));
            user1  = (User) q.uniqueResult();
            return user1;
        }catch (Exception e){
            user1=null;
            return user1;
        }

    }

}
