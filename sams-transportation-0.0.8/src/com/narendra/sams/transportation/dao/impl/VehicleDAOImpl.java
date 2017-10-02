package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.core.domain.UserView;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.transportation.dao.VehicleDAO;
import com.narendra.sams.transportation.domain.Vehicle;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class VehicleDAOImpl extends HibernateDaoSupport implements VehicleDAO {
    public List<Vehicle> getAllVehicles(Long instituteId) {
        Criteria crt = getSession().createCriteria(Vehicle.class);
        crt.add(Restrictions.eq("institute.id", instituteId));
        return crt.list();
    }

    public Long addVehicle(Vehicle vehicle, Long userId) {
        UserView user = (UserView) getHibernateTemplate().load(UserView.class, userId);
        vehicle.setCreatedBy(user);
        vehicle.setLastUpdatedBy(user);
        vehicle.setCreatedDateTime(DateUtil.getSystemDateTime());
        vehicle.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        return (Long) getHibernateTemplate().save(vehicle);
    }

    public Vehicle getVehicle(Long vehicleId) {
        return (Vehicle) getHibernateTemplate().get(Vehicle.class, vehicleId);
    }

    public void updateVehicle(Vehicle vehicle, Long userId) {
        Vehicle loadedVehicle = (Vehicle) getHibernateTemplate().load(Vehicle.class, vehicle.getId());
        loadedVehicle.setVehicleId(vehicle.getVehicleId());
        loadedVehicle.setManufacturer(vehicle.getManufacturer());
        loadedVehicle.setManufacturingYear(vehicle.getManufacturingYear());
        loadedVehicle.setVehicleColor(vehicle.getVehicleColor());
        loadedVehicle.setVehicleName(vehicle.getVehicleName());
        loadedVehicle.setVehicleSeatCapacity(vehicle.getVehicleSeatCapacity());
        loadedVehicle.setVehicleType(vehicle.getVehicleType());
        loadedVehicle.setLastUpdatedBy((UserView) getHibernateTemplate().load(UserView.class, userId));
        loadedVehicle.setLastUpdatedDateTime(DateUtil.getSystemDateTime());
        loadedVehicle.setChassisNo(vehicle.getChassisNo());
        loadedVehicle.setEngineNo(vehicle.getEngineNo());
        loadedVehicle.setRegistrationDate(vehicle.getRegistrationDate());
        loadedVehicle.setTransferDate(vehicle.getTransferDate());
        loadedVehicle.setPucDueDate(vehicle.getPucDueDate());
        loadedVehicle.setFitnessDueDate(vehicle.getFitnessDueDate());
        loadedVehicle.setRoadTaxDueDate(vehicle.getRoadTaxDueDate());
        loadedVehicle.setPermitDueDate(vehicle.getPermitDueDate());
        loadedVehicle.setInsuranceDueDate(vehicle.getInsuranceDueDate());
        getHibernateTemplate().update(loadedVehicle);
    }
}
