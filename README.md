# Balok Sangha Website

A responsive Kali Puja website built with Java 17, Spring Boot, Thymeleaf, MySQL and **native Hibernate Session/Transaction API** (not Spring Data JPA).

## Included features
- Balok Sangha logo in the header and footer
- Kali Puja date: **8 November 2026**
- Responsive homepage and mobile navigation
- Countdown to the puja date
- Total website visit counter stored in MySQL
- Members button and database-backed members list
- About, Puja and Contact sections

## 1. Requirements
- JDK 17 or newer
- Maven 3.6+
- MySQL 8+
- Eclipse / IntelliJ IDEA / VS Code (any one)

## 2. Create the database
Open MySQL Workbench or MySQL command line and run:

```sql
CREATE DATABASE balok_sangha;
```

## 3. Configure MySQL
Open `src/main/resources/application.properties` and set your MySQL username and password.

Default local configuration:
- Username: `root`
- Password: blank

If yours is different, change `spring.datasource.username` and `spring.datasource.password`.

## 4. Run the project
From the project folder:

```bash
mvn spring-boot:run
```

Or import the folder as an Existing Maven Project in Eclipse, wait for dependencies to download, then run `BalokSanghaApplication.java` as a Java Application.

Open:

`http://localhost:8080`

Hibernate creates the `club_members` and `site_visit_counter` tables automatically (`hibernate.hbm2ddl.auto=update`).

## 5. Add the real members' names
1. Start the application once so Hibernate creates the tables.
2. Open MySQL Workbench.
3. Select the `balok_sangha` database.
4. Open and edit `database/member-insert-template.sql`.
5. Replace the example names with the real names and run the SQL.
6. Open `http://localhost:8080/members`.

The website intentionally does not invent your club members' names. The public members page will show a preparation message until actual names are inserted.

## 6. Visitor counter
Every request to the homepage (`/`) increments `site_visit_counter.total_visits` in MySQL. It counts page visits, including repeat visits and refreshes; it is **not** a unique-person counter. The update is atomic in MySQL to avoid lost increments when visitors arrive together.

## 7. Important before public launch
- Confirm the exact venue and contact details with the committee.
- Add the real members' names with their permission.
- Replace the temporary address text in `index.html` with the full approved address.
- Use a hosted MySQL database and set environment variables `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` on your hosting provider.
- Deploy the Spring Boot application to a Java-supporting host. A static-only hosting service cannot run this backend or save the visitor counter.

## Project structure

```text
balok-sangha-website/
├── pom.xml
├── README.md
├── database/
│   └── member-insert-template.sql
└── src/main/
    ├── java/com/baloksangha/website/
    │   ├── BalokSanghaApplication.java
    │   ├── config/HibernateConfig.java
    │   ├── controller/HomeController.java
    │   ├── entity/Member.java
    │   ├── entity/VisitCounter.java
    │   └── service/
    │       ├── MemberService.java
    │       └── VisitCounterService.java
    └── resources/
        ├── application.properties
        ├── templates/
        │   ├── index.html
        │   └── members.html
        └── static/
            ├── css/style.css
            ├── js/site.js
            └── images/balok-sangha-logo.jpeg
```


## Club details supplied for this version
- Club name: Balok Sangha (বালক সংঘ)
- Address: Purba Medinipur, West Bengal, PIN – 721441
- Contact numbers: +91 96417 44952, +91 89724 68381, +91 82503 76319
- Facebook: https://www.facebook.com/share/1KAKdUVwAN/
- Kali Puja date: 8 November 2026

The submitted member text was "balak sangha all members". This is not an individual member-name list, so it has not been inserted as a person's name. Add the actual member names to `database/member-insert-template.sql` after receiving the approved list.


## Step 7: Add the 37 club members
The club supplied 37 individual names in Bengali. They are stored in `database/member-insert-template.sql` and `database/club-members-bengali.txt`.

After the application has started once and Hibernate has created the `club_members` table:
1. Open MySQL Workbench and select the `balok_sangha` database.
2. Open `database/member-insert-template.sql`.
3. Run the SQL script once. It replaces the current `club_members` list with these 37 supplied names.
4. Visit `http://localhost:8080/members` to see the list.

The members page gets its data from MySQL through Hibernate. Do not run the seed script again after adding future edits unless you intend to replace the complete list.


## Step 8: Kali Puja programme schedule
The homepage's Kali Puja details section now includes an **অনুষ্ঠানসূচি দেখুন** button. Clicking it reveals the programme for 8, 9 and 10 November 2026, the temple-construction donation request, the schedule-change note, and the supplied invitation poster image.
