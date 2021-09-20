-- Creating tables
create table public.role
(
    id   SERIAL PRIMARY KEY,
    name varchar(50)
);

create table public.tour
(
    id        BIGSERIAL PRIMARY KEY,
    long_desc varchar(5000),
    name      varchar(250) not null
);

create table public.user
(
    id       BIGSERIAL PRIMARY KEY,
    password varchar(100),
    username varchar(50),

    CONSTRAINT UNIQUEPARENTANDSOURCE
        unique (username)
);

create table public.users_roles
(
    user_id bigint not null
        constraint FK_users_roles_user references "user",
    role_id bigint not null
        constraint FK_users_roles_role references role,
    primary key (user_id, role_id)
);

-- Creating sequences
create sequence public.hibernate_sequence start with 1 increment by 1;

COMMIT;