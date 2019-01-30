package com.yuan.jwt.dto;

import lombok.Getter;
import lombok.Setter;

public enum BRStatus {

    SUCCESS(200, "SUCCESS"),
    CODEERROE(726, "验证码错误或已失效");

    @Setter @Getter private final int status;
    @Setter @Getter private final String msg;

    BRStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
