--liquibase formatted sql

--changeset eyakauleva:change_event_coordinates_field_datatype

alter table events
    drop column coordinates;

alter table events
    add latitude float,
    add longitude float;

-- rollback alter table events
--      drop column latitude,
--      drop column longitude;
--  alter table events
--      add coordinates point;
