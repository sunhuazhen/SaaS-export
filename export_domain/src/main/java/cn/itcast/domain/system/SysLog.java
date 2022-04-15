package cn.itcast.domain.system;

import lombok.Data;

import java.util.Date;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/15 14:28
 */
@Data
public class SysLog {
    private String id;
    private String userName;
    private String ip;
    private Date time;
    private String method;
    private String action;
    private String companyId;
    private String companyName;
}
