# BANKING SYSTEM (MADE BY ADITYA RANJAN SAMAL)
# Prerequisites:<br>
(Minimum Requirements: Java(c) Version: 11)<br>
(OS Required: Windows/MacOS(X)/Linux/Unix/ x86_64)<br>
# Features:<br>

# I. User Management:<br>
  1. Users can register for new accounts by providing required information.<br>
  2. The system validates and stores user data securely.<br>
  3. Users can manage their profiles (Note: This includes functionalities like viewing/updating 
  personal details).<br><br>
# II. Financial Transactions:<br>
  1. Users can deposit and withdraw funds from their accounts.<br>
  2. The system performs necessary balance checks and updates accordingly.<br>
  3. Users can transfer funds between their own accounts or to other users within the system.<br>
  4. Transaction history is maintained for each user account, allowing them to view past activities.<br><br>
# III. Support System:<br>
1. Users can submit support requests to the manager.<br>
2. Requests can specify the issue and desired action (e.g., username change, MPIN reset).<br>
3. The manager can review and address these requests through his separate portal.<br><br>
# IV. Security:<br>
1. User passwords are never stored in plain text.<br>
2. A simple hashing algorithm is used to encrypt and store passwords.<br><br>
# Additional Notes:<br>

1. Planning to implement a function that, Customers can request Manager to delete their accounts in the bank.(beta)<br>
2. Planning to build a frontend with flutter framework.(beta)<br><br>
# New Features:<br>
i) Updated the MPIN Validation procedure for extra security. Now, if a customer types incorrect MPIN for 3 times, the account will be auto-locked & will require Manager's approval to Re-Login!<br>
ii) Added Test cases for Manager & Customer for debugging. By default 3 Customer Accounts & 1 Manager account is present. But as Account Number is Auto Generated, therefore the 3 default customers will have random account number each time the program is run, unless it is connected to a database for persistent storage! Comment out the Test lines in **Login.java** if you don't want this behaviour. <br><br>
# Disclaimer:

**Copyright Notice:**<br>
This code belongs to **Aditya Ranjan Samal**.<br>
Unauthorized copying or sharing of this code without permission from the author is strictly prohibited.<br>
This project is intended for educational purposes only and should not be used in a real-world banking environment without proper security considerations and regulatory compliance.
