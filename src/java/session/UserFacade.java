/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author jvm
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "WebShop2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public User findByLoginPassword(String login, String password) throws LoginException {
        try{
            User user = (User) em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password=:password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
            return user;
        }catch(Exception e){
            throw new LoginException("Проблема авторизации!");
        }
    }
    
}
