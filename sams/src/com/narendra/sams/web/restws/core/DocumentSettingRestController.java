package com.narendra.sams.web.restws.core;

import com.narendra.sams.admission.domain.Document;
import com.narendra.sams.admission.service.DocumentService;
import com.narendra.sams.core.exception.DuplicateNameFoundException;
import com.narendra.sams.web.literal.AjaxStatus;
import com.narendra.sams.web.restws.core.form.DocumentForm;
import com.narendra.sams.web.restws.core.form.mapper.DocumentFormToDomainMapper;
import com.narendra.sams.web.restws.core.vo.DocumentVO;
import com.narendra.sams.web.restws.core.vo.mapper.DocumentDomainToVOMapper;
import com.narendra.sams.web.restws.vo.AjaxResponse;
import com.narendra.sams.web.utils.LoggedinUserAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/documents"})
public class DocumentSettingRestController {
    @Autowired
    private DocumentService documentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {""})
    public AjaxResponse saveDocument(@RequestBody DocumentForm documentForm) {
        Document document = DocumentFormToDomainMapper.mapToDomain(documentForm);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        try {
            this.documentService.saveDocument(document, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.DUPLICATE.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.PUT}, value = {""})
    public AjaxResponse updateDocument(@RequestBody DocumentForm documentForm) {
        Document document = DocumentFormToDomainMapper.mapToDomain(documentForm);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        try {
            this.documentService.updateDocument(document, LoggedinUserAssistant.getLoggedInUserId());
        } catch (DuplicateNameFoundException e) {
            ajaxResponse.setStatus(AjaxStatus.DUPLICATE.toString());
            e.printStackTrace();
        }
        return ajaxResponse;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{documentId}"})
    public DocumentForm getDocument(@PathVariable Long documentId) {
        return DocumentDomainToVOMapper.mapToForm(this.documentService.getDocument(documentId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/academic-session/{academicSessionId}"})
    public List<DocumentVO> getDocuments(@PathVariable Long academicSessionId) {
        return DocumentDomainToVOMapper.mapToVOs(this.documentService.getDocuments(academicSessionId));
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/{documentId}"})
    public AjaxResponse deleteDocument(@PathVariable Long documentId) {
        this.documentService.deleteDocument(documentId);
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setStatus(AjaxStatus.OK.toString());
        return ajaxResponse;
    }
}
