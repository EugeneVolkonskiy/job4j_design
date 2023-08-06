insert into roles(name) values('sysadmin');
insert into roles(name) values('public');
insert into users(name, roles_id) values ('Evgeniy', 1);
insert into rules(name) values ('rule1');
insert into rules(name) values ('rule2');
insert into roles_rules(roles_id, rules_id) values ('1', '1');
insert into roles_rules(roles_id, rules_id) values ('2', '2');
insert into categories(name) values('category1');
insert into categories(name) values('category2');
insert into states(name) values('state1');
insert into states(name) values('state2');
insert into items(name, users_id, categories_id, states_id) values('item1', 1, 1, 1);
insert into comments(name, items_id) values('comment1', 1);
insert into attachs(name, items_id) values('attach1', 1);