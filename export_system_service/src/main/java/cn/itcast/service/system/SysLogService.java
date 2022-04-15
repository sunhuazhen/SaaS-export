package cn.itcast.service.system;

import cn.itcast.domain.system.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * Copyright 2022 juzishu.com Inc. All Rights Reserved.
 *
 * @author sunzhen
 * @date 2022/4/15 14:32
 */
public interface SysLogService {
    PageInfo findPage(String companyId, Integer pageNum, Integer pageSize);

    void save(SysLog sysLog);
}
