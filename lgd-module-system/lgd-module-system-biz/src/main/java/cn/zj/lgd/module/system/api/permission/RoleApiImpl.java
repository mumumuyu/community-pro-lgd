package cn.zj.lgd.module.system.api.permission;

import cn.zj.lgd.module.system.service.permission.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 角色 API 实现类
 *
 *
 */
@Service
public class RoleApiImpl implements RoleApi {

    @Resource
    private RoleService roleService;

    @Override
    public void validRoles(Collection<Long> ids) {
        roleService.validRoles(ids);
    }
}