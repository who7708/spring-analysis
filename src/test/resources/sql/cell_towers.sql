# 数据集下载
# https://datasets.clickhouse.com/cell_towers.csv.xz
# xz -d cell_towers.csv.xz
# load data infile '/Users/chmy/Downloads/cell_towers.csv' into table cell_towers fields terminated by ',' IGNORE 1 ROWS;
CREATE TABLE cell_towers
(
    id            INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    radio         VARCHAR(8),
    mcc           SMALLINT UNSIGNED,
    net           SMALLINT UNSIGNED,
    area          SMALLINT UNSIGNED,
    cell          BIGINT UNSIGNED,
    unit          SMALLINT,
    lon           DOUBLE,
    lat           DOUBLE,
    `range`       INT UNSIGNED,
    samples       INT UNSIGNED,
    changeable    TINYINT UNSIGNED,
    created       DATETIME,
    updated       DATETIME,
    averageSignal TINYINT UNSIGNED
);

ALTER table cell_towers add INDEX idx_radio(radio);
# 按类型划分的基站数量：
EXPLAIN
    SELECT radio, count(radio) AS c FROM cell_towers GROUP BY radio ORDER BY c DESC;

ALTER table cell_towers add INDEX idx_mcc(mcc);
# 各个移动国家代码（MCC）对应的蜂窝信号塔数量：
EXPLAIN
    SELECT mcc, count(mcc) as c FROM cell_towers GROUP BY mcc ORDER BY c DESC LIMIT 10;

ALTER table cell_towers add INDEX  idx_radio_mcc(radio, mcc);

ALTER table cell_towers DROP INDEX idx_radio;
ALTER table cell_towers DROP INDEX idx_mcc;

show INDEX FROM cell_towers;