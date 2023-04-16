package com.ssafy.backend.api.response;

import com.ssafy.backend.db.entity.Company;
import com.ssafy.backend.db.entity.CompanyInfo;
import com.ssafy.backend.db.entity.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectListTestFetchRes {

    Long projectId;

    String category;

    String locationSi;

    String locationGu;

    String skill;

    String title;

    String content;

    Date startDate;

    Date endDate;

    Date deadline;

    int recruitNumber;

    String task;

    String workStyle;

    String workStartTime;

    String workEndTime;

    String lowPrice;

    String highPrice;

    int careerPeriod;

    String domain;

    Company company;

    CompanyInfo companyInfo;

    public ProjectListTestFetchRes(Project project, CompanyInfo companyInfo) {
        this.projectId = project.getProjectId();
        this.category = project.getCategory();
        this.locationSi = project.getLocationSi();
        this.locationGu = project.getLocationGu();
        this.skill = project.getSkill();
        this.title = project.getTitle();
        this.content = project.getContent();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.deadline = project.getDeadline();
        this.recruitNumber = project.getRecruitNumber();
        this.task = project.getTask();
        this.workStyle = project.getWorkStyle();
        this.workStartTime = project.getWorkStartTime();
        this.workEndTime = project.getWorkEndTime();
        this.lowPrice = project.getLowPrice();
        this.highPrice = project.getHighPrice();
        this.careerPeriod = project.getCareerPeriod();
        this.domain = project.getDomain();
        this.company = companyInfo.getCompany();
        this.companyInfo = companyInfo;
    }
}
