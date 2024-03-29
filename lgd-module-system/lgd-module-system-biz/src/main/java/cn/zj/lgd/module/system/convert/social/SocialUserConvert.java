package cn.zj.lgd.module.system.convert.social;

import cn.zj.lgd.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.zj.lgd.module.system.api.social.dto.SocialUserUnbindReqDTO;
import cn.zj.lgd.module.system.controller.admin.socail.vo.SocialUserBindReqVO;
import cn.zj.lgd.module.system.controller.admin.socail.vo.SocialUserUnbindReqVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, SocialUserBindReqVO reqVO);

    SocialUserUnbindReqDTO convert(Long userId, Integer userType, SocialUserUnbindReqVO reqVO);

}
