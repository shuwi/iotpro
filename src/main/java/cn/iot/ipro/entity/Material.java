package cn.iot.ipro.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 点单物资模型
 */
@Data
@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull(message = "物资类别不能为空")
    private String materialType;
    @Column
    @NotNull(message = "物资名称为空")
    private String materialName;
    @Column
    @NotNull(message = "售价为空")
    private BigDecimal materialPrice;
    @Column(nullable = false, columnDefinition = "longtext comment '缩略图'")
    @NotNull(message = "缩略图为空")
    private String thumbnail;
    @Column
    private String createdBy;
    @Column
    private Date created;
    @Column
    private Date modified;
    @Column
    private String modifiedBy;
}
