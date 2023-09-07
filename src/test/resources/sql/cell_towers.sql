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