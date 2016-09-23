package org.seckill.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.register.dto.Exposer;
import org.register.dto.SeckillExecution;
import org.register.entity.Seckill;
import org.register.exception.RepeatKillException;
import org.register.exception.SeckillCloseException;
import org.register.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

/**
 * Created by sunshiwang on 16/5/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:/spring/spring-service.xml"
})
public class SeckillServiceImplTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;
    @Test
    public void testGetSeckillList() throws Exception {

        List<Seckill> list=seckillService.getSeckillList();
        logger.info("list={}",list);
    }

    @Test
    public void testGetById() throws Exception {
        Seckill seckill=seckillService.getById(100001L);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void testSeckillLogic() throws Exception {
        long id=10000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        logger.info("exposer={}",exposer);

        if(exposer.isExposed()){

            long phone=18911247965L;
            String md5=exposer.getMd5();
            try {
                SeckillExecution seckillExecution=seckillService.executeSeckill(id,phone,md5);
                logger.info("seckillExcution={}",seckillExecution);
            }catch (RepeatKillException e){
                logger.error(e.getMessage());
            }catch (SeckillCloseException e){
                logger.error(e.getMessage());
            }
        }else {
            logger.warn("exposer={}",exposer);
        }

    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id=10000;
        long phone=18911243995L;
        String md5="e7befbb260e5c0bf3d938b7f86848832";
        try {
            SeckillExecution seckillExecution=seckillService.executeSeckill(id,phone,md5);
            logger.info("seckillExcution={}",seckillExecution);
        }catch (RepeatKillException e){

        }


    }

    @Test
    public void testExecuteSeckillProcedure(){
        long seckillId=10002;
        long phone=18901273390L;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5=exposer.getMd5();
            SeckillExecution execution=seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info(execution.getStateInfo());
        }else {
            logger.warn("not exposed");
        }

    }
}