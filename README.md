Here I am providing the requirements given from my university for that project.
Functional Requirements
The Functionality Requirements describe the functionality that the application must support.
The application should provide Guest (not logged in) users with the functionality to log in, register and view the Index page.
The application should provide Users (logged in) with the functionality to log out, add a new painting (Add painting page), view all paintings (Home page) and add a painting from Other Paintings to his Favorite Paintings. The logged in Users can rate the paintings of other users (Other Paintings).
Painting Collectors Application in the navbar should redirect to the appropriate URL depending on that if the user is logged in.
The application should provide functionality for adding paintings with following styles - IMPRESSIONISM, ABSTRACT, EXPRESSIONISM, SURREALISM or REALISM.
The paintings should be separated into four different sections according to their ownership and rate.
At the top, above all sections in a banner, the username of the logged in user should be displayed.
My Paintings section
This section displays added paintings by the currently logged in User. When the User adds a new painting, it should appear here. By the "Remove" button in the My Paintings section, the owner of the painting can remove it from Database.
Other Paintings section
This section shows other paintings from all other users who use the app.
When the user clicks on the "Favorite" button of some painting in Other Paintings section, he adds the painting to his Favorite Paintings. You should not delete this painting from DB. The painting should be added to the My Favorites collection with paintings of the currently logged-in User. Added painting should appear in the My Favorites section of the User.
Rate the paintings of other users - NOT REQUIRED IT IS A BONUS!
User can rate the paintings of other users by clicking on the "Vote" button. When the User clicks the button, he rates the given painting by adding 1 vote for it. The User can vote for every painting except his own unlimited number of times and this step cannot be undone.
My Favorites section
In this section, the favorite paintings of currently logged in User are shown.
Removing a Painting from the My Favorites section - NOT REQUIRED IT IS A BONUS!
The painting can be removed from the My Favorites section by clicking the "X" button.
Most Rated Paintings section - NOT REQUIRED IT IS A BONUS!
Аll uploaded pictures with the corresponding number of votes should be displayed in this section, sorted in descending order according to the number of votes. If the count of votes of paintings is equal, order them by the name of the painting ascending. 
The application should store its data in a MySQL database.
