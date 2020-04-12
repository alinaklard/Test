package dao;

import data.Product;
import hibernate.HibernateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Map;

public class ProductHibernateDAO implements ProductDAO {
    @Override
    public boolean addProduct(Product p) {
        boolean isAdded = false;
        try {
            Session session = HibernateUtils.getSession();
            session.beginTransaction();
            session.save(p);
            session.getTransaction().commit();
            isAdded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdded;
    }

    @Override
    public boolean updateProduct(Map<String, String> newValues, int id) {
        int result = 0;
        try {
            Session session = HibernateUtils.getSession();
            Transaction t = session.beginTransaction();
            for (Map.Entry entry : newValues.entrySet()) {
                Query query = session.createQuery(new StringBuilder()
                        .append("UPDATE Product SET ")
                        .append(entry.getKey())
                        .append(" = '")
                        .append(entry.getValue())
                        .append("' WHERE id = ")
                        .append(id)
                        .toString());
                result += query.executeUpdate();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result == newValues.size();
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean isDeleted = false;
        try {
            Session session = HibernateUtils.getSession();
            Product p = (Product) session.createQuery(new StringBuilder()
                    .append("FROM Product WHERE id = ")
                    .append(id)
                    .toString()).uniqueResult();
            session.beginTransaction();
            session.delete(p);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    @Override
    public Product getByID(int id) {
        Product product = null;
        try {
            Session session = HibernateUtils.getSession();
            session.beginTransaction();
            product = (Product) session.createQuery(new StringBuilder()
                    .append("FROM Product WHERE id=")
                    .append(id)
                    .toString()).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = null;
        try {
            Session session = HibernateUtils.getSession();

            products = session.createQuery("FROM Product").list();
            session.beginTransaction();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}
