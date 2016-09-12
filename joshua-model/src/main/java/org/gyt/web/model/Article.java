package org.gyt.web.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

/**
 * 文章实体
 * Created by Administrator on 2016/9/12.
 */
@Entity
@Table
public class Article {

    @Id
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn
    @Cascade(CascadeType.ALL)
    private Fellowship fellowship;

    private Date createdDate;

    private Date lastModifiedTime;

    @Enumerated(value = EnumType.STRING)
    private ArticleStatus status = ArticleStatus.RAW;

    private User author;

    private User lastModifiedUser;

    private User auditor;

    private String auditComment;

    private Long pageView;

    @Lob
    private String content;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fellowship getFellowship() {
        return fellowship;
    }

    public void setFellowship(Fellowship fellowship) {
        this.fellowship = fellowship;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public User getAuditor() {
        return auditor;
    }

    public void setAuditor(User auditor) {
        this.auditor = auditor;
    }

    public String getAuditComment() {
        return auditComment;
    }

    public void setAuditComment(String auditComment) {
        this.auditComment = auditComment;
    }

    public Long getPageView() {
        return pageView;
    }

    public void setPageView(Long pageView) {
        this.pageView = pageView;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
