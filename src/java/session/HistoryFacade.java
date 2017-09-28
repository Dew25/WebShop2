/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Customer;
import entity.History;
import entity.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jvm
 */
@Stateless
public class HistoryFacade extends AbstractFacade<History> {

    @PersistenceContext(unitName = "WebShop2PU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistoryFacade() {
        super(History.class);
    }
//    @Override
//    public void create(History history) {
//       em.getTransaction().begin();
//        
////        Product product = history.getProduct();
////        em.merge(product);
////        Customer customer = history.getCustomer();
////        em.merge(customer);
////        product.setQuantity(product.getQuantity()-history.getNum());
////        customer.setMoney(customer.getMoney()-product.getPrice()*history.getNum());
////        history.setCustomer(customer);
////        history.setProduct(product);
////        em.persist(history);
//        em.getTransaction().commit();
//        
//    }
}
