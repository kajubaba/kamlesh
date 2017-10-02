package com.narendra.sams.web.restws.student;

import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.admission.domain.Student;
import com.narendra.sams.admission.domain.StudentDocument;
import com.narendra.sams.admission.service.StudentDocumentService;
import com.narendra.sams.web.restws.student.form.StudentDocumentUploadForm;
import com.narendra.sams.web.restws.student.vo.StudentDocumentVO;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/student/document"})
public class StudentDocumentRestController {
    @Autowired
    ServletContext servletContext;
    @Autowired
    private StudentDocumentService studentDocumentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/getall"})
    public List<StudentDocumentVO> getStudentDocuments(Long studentId) {
        return prepareStudentDocumentVO(this.studentDocumentService.getStudentDocuments(studentId), studentId);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/upload"})
    public List<StudentDocumentVO> uploadStudentDocument(StudentDocumentUploadForm studentDocumentUploadForm) {
        StudentDocument studentDocument = new StudentDocument();
        if (studentDocumentUploadForm.getDocumentId() != null) {
            studentDocument.setId(studentDocumentUploadForm.getDocumentId());
        }
        Student student = new Student();
        student.setId(studentDocumentUploadForm.getStudentId());
        studentDocument.setStudent(student);
        if (studentDocumentUploadForm.getDocumentCategoryId() != null) {
            Document document = new Document();
            document.setId(studentDocumentUploadForm.getDocumentCategoryId());
            studentDocument.setDocument(document);
        }
        studentDocument.setDocName(studentDocumentUploadForm.getDocumentName());
        studentDocument.setComments(studentDocumentUploadForm.getComments());
        Long documentId = this.studentDocumentService.saveStudentDocument(studentDocument, LoggedinUserAssistant.getLoggedInUserId());
        String filePath = documentId + "_" + studentDocumentUploadForm.getDocumentFile().getOriginalFilename();
        this.studentDocumentService.updateDocumentPath(documentId, filePath);
        System.out.println(studentDocumentUploadForm.getDocumentFile().getContentType());
        try {
            studentDocumentUploadForm.getDocumentFile().transferTo(new File(this.servletContext.getRealPath("/resources/studentdocs/" + filePath)));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return prepareStudentDocumentVO(this.studentDocumentService.getStudentDocuments(studentDocumentUploadForm.getStudentId()), studentDocumentUploadForm.getStudentId());
    }

    private List<StudentDocumentVO> prepareStudentDocumentVO(List<StudentDocument> studentDocuments, Long studentId) {
        List<StudentDocumentVO> studentDocumentVOs = new ArrayList();
        if (studentDocuments != null) {
            for (StudentDocument studentDocument : studentDocuments) {
                StudentDocumentVO studentDocumentVO = new StudentDocumentVO();
                studentDocumentVO.setStudentId(studentId);
                if (studentDocument.getId() != null) {
                    studentDocumentVO.setUploadeDocumentId(studentDocument.getId());
                    studentDocumentVO.setUploaded(Boolean.TRUE);
                    studentDocumentVO.setUploadedDocumentPath("resources/studentdocs/" + studentDocument.getDocPath());
                } else {
                    studentDocumentVO.setUploaded(Boolean.FALSE);
                }
                if (studentDocument.getDocument() != null) {
                    studentDocumentVO.setDocumentIdToBeUploaded(studentDocument.getDocument().getId());
                    studentDocumentVO.setMandatory(studentDocument.getDocument().getMandatory());
                    studentDocumentVO.setDocumentCategory(studentDocument.getDocument().getDocCategory());
                    studentDocumentVO.setDocAcademicYear(studentDocument.getDocument().getAcademicYear().getName());
                }
                studentDocumentVO.setDocumentName(studentDocument.getDocName());
                studentDocumentVO.setComments(studentDocument.getComments());
                studentDocumentVOs.add(studentDocumentVO);
            }
        }
        return studentDocumentVOs;
    }
}
