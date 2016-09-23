package org.register.service;

import org.register.dto.Exposer;
import org.register.dto.SeckillExecution;
import org.register.entity.Seckill;
import org.register.exception.RepeatKillException;
import org.register.exception.SeckillCloseException;
import org.register.exception.SeckillException;

import java.util.List;

/**
 * Created by sunshiwang on 16/5/18.
 * 业务接口:站在使用者角度设计接口
 * 三个方面:方法定义粒度;参数;返回类型(return 类型/异常)
 */
public interface SeckillService {

    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);


    /**
     * 秒杀开启时,输出秒杀接口地址,否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;


    /**
     *执行秒杀过程 by 存储过程
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;
}
