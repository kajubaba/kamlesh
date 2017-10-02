package com.narendra.sams.admission.service;

public interface StudentActivityLogService {
    void addAdmissionConfirmedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addAdmissionRenewActivity(Long l, Long l2, Long l3, Long l4, Long l5, String str);

    void addAdmissionSchemeChangedActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);

    void addBanksDetailsChangedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addBusStopChangeRequestActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);

    void addBusStopChangedActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);

    void addClassChangeRequestActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);

    void addClassChangedActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);

    void addGaurdianAddedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addGaurdianUpdatedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addParentsInformationUpdatedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addPersonalInformationUpdatedActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addRegistrationActivity(Long l, Long l2, Long l3, Long l4, String str);

    void addStudentStatusChangedActivity(Long l, Long l2, Long l3, Long l4, String str, Long l5, Long l6);
}
