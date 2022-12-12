package cn.zj.lgd.module.system.api.errorcode;

import cn.zj.lgd.module.system.api.errorcode.dto.ErrorCodeAutoGenerateReqDTO;
import cn.zj.lgd.module.system.api.errorcode.dto.ErrorCodeRespDTO;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 错误码 Api 接口
 *
 *
 */
public interface ErrorCodeApi {

    /**
     * 自动创建错误码
     *
     * @param autoGenerateDTOs 错误码信息
     */
    void autoGenerateErrorCodes(@Valid List<ErrorCodeAutoGenerateReqDTO> autoGenerateDTOs);

    /**
     * 增量获得错误码数组
     *
     * 如果 minUpdateTime 为空时，则获取所有错误码
     *
     * @param applicationName 应用名
     * @param minUpdateTime 最小更新时间
     * @return 错误码数组
     */
    List<ErrorCodeRespDTO> getErrorCodeList(String applicationName, LocalDateTime minUpdateTime);

}
