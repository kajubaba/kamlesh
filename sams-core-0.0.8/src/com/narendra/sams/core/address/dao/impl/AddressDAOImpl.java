package com.narendra.sams.core.address.dao.impl;

import com.narendra.sams.core.address.dao.AddressDAO;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import java.util.List;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AddressDAOImpl extends HibernateDaoSupport implements AddressDAO {
    public State loadState(Long stateId) {
        return (State) getHibernateTemplate().load(State.class, stateId);
    }

    public State getState(Long stateId) {
        return (State) getHibernateTemplate().get(State.class, stateId);
    }

    public Country getCountry(Long countryId) {
        return (Country) getHibernateTemplate().get(Country.class, countryId);
    }

    public Country loadCountry(Long countryId) {
        return (Country) getHibernateTemplate().load(Country.class, countryId);
    }

    public List<Country> getAllContries() {
        return getSession().createCriteria(Country.class).addOrder(Order.asc("name")).list();
    }

    public List<State> getAllStates() {
        return getSession().createCriteria(State.class).addOrder(Order.desc("displayName")).list();
    }
}
