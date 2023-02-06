--liquibase formatted sql

--changeset eyakauleva:events_name_add_unique_constraint

alter table events
    add constraint name_unique unique (name);

-- rollback alter table events
--      drop constraint name_unique;

