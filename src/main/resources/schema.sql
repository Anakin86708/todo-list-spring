CREATE TABLE todo (
  id BIGINT NOT NULL,
   user VARCHAR(255) NULL,
   `description` VARCHAR(255) NULL,
   target_date datetime NULL,
   done BIT(1) NULL,
   CONSTRAINT pk_todo PRIMARY KEY (id)
);