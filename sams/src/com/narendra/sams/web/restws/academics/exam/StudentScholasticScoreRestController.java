package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.exam.domain.StudentScore;
import com.narendra.sams.academics.service.ScholasticScoreService;
import com.narendra.sams.academics.service.TermAssessmentService;
import com.narendra.sams.web.restws.academics.exam.form.ScholasticMarksCollectionForm;
import com.narendra.sams.web.restws.academics.exam.vo.ScholasticScoreCollectionVO;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/scorecollection/scholastic/score"})
public class StudentScholasticScoreRestController {
    @Autowired
    private ScholasticScoreService scholasticScoreService;
    @Autowired
    private TermAssessmentService termAssessmentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/get"})
    public ScholasticScoreCollectionVO getStduentScore(Long classId, Long sectionId, Long subjectId, Long termAssessmentId) {
        ScholasticScoreCollectionVO scholasticScoreCollectionVO = new ScholasticScoreCollectionVO();
        scholasticScoreCollectionVO.setMaxMarks(this.termAssessmentService.getTermAssessment(termAssessmentId).getMaxMarks());
        List<StudentScore> studentScores = this.scholasticScoreService.getScholasticScore(classId, sectionId, subjectId, termAssessmentId);
        if (!(studentScores == null || studentScores.isEmpty())) {
            Collections.sort(studentScores, new BeanComparator("studentName", new NullComparator()));
        }
        scholasticScoreCollectionVO.setStudentScores(studentScores);
        return scholasticScoreCollectionVO;
    }

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/save/{classId}/{subjectId}/{termAssessmentId}"})
    public List<StudentScore> saveStduentScores(@PathVariable Long classId, Long sectionId, @PathVariable Long subjectId, @PathVariable Long termAssessmentId, @RequestBody ScholasticMarksCollectionForm marksCollectionForm) {
        this.scholasticScoreService.saveScholasticScore(classId, subjectId, termAssessmentId, marksCollectionForm.getStudentScores());
        return this.scholasticScoreService.getScholasticScore(classId, sectionId, subjectId, termAssessmentId);
    }
}
