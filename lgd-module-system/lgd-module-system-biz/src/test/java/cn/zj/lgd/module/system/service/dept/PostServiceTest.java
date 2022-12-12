package cn.zj.lgd.module.system.service.dept;

import cn.zj.lgd.module.system.dal.dataobject.dept.PostDO;
import cn.zj.lgd.framework.common.enums.CommonStatusEnum;
import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.common.util.collection.ArrayUtils;
import cn.zj.lgd.framework.common.util.object.ObjectUtils;
import cn.zj.lgd.module.system.controller.admin.dept.vo.post.PostCreateReqVO;
import cn.zj.lgd.module.system.controller.admin.dept.vo.post.PostExportReqVO;
import cn.zj.lgd.module.system.controller.admin.dept.vo.post.PostPageReqVO;
import cn.zj.lgd.module.system.controller.admin.dept.vo.post.PostUpdateReqVO;
import cn.zj.lgd.module.system.dal.mysql.dept.PostMapper;
import cn.zj.lgd.framework.test.core.ut.BaseDbUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.Consumer;

import static cn.hutool.core.util.RandomUtil.randomEle;
import static cn.zj.lgd.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.zj.lgd.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.zj.lgd.framework.test.core.util.RandomUtils.randomLongId;
import static cn.zj.lgd.framework.test.core.util.RandomUtils.randomPojo;
import static cn.zj.lgd.module.system.enums.ErrorCodeConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@Import(PostServiceImpl.class)
public class PostServiceTest extends BaseDbUnitTest {

    @Resource
    private PostServiceImpl postService;
    @Resource
    private PostMapper postMapper;

    @Test
    void testPagePosts() {
        // mock 数据
        PostDO postDO = randomPojo(PostDO.class, o -> {
            o.setName("码仔");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        postMapper.insert(postDO);
        // 测试 name 不匹配
        postMapper.insert(ObjectUtils.cloneIgnoreId(postDO, o -> o.setName("程序员")));
        // 测试 status 不匹配
        postMapper.insert(ObjectUtils.cloneIgnoreId(postDO, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));

        // 准备参数
        PostPageReqVO reqVO = new PostPageReqVO();
        reqVO.setName("码");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        PageResult<PostDO> pageResult = postService.getPostPage(reqVO);

        // 断言
        assertEquals(1, pageResult.getTotal());
        assertEquals(1, pageResult.getList().size());
        assertPojoEquals(postDO, pageResult.getList().get(0));
    }

    @Test
    void testListPosts() {
        // mock 数据
        PostDO postDO = randomPojo(PostDO.class, o -> {
            o.setName("码仔");
            o.setStatus(CommonStatusEnum.ENABLE.getStatus());
        });
        postMapper.insert(postDO);
        // 测试 name 不匹配
        postMapper.insert(ObjectUtils.cloneIgnoreId(postDO, o -> o.setName("程序员")));
        // 测试 status 不匹配
        postMapper.insert(ObjectUtils.cloneIgnoreId(postDO, o -> o.setStatus(CommonStatusEnum.DISABLE.getStatus())));
        // 准备参数
        PostExportReqVO reqVO = new PostExportReqVO();
        reqVO.setName("码");
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());

        // 调用
        List<PostDO> list = postService.getPosts(reqVO);
        // 断言
        assertEquals(1, list.size());
        assertPojoEquals(postDO, list.get(0));
    }

    @Test
    void testGetPost() {
        // mock 数据
        PostDO dbPostDO = randomPostDO();
        postMapper.insert(dbPostDO);
        // 准备参数
        Long id = dbPostDO.getId();
        // 调用
        PostDO post = postService.getPost(id);
        // 断言
        assertNotNull(post);
        assertPojoEquals(dbPostDO, post);
    }

    @Test
    void testCreatePost_success() {
        // 准备参数
        PostCreateReqVO reqVO = randomPojo(PostCreateReqVO.class,
            o -> o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()));
        // 调用
        Long postId = postService.createPost(reqVO);
        // 断言
        assertNotNull(postId);
        // 校验记录的属性是否正确
        PostDO post = postMapper.selectById(postId);
        assertPojoEquals(reqVO, post);
    }

    @Test
    void testUpdatePost_success() {
        // mock 数据
        PostDO postDO = randomPostDO();
        postMapper.insert(postDO);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PostUpdateReqVO reqVO = randomPojo(PostUpdateReqVO.class,
            o -> {
                // 设置更新的 ID
                o.setId(postDO.getId());
                o.setStatus(randomEle(CommonStatusEnum.values()).getStatus());
            });
        // 调用
        postService.updatePost(reqVO);
        // 校验是否更新正确
        PostDO post = postMapper.selectById(reqVO.getId());// 获取最新的
        assertPojoEquals(reqVO, post);
    }

    @Test
    void testDeletePost_success() {
        // mock 数据
        PostDO postDO = randomPostDO();
        postMapper.insert(postDO);
        // 准备参数
        Long id = postDO.getId();
        // 调用
        postService.deletePost(id);
        assertNull(postMapper.selectById(id));
    }

    @Test
    void testCheckPost_notFoundForDelete() {
        // 准备参数
        Long id = randomLongId();
        // 调用, 并断言异常
        assertServiceException(() -> postService.deletePost(id), POST_NOT_FOUND);
    }

    @Test
    void testCheckPost_nameDuplicateForCreate() {
        // mock 数据
        PostDO postDO = randomPostDO();
        postMapper.insert(postDO);// @Sql: 先插入出一条存在的数据
        // 准备参数
        PostCreateReqVO reqVO = randomPojo(PostCreateReqVO.class,
            // 模拟 name 重复
            o -> o.setName(postDO.getName()));
        assertServiceException(() -> postService.createPost(reqVO), POST_NAME_DUPLICATE);
    }

    @Test
    void testCheckPost_codeDuplicateForUpdate() {
        // mock 数据
        PostDO postDO = randomPostDO();
        postMapper.insert(postDO);
        // mock 数据 稍后模拟重复它的 code
        PostDO codePostDO = randomPostDO();
        postMapper.insert(codePostDO);
        // 准备参数
        PostUpdateReqVO reqVO = randomPojo(PostUpdateReqVO.class,
            o -> {
                // 设置更新的 ID
                o.setId(postDO.getId());
                // 模拟 code 重复
                o.setCode(codePostDO.getCode());
            });
        // 调用, 并断言异常
        assertServiceException(() -> postService.updatePost(reqVO), POST_CODE_DUPLICATE);
    }

    @SafeVarargs
    private static PostDO randomPostDO(Consumer<PostDO>... consumers) {
        Consumer<PostDO> consumer = (o) -> {
            o.setStatus(randomEle(CommonStatusEnum.values()).getStatus()); // 保证 status 的范围
        };
        return randomPojo(PostDO.class, ArrayUtils.append(consumer, consumers));
    }
}
