package cn.zj.lgd.module.product.service.sku;

import cn.zj.lgd.framework.test.core.ut.BaseDbUnitTest;
import cn.zj.lgd.framework.test.core.util.AssertUtils;
import cn.zj.lgd.module.product.api.sku.dto.ProductSkuUpdateStockReqDTO;
import cn.zj.lgd.module.product.dal.dataobject.sku.ProductSkuDO;
import cn.zj.lgd.module.product.dal.mysql.sku.ProductSkuMapper;
import cn.zj.lgd.module.product.service.property.ProductPropertyService;
import cn.zj.lgd.module.product.service.property.ProductPropertyValueService;
import cn.zj.lgd.module.product.service.spu.ProductSpuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

import static cn.zj.lgd.framework.test.core.util.RandomUtils.randomPojo;
import static cn.zj.lgd.module.product.enums.ErrorCodeConstants.SKU_STOCK_NOT_ENOUGH;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

/**
 * {@link ProductSkuServiceImpl} 的单元测试
 *
 *
 */
@Import(ProductSkuServiceImpl.class)
public class ProductSkuServiceTest extends BaseDbUnitTest {

    @Resource
    private ProductSkuService productSkuService;

    @Resource
    private ProductSkuMapper productSkuMapper;

    @MockBean
    private ProductSpuService productSpuService;
    @MockBean
    private ProductPropertyService productPropertyService;
    @MockBean
    private ProductPropertyValueService productPropertyValueService;

    @Test
    public void testUpdateSkuStock_incrSuccess() {
        // 准备参数
        ProductSkuUpdateStockReqDTO updateStockReqDTO = new ProductSkuUpdateStockReqDTO()
                .setItems(singletonList(new ProductSkuUpdateStockReqDTO.Item().setId(1L).setIncrCount(10)));
        // mock 数据
        productSkuMapper.insert(randomPojo(ProductSkuDO.class, o -> o.setId(1L).setSpuId(10L).setStock(20)));

        // 调用
        productSkuService.updateSkuStock(updateStockReqDTO);
        // 断言
        ProductSkuDO sku = productSkuMapper.selectById(1L);
        assertEquals(sku.getStock(), 30);
        verify(productSpuService).updateSpuStock(argThat(spuStockIncrCounts -> {
            assertEquals(spuStockIncrCounts.size(), 1);
            assertEquals(spuStockIncrCounts.get(10L), 10);
            return true;
        }));
    }

    @Test
    public void testUpdateSkuStock_decrSuccess() {
        // 准备参数
        ProductSkuUpdateStockReqDTO updateStockReqDTO = new ProductSkuUpdateStockReqDTO()
                .setItems(singletonList(new ProductSkuUpdateStockReqDTO.Item().setId(1L).setIncrCount(-10)));
        // mock 数据
        productSkuMapper.insert(randomPojo(ProductSkuDO.class, o -> o.setId(1L).setSpuId(10L).setStock(20)));

        // 调用
        productSkuService.updateSkuStock(updateStockReqDTO);
        // 断言
        ProductSkuDO sku = productSkuMapper.selectById(1L);
        assertEquals(sku.getStock(), 10);
        verify(productSpuService).updateSpuStock(argThat(spuStockIncrCounts -> {
            assertEquals(spuStockIncrCounts.size(), 1);
            assertEquals(spuStockIncrCounts.get(10L), -10);
            return true;
        }));
    }

    @Test
    public void testUpdateSkuStock_decrFail() {
        // 准备参数
        ProductSkuUpdateStockReqDTO updateStockReqDTO = new ProductSkuUpdateStockReqDTO()
                .setItems(singletonList(new ProductSkuUpdateStockReqDTO.Item().setId(1L).setIncrCount(-30)));
        // mock 数据
        productSkuMapper.insert(randomPojo(ProductSkuDO.class, o -> o.setId(1L).setSpuId(10L).setStock(20)));

        // 调用并断言
        AssertUtils.assertServiceException(() -> productSkuService.updateSkuStock(updateStockReqDTO),
                SKU_STOCK_NOT_ENOUGH);
    }

}
