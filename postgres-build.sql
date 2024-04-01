
-- Create the person table
CREATE TABLE IF NOT EXISTS person (
    id SERIAL PRIMARY KEY,
    version INT,
    username VARCHAR(100) UNIQUE,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create the item table
CREATE TABLE IF NOT EXISTS item (
    id SERIAL PRIMARY KEY,
    version INT,
    status VARCHAR(15),
    description VARCHAR(4000),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    asignee_id INT,
    tag_id INT,
    FOREIGN KEY (asignee_id) REFERENCES person(id)
);

-- Create the tag table
CREATE TABLE IF NOT EXISTS tag (
    id SERIAL PRIMARY KEY,
    version INT,
    name VARCHAR(100),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

    ALTER TABLE item
    ADD CONSTRAINT fk_item_tag FOREIGN KEY (tag_id) REFERENCES tag(id);

    ALTER TABLE item
    ADD CONSTRAINT fk_item_asignee_id FOREIGN KEY (asignee_id) REFERENCES person(id);

-- Insert dummy data for persons
INSERT INTO person (version, username, first_name, last_name)
VALUES
    (1, 'JohnnyBoy', 'John', 'Doe'),
    (1, 'JaneyGirl', 'Jane', 'Smith'),
    (1, 'MikeyBoy', 'Michael', 'Johnson'),
    (1, 'EmmaWattson', 'Emily', 'Williams'),
    (1, 'BiggyDB', 'David', 'Brown');

-- Insert dummy data for tags
INSERT INTO tag (version, name)
VALUES
    (1, 'Work'),
    (1, 'Personal'),
    (1, 'Shopping'),
    (1, 'Health'),
    (1, 'Home');

-- Insert dummy data for todo items - John
INSERT INTO item (version, status, description, asignee_id, tag_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', 1, 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', 1, 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', 1, 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', 1, 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', 1, 4);

-- Insert dummy data for todo items - Jane
INSERT INTO item (version, status, description, asignee_id, tag_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', 2, 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', 2, 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', 2, 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', 2, 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', 2, 4);

-- Insert dummy data for todo items - Michael
INSERT INTO item (version, status, description, asignee_id, tag_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', 3, 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', 3, 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', 3, 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', 3, 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', 3, 4);

-- Insert dummy data for todo items - Emily
INSERT INTO item (version, status, description, asignee_id, tag_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', 4, 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', 4, 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', 4, 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', 4, 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', 4, 4);

-- Insert dummy data for todo items - David
INSERT INTO item (version, status, description, asignee_id, tag_id)
VALUES
    (1, 'Pending', 'Finish work presentation for meeting with client.', 5, 1),
    (1, 'Pending', 'Go to the grocery store and buy fruits, vegetables, and milk.', 5, 5),
    (1, 'Pending', 'Fix leaking faucet in the kitchen.', 5, 5),
    (1, 'Pending', 'Call mom to wish her a happy birthday.', 5, 2),
    (1, 'Pending', 'Schedule a doctor''s appointment for annual check-up.', 5, 4);
