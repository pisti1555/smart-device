CREATE TABLE IF NOT EXISTS users (
    id UUID DEFAULT RANDOM_UUID(7) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(300) NOT NULL,
    child_account BOOLEAN NOT NULL,
    active_profile_picture_id UUID,
    active_wallpaper_image_id UUID
);

CREATE TABLE IF NOT EXISTS roles (
    id UUID DEFAULT RANDOM_UUID(7) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL,
    role VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_roles_roles FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS apps (
    id UUID DEFAULT RANDOM_UUID(7) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL,
    name VARCHAR(100) NOT NULL,
    icon_url VARCHAR(300),
    category VARCHAR(50),
    adult_only BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS images (
    id UUID DEFAULT RANDOM_UUID(7) PRIMARY KEY,
    created_at TIMESTAMP DEFAULT now() NOT NULL,
    updated_at TIMESTAMP DEFAULT now() NOT NULL,
    url VARCHAR(300),
    owner_user_id UUID NOT NULL,
    CONSTRAINT fk_images_owner_user_id FOREIGN KEY (owner_user_id) REFERENCES users(id)
);

ALTER TABLE users ADD CONSTRAINT fk_users_active_profile_picture_id FOREIGN KEY (active_profile_picture_id) REFERENCES images(id);
ALTER TABLE users ADD CONSTRAINT fk_users_active_wallpaper_image_id FOREIGN KEY (active_wallpaper_image_id) REFERENCES images(id);

CREATE TABLE IF NOT EXISTS app_user (
    app_id UUID NOT NULL,
    user_id UUID NOT NULL,
    PRIMARY KEY (app_id, user_id),
    CONSTRAINT fk_user_app_app_id FOREIGN KEY (app_id) REFERENCES apps(id),
    CONSTRAINT fk_user_app_user_id FOREIGN KEY (user_id) REFERENCES users(id)
);