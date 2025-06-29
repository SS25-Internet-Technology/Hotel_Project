# Hotel_Project

# 1. Analysis
### Scenario
The hotel reservation system is designed to manage room bookings, additional services, and payment processing for a hotel. The system supports different room categories, seasonal pricing, smoking and non-smoking preferences, and payment method is selectable at check-in. It also allows for the recording of additional services such as PayTV and minibar usage. The system provides real-time availability of rooms per category for any given date or period.

### User Stories
1.	As an admin, I want to view so that I can explore and manage room categories, including single, double, smoking, and non-smoking rooms.
2.	As an admin, I want to set and adjust seasonal prices for different room categories so that the pricing reflects high and low demand periods.
3.	As an admin, I want to view and delete reservations so that I can ensure accuracy and address any booking issues.
4.	As an admin, I want to determine the available rooms per category for a specific date or period so that I can provide accurate availability information to potential guests.
5.	As an admin, I want to have a Web app so that I can use it on different mobile devices and on desktop computers.
6.	As an admin, I want to see a consistent visual appearance so that I can navigate easily, and it looks consistent.

   
### Use Case
![Captura de pantalla 2025-06-08 153017](https://github.com/user-attachments/assets/031126d9-6eb7-4835-a6a4-2ac8bb317d20)

# 2. Design 

### Wireframe & Prototype Design
[Prototype](https://www.canva.com/design/DAGg89feR5o/OUcY_-t1v___ChhVfp6hAA/edit?utm_content=DAGg89feR5o&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

![Frame](https://github.com/user-attachments/assets/50af5e0b-2a58-4674-894d-131cee3e482d)


### Domain Design
[ERD](https://drive.google.com/file/d/1vilsHmXhCGowdZmCW5X3UiojYT2ASkFP/view?usp=sharing)


![image](https://github.com/user-attachments/assets/6fb2fc7d-0841-4cac-a99b-e6554ef7afd7)


### Business Logic

As part of the system functionality, before a user can book a room, the admin must first add the room to the system. Only after this action is completed does the room appear in the room table and become available for booking. During the hotel room booking process, the user can also select from the hotel’s extra services: Service 1 is television, and Service 2 is the minibar.

| Extra Option        | Choices                | Price Impact       |
|---------------------|------------------------|--------------------|
| Smoking Preference  | Non-Smoking / Smoking  | No extra cost      |
| Pay TV              | Yes / No               | + CHF 20           |
| Minibar             | Yes / No               | + CHF 40           |

- **Smoking Preference**:
  - Stored for room assignment logic (but does not affect price).
- **Pay TV and Minibar**:
  - If selected, these are added to the booking as separate services.
  - Their prices (CHF 20 and CHF 40) are validated and summed into the reservation total.
- All extra services are **optional**.
- Only services with a **positive price** are accepted in the system.
- Services can be **viewed**, **added**, **updated**, or **deleted** via admin APIs.

**CLASS**: `ExtraServiceService`
**REPOSITORY**: `ExtraServiceRepository`
**ENTITY**: `ExtraService`

|  **Method**  | **Endpoint**               | **Description**                               |
|--------------|----------------------------|-----------------------------------------------|
| GET          | /api/extraservices/{id}    | Retrieves an extra services by ID             |
| GET          | /api/extraservices         | Returns a list of all avaiable extra services |
| POST         | /api/extraservices         | Adds a new extra service                      |
| PUT          | /api/extraservices/{id}    | Updates existing extra service                |
| DELETE       | /api/extraservices/{id}    | Deletes an extra service                      |


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
(https://miniature-waffle-wrgj465rvrggh97vg-8080.app.github.dev)/swagger-ui/index.html
Enter here the codespace URL

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

|   **View**            |   **Description**                                                         |   **APIs Used**             |
|-----------------------|---------------------------------------------------------------------------|-----------------------------|
| Rooms overview        | The administrator can add more rooms                                      | POST /api/addRoom           |
| Rooms overview        | The administrator can change the booking price of the rooms the hotel has | PUT /api/updateRoom         |
| Rooms overview        | The administrator can delete rooms in the hotel                           | DEL /api/deleteRoom         |
| Rooms overview        | A table is generated with the rooms available in the hotel                | GET /api/getRoomList        |
| Reservations overview | A table is generated with the reservations at the hotel                   | GET /api/getReservationList |
| Reservations overview | The administrator can delete reservations at the hotel                    | DEL /api/deleteReservation  |
| Rooms                 | The user can enter to make their reservation                              | POST /api/addReservation    |
# 4. Execution Project Management
### Execution
To initialize the project, you must first review the project's devcontainers and their versions. Additionally, you need to run the codespace on GitHub and put its web link into all the APIs within Budibase’s data to make sure the application functions correctly.

Once you access the application, administrators have access to dedicated tabs that help them efficiently manage the system. The **Reservation** tab provides an overview of all current reservations, while the **Add Rooms** tab allows the admin to create new room entries and view existing ones. It’s important to note that users cannot make reservations until the admin has first added rooms to the system, as these must appear in the booking interface.

On the user side, the application opens with an introduction to the Golden Harmony Hotel, where users can learn more about its history. From there, they can navigate to the **Rooms** tab to browse available accommodations or explore the **Gallery** to view images of hotel spaces and room interiors.

To make a booking, the user navigates to the **Rooms** section, selects their preferred room and the number of days they wish to stay, and then adds any additional services (such as a television or a minibar). Once ready, the user clicks **Book Now**, enters their personal information, and finalizes the reservation by clicking **Confirm**.

### Milestones and workload distribution
To improve productivity, we strategically assigned tasks based on individual strengths and project requirements. However, we had to readapt our approach after one team member left the course. This change placed additional pressure on the remaining members, requiring everyone to take on more responsibilities and double-check each other’s work to maintain our standards. The week before submission, we encountered a setback due to a misunderstanding about which Codespace to use. While some updates had already been made in a newer version, a few team members were still working on the older codebase. Once this issue was identified, we were able to align our work, thoroughly review the front end, and finalize the project. Unfortunately, this misstep cost us valuable time, limiting what we were able to implement in the final stages.

|         Milestones                   | Assigned To      | Status       |   Task / Feature                                                |
|--------------------------------------|------------------|--------------|-----------------------------------------------------------------|
| Analysis                             | Delia, Luz, Been |  Completed   | Scenario ideation, use case analysis and user story writing     |
| Prototype Design                     | Luz              |  Completed   | Creative of wireframe and prototype                             |
| Domain Design                        | Delia            |  Completed   | Definition of domain model                                      |
| Business logic and API design        | Luz, Been, Delia |  Completed   | Definition of business logic and API                            |
| Data and API Implementation          | Been             |  Completed   |Implementation of data access and business logic layers, and API |
| Frontend Implementation              | Been, Delia, Luz |  Completed   |Integration of frontend realisation                              |

### Maintaner
- Been Choi
- Delia Sommet
- Luz Arias
