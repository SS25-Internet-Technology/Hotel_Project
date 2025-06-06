# Hotel_Project

# 1. Analysis
### Scenario
The hotel reservation system is designed to manage room bookings, additional services, and payment processing for a hotel. The system supports different room categories, seasonal pricing, smoking and non-smoking preferences, and payment method is selectable at check-in. It also allows for the recording of additional services such as PayTV and minibar usage. The system provides real-time availability of rooms per category for any given date or period.

### User Stories
1.	As an admin, I want to view so that I can explore and manage room categories, including single, double, smoking, and non-smoking rooms.
2.	As an admin, I want to set and adjust seasonal prices for different room categories so that the pricing reflects high and low demand periods.
3.	As an admin, I want to view, edit, and delete reservations so that I can ensure accuracy and address any booking issues.
4.	As an admin, I want to manage additional services such as PayTV and minibar so that I can add, edit, or remove service options available to guests.
5.	As an admin, I want to determine the available rooms per category for a specific date or period so that I can provide accurate availability information to potential guests.
6.	As an admin, I want to log in so that I can authenticate myself and access the admin functionalities securely.

### Use Case
![UserCase drawio](https://github.com/user-attachments/assets/2342a8c6-fa59-468a-adc3-2c4526bef433)

# 2. Design 
### Prototype Design
[Prototype](https://www.canva.com/design/DAGg89feR5o/OUcY_-t1v___ChhVfp6hAA/edit?utm_content=DAGg89feR5o&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

![Frame](https://github.com/user-attachments/assets/50af5e0b-2a58-4674-894d-131cee3e482d)


### Domain Design
[ERD](https://drive.google.com/file/d/1vilsHmXhCGowdZmCW5X3UiojYT2ASkFP/view?usp=sharing)


![image](https://github.com/user-attachments/assets/6fb2fc7d-0841-4cac-a99b-e6554ef7afd7)


### Business Logic
The core business logic of the application ensures that a **room reservation can only be made if the selected room is available**.

When a user tries to book a room, the system performs the following steps:

1. ✅ Checks if the selected room is available on the desired date.
2. 📌 If the room is available:
   - The reservation is created and saved.
   - The room status is updated to “reserved”.
3. ❌ If the room is already reserved:
   - The system returns an error message indicating unavailability.

# 3. Implementation Execution
### Backend Technology
This Web application is relying on [Spring Boot](https://projects.spring.io/spring-boot) and the following dependencies:

- [Spring Boot] (https://projects.spring.io/spring-boot)
- [Spring Data] (https://projects.spring.io/spring-data)
- [Java Persistence API (JPA)] (http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
- [H2 Database Engine] (https://www.h2database.com)
To bootstrap the application, the [Spring Initializr](https://start.spring.io/) has been used.
Then, the following further dependencies have been added to the project `pom.xml`:

-DB:
```XML
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
</dependency>
```
- SWAGGER:
```XML
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.3.0</version>
   </dependency>
```


### Frontend Technology
Available REST API

|     **Method**     |     **Endpoint**      |       **Description**                            |
|--------------------|-----------------------|--------------------------------------------------|
|POST                |/api/ExtraService      | Create a new Extra Service                       |
|DELETE		     |/api/ExtraService      | Cancel an Extra Service                          |
|GET	             |/api/ExtraService      | List all Extra Services                          |
|GET                 |/api/ExtraServiceList  | List Extra Services (alternative view)           |
|PUT                 |/api/ExtraService      | Update Extra Service information                 |
|POST	             |/api/PaymentInfo       | Create a new Payment information                 |
|DELETE              |/api/PaymentInfo       | Cancel Payment                                   |
|GET                 |/api/PaymentInfo       | List all Payment Information                     |
|GET                 |/api/PaymentInfoList   | List Payment Information (alternative view)      |
|PUT                 |/api/PaymentInfo       | Update Payment Information                       |
|POST                |/api/Reservation       | Create a new Reservation                         |
|DELETE              |/api/Reservation       | Cancel a Reservation                             |
|GET                 |/api/Reservation       | List all Reservations                            |
|GET                 |/api/ReservationList   | List Reservations (alternative view)             |
|PUT                 |/api/Reservation       | Update Reservation                               |
|POST                |/api/Room              | Create a new room                                |
|DELETE              |/api/Room              | Delete a room                                    |
|GET                 |/api/Room              | List all rooms                                   |
|GET                 |/api/RoomList          | List rooms (alternative view)                    |
|PUT                 |/api/Room              | Update room                                      |

Application views and used APIs

|   **View**           |   **Description**        |   **APIs Used**     |
|----------------------|--------------------------|---------------------|
| Room reservation     |                          | POST/api/Room       |
| Extra service        |                          | POST/api/Room       |
| Oersonal information |                          | GET/api/Reservation |
| Gallery              |                          | POST/api/Room       |
| Rooms                |                          | DELETE/api/Room     |
# 4. Execution Project Management
### Execution
Upon entering the app, you'll be able to view the history of the Golden Harmony Hotel. Afterward, you can click on the Rooms, Gallery, Login, and Register tabs.
When you click on the Rooms tab, you must select the desired room type and the number of days you wish to book to continue with your reservation. Next, you must select the additional services that your room will include to continue with your reservation and payment.
By selecting the gallery, you will be able to view the spaces available in the rooms and you can book whenever you want.
Finally, you can click on Login or Register, depending on whether you already have an account with our hotel or not.

### Milestones and workload distribution
To improve productivity, we strategically assigned tasks based on individual strengths and project requirements. We had to readapt it since one of the team member left the course and preasured each team memeber to work more and double check each others works. Due to a team member’s departure, we reorganized tasks to ensure timely delivery while maintaining rigorous standards

|         Milestones      | Assigned To  | Status       |   Task / Feature               |
|-------------------------|--------------|--------------|--------------------------------|
|  Use Case               | Delia        |  Completed   | Implemented login/logout       |
| Room Management UI      | Luz          |  Completed   | Pending responsive design      |
| Payment System          | Been         |  Completed   | Waiting for API documentation  |
| Database Schema Design  | Luz, Been, Delia   |  Completed   | Reviewing by team               |
