package org.register.dao.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.register.dao.SuccellKilledDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by shanhaidaye on 2016/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:/spring/spring-service.xml"
})
public class RedisDaoTest {

    @Resource
    SuccellKilledDao succellKilledDao;

    @Test
    public void testPutSeckill() throws Exception {
        System.out.println();
    }
}