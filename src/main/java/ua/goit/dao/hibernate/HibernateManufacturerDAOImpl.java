package ua.goit.dao.hibernate;

import ua.goit.dao.ManufacturerDAO;
import ua.goit.model.Manufacturer;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateManufacturerDAOImpl implements ManufacturerDAO {
    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private static HibernateManufacturerDAOImpl instance = new HibernateManufacturerDAOImpl();

    private HibernateManufacturerDAOImpl() {
    }

    public static ManufacturerDAO getInstance() {
        return instance;
    }

    public void save(Manufacturer manufacturer) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(manufacturer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void update(Manufacturer manufacturer) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.update(manufacturer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public Manufacturer getById(Long id) {
        try (Session session = this.sessionFactory.openSession()) {
            Manufacturer manufacturer = session.get(Manufacturer.class, id);
            Hibernate.initialize(manufacturer.getProducts());
            return manufacturer;
        }
    }

    public void delete(Manufacturer manufacturer) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.delete(manufacturer);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Manufacturer> getAll() {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Manufacturer m");
            List<Manufacturer> result = query.list();
            for (Manufacturer manufacturer : result) {
                Hibernate.initialize(manufacturer.getProducts());
            }
            return result;
        }
    }
}