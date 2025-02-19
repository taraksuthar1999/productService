CREATE TABLE cart
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    is_active   BIT(1) NULL,
    created_at  datetime NULL,
    updated_at  datetime NULL,
    is_deleted  BIT(1) NULL,
    user_id     BIGINT NOT NULL,
    total_amount DOUBLE NOT NULL,
    total_items INT    NOT NULL,
    CONSTRAINT pk_cart PRIMARY KEY (id)
);

CREATE TABLE cart_item
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    is_active  BIT(1) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    is_deleted BIT(1) NULL,
    product_id BIGINT NULL,
    quantity   INT NULL,
    cart_id    BIGINT NULL,
    CONSTRAINT pk_cartitem PRIMARY KEY (id)
);

ALTER TABLE cart
    ADD CONSTRAINT uc_cart_user UNIQUE (user_id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CARTITEM_ON_CART FOREIGN KEY (cart_id) REFERENCES cart (id);