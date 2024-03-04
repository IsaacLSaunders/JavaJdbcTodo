-- Create the person table
CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    version INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Create the item table
CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    version INT,
    status VARCHAR(15),
    description VARCHAR(4000),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP,
    asignee_id INT,
    FOREIGN KEY (asignee_id) REFERENCES person(id)
);

-- Create the tag table
CREATE TABLE IF NOT EXISTS tag (
    id SERIAL PRIMARY KEY,
    version INT,
    name VARCHAR(100),
    created_date TIMESTAMP,
    last_modified_date TIMESTAMP
);

-- Create the item_tag table
CREATE TABLE IF NOT EXISTS item_tag (
    id SERIAL PRIMARY KEY,
    item_id INT,
    tag_id INT,
    FOREIGN KEY (item_id) REFERENCES item(id),
    FOREIGN KEY (tag_id) REFERENCES tag(id)
);

-- Insert dummy data for persons
INSERT INTO person (version, first_name, last_name, created_date, last_modified_date)
VALUES
    (1, 'John', 'Doe', NOW(), NOW()),
    (1, 'Jane', 'Smith', NOW(), NOW()),
    (1, 'Michael', 'Johnson', NOW(), NOW()),
    (1, 'Emily', 'Williams', NOW(), NOW()),
    (1, 'David', 'Brown', NOW(), NOW());

-- Insert dummy data for tags
INSERT INTO tag (version, name, created_date, last_modified_date)
VALUES
    (1, 'Work', NOW(), NOW()),
    (1, 'Personal', NOW(), NOW()),
    (1, 'Shopping', NOW(), NOW()),
    (1, 'Health', NOW(), NOW()),
    (1, 'Home', NOW(), NOW());

-- Insert dummy data for todo items - John
INSERT INTO item (version, status, description, created_date, last_modified_date, asignee_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', NOW(), NOW(), 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', NOW(), NOW(), 1),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', NOW(), NOW(), 1),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', NOW(), NOW(), 1),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', NOW(), NOW(), 1);

-- Insert dummy data for todo items - Jane
INSERT INTO item (version, status, description, created_date, last_modified_date, asignee_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', NOW(), NOW(), 2),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', NOW(), NOW(), 2),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', NOW(), NOW(), 2),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', NOW(), NOW(), 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', NOW(), NOW(), 2);

-- Insert dummy data for todo items - Michael
INSERT INTO item (version, status, description, created_date, last_modified_date, asignee_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', NOW(), NOW(), 3),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', NOW(), NOW(), 3),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', NOW(), NOW(), 3),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', NOW(), NOW(), 3),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', NOW(), NOW(), 3);

-- Insert dummy data for todo items - Emily
INSERT INTO item (version, status, description, created_date, last_modified_date, asignee_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', NOW(), NOW(), 4),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', NOW(), NOW(), 4),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', NOW(), NOW(), 4),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', NOW(), NOW(), 4),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', NOW(), NOW(), 4);

-- Insert dummy data for todo items - David
INSERT INTO item (version, status, description, created_date, last_modified_date, asignee_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', NOW(), NOW(), 5),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', NOW(), NOW(), 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', NOW(), NOW(), 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', NOW(), NOW(), 5),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', NOW(), NOW(), 5);

-- Insert dummy data for item_tag associations
-- John's tasks
INSERT INTO item_tag (item_id, tag_id)
VALUES (1, 1), (2, 5), (3, 5), (4, 2), (5, 4);

-- Jane's tasks
INSERT INTO item_tag (item_id, tag_id)
VALUES (6, 1), (7, 5), (8, 5), (9, 2), (10, 4);

-- Michael's tasks
INSERT INTO item_tag (item_id, tag_id)
VALUES (11, 1), (12, 5), (13, 5), (14, 2), (15, 4);

-- Emily's tasks
INSERT INTO item_tag (item_id, tag_id)
VALUES (16, 1), (17, 5), (18, 5), (19, 2), (20, 4);

-- David's tasks
INSERT INTO item_tag (item_id, tag_id)
VALUES (21, 1), (22, 5), (23, 5), (24, 2), (25, 4);
