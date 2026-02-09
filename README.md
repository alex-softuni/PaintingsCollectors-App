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

5. Voting (optional/bonus):
Users can vote for other users’ paintings multiple times, cannot vote on their own.

5. Data Storage:
All data stored in a MySQL database.
