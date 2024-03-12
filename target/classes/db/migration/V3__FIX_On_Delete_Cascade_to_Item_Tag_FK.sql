ALTER TABLE item
DROP CONSTRAINT asignee_id;

ALTER TABLE item
ADD CONSTRAINT item_asignee_id_fkey
    FOREIGN KEY (asignee_id)
    REFERENCES person(id);