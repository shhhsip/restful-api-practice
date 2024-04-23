insert into users(id, join_date, name, password, ssn) values(90001, now(), 'user1', 'test11', '911111-1111111');
insert into users(id, join_date, name, password, ssn) values(90002, now(), 'user2', 'test22', '922222-1111111');
insert into users(id, join_date, name, password, ssn) values(90003, now(), 'user3', 'test33', '933333-1111111');

insert into post(description, user_id) values ('첫번째 글', 90001);
insert into post(description, user_id) values ('두번째 글', 90001);