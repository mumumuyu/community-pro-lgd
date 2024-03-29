package cn.zj.lgd.module.visualization.framework.jmreport.core.service;

import cn.hutool.core.util.StrUtil;
import cn.zj.lgd.framework.common.exception.ServiceException;
import cn.zj.lgd.framework.security.core.LoginUser;
import cn.zj.lgd.framework.security.core.util.SecurityFrameworkUtils;
import cn.zj.lgd.framework.web.core.util.WebFrameworkUtils;
import cn.zj.lgd.module.system.api.oauth2.OAuth2TokenApi;
import cn.zj.lgd.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;

/**
 * {@link JmReportTokenServiceI} 实现类，提供积木报表的 Token 校验、用户信息的查询等功能
 *
 * @author 随心
 */
@RequiredArgsConstructor
public class JmReportTokenServiceImpl implements JmReportTokenServiceI {

    private final OAuth2TokenApi oauth2TokenApi;

    /**
     * 校验 Token 是否有效，即验证通过
     *
     * @param token JmReport 前端传递的 token
     * @return 是否认证通过
     */
    @Override
    public Boolean verifyToken(String token) {
        if (StrUtil.isEmpty(token)) {
            return false;
        }
        // TODO 如下的实现不算特别优雅，主要咱是不想搞的太复杂，所以参考对应的 Filter 先实现了

        // ① 参考 TokenAuthenticationFilter 的认证逻辑（Security 的上下文清理，交给 Spring Security 完成）
        // 目的：实现基于 JmReport 前端传递的 token，实现认证
        LoginUser user = null;
        try {
            OAuth2AccessTokenCheckRespDTO accessToken = oauth2TokenApi.checkAccessToken(token);
            if (accessToken == null) {
                return false;
            }
            user = new LoginUser().setId(accessToken.getUserId()).setUserType(accessToken.getUserType())
                    .setTenantId(accessToken.getTenantId()).setScopes(accessToken.getScopes());
        } catch (ServiceException ignored) {
            // do nothing：如果报错，说明认证失败，则返回 false 即可
        }
        if (user == null) {
            return false;
        }
        SecurityFrameworkUtils.setLoginUser(user, WebFrameworkUtils.getRequest());
        return true;
    }

    /**
     * 获得用户编号
     *
     * 虽然方法名获得的是 username，实际对应到项目中是用户编号
     *
     * @param token JmReport 前端传递的 token
     * @return 用户编号
     */
    @Override
    public String getUsername(String token) {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return userId != null ? String.valueOf(userId) : null;
    }

}
