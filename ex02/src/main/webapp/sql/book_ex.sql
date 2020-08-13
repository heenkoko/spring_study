create sequence seq_board;

create table tbl_board
(
    bno number(10,0),
    title varchar2(200) not null,
    content varchar2(2000) not null,
    writer varchar2(50) not null,
    regdate date default sysdate,
    updatedate date default sysdate
);

alter table tbl_board add constraint pk_board primary key (bno);

insert into tbl_board (bno, title, content, writer)
select seq_board.nextval, title, content, writer from tbl_board;

select * from tbl_board;
commit;

