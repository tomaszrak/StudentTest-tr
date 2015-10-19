package com.prz.testing.repository.impl;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.User;
import com.prz.testing.repository.AbstractRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 10.09.2015.
 */
public class AbstractRepositoryImpl<T> implements AbstractRepository<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    AbstractRepositoryImpl(Class<T> t) {
        this.clazz = t;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Object o) throws SQLException {
        getCurrentSession().save(o);
    }

    public List<T> getAll() throws SQLException {
        return getCurrentSession().createCriteria(clazz).list();
    }

    public T getById(Long id) throws SQLException {
        return (T) getCurrentSession().get(clazz, id);
    }

    public void delete(Object o) throws SQLException {
        getCurrentSession().delete(o);
    }

    public void delete(Long id) throws SQLException {
        getCurrentSession().delete("ID", id);
    }

    public int countAll() throws SQLException {
        return Integer.valueOf(getCurrentSession().createCriteria(clazz)
                .setProjection(Projections.rowCount()).uniqueResult().toString());
    }

    public List<T> getPaginated(Criteria criteria) throws SQLException {
        return getCurrentSession().createCriteria(clazz).setFirstResult(criteria.getOffset())
                .setMaxResults(criteria.getLimit()).list();
    }

    public void update(Object o) throws SQLException {
        getCurrentSession().update(o);
    }

    public List<T> getWithCriteriaPaginated(Criteria criteria) {
        List<T> data = new ArrayList<T>();

        Order order = null;
        if (criteria.getOrderWay().equals("ASC")) {
            order = Order.asc(criteria.getOrderParam());
        }
        if (criteria.getOrderWay().equals("DESC")) {
            order = Order.desc(criteria.getOrderParam());
        }

        org.hibernate.Criteria crit = getCurrentSession().createCriteria(clazz);
        if (null == criteria.getSearchParam() || null == criteria.getSearchValue()) {
            data = crit.setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).addOrder(order).list();
        } else {
            data = crit.add(Restrictions.eq(criteria.getSearchParam(), criteria.getSearchValue()))
                    .setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).list();
        }
        return data;
    }

    public Integer countWithCriteria(Criteria criteria) {
        Integer totalItems;

        if (null == criteria.getSearchParam() || null == criteria.getSearchValue()) {
            totalItems = Integer.valueOf(getCurrentSession().createCriteria(clazz)
                    .setProjection(Projections.rowCount()).uniqueResult().toString());
        } else {
            totalItems = Integer.valueOf(getCurrentSession().createCriteria(clazz)
                    .add(Restrictions.eq(criteria.getSearchParam(), criteria.getSearchValue()))
                    .setProjection(Projections.rowCount()).uniqueResult().toString());
        }

        return totalItems;
    }
}
