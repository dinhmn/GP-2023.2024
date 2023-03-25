DROP TABLE if EXISTS tbl_article CASCADE;
CREATE TABLE tbl_article (
     article_id BIGINT NOT NULL AUTO_INCREMENT,
     product_id BIGINT NOT NULL,
     article_description VARCHAR(255) NOT NULL,
     article_name VARCHAR(255) NOT NULL,
     article_seo VARCHAR(255) NOT NULL,
     created_by INTEGER NOT NULL,
     created_date DATETIME(6) NOT NULL,
     status BIT,
     updated_by INTEGER NOT NULL,
     updated_date DATETIME(6) NOT NULL,
     PRIMARY KEY (article_id, product_id)
);

DROP TABLE if EXISTS tbl_cart CASCADE;
create table tbl_cart (
      cart_id BIGINT NOT NULL AUTO_INCREMENT,
      product_id BIGINT NOT NULL,
      product_current_price DECIMAL(15, 2) NOT NULL,
      product_name VARCHAR(255) NOT NULL,
      product_quantity INTEGER NOT NULL,
      created_by INTEGER NOT NULL,
      created_date DATETIME(6) NOT NULL,
      status BIT,
      updated_by INTEGER NOT NULL,
      updated_date DATETIME(6) NOT NULL,
      PRIMARY KEY (cart_id, product_id)
);

DROP TABLE if EXISTS tbl_category CASCADE;
CREATE TABLE tbl_category (
      category_id BIGINT NOT NULL AUTO_INCREMENT,
      trademark_id BIGINT NOT NULL,
      category_description VARCHAR(3000) NOT NULL,
      category_name VARCHAR(255) NOT NULL,
      seo VARCHAR(255) NOT NULL,
      created_by INTEGER NOT NULL,
      created_date DATETIME(6) NOT NULL,
      status BIT ,
      updated_by INTEGER NOT NULL,
      updated_date DATETIME(6) NOT NULL,
      PRIMARY KEY (category_id, trademark_id)
);

DROP TABLE if EXISTS tbl_comment CASCADE;
create table tbl_comment (
     comment_id BIGINT NOT NULL AUTO_INCREMENT,
     user_id BIGINT NOT NULL,
     product_id BIGINT NOT NULL,
     article_id BIGINT,
     comment_code VARCHAR(2) NOT NULL,
     comment_detail VARCHAR(255) NOT NULL,
     created_by INTEGER NOT NULL,
     created_date DATETIME(6) NOT NULL,
     status BIT,
     updated_by INTEGER NOT NULL,
     updated_date DATETIME(6) NOT NULL,
     PRIMARY KEY (comment_id, user_id)
);

DROP TABLE if EXISTS tbl_contact CASCADE;
create table tbl_contact (
     contact_id BIGINT NOT NULL AUTO_INCREMENT,
     product_id BIGINT NOT NULL,
     contact_email VARCHAR(255) NOT NULL,
     contact_message VARCHAR(1000),
     contact_name VARCHAR(255) NOT NULL,
     created_by INTEGER NOT NULL,
     created_date DATETIME(6) NOT NULL,
     status BIT,
     updated_by INTEGER NOT NULL,
     updated_date DATETIME(6) NOT NULL,
     PRIMARY KEY (contact_id)
);

DROP TABLE if EXISTS tbl_order CASCADE;
create table tbl_order (
   order_id BIGINT NOT NULL AUTO_INCREMENT,
   user_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   user_information_id BIGINT,
   order_code VARCHAR(255) NOT NULL,
   order_status BIT,
   order_total_price DECIMAL(15,2) NOT NULL,
   created_by INTEGER NOT NULL,
   created_date DATETIME(6) NOT NULL,
   status BIT,
   updated_by INTEGER NOT NULL,
   updated_date DATETIME(6) NOT NULL,
   PRIMARY KEY (order_id, user_id)
);

DROP TABLE if EXISTS tbl_password CASCADE;
create table tbl_password (
      password_Id BIGINT NOT NULL AUTO_INCREMENT,
      user_id BIGINT NOT NULL,
      old_password VARCHAR(255),
      created_by INTEGER NOT NULL,
      created_date DATETIME(6) NOT NULL,
      status BIT,
      updated_by INTEGER NOT NULL,
      updated_date DATETIME(6) NOT NULL,
      PRIMARY KEY (password_Id)
);

DROP TABLE if EXISTS tbl_product CASCADE;
create table tbl_product (
     product_id BIGINT NOT NULL AUTO_INCREMENT,
     category_id BIGINT NOT NULL,
     product_description VARCHAR(2000),
     product_name VARCHAR(255) NOT NULL,
     product_price DECIMAL(20,2) NOT NULL,
     product_price_sale DECIMAL(20,2),
     product_seo VARCHAR(255),
     quantity INTEGER NOT NULL,
     created_by INTEGER NOT NULL,
     created_date DATETIME(6) NOT NULL,
     status BIT,
     updated_by INTEGER NOT NULL,
     updated_date DATETIME(6) NOT NULL,
     PRIMARY KEY (product_id, category_id)
);

DROP TABLE if EXISTS tbl_product_color CASCADE;
create table tbl_product_color (
   product_color_id BIGINT NOT NULL AUTO_INCREMENT,
   product_id BIGINT NOT NULL,
   product_color_name VARCHAR(255) NOT NULL,
   created_by INTEGER NOT NULL,
   created_date DATETIME(6) NOT NULL,
   status BIT,
   updated_by INTEGER NOT NULL,
   updated_date DATETIME(6) NOT NULL,
   PRIMARY KEY (product_color_id)
);

DROP TABLE if EXISTS tbl_product_size CASCADE;
create table tbl_product_size (
  product_size_id BIGINT NOT NULL AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  product_size_name VARCHAR(255) NOT NULL,
  created_by INTEGER NOT NULL,
  created_date DATETIME(6) NOT NULL,
  status BIT,
  updated_by INTEGER NOT NULL,
  updated_date DATETIME(6) NOT NULL,
  PRIMARY KEY (product_size_id)
);

DROP TABLE if EXISTS tbl_rates CASCADE;
create table tbl_rates (
   rate_id BIGINT NOT NULL AUTO_INCREMENT,
   article_id BIGINT,
   comment_id BIGINT,
   product_id BIGINT,
   star_rating FLOAT(10),
   created_by INTEGER NOT NULL,
   created_date DATETIME(6) NOT NULL,
   status BIT,
   updated_by INTEGER NOT NULL,
   updated_date DATETIME(6) NOT NULL,
   PRIMARY KEY (rate_id)
);

DROP TABLE if EXISTS tbl_role CASCADE;
create table tbl_role (
  role_id BIGINT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(255) NOT NULL,
  role_description VARCHAR(255) NOT NULL,
  created_by INTEGER NOT NULL,
  created_date DATETIME(6) NOT NULL,
  status BIT,
  updated_by INTEGER NOT NULL,
  updated_date DATETIME(6) NOT NULL,
  PRIMARY KEY (role_id)
);

DROP TABLE if EXISTS tbl_source_images CASCADE;
create table tbl_source_images (
   image_id BIGINT NOT NULL AUTO_INCREMENT,
   product_id BIGINT NOT NULL,
   user_information_id BIGINT,
   data tinyblob NOT NULL,
   file_name VARCHAR(255) NOT NULL,
   file_type VARCHAR(255) NOT NULL,
   image_code VARCHAR(2),
   created_by INTEGER NOT NULL,
   created_date DATETIME(6) NOT NULL,
   status BIT,
   updated_by INTEGER NOT NULL,
   updated_date DATETIME(6) NOT NULL,
   PRIMARY KEY (image_id, product_id)
);

DROP TABLE if EXISTS tbl_trademark CASCADE;
create table tbl_trademark (
   trademark_id BIGINT NOT NULL AUTO_INCREMENT,
   trademark_name VARCHAR(255) NOT NULL,
   seo VARCHAR(255) NOT NULL,
   created_by INTEGER NOT NULL,
   created_date DATETIME(6) NOT NULL,
   status BIT,
   updated_by INTEGER NOT NULL,
   updated_date DATETIME(6) NOT NULL,
   PRIMARY KEY (trademark_id)
);

DROP TABLE if EXISTS tbl_user CASCADE;
create table tbl_user (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
  role_id BIGINT NOT NULL,
  profile_id BIGINT,
  user_password VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  created_by INTEGER NOT NULL,
  created_date DATETIME(6) NOT NULL,
  status BIT,
  updated_by INTEGER NOT NULL,
  updated_date DATETIME(6) NOT NULL,
  PRIMARY KEY (user_id)
);

DROP TABLE if EXISTS tbl_user_information CASCADE;
create table tbl_user_information (
  user_information_id BIGINT NOT NULL AUTO_INCREMENT,
  source_image_id BIGINT NOT NULL,
  user_id BIGINT,
  address VARCHAR(255) NOT NULL,
  age VARCHAR(2),
  email VARCHAR(80) NOT NULL,
  first_name VARCHAR(40),
  last_name VARCHAR(40),
  phone VARCHAR(10) NOT NULL,
  user_code VARCHAR(2) NOT NULL,
  created_by INTEGER NOT NULL,
  created_date DATETIME(6) NOT NULL,
  status BIT,
  updated_by INTEGER NOT NULL,
  updated_date DATETIME(6) NOT NULL,
  PRIMARY KEY (user_information_id, source_image_id)
);
