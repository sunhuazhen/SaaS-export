package cn.itcast.dao.system;

import cn.itcast.domain.system.SysLog;

import java.util.List;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/15 14:30
 */
public interface SysLogDao {
    List<SysLog> findAll(String companyId);

    void save(SysLog sysLog);
}
