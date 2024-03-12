ALTER TABLE item
DROP CONSTRAINT item_asignee_id_fkey;

ALTER TABLE item
ADD CONSTRAINT asignee_id
    FOREIGN KEY (asignee_id)
    REFERENCES person(id)
    ON DELETE CASCADE;