CREATE SCHEMA IF NOT EXISTS auth_service;
SET SCHEMA auth_service;

--- Table: User
CREATE TABLE auth_service."user" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    mobile VARCHAR(20),
    date_of_birth DATETIME,
    create_by VARCHAR(255),
    create_date DATETIME,
    update_by VARCHAR(255),
    modified_date DATETIME,
    version BIGINT,
    status INTEGER,
    CONSTRAINT unique_user_username UNIQUE (username)
);

CREATE INDEX idx_user_id ON auth_service."user" (id);
CREATE INDEX idx_user_username ON auth_service."user" (username);
CREATE INDEX idx_user_status ON auth_service."user" (status);
------------------------------------------------------

--- Table: Role
CREATE TABLE auth_service."role" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    create_by VARCHAR(255),
    create_date DATETIME,
    update_by VARCHAR(255),
    modified_date DATETIME,
    version BIGINT,
    status INTEGER,
    CONSTRAINT unique_role_name UNIQUE (name)
);

CREATE INDEX idx_role_id ON auth_service."role" (id);
CREATE INDEX idx_role_status ON auth_service."role" (status);
------------------------------------------------------

--- Table: Menu
CREATE TABLE auth_service."menu" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type INTEGER,
    url VARCHAR(200),
    parent_id BIGINT,
    create_by VARCHAR(255),
    create_date DATETIME,
    update_by VARCHAR(255),
    modified_date DATETIME,
    version BIGINT,
    status INTEGER,
    CONSTRAINT unique_menu_name UNIQUE (name)
);

CREATE INDEX idx_menu_id ON auth_service."menu" (id);
CREATE INDEX idx_menu_parent_id ON auth_service."menu" (parent_id);
CREATE INDEX idx_menu_status ON auth_service."menu" (status);
------------------------------------------------------

--- Table: Endpoint
CREATE TABLE auth_service."endpoint" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    url_api VARCHAR(200) NOT NULL,
    type_api INTEGER,
    description VARCHAR(500),
    create_by VARCHAR(255),
    create_date DATETIME,
    update_by VARCHAR(255),
    modified_date DATETIME,
    version BIGINT,
    status INTEGER,
    CONSTRAINT unique_endpoint_url_api UNIQUE (url_api)
);

CREATE INDEX idx_endpoint_id ON auth_service."endpoint" (id);
CREATE INDEX idx_endpoint_status ON auth_service."endpoint" (status);
------------------------------------------------------

--- Table: Permission
CREATE TABLE auth_service."permission" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    create_by VARCHAR(255),
    create_date DATETIME,
    update_by VARCHAR(255),
    modified_date DATETIME,
    version BIGINT,
    status INTEGER
);

CREATE INDEX idx_permission_id ON auth_service."permission" (id);
CREATE INDEX idx_permission_status ON auth_service."permission" (status);
------------------------------------------------------

--- Table Mapping: User_Role
CREATE TABLE auth_service."user_role" (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES auth_service."user" (id),
    FOREIGN KEY (role_id) REFERENCES auth_service."role" (id)
);
------------------------------------------------------

--- Table Mapping: Menu_Endpoint
CREATE TABLE auth_service."menu_endpoint" (
    menu_id BIGINT,
    endpoint_id BIGINT,
    PRIMARY KEY (menu_id, endpoint_id),
    FOREIGN KEY (menu_id) REFERENCES auth_service."menu" (id),
    FOREIGN KEY (endpoint_id) REFERENCES auth_service."endpoint" (id)
);
------------------------------------------------------

--- Table Mapping: Endpoint_Permission
CREATE TABLE auth_service."endpoint_permission" (
    endpoint_id BIGINT,
    permission_id BIGINT,
    PRIMARY KEY (endpoint_id, permission_id),
    FOREIGN KEY (endpoint_id) REFERENCES auth_service."endpoint" (id),
    FOREIGN KEY (permission_id) REFERENCES auth_service."permission" (id)
);
------------------------------------------------------