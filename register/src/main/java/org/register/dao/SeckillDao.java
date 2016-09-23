package org.register.dao;

import org.apache.ibatis.annotations.Param;
import org.register.entity.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sunshiwang on 16/5/17.
 */
public interface SeckillDao {

    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return 如果影响行数>0,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 根据id查询秒杀接口
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 跟据偏移量查询秒杀商品列表
     * @param offet
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offet,@Param("limit") int limit);

    /**
     * 使用存储过程执行秒杀
     * @param paramMap
     */
    void killByProcedure(Map<String,Object> paramMap);
}
