package com.my.db.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@ApiModel(description = "會員 Member table 資料")
@Data
@Entity
@Table(name = "member",
        indexes = {
                @Index(name = "member_idx_1",  columnList="account, isActive"),
                @Index(name = "member_idx_2",  columnList="account, password")
        }
)
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name="native", strategy="native")
    @ApiModelProperty(required=true, readOnly=true, position=1, value="會員ID")
    protected Long id;

    @ApiModelProperty(required = true, value = "會員登入帳號")
    @Column(nullable = false, length = 40, unique = true, updatable = false)
    protected String account;

    @ApiModelProperty(required = true, value = "會員登入密碼")
    @Column(nullable = false, length = 512)
    protected String password;

    @ApiModelProperty(required = true, value = "是否啟用, 0->否/1->是")
    @Column(nullable = false, length = 5)
    protected int isActive;

    @ApiModelProperty(required = true, value = "會員名稱")
    @Column(nullable = false, length = 20)
    protected String name;

    @ApiModelProperty(required = true, value = "誰建立", readOnly = true)
    @Column(nullable = false, length = 50)
    protected String createBy;

    @ApiModelProperty(value = "建立記錄日期", required = true, readOnly = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected Date createDate;

    @ApiModelProperty(required = false, value = "誰更新", readOnly = true)
    @Column(length = 50)
    protected String updateBy;

    @ApiModelProperty(value = "更新記錄日期", readOnly = true, required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, insertable = false, updatable = false, columnDefinition = "TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP")
    protected Date updateDate;


}

