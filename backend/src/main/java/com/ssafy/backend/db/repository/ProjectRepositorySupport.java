package com.ssafy.backend.db.repository;

import java.util.List;
import java.util.Optional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.backend.api.response.ProjectListTestFetchRes;
import com.ssafy.backend.db.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository

public class ProjectRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    QProject qProject = QProject.project;
    QCompany qCompany = QCompany.company;

    QCompanyInfo qCompanyInfo = QCompanyInfo.companyInfo;

    public Page<Project> findAllWithCompany(Pageable pageable) {
        List<Project> fetch = jpaQueryFactory
                .selectFrom(qProject)
                .join(qProject.company, qCompany)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = jpaQueryFactory
                .selectFrom(qProject)
                .join(qProject.company, qCompany)
                .fetchCount();

        return new PageImpl<>(fetch,pageable,total);
    }

    public Page<ProjectListTestFetchRes> findAllWithCompanyInfo(Pageable pageable) {
        List<ProjectListTestFetchRes> fetch = jpaQueryFactory
                .select(Projections.constructor(ProjectListTestFetchRes.class,
                        qProject, qCompanyInfo))
                .from(qProject)
                .join(qProject.company, qCompany)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(qProject)
                .join(qProject.company, qCompany)
                .fetchCount();

        return new PageImpl<>(fetch,pageable,total);
    }

    public ProjectRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Project.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Optional<Project> findProjectByProjectId(Long pId){
        Project project= jpaQueryFactory.selectFrom(qProject).where(qProject.projectId.eq(pId)).fetchOne();
        return Optional.ofNullable(project) ;
    }

    public List<Project> findProjectsByProjectId(Long pId){
        return jpaQueryFactory.selectFrom(qProject).where(qProject.projectId.eq(pId)).fetch();
    }

    public List<Project> findProjectsByCompanyId(Long companyId){
        return jpaQueryFactory.selectFrom(qProject).where(qProject.company.companyId.eq(companyId)).fetch();
    }



}
