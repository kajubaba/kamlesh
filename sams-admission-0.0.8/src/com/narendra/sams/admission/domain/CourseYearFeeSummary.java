package com.narendra.sams.admission.domain;

import com.narendra.sams.core.domain.BusFeeInstallment;
import com.narendra.sams.core.domain.Course;
import com.narendra.sams.core.domain.CourseYear;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseYearFeeSummary {
    private long advanceDeposited;
    private String busFeeInstallmentSetupType;
    private long busFeePercentage;
    private Map<Long, BusStopWiseFee> busStopStudentCountMap = new HashMap();
    private Course course;
    private CourseYear courseYear;
    private long depositedFee;
    private long discoutGiven;
    private Long dueProjectedFee;
    private long lateFeeDeposited;
    private long newAdmissionCount;
    private long newAdmissionCustomizeCount;
    private long newAdmissionCustomizeFee;
    private long newAdmissionFee;
    private Map<Long, Long> newAdmissionsUnderScheme = new HashMap();
    private Map<Long, Long> newUnderSchemeAdmissionsBusStop = new HashMap();
    private Map<Long, Long> regAdmissionsUnderScheme = new HashMap();
    private Map<Long, BusStopWiseFee> regStopStudentCountMap = new HashMap();
    private Map<Long, Long> regUnderSchemeAdmissionsBusStop = new HashMap();
    private long regularAdmissBusFeePercentage;
    private long regularAdmissionCount;
    private long regularAdmissionCustomizeCount;
    private long regularAdmissionCustomizeFee;
    private long regularAdmissionFee;
    private long underSchemeDiscountOnBusFee;
    private long underSchemeDiscountOnFee;

    public long getBusFeePercentage() {
        return this.busFeePercentage;
    }

    public void setBusFeePercentage(long busFeePercentage) {
        this.busFeePercentage = busFeePercentage;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseYear getCourseYear() {
        return this.courseYear;
    }

    public void setCourseYear(CourseYear courseYear) {
        this.courseYear = courseYear;
    }

    public long getProjectedFee() {
        return (this.newAdmissionCount * this.newAdmissionFee) + (this.regularAdmissionCount * this.regularAdmissionFee);
    }

    public long getDiscoutGiven() {
        return this.discoutGiven;
    }

    public void setDiscoutGiven(long discoutGiven) {
        this.discoutGiven = discoutGiven;
    }

    public long getDepositedFee() {
        return this.depositedFee;
    }

    public void setDepositedFee(long depositedFee) {
        this.depositedFee = depositedFee;
    }

    public long getNewAdmissionCount() {
        return this.newAdmissionCount;
    }

    public void setNewAdmissionCount(long newAdmissionCount) {
        this.newAdmissionCount = newAdmissionCount;
    }

    public long getRegularAdmissionCount() {
        return this.regularAdmissionCount;
    }

    public void setRegularAdmissionCount(long regularAdmissionCount) {
        this.regularAdmissionCount = regularAdmissionCount;
    }

    public long getNewAdmissionFee() {
        return this.newAdmissionFee;
    }

    public void setNewAdmissionFee(long newAdmissionFee) {
        this.newAdmissionFee = newAdmissionFee;
    }

    public long getRegularAdmissionFee() {
        return this.regularAdmissionFee;
    }

    public void setRegularAdmissionFee(long regularAdmissionFee) {
        this.regularAdmissionFee = regularAdmissionFee;
    }

    public long getAdmittedStudent() {
        return this.newAdmissionCount + this.regularAdmissionCount;
    }

    public long getNewAdmissionCustomizeFee() {
        return this.newAdmissionCustomizeFee;
    }

    public void setNewAdmissionCustomizeFee(long newAdmissionCustomizeFee) {
        this.newAdmissionCustomizeFee = newAdmissionCustomizeFee;
    }

    public long getRegularAdmissionCustomizeFee() {
        return this.regularAdmissionCustomizeFee;
    }

    public void setRegularAdmissionCustomizeFee(long regularAdmissionCustomizeFee) {
        this.regularAdmissionCustomizeFee = regularAdmissionCustomizeFee;
    }

    public long getNewAdmissionCustomizeCount() {
        return this.newAdmissionCustomizeCount;
    }

    public void setNewAdmissionCustomizeCount(long newAdmissionCustomizeCount) {
        this.newAdmissionCustomizeCount = newAdmissionCustomizeCount;
    }

    public long getRegularAdmissionCustomizeCount() {
        return this.regularAdmissionCustomizeCount;
    }

    public void setRegularAdmissionCustomizeCount(long regularAdmissionCustomizeCount) {
        this.regularAdmissionCustomizeCount = regularAdmissionCustomizeCount;
    }

    public long getDueFeeAdmittedStudent() {
        return ((this.newAdmissionCount + this.regularAdmissionCount) + this.newAdmissionCustomizeCount) + this.regularAdmissionCustomizeCount;
    }

    public long getDueProjectedFee() {
        if (this.dueProjectedFee == null) {
            this.dueProjectedFee = Long.valueOf((((((this.newAdmissionCount * this.newAdmissionFee) + (this.regularAdmissionCount * this.regularAdmissionFee)) + this.newAdmissionCustomizeFee) + this.regularAdmissionCustomizeFee) - this.underSchemeDiscountOnFee) - this.underSchemeDiscountOnBusFee);
        }
        return this.dueProjectedFee.longValue();
    }

    public boolean getNonCustomizeStudentAvailable() {
        if (this.newAdmissionCount > 0 || this.regularAdmissionCount > 0) {
            return true;
        }
        return false;
    }

    public long getAdvanceDeposited() {
        return this.advanceDeposited;
    }

    public void setAdvanceDeposited(long advanceDeposited) {
        this.advanceDeposited = advanceDeposited;
    }

    public long getLateFeeDeposited() {
        return this.lateFeeDeposited;
    }

    public void setLateFeeDeposited(long lateFeeDeposited) {
        this.lateFeeDeposited = lateFeeDeposited;
    }

    public Map<Long, BusStopWiseFee> getBusStopStudentCountMap() {
        return this.busStopStudentCountMap;
    }

    public void setBusStopStudentCountMap(Map<Long, BusStopWiseFee> busStopStudentCountMap) {
        this.busStopStudentCountMap = busStopStudentCountMap;
    }

    public long getBusFee() {
        long busFee = 0;
        if (this.busStopStudentCountMap != null) {
            for (BusStopWiseFee busStopWiseFee : new ArrayList<BusStopWiseFee>(this.busStopStudentCountMap.values())) {
                if (!(busStopWiseFee.getStudentCount() == null || busStopWiseFee.getBusFee() == null)) {
                    if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(this.busFeeInstallmentSetupType)) {
                        busFee += ((busStopWiseFee.getBusFee().longValue() * getBusFeePercentage()) / 100) * busStopWiseFee.getStudentCount().longValue();
                    } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(this.busFeeInstallmentSetupType)) {
                        busFee += busStopWiseFee.getBusFee().longValue() * busStopWiseFee.getStudentCount().longValue();
                    }
                }
            }
        }
        if (this.regStopStudentCountMap != null) {
            for (BusStopWiseFee busStopWiseFee2 : new ArrayList<BusStopWiseFee>(this.regStopStudentCountMap.values())) {
                if (!(busStopWiseFee2.getStudentCount() == null || busStopWiseFee2.getBusFee() == null)) {
                    if (BusFeeInstallment.SETUP_TYPE_PERCENTAGE.equals(this.busFeeInstallmentSetupType)) {
                        busFee += ((busStopWiseFee2.getBusFee().longValue() * getRegularAdmissBusFeePercentage()) / 100) * busStopWiseFee2.getStudentCount().longValue();
                    } else if (BusFeeInstallment.SETUP_TYPE_MANUAL.equals(this.busFeeInstallmentSetupType)) {
                        busFee += busStopWiseFee2.getBusFee().longValue() * busStopWiseFee2.getStudentCount().longValue();
                    }
                }
            }
        }
        return busFee;
    }

    public long studentCount() {
        return ((this.newAdmissionCount + this.regularAdmissionCount) + this.newAdmissionCustomizeCount) + this.regularAdmissionCustomizeCount;
    }

    public long projectedFee() {
        return (((this.newAdmissionCount * this.newAdmissionFee) + (this.regularAdmissionCount * this.regularAdmissionFee)) + this.newAdmissionCustomizeFee) + this.regularAdmissionCustomizeFee;
    }

    public long getRegularAdmissBusFeePercentage() {
        return this.regularAdmissBusFeePercentage;
    }

    public void setRegularAdmissBusFeePercentage(long regularAdmissBusFeePercentage) {
        this.regularAdmissBusFeePercentage = regularAdmissBusFeePercentage;
    }

    public Map<Long, BusStopWiseFee> getRegStopStudentCountMap() {
        return this.regStopStudentCountMap;
    }

    public void setRegStopStudentCountMap(Map<Long, BusStopWiseFee> regStopStudentCountMap) {
        this.regStopStudentCountMap = regStopStudentCountMap;
    }

    public String getBusFeeInstallmentSetupType() {
        return this.busFeeInstallmentSetupType;
    }

    public void setBusFeeInstallmentSetupType(String busFeeInstallmentSetupType) {
        this.busFeeInstallmentSetupType = busFeeInstallmentSetupType;
    }

    public Map<Long, Long> getNewAdmissionsUnderScheme() {
        return this.newAdmissionsUnderScheme;
    }

    public void setNewAdmissionsUnderScheme(Map<Long, Long> newAdmissionsUnderScheme) {
        this.newAdmissionsUnderScheme = newAdmissionsUnderScheme;
    }

    public Map<Long, Long> getRegAdmissionsUnderScheme() {
        return this.regAdmissionsUnderScheme;
    }

    public void setRegAdmissionsUnderScheme(Map<Long, Long> regAdmissionsUnderScheme) {
        this.regAdmissionsUnderScheme = regAdmissionsUnderScheme;
    }

    public long getUnderSchemeDiscountOnFee() {
        return this.underSchemeDiscountOnFee;
    }

    public void setUnderSchemeDiscountOnFee(long underSchemeDiscountOnFee) {
        this.underSchemeDiscountOnFee = underSchemeDiscountOnFee;
    }

    public long getUnderSchemeDiscountOnBusFee() {
        return this.underSchemeDiscountOnBusFee;
    }

    public void setUnderSchemeDiscountOnBusFee(long underSchemeDiscountOnBusFee) {
        this.underSchemeDiscountOnBusFee = underSchemeDiscountOnBusFee;
    }

    public Map<Long, Long> getNewUnderSchemeAdmissionsBusStop() {
        return this.newUnderSchemeAdmissionsBusStop;
    }

    public void setNewUnderSchemeAdmissionsBusStop(Map<Long, Long> newUnderSchemeAdmissionsBusStop) {
        this.newUnderSchemeAdmissionsBusStop = newUnderSchemeAdmissionsBusStop;
    }

    public Map<Long, Long> getRegUnderSchemeAdmissionsBusStop() {
        return this.regUnderSchemeAdmissionsBusStop;
    }

    public void setRegUnderSchemeAdmissionsBusStop(Map<Long, Long> regUnderSchemeAdmissionsBusStop) {
        this.regUnderSchemeAdmissionsBusStop = regUnderSchemeAdmissionsBusStop;
    }
}
