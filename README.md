

# Anecdotes Website

Website for reading anecdotes. Registered Users can add, like and comment anecdotes. 

 - The Backend is implemented with <b>Spring Boot</b>. 
 - The frontend is using <b>Bootstrap</b> and <b>Thymeleaf</b>. 
 - <b>MySQL</b> is used for database.
 
## Main user roles

 - ***Anonymous User*** – can only view the information pages.
 - ***Regular User*** (extends ***Anonymous User***) – can create, edit and delete their own anecdotes, comment other anecdotes and view information pages.
 - ***Administrator***  (extends ***Registered User***) – can manage (edit user data and delete) all ***Registered Users***, create, edit and delete anecdote's categories and manage (edit and delete) all anecdotes.

##  Main Use Cases / Scenarios
| **Use case name** | **Brief Descriptions** |**Actors Involved**
|--|--|--|
|**1. Browse information**  |The **_User_** can browse the information views (Home, Anecdote's Categories, Anecdotes View, About).  |**All users**
|**2. Register**|**_Anonymous User_** can register in the system by providing a valid e-mail address, first and last name, and choosing password.|**_Anonymous User_**|
|**3. Change User Data**|**_Registered User_** can view and edit her personal User Data. **_Administrator_** can view and edit User Data of all ***Registered Users***. |**_Regular User, Administrator_**|
|**4. Manage Users**|**_Administrator_** can manage ***Registered User*** - edit or delete.|**_Administrator_**|
|**5. Manage Anecdote's Categories**|**_Administrator_** can create, edit and delete anecdote's categories|**_Administrator_**|
|**6. Manage Anecdotes**|**_Registered User_** can create, edit and delete their own anecdotes. **_Administrator_** can edit and delete all anecdotes.(Reasons: repetitive, cynical, vulgar)|**_Regular User, Administrator_**|
|**7. Like anecdote**|**_Registered User_** can like other **_Registered User_**'s anecdotes.|**_Regular User, Administrator_**|
|**8. Add comments**|**_Registered User_** can add comments to anecdotes.|**_Regular User, Administrator_**|


##  Main Views (Frontend)
| **View name** | **Brief Descriptions** |**URI**
|--|--|--|
|**1. Home**|Presents the introductory information for the purpose of the system as well as detailed instructions on how to start using it. Prominently offers ability to register.|/home|
|**2. User Registration**|Presents a view allowing the **_Anonymous Users_** to register.|_/register_|
|**3. Login**|Presents a view allowing the users to login.|_/login_|
|**4. User Data**|Provides ability to view and edit personal User Data.|_/personal_|
|**5. Users**|Presents ability to manage (CRUD) Users and their User Data (available for **_Administrators_** only, as described in UCs).|_/users_|
|**6. Anecdote's Categories**|Provides ability to view all anecdote's categories|_/anecdotes-categories_|
|**7. Categories**|Presents ability to manage (CRUD) Anecdote's Categories (available for **_Administrators_** only, as described in UCs).|_/categories_|
|**8. Anecdotes View**|Provides ability to view all anecdotes by category.|_/anecdotes-view_|
|**9. Anecdotes**|Presents ability to manage (CRUD) Anecdotes by category (available for **_Administrators_** only, as described in UCs).|_/anecdotes_|

##  API Resources (Backend)
| **View name** | **Brief Descriptions** |**URI**
|--|--|--|
|**1. Users**|**GET** User Data for all users, and POST new User Data (Id is auto-filled by _OKTS_ and modified entity is returned as result from **POST** request). Available only for **_Administrators_**.|_/api/users_|
|**2. User**|**GET, PUT, DELETE** User Data for **_Registered User_** with specified _userId_, according to restrictions described in UCs.|_/api/users/{userId}_|
|**3. Login**|**POST** User Credentials (e-mail address and password) and receive a valid Security Token to use in subsequent API requests.|_/api/login_|
|**4. Logout**|**POST** a logout request for ending the active session with _OKTS,_ and invalidating the issued Security Token.|_/api/logout_|
|**5. Anecdote's Categories**|**GET** Anecdote's Category, and **POST** new Anecdote's Category (Id is auto-filled by _OKTS_ and modified entity is returned as result from **POST** request). Available only for **_Administrators_**.|_/api/anecdotes-categories_|
|**6. Anecdote's Category**|**GET, PUT, DELETE** Anecdote's Category with specified _groupId_.|_/api/anecdotes-categories/{categoryId}_|
|**7. Anecdotes**|**GET** Anecdote, and **POST** new Anecdote (Id is auto-filled by _OKTS_ and modified entity is returned as result from **POST** request).|_/api/anecdotes_|
|**8. Anecdote**|**GET, PUT, DELETE** Anecdote  with specified _anecdoteId_.|_/api/anecdotes/{anecdoteId}_|
|**9. Comments**|**GET** Comment, and **POST** new Comment(Id is auto-filled by _OKTS_ and modified entity is returned as result from **POST** request) to existing Anecdote with specified _anecdoteId_|_/api/anecdotes/{anecdoteId}comment_|
|**10. Comment**|**GET, PUT, DELETE** Comments with specified _commentId_.|_/api/anecdotes/{anecdoteId}comment/{commentId}_|

# Screens


## Login

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/login.png?raw=true)

## Registration

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/registration.png?raw=true)

## Anonymous User Menu

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/annonymous_user.png?raw=true)

## Regular User Menu

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/regular_user.png?raw=true)

## Users

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/users.png?raw=true)

## Categories

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/categories.png?raw=true)

## My Profile

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/my_profile.png?raw=true)

## Add Anecdote

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/add_anecdote.png?raw=true)

## My Anecdotes

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/my_anecdotes.png?raw=true)

## Add Comment

![alt text](https://github.com/Tsvetelinna/Anecdotes-Website/blob/main/pictures/add_comment.png?raw=true)



