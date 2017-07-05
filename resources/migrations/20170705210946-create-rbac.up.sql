create table user (
id int unsigned primary key auto_increment,
user_name varchar(16) not null default '',
email varchar(64) not null default '',
phone varchar(20) not null default '',
phone_code varchar(8) not null default '',
password char(32) not null default '',
create_time int unsigned not null
);

-- create table user_group (
-- id int unsigned primary key auto_increment,
-- user_name varchar(16) not null default '',
-- create_time int unsigned not null
-- );

-- create table user_group_relation (
-- user_id int unsigned,
-- user_group_id int unsigned,
-- primary_key(user_id, user_group_id)
-- );
