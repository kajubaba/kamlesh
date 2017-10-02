package com.narendra.sams.admission.service;

import com.narendra.sams.admission.domain.ClassHistory;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.admission.domain.StudentGaurdian;
import com.narendra.sams.admission.domain.StudentParentsInformation;
import com.narendra.sams.admission.domain.StudentPersonalInformation;
import com.narendra.sams.admission.domain.StudentTranslation;
import java.util.Collection;

public interface StudentService {
    Long addGaurdian(StudentGaurdian studentGaurdian, Long l);

    ClassHistory getClassHistory(Long l);

    Student getStudent(Long l, String str);

    StudentBankDetails getStudentBankDetails(Long l);

    Student getStudentById(Long l);

    void updateGaurdian(StudentGaurdian studentGaurdian, Long l);

    void updateImageName(Long l, String str);

    void updatePersonalInformation(Student student, Long l);

    Long updateStudentBankDetails(StudentBankDetails studentBankDetails, Long l);

    void updateStudentParentsInformation(StudentParentsInformation studentParentsInformation, Long l);

    void updateStudentPersonalInformation(StudentPersonalInformation studentPersonalInformation, Long l);

    void updateStudentTranslation(StudentTranslation studentTranslation);

    void updateStudentTranslations(Collection<StudentTranslation> collection);
}
