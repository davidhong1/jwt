package com.yuan.jwt.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author: mac
 * @date: 2019-01-23
 * @description: BaseResponseBean
 */
public class BRB {

    @Setter @Getter private int status; // 状态
    @Setter @Getter private String msg; // 消息
    @Setter @Getter private Object content; // 内容
    @Setter @Getter private Date timestamp;// 时间

    public BRB(BRStatus brStatus, Object content) {
        this.status = brStatus.getStatus();
        this.msg = brStatus.getMsg();
        this.content = content;
        this.timestamp = new Date();
    }

    public BRB(BRStatus brStatus) {
        this.status = brStatus.getStatus();
        this.msg = brStatus.getMsg();
        this.timestamp = new Date();
    }

}
