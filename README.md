# Tour-Management-System
In this repository I have build desktop application to manage tours recevied and confirmed by the guest in tour/travel agency.
Here you can manage tours, manage guests, manage guides, manage registed hotels, manage vehicles, manage places visit by guests, and create tour reports. 
Apart from that all forms are validated and user friendly. To log into the system you must have valid login credential other wise you have to create a account for you by sign up form.

### Setup the environment 🛠
Before you run this project make sure you have satisfy following conditions,

1. Install Java version 1.8.0/(above) type `java -version` in cmd to verify
2. Install MySQL Server version 8.0.15/(above) type `mysql -V` or `SELECT VERSION()` to verify
3. Import this [Database](/projecttour.sql) to your MySQL server (after import no need to keep that file in your project directory)
4. Try to open the project in your favourite text editor eg:- NetBeans IDE, if you don't see any warning or errors your are good to go

### Setting up the database connection 🔌
You need to Update following three variables in following [file](/src/db/DBConnection.java), line 23 of Source_Packages/db/DBConnection.java 
   - DB_NAME -> your database name eg:- projecttour
   - DB_USER -> User in MySQL server, default "root"
   - DB_PASSWORD -> database user password, default ""

### Login to Tour-Management-System
To login you must enter username and password. All the passwords are hash using `md5()` hashing algorithm.
To make login process more convenient I have add create user form,
- Username - a valid user name is accepted (no need to enter a email)
- Password - a valid password
- Confirm Password - Same password you enter above step

To login to the system you need to satisfy following conditions,
- Username - previously created account username eg:- `test`
- Password - account password eg:- `test123`
- Capcha - capcha display on the login screen

### Run the project
There is different ways to test and run project
1. Open project in text editor(IDE) eg:-NetBeans IDE, press `F6` or `Play button`
2. Double click [jar file](/dist/SmartMultiplug.jar) in ProjectTour/dist/ProjectTour.jar 

## User Interfaces

![1](https://github.com/user-attachments/assets/3fc268cf-0a6f-4db3-b965-c82b207a9a28)

![2](https://github.com/user-attachments/assets/bb9092e7-34d7-499a-b1e8-1c4217e620b5)

![3](https://github.com/user-attachments/assets/b84b2708-4bfb-4c5f-821b-4351d209bbbf)

![4](https://github.com/user-attachments/assets/a7d2ff5a-c51a-440c-8781-51edf82e596a)

![5](https://github.com/user-attachments/assets/c6af9d13-78a6-47d6-9b33-5166689eef81)

![6](https://github.com/user-attachments/assets/41a71e61-43cd-472f-b0db-6e209647e4f5)

![7](https://github.com/user-attachments/assets/8839e5d9-d989-44d2-9ba8-ae7405861d43)

![8](https://github.com/user-attachments/assets/88c87300-cb0b-4bc8-a296-2411b6adb067)

![9](https://github.com/user-attachments/assets/5edfda8a-5512-4237-a727-946c80dc09d5)

