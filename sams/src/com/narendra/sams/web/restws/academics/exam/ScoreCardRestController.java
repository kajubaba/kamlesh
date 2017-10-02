package com.narendra.sams.web.restws.academics.exam;

import com.narendra.sams.academics.domain.ScoreCard;
import com.narendra.sams.academics.service.ScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ws/academics/scorecard"})
public class ScoreCardRestController {
    @Autowired
    private ScoreCardService scoreCardService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.GET}, value = {"/sample/{examPatternId}"})
    public ScoreCard getBlankScoreCard(@PathVariable Long examPatternId) {
        return this.scoreCardService.getBlankScoreCard(examPatternId);
    }
}
