# Quick readme

To use this application

you need:
1. JDK 25
2. Maven

you need to:

1. first clone the repository
2. run mvn package in both directories of root
3. start backend-api and device separately
4. use device

The device communicates with the backend on network.

The backend starts on port 8080.

There is no persistence, everything is stored in-memory.

### Login and registration
On startup, the app generates a user with Administrator role.
- username: admin
- password: admin

This user can registrate other users, they are not able to make it on their own.

On the registration form, you have to provide these fields:
- username
- password
- repeatPassword
- role ( which can be: role_admin, role_user or role_child )

### Roles and Accounts
On startup, the app generates 3 roles:
- ROLE_ADMIN
- ROLE_USER
- ROLE_CHILD

A child cannot upload, delete or set profile pictures or wallpapers,
and cannot add "Adult only" games to their library.

A user cannot register other users.

An admin has permission to everything.

