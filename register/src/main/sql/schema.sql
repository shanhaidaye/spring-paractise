--数据库初始化脚本
CREATE DATABASE seckill
-- 使用数据库
use seckill
--创建秒杀库存表

CREATE TABLE seckill(
seckill_id bigint not NULL auto_increment comment '商品库存id',
name varchar(120) not NULL comment '商品名称',
number varchar(120) not null comment '库存数量',
start_time TIMESTAMP NOT NULL comment '秒杀开始时间',
end_time TIMESTAMP  NOT NULL  comment '秒杀结束时间',
create_time TIMESTAMP NOT null DEFAULT  CURRENT_TIMESTAMP comment '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=Innodb AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

insert into seckill(name,number,start_time,end_time)values('1000元秒杀iphone6',100,'2016-05-16 00:00:00','2016-05-20 00:00:00'),
('500元秒杀ipad2',100,'2016-05-16 00:00:00','2016-05-20 00:00:00'),
('1000元秒杀小米4',100,'2016-05-16 00:00:00','2016-05-20 00:00:00'),
('200元秒杀红米note',100,'2016-05-16 00:00:00','2016-05-20 00:00:00');

--秒杀成功明细表
-- 用户登陆认证相关信息
create table success_killed(

seckill_id bigint NOT NULL comment '秒杀商品id',
user_phone bigint NOT NULL comment '用户手机号',
state tinyint not NULL DEFAULT -1 comment '状态表示 -1:无效 0:成功 1已付款',
create_time TIMESTAMP NOT NULL comment '创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)
)engine=Innodb  DEFAULT charset=utf8 comment='秒杀成功明细';

