drop database shopdb;
drop user me;
create user me with password 'password';
create database shopdb with template=template0 owner=me;
\connect shopdb;

/* Address
* id: address id
*
*/
-- DROP TABLE if exists Address;
CREATE TABLE Address (
    id int NOT NULL ,
    street VARCHAR(100) NOT NULL,
    province VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    phone VARCHAR(20),
    PRIMARY KEY(id)
);
create sequence address_seq increment 1 start 100;

INSERT INTO Address (id, street, province, country, zip, phone) VALUES (1, '123 Yonge St', 'ON','Canada', 'K1E 6T5' ,'647-123-4567');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (2, '445 Avenue rd', 'ON','Canada', 'M1C 6K5' ,'416-123-8569');
INSERT INTO Address (id, street, province, country, zip, phone) VALUES (3, '789 Keele St.', 'ON','Canada', 'K3C 9T5' ,'416-123-9568');

-- DROP TABLE if exists USERS;
create table users(
    user_id int not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(30) not null,
    password text not null,
    PRIMARY KEY(user_id)
);
create sequence users_seq increment 1 start 100;

INSERT INTO users (user_id, first_name, last_name, email, password) VALUES ('1', 'a', 'b', 'a@b.c', 'pswd');
INSERT INTO users (user_id, first_name, last_name, email, password) VALUES ('2', 'c', 'd', 'c@d.c', 'pswd');
INSERT INTO users (user_id, first_name, last_name, email, password) VALUES ('3', 'e', 'f', 'e@f.c', 'pswd');

/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/
CREATE TYPE category_type AS ENUM ('Science','Fiction','Engineering');

-- DROP TABLE if exists Book;
CREATE TABLE Book (
    bid VARCHAR(20) NOT NULL,
    title VARCHAR(60) NOT NULL,
    price INT NOT NULL,
    category VARCHAR(20) NOT NULL,
    PRIMARY KEY(bid)
);

INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002','Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003','Mechanics' ,100,'Engineering');

CREATE TABLE REVIEW (
    ID INT NOT NULL,
    BID VARCHAR(20) NOT NULL,
    RATING INT CHECK (RATING < 11) NOT NULL,
    COMMENT TEXT NOT NULL,
    PRIMARY KEY(ID),
    FOREIGN KEY (BID) REFERENCES BOOK (BID) ON DELETE CASCADE
);
create sequence review_seq increment 1 start 100;

INSERT INTO REVIEW (ID, BID, RATING, COMMENT) VALUES (1, 'b001', 9, 'such a good book!!!');
INSERT INTO REVIEW (ID, BID, RATING, COMMENT) VALUES (2, 'b001', 8, 'okay, not bad');
INSERT INTO REVIEW (ID, BID, RATING, COMMENT) VALUES (3, 'b003', 7, 'such a good book!!!');


/* Purchase Order
* last_name: last name
* first_name: first name
* id: purchase order id
* status:status of purchase
*/
CREATE TYPE status_type AS ENUM ('ORDERED','PROCESSED','DENIED');
-- DROP TABLE if exists PO;
CREATE TABLE PO (
    id int NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL,
    address INT CHECK (address > 0) NOT NULL,
    PRIMARY KEY(id),
--     INDEX (address),
    FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);
create sequence po_seq increment 1 start 100;

INSERT INTO PO (id, first_name, last_name, status, address) VALUES (1, 'John', 'White', 'PROCESSED', '1');
INSERT INTO PO (id, first_name, last_name, status, address) VALUES (2, 'Peter', 'Black', 'DENIED', '2');
INSERT INTO PO (id, first_name, last_name, status, address) VALUES (3, 'Andy', 'Green', 'ORDERED', '3');
/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
-- DROP TABLE if exists POItem;
CREATE TABLE POItem (
    id INT CHECK (id > 0) NOT NULL,
    bid VARCHAR(20) NOT NULL,
    price INT CHECK (price > 0)NOT NULL,
    PRIMARY KEY(id,bid),
--     INDEX (id),
    FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
--     INDEX (bid),
    FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);

INSERT INTO POItem (id, bid, price) VALUES (1, 'b001', '200');
INSERT INTO POItem (id, bid, price) VALUES (1, 'b002', '200');
INSERT INTO POItem (id, bid, price) VALUES (2, 'b002', '300');
INSERT INTO POItem (id, bid, price) VALUES (3, 'b001', '100');

-- CREATE TYPE event_type AS ENUM ('VIEW','CART','PURCHASE');
-- -- DROP TABLE if exists VisitEvent;
-- CREATE TABLE VisitEvent (
--     day varchar(8) NOT NULL,
--     bid varchar(20) not null REFERENCES Book.bid,
--     type event_type NOT NULL,
--     FOREIGN KEY(bid) REFERENCES Book(bid)
-- );
--
-- INSERT INTO VisitEvent (day, bid, type) VALUES ('12202015', 'b001', 'VIEW');
-- INSERT INTO VisitEvent (day, bid, type) VALUES ('12242015', 'b001', 'CART');
-- INSERT INTO VisitEvent (day, bid, type) VALUES ('12252015', 'b001', 'PURCHASE');

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to me;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public to me;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public to me;
