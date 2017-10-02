package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.exception.CanNotDeleteException;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.VehicleDriverDAO;
import com.narendra.sams.transportation.domain.VehicleDriver;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VehicleDriverDAOImpl extends HibernateDaoSupport implements VehicleDriverDAO {
    public Long addVehicleDriver(VehicleDriver vehicleDriver, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        vehicleDriver.setCreatedBy(user);
        vehicleDriver.setLastUpdatedBy(user);
        vehicleDriver.setCreatedDateTime(DateUtil.getSystemDateTime());
        vehicleDriver.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(vehicleDriver);
    }

    public void updateVehicleDriver(VehicleDriver vehicleDriver, Long userId) {
        VehicleDriver persistDriver = (VehicleDriver) getHibernateTemplate().load(VehicleDriver.class, vehicleDriver.getId());
        persistDriver.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        persistDriver.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        persistDriver.setName(vehicleDriver.getName());
        persistDriver.setAddress(vehicleDriver.getAddress());
        persistDriver.setPrimaryContact(vehicleDriver.getPrimaryContact());
        persistDriver.setSecondaryContact(vehicleDriver.getSecondaryContact());
        persistDriver.setLicenseNo(vehicleDriver.getLicenseNo());
        persistDriver.setRole(vehicleDriver.getRole());
        getHibernateTemplate().update(persistDriver);
    }

    public void deleteVehicleDriver(Long driverId, Long instituteId) throws CanNotDeleteException {
        VehicleDriver vehicleDriver = getVehicleDriver(driverId, instituteId);
        if (vehicleDriver == null) {
            throw new CanNotDeleteException("Vehicle Driver " + driverId + " does not exist");
        }
        getHibernateTemplate().delete(vehicleDriver);
    }

    public VehicleDriver getVehicleDriver(Long driverId, Long instituteId) {
        Criteria crt = getSession().createCriteria(VehicleDriver.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        crt.add(Restrictions.eq("id", driverId));
        return (VehicleDriver) crt.uniqueResult();
    }

    public List<VehicleDriver> getVehicleDrivers(Long instituteId) {
        Criteria crt = getSession().createCriteria(VehicleDriver.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        return crt.list();
    }
}
