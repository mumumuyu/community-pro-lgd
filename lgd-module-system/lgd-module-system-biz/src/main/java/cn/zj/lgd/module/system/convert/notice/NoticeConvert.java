package cn.zj.lgd.module.system.convert.notice;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.system.controller.admin.notice.vo.NoticeCreateReqVO;
import cn.zj.lgd.module.system.controller.admin.notice.vo.NoticeRespVO;
import cn.zj.lgd.module.system.controller.admin.notice.vo.NoticeUpdateReqVO;
import cn.zj.lgd.module.system.dal.dataobject.notice.NoticeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoticeConvert {

    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    PageResult<NoticeRespVO> convertPage(PageResult<NoticeDO> page);

    NoticeRespVO convert(NoticeDO bean);

    NoticeDO convert(NoticeUpdateReqVO bean);

    NoticeDO convert(NoticeCreateReqVO bean);

}
