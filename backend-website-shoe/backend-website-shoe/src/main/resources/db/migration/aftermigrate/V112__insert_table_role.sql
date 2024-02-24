insert into tbl_role(role_id, created_by, created_date, status, updated_by, updated_date, role_name, role_description)
values (1, 1, SYSDATE(), true, 1, SYSDATE(), 'USER', 'User Authentication'),
       (2, 1, SYSDATE(), true, 1, SYSDATE(), 'CUSTOMER', 'Customer Authentication'),
       (3, 1, SYSDATE(), true, 1, SYSDATE(), 'EDITOR', 'Editor Authentication'),
       (4, 1, SYSDATE(), true, 1, SYSDATE(), 'ADMIN', 'Admin Authentication');