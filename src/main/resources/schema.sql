USE rockstardb;

CREATE TABLE IF NOT EXISTS PRODUCT (
  ID varchar(36) PRIMARY KEY,
  NAME varchar(255),
  TITLE varchar(255),
  SUBTITLE varchar(255),
  DESCRIPTION varchar(1000),
  IMAGE varchar(255),
  BLOG_URL varchar(255),
  SCHEMA_URL varchar(255),
  FEATURED tinyint,
  STATE varchar(255),
  PRICE varchar(255),
  VISIBILITY varchar(255),
  VERSION varchar(100),
  AUTHOR varchar(255),
  ORGANIZATION varchar(255),
  CREATED_AT datetime);

CREATE TABLE IF NOT EXISTS OPTION_ITEM (
  ID varchar(36) PRIMARY KEY,
  PRODUCT_ID varchar(36),
  NAME varchar(255),
  VALUE varchar(255),
  TITLE varchar(255),
  IMAGE varchar(255),
  FEATURED tinyint,
  TAGS varchar(255),
  VERSION varchar(100));

CREATE TABLE IF NOT EXISTS ATTRIBUTE (
  ID varchar(36) PRIMARY KEY,
  PRODUCT_ID varchar(36),
  NAME varchar(255),
  VALUE varchar(255),
  TITLE varchar(255),
  IMAGE varchar(255),
  VERSION varchar(100));

CREATE TABLE IF NOT EXISTS MEDIA_ITEM (
    ID varchar(36) PRIMARY KEY,
    PRODUCT_ID varchar(36),
    SLUG varchar(128),
    TITLE varchar(255),
    SRC varchar(255),
    THUMBNAIL varchar(255),
    MEDIA_TYPE varchar(255),
    DISPLAY_ORDER integer);
