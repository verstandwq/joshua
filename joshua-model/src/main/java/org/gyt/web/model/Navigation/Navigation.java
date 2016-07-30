package org.gyt.web.model.Navigation;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 导航栏实体，一个导航栏可以包含多个导航栏
 * Created by AaricTech on 2016/7/28.
 */
@Entity
@Table
public class Navigation {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String link;

    /**
     * 导航栏排列顺序，最小的排在最前面
     */
    private Integer order;

    @OneToMany
    @JoinColumn(name = "nav_id", referencedColumnName = "id")
    private List<Navigation> navigationList = new ArrayList<>();
}
