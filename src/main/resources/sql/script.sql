-- tao cau hoi bang group
create
definer = root@localhost function _createQuestionByGroupID(IdGroupQuestion int, number_question int, questionOrder int) returns int
begin
    -- khai bao bien
    declare
i int default 0;
    while
(i < number_question)
        do
            insert into question   set id_group_question = IdGroupQuestion , question_order = questionOrder;
            set
questionOrder = questionOrder + 1;
            set
number_question = number_question + 1;
end while;
return i;
end;

drop function
_createQuestionByGroupID;

-- tao bai test
create
definer = root@localhost procedure _create_test(IN id int)
begin
    Declare
id_group int(11);
    Declare
linkmp4 nvarchar(256);
    Declare
part_test int default 0;
    Declare
num int default 1;
    Declare
v_Count Integer;
    declare
v_Found Integer default 0;
    -- start tao 45 group
    declare
str nvarchar(1024) default '';
    declare
str2 nvarchar(1024) default '';
    -- end
    declare
my_CURSOR CURSOR for (select g.ID, g.PART from group_question g where g.ID_TEST = id);
    -- (Chú ý: Khai báo điều khiển ngoại lệ sau các khai báo Cursor).
    -- Duyệt con trỏ từ bản ghi đầu tiên tới cuối cùng.
    -- Khi duyệt hết các bản ghi, ngoại lệ NOT FOUND sẽ ném ra
    -- Khi đó gán giá trị 0 cho v_Found.
    declare
continue handler for not found set v_Found = 1;
    Set
v_Count = 0;
open my_CURSOR;
-- Con trỏ đang đứng tại 1 bản ghi.
-- Đọc các giá trị cột gán vào các biến.
my_Loop
:
    loop
        fetch My_Cursor into id_group,part_test;
        if
(v_Found = 1) then
            leave my_Loop;
end if;
        set
str = Concat(str,',',CONVERT(id_group, NCHAR), ' _ ', CONVERT(part_test, NCHAR));
CASE part_test
            WHEN 1 THEN call _createQuestionByGroupID(id_group, 10, num);
WHEN 2 THEN call _createQuestionByGroupID(id_group, 30, num);
WHEN 3 THEN call _createQuestionByGroupID(id_group, 1, num);
WHEN 4 THEN call _createQuestionByGroupID(id_group, 1, num);
WHEN 5 THEN call _createQuestionByGroupID(id_group, 1, num);
WHEN 6 THEN call _createQuestionByGroupID(id_group, 1, num);
WHEN 7 THEN call _createQuestionByGroupID(id_group, 1, num);
ELSE
select 'ko co gi' as message;
END
case;
        Set
v_Count = v_Count + 1;
end loop
my_Loop;
close my_CURSOR;
select str as 'noi_dung_chinh';
end;


