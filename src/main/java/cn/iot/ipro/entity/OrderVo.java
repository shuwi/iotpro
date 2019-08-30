package cn.iot.ipro.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class OrderVo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "联系人不能为空")
    @Column
    private String userName;
    @NotEmpty(message = "联系电话不能为空")
    @Column
    private String userPhone;
    @NotEmpty(message = "点单房间不能为空")
    @Column
    private String roomType;
    @OneToMany(mappedBy = "orderVo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //级联保存、更新、删除、刷新;延迟加载。当删除物料，会级联删除该物料的所有订单
    //拥有mappedBy注解的实体类为关系被维护端
    private List<Orders> ordersList = new ArrayList<>();//订单列表
    @Column
    private Date createdDate;
}
