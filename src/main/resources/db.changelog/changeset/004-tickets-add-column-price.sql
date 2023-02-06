--liquibase formatted sql

--changeset eyakauleva:tickets_add_column_price

alter table tickets
    add price decimal(10, 2) not null;

-- rollback alter table tickets
--      drop column price;