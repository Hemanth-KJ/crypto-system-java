CREATE DATABASE IF NOT EXISTS crypto_system;

USE crypto_system;



CREATE TABLE users(

id INT AUTO_INCREMENT PRIMARY KEY,

username VARCHAR(100),

password VARCHAR(255),

role VARCHAR(20)

);



CREATE TABLE files(

id INT AUTO_INCREMENT PRIMARY KEY,

filename VARCHAR(255),

encrypted_path TEXT,

secret_key VARCHAR(255),

decrypted_path TEXT,

created_at TIMESTAMP
DEFAULT CURRENT_TIMESTAMP

);



CREATE TABLE history(

id INT AUTO_INCREMENT PRIMARY KEY,

user_id INT,

action_type VARCHAR(50),

file_name VARCHAR(255),

created_at TIMESTAMP
DEFAULT CURRENT_TIMESTAMP

);


INSERT INTO users(

username,
password,
role

)

VALUES(

'admin',

'admin123',

'ADMIN'

);