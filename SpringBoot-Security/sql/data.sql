create table user
(
    ID       int auto_increment
        primary key,
    username varchar(50) charset utf8 null,
    password varchar(50) charset utf8 null,
    constraint user_username_uindex
        unique (username)
);

INSERT INTO demo.user (ID, username, password) VALUES (1, 'admin', '123456');