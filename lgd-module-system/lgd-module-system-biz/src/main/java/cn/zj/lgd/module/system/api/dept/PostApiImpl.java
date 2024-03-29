package cn.zj.lgd.module.system.api.dept;

import cn.zj.lgd.module.system.service.dept.PostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 岗位 API 实现类
 *
 *
 */
@Service
public class PostApiImpl implements PostApi {

    @Resource
    private PostService postService;

    @Override
    public void validPosts(Collection<Long> ids) {
        postService.validPosts(ids);
    }
}
