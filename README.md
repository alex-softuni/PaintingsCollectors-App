Tech Stack:

Java 17

Spring Boot

Spring Data

Thymeleaf

MySQL

Maven

Front-end part - HTML&CSS, BOOTSTRAP was provided by a skeleton by my university everything else was created by me :)

PROJECT SDK: Microsoft OpenJDK 17.0.16

How to setup and run the project:

1.Clone repository or download .zip file

2.Extract the file to your desired destination

3.Open with IDE of your choice

4. Go to src/main/resources and open application.yaml

5. Adjust the credentials to your database setup (username,password,port):
   
password: '1234'

username: 'root'

url: jdbc:mysql://localhost:3306/paintings_collectors?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC

6.Project can be started from Application.java 

All working endpoints are:

IndexController:

"/" - index page

"/register" - register page;

"/login" - login page - creates user session if login is 200 OK;

"/home" - home page;

"/logout" - logs out current user in session invalidates and redirects to index page;

PaintingController:

"/paintings/add-new" - add new painting page;

"/paintings/make-favourite/{id}" - Adds a painting to favourites by painting id;

"/delete/{id} - deletes painting from MyPaintings section by id;

"/delete-favourite/{id} - deletes painting from MyFavouritesPaintings section by id;

Functional Requirements for Painting Collectors Application:

1. User Roles & Access:

Guest (not logged in): Can view Index page, register, and log in.

User (logged in): Can log out, add new paintings, view all paintings, add others paintings to favorites, and optionally rate others paintings.

2. Navigation & UI:

Navbar link "Painting Collectors Application" redirects based on login status.

Logged-in username displayed in a top banner.

3. Painting Management:
   
Paintings can be added with styles: IMPRESSIONISM, ABSTRACT, EXPRESSIONISM, SURREALISM, REALISM.

Paintings are displayed in sections:

My Paintings: Paintings added by the user; can remove them.

Other Paintings: Paintings by other users; can mark as favorite.

My Favorites: Favorite paintings of the logged-in user; removal is optional.

Most Rated (optional): Paintings sorted by votes, then name if tied.

4. Voting (optional/bonus):
   
Users can vote for other users’ paintings multiple times, cannot vote on their own.

5. Data Storage:
   
All data stored in a MySQL database.
