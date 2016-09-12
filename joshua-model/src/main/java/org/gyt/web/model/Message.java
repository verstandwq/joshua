package org.gyt.web.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import java.util.Date;

/**
 * 留言信息实体
 * Created by Administrator on 2016/9/12.
 */
@Entity
@Table
public class Message {

    @Id
    private Long id;

    private User owner;

    private Date createdDate;

    private MessageType type;

    @Max(100)
    private String content;

    /**
     * 联系方式，可以是电话，邮箱或者地址
     */
    private String contact;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
