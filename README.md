# backend-oredata-demo-banking-app
## This is the front-end of the demo project developed for the Oredata recruitment process.
The project uses JWT authentication, Axios for HTTP requests, React Redux for state management, and React Router for client-side routing.

### Login & Register Pages
Unless logging in and getting JWT token, users cannot reach any other routes except "/login" and "/register". If user tries to reach another route like "/dashboard/home", user automatically forwarded to "/login" route.

<img width="1080" height="680" alt="front-1" src="https://github.com/user-attachments/assets/d15f13d7-ad2b-4704-b0d6-94fb5f752100" />
<img width="1080" height="681" alt="front-2" src="https://github.com/user-attachments/assets/8a7bd658-3355-4448-8f82-4eaef629b1c2" />

### Home Page and Components
After logging in successfully, user gets JWT token and grants access to the application. User automatically forwarding to the "/dashboard/home" route after a successful login. Now user cannot reach "/login" and "/register" routes. If user tries to reach these routes, user automatically forwarded to "/dashboard/home" route. Now user can reach all available routes in the "/dashboard/**"

On the Navbar, user can go any route as they wish quickly. Also on the right side, there is a user information and Logout button that applies the logging out logic for user. Deletes the token and forwards user to "/login" page.

<img width="1080" height="685" alt="front-3" src="https://github.com/user-attachments/assets/52bcdf0a-e398-4bd0-92b9-c5901310edc4" />
