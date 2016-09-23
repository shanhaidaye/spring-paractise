package org.register.web;

import org.register.dto.Exposer;
import org.register.dto.SeckillResult;
import org.register.entity.Seckill;
import org.register.enums.SeckillStateEnum;
import org.register.exception.RepeatKillException;
import org.register.service.SeckillService;
import org.register.dto.SeckillExecution;
import org.register.exception.SeckillCloseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by sunshiwang on 16/5/22.
 */
@Controller
@RequestMapping("/seckill")//url :模块/ 资源 /id /细分
public class SeckillController {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Autowired
   private SeckillService seckillService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){

        //list.jsp+model=ModelAndView
        List<Seckill> list=seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value="/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId, Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";
        }
        Seckill seckill=seckillService.getById(seckillId);
        model.addAttribute("seckill",seckill);
        if(seckill==null){
            return "forward:/seckill/list";
        }
        return "detail";
    }

    //ajax 返回json
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
    produces ={"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> /*TODO*/ exposer(@PathVariable("seckillId") Long seckillId){

        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,
    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5")String md5,
                                                   @CookieValue(value = "killPhone",required = false)Long phone){

        //如果多参数 用 springmvc valid  验证信息
        if(phone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        try {
            SeckillExecution seckillExecution=seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,seckillExecution);
        }catch (RepeatKillException e){
            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (SeckillCloseException e){
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.END);
            return new SeckillResult<SeckillExecution>(true,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true,execution);
        }

    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Long> time(){

        return new SeckillResult<Long>(true,new Date().getTime());

    }
}
