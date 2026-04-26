INSERT INTO roles(role)
    VALUES
        ('ROLE_ADMIN'),
        ('ROLE_USER'),
        ('ROLE_CHILD');

INSERT INTO apps(name, icon_url, category, adult_only)
    VALUES
        ('Recipes', 'recipes-icon', 'PROGRAM', false),
        ('Calorie counter', 'cal-counter-icon', 'PROGRAM', false),
        ('PDF Reader', 'pdf-reader-icon', 'PROGRAM', false),
        ('Waze GPS', 'waze-icon', 'PROGRAM', false),
        ('QR Code scanner', 'qr-scanner-icon', 'PROGRAM', false),
        ('Messenger', 'messenger-icon', 'SOCIAL', false),
        ('Emails', 'emails-icon', 'SOCIAL', false),
        ('Gallery', 'gallery-icon', 'PROGRAM', false),
        ('Kifli delivery', 'kifli-icon', 'SHOPPING', false),
        ('FoxPost', 'fox-post-icon', 'SHOPPING', true),
        ('Steam', 'steam-icon', 'GAME', false),
        ('Counter Strike 2', 'cs2-icon', 'GAME', true),
        ('Helldivers 2', 'helldivers2-icon', 'GAME', true),
        ('League of Legends', 'lol-icon', 'GAME', false),
        ('Pou', 'pou-icon', 'GAME', false);