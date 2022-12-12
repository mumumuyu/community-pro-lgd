package cn.zj.lgd.framework.common.util.object;

import cn.zj.lgd.framework.common.pojo.PageParam;

/**
 * {@link cn.zj.lgd.framework.common.pojo.PageParam} 工具类
 *
 *
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
