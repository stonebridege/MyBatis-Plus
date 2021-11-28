package com.stonebridge.itcastmpspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    @TableField(select = false) //该字段不加入查询语句
    private String password;
    private String name;
    private Integer age;
    @TableField(value = "email") //解决字段名不一致
    private String mail;
    @TableField(exist = false)  //该字段在数据库表中不存在
    private String address;
}
