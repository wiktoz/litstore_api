drop database litstore;

create database litstore;

\connect litstore;

alter default privileges grant all on tables to litstore;
alter default privileges grant all on sequences to litstore;

create table Users(
                      UserID int primary key unique not null default nextval('UserID_seq'),
                      email varchar(60) not null,
                      password char(64) not null,
                      MainAddressID int,
                      RoleID int,
                      active boolean default true,
                      created_at timestamp default current_timestamp not null,
                      updated_at timestamp default current_timestamp not null,
                      FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
                      FOREIGN KEY (MainAddressID) REFERENCES Addresses(AddressID)
);

create table Roles(
                      RoleID int primary key unique not null default nextval('RoleID_seq'),
                      name varchar(60) not null
);

create table Permissions(
                            PermissionID int primary key unique not null default nextval('PermissionID_seq'),
                            name varchar(60) not null
);

create table PermissionsRoles(
                                 RoleID int not null,
                                 PermissionID int not null,
                                 PRIMARY KEY (RoleID, PermissionID),
                                 FOREIGN KEY (RoleID) REFERENCES Roles(RoleID),
                                 FOREIGN KEY (PermissionID) REFERENCES Permissions(PermissionID)
);

create table PermissionsUsers(
                                 UserID int not null,
                                 PermissionID int not null,
                                 PRIMARY KEY (UserID, PermissionID),
                                 FOREIGN KEY (UserID) REFERENCES Users(UserID),
                                 FOREIGN KEY (PermissionID) REFERENCES Permissions(PermissionID)
);

create table Addresses(
                          AddressID int primary key unique not null default nextval('AddressID_seq'),
                          UserID int not null,
                          name varchar(60) not null,
                          surname varchar(60) not null,
                          street varchar(60) not null,
                          house varchar(20) not null,
                          flat varchar(20),
                          post_code varchar(10) not null,
                          city varchar(60) not null,
                          phone varchar(20) not null,
                          country varchar(60) not null
);

create table Categories(
                           CategoryID int primary key unique not null default nextval('CategoryID_seq'),
                           name varchar(50) not null,
                           description text,
                           seo_description text,
                           img varchar(100),
                           bg_img varchar(100),
                           display_navbar boolean default true,
                           display_footer boolean default true,
                           active boolean default true,
                           slug varchar(60) not null
);

create table Subcategories(
                              SubcategoryID int primary key unique not null default nextval('SubcategoryID_seq'),
                              CategoryID int not null,
                              name varchar(50) not null,
                              description text,
                              seo_description text,
                              img varchar(100),
                              bg_img varchar(100),
                              display_navbar boolean default true,
                              display_footer boolean default true,
                              active boolean default true,
                              slug varchar(60) not null,
                              FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

create table PromoCodes(
                           PromoCodeID int primary key unique not null default nextval('PromoCodeID_seq'),
                           code varchar(50) not null,
                           discount numeric(6, 2) not null,
                           unit discount_unit not null,
                           name varchar(60) not null,
                           description text,
                           current_use int not null default 0,
                           max_use int,
                           start_date timestamp not null default current_timestamp,
                           end_date timestamp
);

create table PromoCodeCategories(
                                    PromoCodeID int not null,
                                    CategoryID int not null,
                                    PRIMARY KEY (PromoCodeID, CategoryID),
                                    FOREIGN KEY (PromoCodeID) REFERENCES PromoCodes(PromoCodeID),
                                    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

create table PromoCodeSubcategories(
                                       PromoCodeID int not null,
                                       SubcategoryID int not null,
                                       PRIMARY KEY (PromoCodeID, SubcategoryID),
                                       FOREIGN KEY (PromoCodeID) REFERENCES PromoCodes(PromoCodeID),
                                       FOREIGN KEY (SubcategoryID) REFERENCES Subcategories(SubcategoryID)
);

create table PromoCodeProducts(
                                  PromoCodeID int not null,
                                  ProductID int not null,
                                  PRIMARY KEY (PromoCodeID, ProductID),
                                  FOREIGN KEY (PromoCodeID) REFERENCES PromoCodes(PromoCodeID),
                                  FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

create table PromoCodeVariantOptions(
                                        PromoCodeID int not null,
                                        VariantOptionID int not null,
                                        PRIMARY KEY (PromoCodeID, VariantOptionID),
                                        FOREIGN KEY (PromoCodeID) REFERENCES PromoCodes(PromoCodeID),
                                        FOREIGN KEY (VariantOptionID) REFERENCES VariantOptions(VariantOptionID)
);

create table Products(
                         ProductID int primary key unique not null default nextval('ProductID_seq'),
                         CategoryID int,
                         SubcategoryID int,
                         name varchar(60),
                         manufacturer varchar(60),
                         new boolean not null default true,
                         active boolean not null default true,
                         slug varchar(70) not null,
                         created_at timestamp default current_timestamp not null,
                         updated_at timestamp default current_timestamp not null
);

create table ProductPhotos(
                              ProductPhotoID int primary key unique not null default nextval('ProductPhotoID_seq'),
                              ProductID int not null,
                              url varchar(150) not null,
                              order_index int not null,
                              FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

create table ProductDescriptions(
                                    ProductDescriptionID int primary key unique not null default nextval('ProductDescriptionID_seq'),
                                    ProductID int not null,
                                    lang_code languages not null,
                                    description text,
                                    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

create table VariantTypes(
                             VariantTypeID int primary key unique not null default nextval('VariantTypeID_seq'),
                             name varchar(60) not null,
                             display_name varchar(40) not null,
                             selection_type selection_types not null,
                             slug varchar(70) not null
);

create table VariantOptions(
                               VariantOptionID int primary key unique not null default nextval('VariantOptionID_seq'),
                               VariantTypeID int not null,
                               name varchar(40) not null,
                               order_index int not null,
                               FOREIGN KEY (VariantTypeID) REFERENCES VariantTypes(VariantTypeID)
);

create table Items(
                      ItemID int primary key unique not null default nextval('ItemID_seq'),
                      ProductID int not null,
                      VariantOptionID int not null,
                      price numeric(6,2) not null,
                      stock int not null,
                      unit item_unit not null default 'pcs.',
                      sku varchar(50) not null unique,
                      FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
                      FOREIGN KEY (VariantOptionID) REFERENCES VariantOptions(VariantOptionID)
);

create table Deliveries(
                           DeliveryID int primary key unique not null default nextval('DeliveryID_seq'),
                           name varchar(60) not null,
                           description text,
                           img varchar(150) not null,
                           price numeric(6,2) not null,
                           free_from numeric(6,2) not null,
                           personal_collect boolean not null default false,
                           cash_on_delivery boolean not null default false,
                           active boolean not null default true,
                           slug varchar(70) not null
);

create table DeliveriesItems(
                                DeliveryID int not null,
                                ItemID int not null,
                                PRIMARY KEY (DeliveryID, ItemID),
                                FOREIGN KEY (DeliveryID) REFERENCES Deliveries(DeliveryID),
                                FOREIGN KEY (ItemID) REFERENCES Items(ItemID)
);

create table Orders(
                       OrderID int primary key unique not null default nextval('OrderID_seq'),
                       AddressID int not null,
                       DeliveryID int not null,
                       DeliveryAddressID int not null,
                       PromoCodeID int,
                       UserID int,
                       guest_email varchar(60),
                       discount_amount numeric(6,2) not null,
                       payment_amount numeric(6,2) not null,
                       payment_currency currencies not null,
                       payment_method varchar(50),
                       payment_hash varchar(64),
                       payment_url varchar(300),
                       payment_status payment_status not null default 'PENDING',
                       status order_status not null default 'pending',
                       created_at timestamp default current_timestamp not null,
                       updated_at timestamp default current_timestamp not null
);

create table OrderItems(
                           OrderID int not null,
                           ItemID int not null,
                           quantity int not null,
                           price numeric(6,2) not null,
                           PRIMARY KEY (OrderID, ItemID),
                           FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
                           FOREIGN KEY (ItemID) REFERENCES Items(ItemID)
);

create table Tokens(
                       TokenID int primary key unique not null default nextval('TokenID_seq'),
                       token char(64) not null,
                       created_at timestamp not null default current_timestamp,
                       expiring_at timestamp not null
);

--- Sequences for auto ID generation
create sequence UserID_seq start 1 increment 1 cache 1;
create sequence RoleID_seq start 1 increment 1 cache 1;
create sequence PermissionID_seq start 1 increment 1 cache 1;
create sequence AddressID_seq start 1 increment 1 cache 1;
create sequence CategoryID_seq start 1 increment 1 cache 1;
create sequence SubcategoryID_seq start 1 increment 1 cache 1;
create sequence PromoCodeID_seq start 1 increment 1 cache 1;
create sequence ProductID_seq start 1 increment 1 cache 1;
create sequence ProductPhotoID_seq start 1 increment 1 cache 1;
create sequence ProductDescriptionID_seq start 1 increment 1 cache 1;
create sequence VariantTypeID_seq start 1 increment 1 cache 1;
create sequence VariantOptionID_seq start 1 increment 1 cache 1;
create sequence ItemID_seq start 1 increment 1 cache 1;
create sequence DeliveryID_seq start 1 increment 1 cache 1;
create sequence OrderID_seq start 1000 increment 1 cache 1;
create sequence TokenID_seq start 1 increment 1 cache 1;

--- Functions
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_order_index_photos() RETURNS TRIGGER AS $$
BEGIN
    NEW.order_index := (
        SELECT COUNT(*) + 1
        FROM ProductPhotos
        WHERE ProductID = NEW.ProductID
    );
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION set_order_index_variant_options() RETURNS TRIGGER AS $$
BEGIN
    NEW.order_index := (
        SELECT COUNT(*) + 1
        FROM VariantOptions
        WHERE VariantTypeID = NEW.VariantTypeID
    );
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--- Triggers for updating updated_at columns
CREATE TRIGGER update_updated_at_users
    BEFORE UPDATE ON Users
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_updated_at_products
    BEFORE UPDATE ON Products
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_updated_at_orders
    BEFORE UPDATE ON Orders
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER set_order_index_product_photos
    BEFORE UPDATE ON ProductPhotos
    FOR EACH ROW
    EXECUTE FUNCTION set_order_index_photos();

CREATE TRIGGER set_order_index_variant_options
    BEFORE UPDATE ON VariantOptions
    FOR EACH ROW
    EXECUTE FUNCTION set_order_index_variant_options();

--- Enums
CREATE TYPE languages AS ENUM ('pl', 'en', 'fr', 'de');
CREATE TYPE currencies AS ENUM ('PLN', 'USD', 'EUR', 'CHF');
CREATE TYPE selection_types as ENUM ('select', 'button');
CREATE TYPE discount_unit AS ENUM ('percent', 'cash');
CREATE TYPE item_unit AS ENUM ('pcs.', 'set', 'kg', 'l');
CREATE TYPE order_status AS ENUM ('pending', 'processing', 'shipped', 'delivered', 'cancelled');
CREATE TYPE payment_status AS ENUM ('PENDING', 'SUCCESS', 'CANCELLED');
