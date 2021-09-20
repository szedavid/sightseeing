-- Creating tables
create table role
(
    id   bigint not null,
    name varchar(50),
    primary key (id)
);

create table tour
(
    id        bigint       not null,
    long_desc varchar(5000),
    name      varchar(250) not null,
    primary key (id)
);

create table user
(
    id       bigint not null,
    password varchar(100),
    username varchar(50),
    primary key (id)
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id)
);

-- Creating keys
alter table user
    drop constraint if exists UK_username;

alter table user
    add constraint UK_username unique (username);

alter table users_roles
    add constraint FK_users_roles_role foreign key (role_id) references role;

alter table users_roles
    add constraint FK_users_roles_user foreign key (user_id) references user;

-- Creating sequences
create sequence hibernate_sequence start with 1 increment by 1;