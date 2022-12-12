package cn.zj.lgd.module.member.convert.social;

import cn.zj.lgd.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import cn.zj.lgd.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import cn.zj.lgd.module.system.api.social.dto.SocialUserBindReqDTO;
import cn.zj.lgd.module.system.api.social.dto.SocialUserUnbindReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SocialUserConvert {

    SocialUserConvert INSTANCE = Mappers.getMapper(SocialUserConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppSocialUserBindReqVO reqVO);

    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

}
