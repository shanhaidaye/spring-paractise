package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.register.dao.SeckillDao;
import org.register.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * Created by sunshiwang on 16/5/18.
 * 配置spring和junit整合,junit启动时候家在spring IOC容器
 * spring-test,junit
 *
 * */
//启动时加载spring IOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {


    //注入dao实现类依赖

    @Resource
    private SeckillDao seckillDao;
    @Test
    public void testReduceNumber() throws Exception {

        /**
         * 21:26:29.202 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@5c86a017]
         * will not be managed by Spring(jdbc连接没有被spring所托管,c3po拿到的)
         21:26:29.246 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>
         Preparing: update seckill set number=number-1 where seckill_id = ? and start_time <= ? and end_time >=? and number >0
         21:26:29.321 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>
         Parameters: 10000(Long), 2016-05-18 21:26:28.832(Timestamp), 2016-05-18 21:26:28.832(Timestamp)
         21:26:29.322 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
         21:26:29.323 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@43f02ef2]
         1
         */
        int updateCount= seckillDao.reduceNumber(10000L,new Date());
        System.out.println(updateCount);
    }

    /**
     *     List<Seckill> queryAll(int offet,int limit);
     *     java 没有保存形参的记录,queryAll(int offet,int limit);->queryAll(arg0,arg1);
     *     多个参数,告诉mybatis每个位置参数的名字 Dao接口通过注解声明下
     *     queryAll(@Param("offset") int offset,@Param("limit") int limit);
     * @throws Exception
     */
    @Test
    public void testQueryById() throws Exception {
        long id=10000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckillList=seckillDao.queryAll(0,100);
        for(Seckill seckill:seckillList){
            System.out.println(seckill);
        }
    }
}