package net.proselyte.dao.hibernate;

import net.proselyte.dao.ProductDAO;
import net.proselyte.model.Manufacturer;
import net.proselyte.model.Product;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nastya on 27.11.2017.
 */
public class HibernateProductDAOImpl implements ProductDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static HibernateProductDAOImpl instance = new HibernateProductDAOImpl();

    private HibernateProductDAOImpl() {
    }

    public static ProductDAO getInstance() {
        return instance;
    }

    public void save(Product product) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(Product product) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.update(product);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Product getById(Long id) {
        try (Session session = this.sessionFactory.openSession()) {
            Product product = session.get(Product.class, id);
            if (product != null) {
                Hibernate.initialize(product.getManufacturer());
            }
            return product;
        }
    }

    public void delete(Product product) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.delete(product);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Product> getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Product p");
            List<Product> result = query.list();
            for (Product product : result) {
                Hibernate.initialize(product.getManufacturer());
            }
            return result;
        }
    }
}
