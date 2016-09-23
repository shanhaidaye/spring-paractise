package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.register.dao.SuccellKilledDao;
import org.register.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sunshiwang on 16/5/18.
 * command+shift+t 添加测试类
 */

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
public class SuccellKilledDaoTest {

    @Resource
    SuccellKilledDao succellKilledDao;
    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id=10002L;
        long phone=18901273995L;
        int insertCount=succellKilledDao.insertSuccessKilled(id,phone);
        System.out.println("insertCount="+insertCount);

        //因为唯一索引 一个手机号只能对应同一个商品
        /**
         * 21:34:05.868 [main] DEBUG o.m.s.t.SpringManagedTransaction -
         * JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@48f5bde6] will not be managed by Spring
         21:34:05.891 [main] DEBUG o.s.d.S.insertSuccessKilled - ==>
         Preparing: insert ignore into success_killed(seckill_id,user_phone) values(?,?)
         21:34:05.941 [main] DEBUG o.s.d.S.insertSuccessKilled - ==> Parameters: 10000(Long), 18901273995(Long)
         21:34:05.945 [main] DEBUG o.s.d.S.insertSuccessKilled - <==    Updates: 1
         21:34:05.954 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@10b892d5]
         insertCount=1
         */
    }

    @Test
    public void testQueryByIdWitchSeckill() throws Exception {
        long id=10002L;
        long phone=18901273995L;
        SuccessKilled successKilled=succellKilledDao.queryByIdWithSeckill(id,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());

        /**
         * 21:45:52.095 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@35038141] will not be managed by Spring
         21:45:52.131 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>
         Preparing: select sk.seckill_id, sk.user_phone, sk.create_time, sk.state, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id= s.seckill_id where sk.seckill_id=? and sk.user_phone= ?
         21:45:52.177 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 10001(Long), 18901273995(Long)
         21:45:52.274 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
         21:45:52.286 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@10b892d5]
         SuccessKilled{seckillId=10001, userPhone=18901273995, state=-1, createTime=Wed May 18 21:36:02 CST 2016}
         Seckill{seckillId=10001, name='500元秒杀ipad2', number=100, startTime=Wed May 18 21:11:29 CST 2016, endTime=Fri May 20 00:00:00 CST 2016, createTime=Mon May 16 00:00:00 CST 2016}
         */
    }
}