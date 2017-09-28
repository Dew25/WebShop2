/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import entity.User;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import session.UserFacade;

/**
 *
 * @author jvm
 */
@Stateless
public class Authorisation {
    @Inject UserFacade userFacade;
    
    private HttpSession session;
    private String login;
    private String password;
    private Boolean status;

    public Authorisation() {
    }

    public Authorisation(HttpSession session, String login, String password) {
        this.session = session;
        this.login = login;
        this.password = password;
        check();
    }

    public Boolean getStatus() {
        return status;
    }

    public Authorisation(HttpSession session) {
        this.session = session;
    }

    private void setStatus(Boolean status) {
        this.status = status;
    }

    private void check() {
        try{ 
           
            User user = userFacade.findByLoginPassword(login,password);
            session.setAttribute("regUser", user);
            setStatus(true);
        }catch(Exception e){
            if(session != null){
                session.invalidate();
            }
            setStatus(false);
        }
        
    }
    
}
