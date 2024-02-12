CREATE TABLE IF NOT EXISTS bus_schedule (
    id                  INT             NOT NULL    PRIMARY KEY,
    day_of_week         INT             NOT NULL,
    day_time            BIGINT          NOT NULL,
    day_time_string     VARCHAR(5)      NOT NULL,
    direction           VARCHAR(3)      NOT NULL,
    station             VARCHAR(3)      NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    id                  IDENTITY        NOT NULL    PRIMARY KEY,
    email               VARCHAR(50)     NOT NULL,
    name                VARCHAR(50)     NOT NULL,
    password            VARCHAR(100)    NOT NULL,
    enabled             BOOL            NOT NULL
);

CREATE TABLE IF NOT EXISTS registration_tokens (
    id                  IDENTITY        NOT NULL    PRIMARY KEY,
    email               VARCHAR(50)     NOT NULL,
    token               VARCHAR         NOT NULL,
    expiry_date         LONG            NOT NULL
);
