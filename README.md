# backend-oredata-demo-banking-app
## This is the front-end of the demo project developed for the Oredata recruitment process.
The project uses JWT authentication, Axios for HTTP requests, React Redux for state management, and React Router for client-side routing.

## .env file
REACT_APP_API_BASE_URL=https://{your_url}/api

### Login & Register Pages
Unless logging in and getting JWT token, users cannot reach any other routes except "/login" and "/register". If user tries to reach another route like "/dashboard/home", user automatically forwarded to "/login" route.

<img width="1080" height="680" alt="front-1" src="https://github.com/user-attachments/assets/d15f13d7-ad2b-4704-b0d6-94fb5f752100" />
<img width="1080" height="681" alt="front-2" src="https://github.com/user-attachments/assets/8a7bd658-3355-4448-8f82-4eaef629b1c2" />

### Home Page and Components
After logging in successfully, user gets JWT token and grants access to the application. User automatically forwarding to the "/dashboard/home" route after a successful login. Now user cannot reach "/login" and "/register" routes. If user tries to reach these routes, user automatically forwarded to "/dashboard/home" route. Now user can reach all available routes in the "/dashboard/**"

On the Navbar, user can go any route as they wish quickly. Also on the right side, there is a user information and Logout button that applies the logging out logic for user. Deletes the token and forwards user to "/login" page.

<img width="1080" height="685" alt="front-3" src="https://github.com/user-attachments/assets/52bcdf0a-e398-4bd0-92b9-c5901310edc4" />

### Account Pages

In this panel, user can preview all accounts they have. On the top of the table, there are options for filtering accounts. In the page, user can go to the "Create New Account" page, "View Details" page and "Transaction History" of the selected account. 

<img width="1080" height="683" alt="front-4" src="https://github.com/user-attachments/assets/9947ddf5-007e-4045-a098-819e793793fe" />
<img width="1080" height="681" alt="front-5" src="https://github.com/user-attachments/assets/74b65b67-00f5-4d43-af96-0fdd489a6499" />
<img width="1080" height="682" alt="front-6" src="https://github.com/user-attachments/assets/ff2f7c8c-a72b-41a1-8767-f537dca6eaef" />

In the Create Account page, user can create accounts. For testing, users can type their own balance when creating new account.

<img width="1080" height="680" alt="front-7" src="https://github.com/user-attachments/assets/b769d445-833e-4a88-aa46-de968126d97c" />

In the Account Details page, user can view specific account's details. If user don't presses the "Update Account" button, this page can be view read only. However, if user presses the "Update Account" button new buttons shows up and Account Name becomes mutable and can be changed by user. Also users can delete their own account. 

<img width="1080" height="683" alt="front-8" src="https://github.com/user-attachments/assets/61b9b126-9244-4c85-b600-f36b563e957a" />
<img width="1080" height="682" alt="front-9" src="https://github.com/user-attachments/assets/a4f195c0-423c-4ff0-8305-aaf5e211bf50" />

In the Transfer page, all accounts of the user are listed. User can select specific account and transfer money from it. When an account selected, the Sender Account Number box automatically filled and becomes immutable. Recipient Account Number can be entered by free text. Also if user wants to transfer between their own account, user can easily select the account and Recipient Account Number part will be filled automatically. Also user cannot transfer to the same account number. If Amount is sufficient and proper, transaction can be done. 

<img width="1080" height="685" alt="front-10" src="https://github.com/user-attachments/assets/fe3a5325-b2e4-4e38-b225-0f5e1757f226" />
<img width="1080" height="683" alt="front-11" src="https://github.com/user-attachments/assets/dffee5f7-5f6a-4e6c-9414-8bb359dddf67" />

In the Transaction History page, user can see the specific account's transactions. Transaction Date can be seen on the table. "From" and "To" part, the blue text indicates that, that account is previewing right now. Amount part, shows that whether the money comes in or out. Status shows that the transaction completed successfully or failed.

<img width="1080" height="682" alt="front-12" src="https://github.com/user-attachments/assets/bb0b52f9-76fc-4204-9902-0f9297a79954" />

In these pictures, another user's (testuser2) account can be seen. The transfers also can be done between different user's accounts.  

<img width="1078" height="679" alt="front-13" src="https://github.com/user-attachments/assets/e068f1d6-0c30-4f13-af70-0ccaa602f7f1" />
<img width="1080" height="680" alt="front-14" src="https://github.com/user-attachments/assets/498f89a8-8e89-4206-83ce-316a6e2af98c" />


