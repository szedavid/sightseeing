-- Creating roles
insert into public.role (name, id) values ('ROLE_USER', 1);
insert into public.role (name, id) values ('ROLE_ADMIN', 2);

-- Creating users
insert into public.user (username, password, id) values ('john', 'john12', 1);
insert into public.users_roles (user_id, role_id) values (1, 1);

insert into public.user (username, password, id) values ('admin', 'admin12', 2);
insert into public.users_roles (user_id, role_id) values (2, 1);
insert into public.users_roles (user_id, role_id) values (2, 2);

COMMIT;