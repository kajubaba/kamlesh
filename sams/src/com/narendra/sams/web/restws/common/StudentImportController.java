package com.narendra.sams.web.restws.common;

import com.narendra.sams.admission.domain.Address;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentBankDetails;
import com.narendra.sams.admission.service.AdmissionService;
import com.narendra.sams.admission.service.StudentService;
import com.narendra.sams.core.address.domain.Country;
import com.narendra.sams.core.address.domain.State;
import com.narendra.sams.core.domain.AcademicYear;
import com.narendra.sams.core.domain.AcademicYearClass;
import com.narendra.sams.core.domain.AdmissionScheme;
import com.narendra.sams.core.domain.AdmissionType;
import com.narendra.sams.core.domain.Bank;
import com.narendra.sams.core.domain.BusStop;
import com.narendra.sams.core.domain.Institute;
import com.narendra.sams.core.domain.StudentCategory;
import com.narendra.sams.core.domain.StudentStatus;
import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.io.FileInputStream;
import java.util.Iterator;
import javax.servlet.ServletContext;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({"/import"})
public class StudentImportController {
    @Autowired
    private AdmissionService admissionService;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentService studentService;

    @RequestMapping(method = {RequestMethod.GET})
    public void readCategory() throws Exception {
        Iterator<Row> rowIterator = new XSSFWorkbook(new FileInputStream(this.servletContext.getRealPath("/resources/School Data.xlsx"))).getSheetAt(0).iterator();
        while (rowIterator.hasNext()) {
            Row row = (Row) rowIterator.next();
            Cell classCell = row.getCell(3);
            System.out.println("Row Number :" + row.getRowNum());
            System.out.println("Cell type :" + classCell.getCellType());
            classCell.setCellType(1);
            System.out.println("Cell type :" + classCell.getCellType());
            System.out.println("Class Id " + classCell.getStringCellValue());
            if (!(row.getRowNum() == 0 || classCell.getStringCellValue().trim().isEmpty())) {
                Student student = new Student();
                Address address = new Address();
                StudentBankDetails studentBankDetails = new StudentBankDetails();
                AcademicYear academicYear = new AcademicYear();
                academicYear.setId(Long.valueOf(1));
                student.setAcademicYear(academicYear);
                Institute institute = new Institute();
                institute.setId(Long.valueOf(1));
                student.setInstitute(institute);
                if (row.getRowNum() > 0) {
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = (Cell) cellIterator.next();
                        if (!(cell.getColumnIndex() == 7 || cell.getColumnIndex() == 8)) {
                            cell.setCellType(1);
                        }
                        if (cell.getColumnIndex() == 0) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setStudentId(cell.getStringCellValue().trim());
                            }
                        } else if (cell.getColumnIndex() == 1) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                String[] names = cell.getStringCellValue().trim().split(" ");
                                if (names != null) {
                                    if (names.length == 1) {
                                        student.setFirstName(names[0].trim());
                                    } else if (names.length == 2) {
                                        student.setFirstName(names[0].trim());
                                        student.setLastName(names[1].trim());
                                    } else if (names.length == 3) {
                                        student.setFirstName(names[0].trim());
                                        student.setMiddleName(names[1].trim());
                                        student.setLastName(names[2].trim());
                                    }
                                }
                            }
                        } else if (cell.getColumnIndex() == 2) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                AdmissionType admissionType = new AdmissionType();
                                if ("New".equals(cell.getStringCellValue())) {
                                    admissionType.setId(AdmissionType.NEW_ADMISSION_ID);
                                } else if ("Regular".equals(cell.getStringCellValue())) {
                                    admissionType.setId(AdmissionType.REGULAR_ADMISSION_ID);
                                }
                                student.setAdmissionType(admissionType);
                            }
                        } else if (cell.getColumnIndex() == 6) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                if ("Male".equals(cell.getStringCellValue())) {
                                    student.setGender("male");
                                } else {
                                    student.setGender("female");
                                }
                            }
                        } else if (cell.getColumnIndex() == 7) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setAdmissionConfirmationDate(DateUtil.parseDate(cell.getStringCellValue().replace(".", "/"), "MM/dd/yyyy"));
                            }
                        } else if (cell.getColumnIndex() == 8) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setDob(DateUtil.parseDate(cell.getStringCellValue().replace(".", "/"), "MM/dd/yyyy"));
                            }
                        } else if (cell.getColumnIndex() == 9) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setPhone1(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 10) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setEmailId(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 11) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                address.setLine2(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 12) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                address.setCity(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 13) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                address.setZipCode(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 15) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setFatherName(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 16) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setFatherOccupation(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 17) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setFatherContact1(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 18) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setMotherName(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 19) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setMotherOccupation(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 20) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setMotherContact1(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 21) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setNationality(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 22) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setReligion(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 23) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                String ctg = cell.getStringCellValue();
                                if (!(ctg == null || ctg.isEmpty())) {
                                    StudentCategory studentCategory = new StudentCategory();
                                    if ("OBC".equals(ctg)) {
                                        studentCategory.setId(Long.valueOf(2));
                                    } else if ("GENERAL".equalsIgnoreCase(ctg)) {
                                        studentCategory.setId(Long.valueOf(1));
                                    } else if ("SC".equals(ctg)) {
                                        studentCategory.setId(Long.valueOf(3));
                                    } else if ("ST".equals(ctg)) {
                                        studentCategory.setId(Long.valueOf(4));
                                    }
                                    if (studentCategory.getId() != null) {
                                        student.setStudentCategory(studentCategory);
                                    }
                                }
                            }
                        } else if (cell.getColumnIndex() == 24) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setSubCaste(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 25) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setEnrollmentNo(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 26) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setFamilyId(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 27) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setSamagraId(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 28) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setAadharNo(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 29) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setBloodGroup(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 30) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setLanguagesKnown(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 31) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                student.setBirthPlace(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 34) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                studentBankDetails.setIfsc(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 35) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                studentBankDetails.setBankAcNo(cell.getStringCellValue());
                            }
                        } else if (cell.getColumnIndex() == 39) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                AcademicYearClass academicYearClass = new AcademicYearClass();
                                academicYearClass.setId(Long.valueOf(cell.getStringCellValue()));
                                student.setAcademicYearClass(academicYearClass);
                            }
                        } else if (cell.getColumnIndex() == 40) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                BusStop busStop = new BusStop();
                                busStop.setId(Long.valueOf(cell.getStringCellValue()));
                                student.setBusStop(busStop);
                            }
                        } else if (cell.getColumnIndex() == 41) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                Bank bank = new Bank();
                                bank.setId(Long.valueOf(cell.getStringCellValue()));
                                studentBankDetails.setBank(bank);
                            }
                        } else if (cell.getColumnIndex() == 42) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                State state = new State();
                                state.setId(Long.valueOf(cell.getStringCellValue()));
                                address.setState(state);
                            }
                        } else if (cell.getColumnIndex() == 43) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                AdmissionScheme admissionScheme = new AdmissionScheme();
                                admissionScheme.setId(Long.valueOf(cell.getStringCellValue()));
                                student.setAdmissionScheme(admissionScheme);
                            }
                        } else if (cell.getColumnIndex() == 44) {
                            if (!(cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                                StudentStatus studentStatus = new StudentStatus();
                                studentStatus.setId(Long.valueOf(cell.getStringCellValue()));
                                student.setStudentStatus(studentStatus);
                            }
                        } else if (!(cell.getColumnIndex() != 45 || cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
                            Country country = new Country();
                            country.setId(Long.valueOf(cell.getStringCellValue()));
                            address.setCountry(country);
                        }
                    }
                }
                student.setAddress(address);
                Long studentId = this.admissionService.addStudent(student, LoggedinUserAssistant.getLoggedInUserId());
                if (studentBankDetails != null) {
                    student.setId(studentId);
                    studentBankDetails.setStudent(student);
                    this.studentService.updateStudentBankDetails(studentBankDetails, Long.valueOf(1));
                }
            }
        }
    }
}
