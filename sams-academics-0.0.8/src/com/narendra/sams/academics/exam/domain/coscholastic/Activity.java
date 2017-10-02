package com.narendra.sams.academics.exam.domain.coscholastic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.NullComparator;

public class Activity {
    private String displayName;
    private Long displayOrder;
    private Long id;
    private String name;
    private List<Skill> skills;
    private Map<Long, Skill> skillsMap = new HashMap();
    private List<Activity> subActivities;
    private Map<Long, Activity> subActivitiesMap = new HashMap();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getDisplayOrder() {
        return this.displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Map<Long, Activity> getSubActivitiesMap() {
        return this.subActivitiesMap;
    }

    public void setSubActivitiesMap(Map<Long, Activity> subActivitiesMap) {
        this.subActivitiesMap = subActivitiesMap;
    }

    public Map<Long, Skill> getSkillsMap() {
        return this.skillsMap;
    }

    public void setSkillsMap(Map<Long, Skill> skillsMap) {
        this.skillsMap = skillsMap;
    }

    public List<Activity> getSubActivities() {
        if (this.subActivitiesMap != null) {
            this.subActivities = new ArrayList(this.subActivitiesMap.values());
            Collections.sort(this.subActivities, new BeanComparator("displayOrder", new NullComparator()));
        }
        return this.subActivities;
    }

    public List<Skill> getSkills() {
        if (this.skillsMap != null) {
            this.skills = new ArrayList(this.skillsMap.values());
            Collections.sort(this.skills, new BeanComparator("displayOrder", new NullComparator()));
        }
        return this.skills;
    }
}
