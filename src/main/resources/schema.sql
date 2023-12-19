CREATE TABLE IF NOT EXISTS bus_schedule (
    id          IDENTITY        NOT NULL    PRIMARY KEY,
    day_of_week INT             NOT NULL,
    day_time    TIME            NOT NULL,
    direction   VARCHAR(3)      NOT NULL,
    station     VARCHAR(3)      NOT NULL
);