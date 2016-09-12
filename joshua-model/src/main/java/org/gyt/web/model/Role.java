package org.gyt.web.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限角色实体
 * Created by Administrator on 2016/9/12.
 */
@Entity
@Table
public class Role {

    @Id
    private String name;

    @ElementCollection
    private List<String> authorities = new ArrayList<>();

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
