package cn.zj.lgd.module.member.api.address;

import cn.zj.lgd.module.member.api.address.dto.AddressRespDTO;
import cn.zj.lgd.module.member.convert.address.AddressConvert;
import cn.zj.lgd.module.member.service.address.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 用户收件地址 API 实现类
 *
 *
 */
@Service
@Validated
public class AddressApiImpl implements AddressApi {

    @Resource
    private AddressService addressService;

    @Override
    public AddressRespDTO getAddress(Long id, Long userId) {
        return AddressConvert.INSTANCE.convert02(addressService.getAddress(userId, id));
    }

}
