# Spring Data JPA Deep Dive 

This project is a Spring Boot and Spring Data JPA application built for the purpose of understanding how JPA and Hibernate function internally.  
It is not a feature-complete CRUD application. The focus is on demystifying JPA and Hibernate internals, not building REST endpoints.

---

## Purpose

Most tutorials end at “it works.” This project explains **why** it works.

The application is designed to:
- Clarify how Spring Data JPA abstracts database interactions
- Show Hibernate's internal management of entities and state
- Demonstrate persistence context behavior and dirty checking
- Apply correct JPA annotations and define database constraints
- Seed initial data in a reproducible, production-style manner

If you are seeking a typical controller-based CRUD app, this is not your repository.  
This is for those who want to understand JPA beyond copying annotations.

---

## Scope and Topics Covered

**Included:**
- Spring Data JPA core concepts
- Architecture and internal layers of Spring Data JPA
- Hibernate internals at a practical, not hand-wavy, level
- `JpaRepository` and real repository patterns
- JPA entity lifecycle and state transitions
- Use of EntityManager and PersistenceContext
- Dirty checking with `@Transactional`
- Entity mapping using:
    - `@Entity`
    - `@Table`
    - `@Column`
    - `@Enumerated`
- Database constraints:
    - Unique constraints
    - Indexes
- Hibernate `ddl-auto` configuration
- Database seeding and initialization
- Best practices for designing JPA entities

**Not Included** (by design):
- Full CRUD REST APIs
- Web controllers and HTTP endpoints
- DTO mapping layers
- Validation and exception handling
- Security (JWT, OAuth, etc.)
- Pagination, complex filtering, or advanced query logic

For these features, see a different project.

---

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Relational Database (PostgreSQL or MySQL recommended)
- Maven or Gradle

---

## Project Structure

```
entity/                # JPA entity classes with mappings and constraints
repository/            # JpaRepository interfaces
config/                # Database and JPA configuration
resources/data/        # Database initialization and seeding logic
```

---

## How to Use

1. Clone the repository.
2. Configure your database and connection settings in `application.properties`.
3. Run the application with your preferred build tool.
4. Study entity definitions, repository interfaces, and configuration files.
5. Observe and experiment with entity state transitions, dirty checking, and persistence context behavior.

---

## Learning Outcomes

On completion, you should:
- Understand how JPA operates (not just how to use its annotations)
- Know what occurs within Hibernate during entity persistence and update
- Grasp persistence context and dirty checking mechanisms
- Design safer, more predictable JPA entities
- Treat JPA as a framework you control, not as a black box

---

## Disclaimer

This project is strictly for educational purposes and does not implement production-level REST APIs or web interfaces.

---
