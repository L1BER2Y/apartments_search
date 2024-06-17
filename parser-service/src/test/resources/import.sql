CREATE TABLE IF NOT EXISTS flats (area INTEGER, bedrooms INTEGER, floor INTEGER, price INTEGER, dt_create TIMESTAMP(3),  dt_update TIMESTAMP(2), description VARCHAR(255), "offer_type" VARCHAR(255) CHECK ("offer_type" IN ('RENT', 'SALE')),  "original_url" VARCHAR(255),  "photo_urls" VARCHAR(255) ARRAY, "uuid" UUID PRIMARY KEY);






