drop sequence rid_seq;
create sequence rid_seq
  start with 45653
  increment by 1
  nocycle
  nocache;

drop sequence did_seq;
create sequence did_seq
  start with 1
  maxvalue 9999
  increment by 1
  nocycle
  nocache;
  
drop sequence oid_seq;
create sequence oid_seq
  start with 12346
  increment by 1
  nocache;

drop table cuisinestyle;
create table cuisinestyle (
  styleName varchar2(50) primary key,
  description varchar2(500)
);

drop table restaurant;
create table restaurant (
  rid number(10,0) primary key,
  rPassword varchar2(20)not null,
  address varchar2(500) not null,
  rName varchar2(50) not null,
  phone char(10) not null,
  deliveryMin number(6,2) not null,
  stylename varchar2(50),
  foreign key (stylename) references cuisinestyle(stylename)
      on delete set null
);

drop table customer;
create table customer(
  cUsername varchar2(20) primary key,
  cPassword varchar2(20) not null,
  fname varchar2(50) not null,
  lname varchar2(50) not null,
  address varchar2(500) not null,
  phone char(10) not null,
  email varchar2(50)
);

drop table message;
create table message(
  rid number(15,0),
  cusername varchar2(20),
  text varchar2(500),
  primary key (rid, cusername),
  foreign key (rid) references restaurant(rid)
      on delete cascade,
  foreign key (cusername) references customer (cusername)
      on delete cascade
);

drop table menu;
create table menu(
  rid number(15,0),
  mtype varchar2(50),
  primary key(mtype, rid),
  foreign key (rid) references restaurant(rid)
      on delete cascade
);

drop table submenu;
create table submenu(
  rid number(15,0),
  mtype varchar2(50),
  smcategory varchar2(50),
  primary key (rid, mtype, smcategory),
  foreign key (rid, mtype) references menu(rid, mtype)
      on delete cascade
);

drop table dishes;
create table dishes(
  did number(7,0) primary key,
  dname varchar2(50) not null,
  price number(10,2) not null,
  description varchar2(500),
  mtype varchar2(50),
  rid number(15,0),
  smcategory varchar2(50),
  foreign key (smcategory, mtype, rid) references submenu(smcategory, mtype, rid)
      on delete cascade
);

drop table review;
create table review(
  did number(7,0),
  cusername varchar2(20),
  rating number(1,0) not null,
  comments varchar2(100),
  primary key (did, cusername),
  foreign key (did) references dishes(did)
      on delete cascade,
  foreign key (cusername) references customer(cusername)
      on delete cascade
);

drop table promotions;
create table promotions(
  did number(7,0),
  rid number(10,0),
  startDate Date not null,
  endDate Date not null,
  ptype varchar2(50) not null,
  discount number(2,2) not null,
  primary key (did, rid),
  foreign key (did) references restaurant(rid)
      on delete cascade,
  foreign key (did) references dishes(did)
      on delete cascade
);

drop table ord;
create table ord(
  ordid number(10,0) primary key,
  cusername varchar2(20),
  status varchar2(10),
  odate date,
  otime timestamp,
  tax number(2,2),
  total number(6,2),
  foreign key (cusername) references customer(cusername)
      on delete cascade
);

drop table dishord;
create table dishord(
  did number(7,0),
  ordid number(10,0),
  rid number(10,0),
  quantity number(2,0) not null,
  status varchar2(10) not null,
  price number(10,2) not null,
  primary key (did,ordid),
  foreign key (did) references dishes(did)
      on delete cascade,
  foreign key (ordid) references ord(ordid)
      on delete cascade,
  foreign key (rid) references restaurant(rid)
      on delete cascade
);

drop table delivery;
create table delivery(
  ordid number(10,0) primary key,
  cusername varchar2(20),
  address varchar2(500),
  dtime timestamp,
  fee number(7,2),
  foreign key (ordid) references ord(ordid)
      on delete cascade,
  foreign key (cusername) references customer(cusername)
      on delete cascade
);
