package com.narendra.sams.web.restws.enquiry;

import com.narendra.sams.core.util.DateUtil;
import com.narendra.sams.enquiry.domain.ClasswiseEnquiryCount;
import com.narendra.sams.enquiry.domain.Enquiry;
import com.narendra.sams.enquiry.domain.EnquiryBriefInfo;
import com.narendra.sams.enquiry.domain.EnquiryStatus;
import com.narendra.sams.enquiry.domain.StatusWiseEnquiryCount;
import com.narendra.sams.enquiry.service.EnquiryService;
import com.narendra.sams.enquiry.service.EnquiryStatusService;
import com.narendra.sams.web.auth.UserSessionManager;
import com.narendra.sams.web.restws.enquiry.mapper.vo.EnquiryVOMapper;
import com.narendra.sams.web.restws.enquiry.vo.CountVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryIssuedFormVO;
import com.narendra.sams.web.restws.enquiry.vo.EnquiryVO;
import com.narendra.sams.web.restws.vo.QuickSearchResultVO;
import com.narendra.sams.web.utils.Validator;
import com.narendra.uuc.core.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

@Controller
@RequestMapping({"/ws/enquiry/list"})
public class ListEnquiryController {
    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private EnquiryStatusService enquiryStatusService;
    @Autowired
    private UserService userService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/count"})
    public CountVO getCount() {
        long count = this.enquiryService.getCountByStatusName(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), null);
        CountVO countVO = new CountVO();
        countVO.setCount(count);
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/hotcount"})
    public CountVO getHotCount() {
        long count = this.enquiryService.getCountByStatusName(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), EnquiryStatus.HOT);
        EnquiryStatus enquiryStatus = this.enquiryStatusService.getStatusByName(EnquiryStatus.HOT, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        CountVO countVO = new CountVO();
        countVO.setCount(count);
        countVO.setId(enquiryStatus.getId().longValue());
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/inprogresscount"})
    public CountVO getInProgressCount() {
        long count = this.enquiryService.getCountByStatusName(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), EnquiryStatus.IN_PROGRESS);
        EnquiryStatus enquiryStatus = this.enquiryStatusService.getStatusByName(EnquiryStatus.IN_PROGRESS, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        CountVO countVO = new CountVO();
        countVO.setCount(count);
        countVO.setId(enquiryStatus.getId().longValue());
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/completedcount"})
    public CountVO getCompletedCount() {
        long count = this.enquiryService.getCountByStatusName(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), EnquiryStatus.CONVERTED_INTO_ADMISSION);
        EnquiryStatus enquiryStatus = this.enquiryStatusService.getStatusByName(EnquiryStatus.CONVERTED_INTO_ADMISSION, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
        CountVO countVO = new CountVO();
        countVO.setCount(count);
        countVO.setId(enquiryStatus.getId().longValue());
        return countVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/recent"})
    public List<EnquiryVO> getRecentEnquiries() {
        Long academicSession = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        return EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getRecentEnquiries(academicSession), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{academicSessionId}"})
    public List<EnquiryVO> getAllEnquiries(@PathVariable Long academicSessionId) {
        return EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiries(academicSessionId, null), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/classwise/{academicSessionId}"})
    public List<ClasswiseEnquiryCount> getClasswiseEnquiryCount(@PathVariable Long academicSessionId) {
        return this.enquiryService.getClasswiseEnquiryCount(academicSessionId);
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/byclass/{classId}"})
    public List<EnquiryVO> getEnquiriesByClass(@PathVariable Long classId) {
        return EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiriesByClass(classId), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/statuswise/{academicSessionId}"})
    public List<StatusWiseEnquiryCount> getStatusWiseEnquiryCount(@PathVariable Long academicSessionId) {
        return this.enquiryService.getStatusWiseEnquiryCount(academicSessionId, UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/{academicSessionId}/bystatus/{statusId}"})
    public List<EnquiryVO> getEnquiriesByClass(@PathVariable Long academicSessionId, @PathVariable Long statusId) {
        return EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiriesByStatus(academicSessionId, statusId), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/by-contact"})
    public List<EnquiryVO> getEnquiriesByStudentContactNo(String contactNo, String contactOf) {
        Long academicSession = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        return EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiriesByContactNo(academicSession, contactNo, contactOf), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/cities"})
    public List<String> getCities() {
        return this.enquiryService.getEnquiryCities(UserSessionManager.getUserSession(this.webApplicationContext).getWorkingInstituteId());
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/search"})
    public List<QuickSearchResultVO> searchEnquiries(String searchString) {
        List<EnquiryBriefInfo> enquiries = this.enquiryService.getEnquiryBrifInfo(UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId(), searchString);
        List<QuickSearchResultVO> quickSearchResultVOs = new ArrayList();
        if (enquiries != null) {
            for (EnquiryBriefInfo enquiryBriefInfo : enquiries) {
                QuickSearchResultVO quickSearchResultVO = new QuickSearchResultVO();
                quickSearchResultVO.setId(enquiryBriefInfo.getEnquiryId());
                StringBuffer sb = new StringBuffer("");
                sb.append(enquiryBriefInfo.getStudentFirstName());
                if (enquiryBriefInfo.getStudentLastName() != null) {
                    sb.append(" ").append(enquiryBriefInfo.getStudentLastName());
                }
                if (!(enquiryBriefInfo.getStudentFirstName() == null || enquiryBriefInfo.getStudentFirstName().isEmpty())) {
                    if (enquiryBriefInfo.getGender() == null || enquiryBriefInfo.getGender().isEmpty()) {
                        sb.append(" ").append(" S/O ").append(enquiryBriefInfo.getFatherName());
                    } else if ("Male".equals(enquiryBriefInfo.getGender())) {
                        sb.append(" ").append(" S/O ").append(enquiryBriefInfo.getFatherName());
                    } else {
                        sb.append(" ").append(" D/O ").append(enquiryBriefInfo.getFatherName());
                    }
                }
                quickSearchResultVO.setName(sb.toString());
                quickSearchResultVOs.add(quickSearchResultVO);
            }
        }
        return quickSearchResultVOs;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/todays-issued-form"})
    public EnquiryIssuedFormVO getFormIssuedOnToday() {
        Date date = DateUtil.getSystemDateTime();
        EnquiryIssuedFormVO enquiryIssuedFormVO = new EnquiryIssuedFormVO();
        enquiryIssuedFormVO.setFromDate(DateUtil.formatDate(date, "dd-MMM-yyyy"));
        enquiryIssuedFormVO.setToDate(DateUtil.formatDate(date, "dd-MMM-yyyy"));
        Long academicSession = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        enquiryIssuedFormVO.setEnquiryVOs(EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiriesByFormIssueDate(academicSession, date, date), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee()));
        return enquiryIssuedFormVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/issued-form"})
    public EnquiryIssuedFormVO getFormIssued(String fromDateStr, String toDateStr) {
        EnquiryIssuedFormVO enquiryIssuedFormVO = new EnquiryIssuedFormVO();
        enquiryIssuedFormVO.setFromDate(fromDateStr);
        enquiryIssuedFormVO.setToDate(toDateStr);
        Long academicSession = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        enquiryIssuedFormVO.setEnquiryVOs(EnquiryVOMapper.prepareEnquiryVOs(this.enquiryService.getEnquiriesByFormIssueDate(academicSession, Validator.convertToDate(fromDateStr), Validator.convertToDate(toDateStr)), UserSessionManager.getUserSession(this.webApplicationContext).getInstituteSetting().getEnquirySettings().getFormFee()));
        return enquiryIssuedFormVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/issuedFormCount"})
    public CountVO getIssuedFormCount() {
        Long academicSession = UserSessionManager.getUserSession(this.webApplicationContext).getEnquiryAcademicYearId();
        Date date = DateUtil.getSystemDateTime();
        List<Enquiry> enquiries = this.enquiryService.getEnquiriesByFormIssueDate(academicSession, date, date);
        CountVO countVO = new CountVO();
        countVO.setCount(0);
        if (enquiries != null) {
            countVO.setCount((long) enquiries.size());
        }
        return countVO;
    }
}
