CREATE TABLE category
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    is_active  BIT(1) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    is_deleted BIT(1) NULL,
    name       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE category_featured_products
(
    category_id          BIGINT NOT NULL,
    featured_products_id BIGINT NOT NULL,
    PRIMARY KEY (category_id, featured_products_id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    is_active     BIT(1) NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    is_deleted    BIT(1) NULL,
    title         VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    price DOUBLE NOT NULL,
    quantity      BIGINT       NOT NULL,
    category_id   BIGINT       NOT NULL,
    img           VARCHAR(255) NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

ALTER TABLE category_featured_products
    ADD CONSTRAINT uc_category_featured_products_featuredproducts UNIQUE (featured_products_id);

ALTER TABLE category
    ADD CONSTRAINT uc_category_name UNIQUE (name);

ALTER TABLE product
    ADD CONSTRAINT uc_product_title UNIQUE (title);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_featured_products
    ADD CONSTRAINT fk_catfeapro_on_category FOREIGN KEY (category_id) REFERENCES category (id);

ALTER TABLE category_featured_products
    ADD CONSTRAINT fk_catfeapro_on_product FOREIGN KEY (featured_products_id) REFERENCES product (id);