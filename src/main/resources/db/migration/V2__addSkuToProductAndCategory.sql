Alter Table product Add Column sku varchar(255) Not Null unique;
Alter Table category Add Column sku varchar(255) Not Null unique;
Alter Table product Add INDEX idx_product_sku (sku);
Alter Table category Add INDEX idx_category_sku (sku);