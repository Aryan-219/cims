create database cimsdb;

use cimsdb

create table status
(
    status_id int auto_increment primary key,
    type char(20) not null unique
); 

insert into status (type) values ('Active'), ('Inactive'), ('Blocked'), ('Closed');
alter table status change column type type char(35) not null;
insert into status (type) values ('Email Verified'), ('Manual Verification Done');

create table user_types 
(
    user_type_id int auto_increment primary key,
    type char(15) not null unique
);

insert into user_types (type) values ('Institute'), ('Student'), ('Teacher');


create table states 
(
    state_id int auto_increment primary key,
    name char(35) not null unique
);

insert into states (name) values ('Madhya Pradesh'), ('Gujrat'), ('Uttar Pradesh'), ('Maharashtra');

create table cities 
(
    city_id int auto_increment primary key,
    name char(35) not null,
    state_id int not null,
    constraint fk_cities_states foreign key (state_id) references states (state_id)
);


insert into cities (name, state_id) 
values 
('Jabalpur', 1), ('Bhopal', 1), ('Gwalior', 1), ('Indore', 1), 
('Ahmedabad', 2), ('Surat', 2), ('Bhuj', 2), ('Gandhinagar', 2), 
('Kanpur', 3), ('Laknow', 3), ('Prayagraj', 3), ('Varansi', 3), 
('Nagpur', 4), ('Pune', 4), ('Nasik', 4), ('Mumbai', 4); 

create table users 
(
    user_id int auto_increment primary key,
    name char(50) not null,
    email char(255) not null unique,
    password char(255) not null,
    city_id int not null,
    contact char(10) not null unique,
    dob date,
    gender char(1),
    address varchar(400),
    logo char(255),
    verification_code char(255),
    status_id int not null default 2,
    constraint fk_users_cities foreign key (city_id) references cities (city_id),
    constraint fk_users_status foreign key (status_id) references status (status_id)
);

alter table users add column user_type_id int not null,
add constraint fk_users_utypes foreign key (user_type_id) references user_types (user_type_id);

create table institutes 
(
    institute_id int auto_increment primary key,
    user_id	   int not null unique,
    details varchar(2000) not null,
    website 	char(100) not null,
    start_time time not null,
    end_time time not null,
    constraint fk_institutes_users foreign key (user_id) references users (user_id)
);

alter table institutes change column website website char(100) null;
 
create table teachers 
( 
    teacher_id int auto_increment primary key,
    user_id 	int not null unique,
    qualification char(10) not null,
    experience int not null,
    constraint fk_teachers_users foreign key (user_id) references users (user_id)
);



create table courses 
(
    course_id int auto_increment primary key,
    name char(50) not null,
    institute_id int not null,
    details varchar(2000) not null,
    course_pic char(255),
    syllabus_pdf char(255) not null,
    star_rank int not null default 0,
    star_rank_count int not null default 0,
    constraint fk_course_institutes foreign key (institute_id) references institutes (institute_id)
);


create table batches
(
    batch_id int auto_increment primary key,
    name char(50) not null,
    course_id int not null,
    mode bool not null,
    frequency bool not null,
    start_date date not null,
    duration int not null,
    class_time time not null,
    teacher_id int not null,
    status_id int not null,
    student_count int not null default 0,
    course_fees int not null,
    concession int not null default 0,
    constraint fk_batches_courses foreign key (course_id) references courses (course_id), 
    constraint fk_batches_teachers foreign key (teacher_id) references teachers (teacher_id), 
    constraint fk_batches_status foreign key (status_id) references status (status_id) 
);
 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

cities: ---
select city_id,c.name,s.name from cities as c inner join states as s where c.state_id=s.state_id;

users: ---
insert into users (name, email, password, contact, city_id, user_type_id) value (?,?,?,?,?,?);

select password,user_id,u.name,c.city_id,c.name,s.state_id,s.name,contact,dob,gender,address,logo,status_id from users as u inner join cities as c inner join states as s where c.state_id=s.state_id and u.city_id=c.city_id and email=?

update users set verification_code=null,status_id=1 where email=? and verification_code=?;

select password,user_id,u.name,c.city_id,c.name,s.state_id,s.name,contact,
dob,gender,address,logo,status_id from users as u inner join cities as c inner join 
states as s where c.state_id=s.state_id and u.city_id=c.city_id and email=?

update users set verification_code=? where email=?

update users set dob=?,logo=? where user_id=?;


institutes: ---
select * from institutes where user_id=?

insert into institutes (user_id,details,website,start_time,end_time) value (?,?,?,?,?);


courses: ---
insert into courses (name,institute_id,details,syllabus_pdf) value (?,?,?,?)
select * from courses where institute_id=?
select name,details,course_pic,syllabus_pdf,star_rank,star_rank_count from courses where course_id=?

update courses set course_pic=? where course_id=?


teachers: --

insert into teachers (user_id, qualification, experience) value (?,?,?)

select teacher_id,qualification,experience from teachers where user_id=?

alter table teachers add column institute_id int, add constraint fk_teachers_institutes foreign key (institute_id) references institutes (institute_id);