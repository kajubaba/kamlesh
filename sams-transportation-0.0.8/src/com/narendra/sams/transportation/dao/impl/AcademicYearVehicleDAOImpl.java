package com.narendra.sams.transportation.dao.impl;

import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.transportation.dao.AcademicYearVehicleDAO;
import com.narendra.sams.transportation.domain.AcademicYearVehicle;
import com.narendra.sams.transportation.domain.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AcademicYearVehicleDAOImpl extends HibernateDaoSupport implements AcademicYearVehicleDAO {
    public List<AcademicYearVehicle> getAcademicYearVehicles(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearVehicle.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public List<Vehicle> getActiveVehiclesInAcademicYear(Long academicYearId) {
        Criteria crt = getSession().createCriteria(Vehicle.class);
        crt.createAlias("academicYearVehicles", "academicYearVehicle");
        crt.createAlias("academicYearVehicle.academicYear", "academicYear");
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        return crt.list();
    }

    public Long getActiveBusesCountInAcademicYear(Long academicYearId) {
        Criteria crt = getSession().createCriteria(AcademicYearVehicle.class);
        crt.add(Restrictions.eq("academicYear.id", academicYearId));
        crt.setProjection(Projections.projectionList().add(Projections.countDistinct("id"), "vehicleCount"));
        return (Long) crt.uniqueResult();
    }

    public void addVehiclesInAcademicYear(List<Integer> vehicleIds, Long academicYearId) {
        if (vehicleIds != null && !vehicleIds.isEmpty()) {
            AcademicYear academicYear = (AcademicYear) getHibernateTemplate().load(AcademicYear.class, academicYearId);
            List<AcademicYearVehicle> academicYearVehicles = new ArrayList();
            for (Integer vehicleId : vehicleIds) {
                AcademicYearVehicle academicYearVehicle = new AcademicYearVehicle();
                academicYearVehicle.setAcademicYear(academicYear);
                academicYearVehicle.setVehicle((Vehicle) getHibernateTemplate().load(Vehicle.class, Long.valueOf(vehicleId.longValue())));
                academicYearVehicles.add(academicYearVehicle);
            }
            getHibernateTemplate().saveOrUpdateAll(academicYearVehicles);
        }
    }

    public AcademicYearVehicle getAcademicYearVehicle(Long academicSessionBusId) {
        return (AcademicYearVehicle) getHibernateTemplate().get(AcademicYearVehicle.class, academicSessionBusId);
    }

    public Long getTotalSeatCapacityOfVehicles(Long academicSessionId) {
        Criteria crt = getSession().createCriteria(AcademicYearVehicle.class);
        crt.createAlias("vehicle", "vehicle");
        crt.add(Restrictions.eq("academicYear.id", academicSessionId));
        crt.setProjection(Projections.projectionList().add(Projections.sum("vehicle.vehicleSeatCapacity"), "totalSeatCapacity"));
        return (Long) crt.uniqueResult();
    }
}
