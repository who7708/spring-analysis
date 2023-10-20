drop table t_distribute_lock;
CREATE TABLE `t_distribute_lock`
(
    `key_resource` varchar(45)      NOT NULL DEFAULT '资源主键',
    `status`       char(1)          NOT NULL DEFAULT '' COMMENT 'S,F,P',
    `lock_flag`    int(10) unsigned NOT NULL DEFAULT '0' COMMENT '1是已经锁 0是未锁',
    `begin_time`   datetime                  DEFAULT NULL COMMENT '开始时间',
    `end_time`     datetime                  DEFAULT NULL COMMENT '结束时间',
    `client_ip`    varchar(45)      NOT NULL DEFAULT '抢到锁的IP',
    `time`         int(10) unsigned NOT NULL DEFAULT '60' COMMENT '方法生命周期内只允许一个结点获取一次锁，单位：分钟',
    PRIMARY KEY (`key_resource`) USING BTREE
) ENGINE = InnoDB
;

CREATE TABLE time_demo_kalacloud
(
    `timestamp` timestamp,
    `datetime`  datetime
);
insert into time_demo_kalacloud
values (NULL, NULL),
       (now(), now()),
       ('19970701171207', '19970701171207');

select *
from time_demo_kalacloud;

CREATE TABLE `test_uuid_int_sort`
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `dept_no`   varchar(64) DEFAULT '',
    `dept_name` varchar(32) DEFAULT '',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
;

alter table test_uuid_int_sort add index idx_dept_no(dept_no);
alter table test_uuid_int_sort add index idx_dept_name(dept_name);
alter table test_uuid_int_sort add index idx_dept_no_name(dept_no, dept_name);

alter table test_uuid_int_sort drop index idx_dept_no;
alter table test_uuid_int_sort drop index idx_dept_name;

# show global variables like 'local_infile';
# show global variables like 'innodb_file_per_table';
# set global local_infile=1;
# load data local infile 'D:\\git\\GitHub\\spring-analysis\\build\\test_uuid_int_sort_1.csv' into table `test`.`test_uuid_int_sort` fields escaped by '\\' terminated by ',' lines terminated by '\n' (`dept_no`, `dept_name`);

explain select count(*) from test_uuid_int_sort;
explain select count(id) from test_uuid_int_sort;
explain select count(1) from test_uuid_int_sort;

# 未加索引前，大概执行 8.5s
# 加索引后， 50ms 左右，与直接使用 id 查询基本相同
explain select * from test_uuid_int_sort where id = 10485611;
explain select * from test_uuid_int_sort where dept_no = '235236842001010075509188762564150080478';
explain select * from test_uuid_int_sort where dept_no = '10000000314592718599937051572897212786';
explain select * from test_uuid_int_sort where dept_no = '315758863383982355776035755525511660616';
explain select * from test_uuid_int_sort where dept_no like '2352368%';
explain select * from test_uuid_int_sort where dept_no like '1000000031%';
explain select * from test_uuid_int_sort where dept_no like '3157588%';
explain select id,dept_no from test_uuid_int_sort where dept_no = '235236842001010075509188762564150080478';
explain select id,dept_no from test_uuid_int_sort where dept_no = '10000000314592718599937051572897212786';
explain select * from test_uuid_int_sort where dept_no > '235236842001010075509188762564150080478' ;
explain select * from test_uuid_int_sort where dept_name = 'b0f8f7c1f4bb4d7ab7a5c19d0bc2b3de';
explain select * from test_uuid_int_sort where dept_name > 'b0f8f7c1f4bb4d7ab7a5c19d0bc2b3de';

insert into test_uuid_int_sort(dept_no, dept_name) values ('31575889980768754187651633727675198487','17c14b9274474eadab45fa5a970a6c17');


