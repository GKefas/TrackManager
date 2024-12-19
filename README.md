# TrackManager

**Note**: This application is still in development and **not in production** yet. API access is currently restricted,
and you can only access it with a provided token.

TrackManager is a Spring Boot application that provides an API for getting music data, including artists, albums, and
tracks.

## Features

- **REST API**: Exposes endpoints for managing music data (artists, albums, and tracks).
- **Database Integration**: Uses JPA to interact with a relational database (MySQL).

## Technologies Used

- Spring Boot
- Spring Web & Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL

## Authentication

To access the API, you need a **token** for authentication. To obtain the token:

1. **Direct Message**: Send a message to me via LinkedIn.
2. **Receive Token**: Once you contact me, I will provide you with a token for authentication.
3. **Usage**: Include the token in the `token` header when making API requests.

Example:

```bash
curl -X GET "http://localhost:8080/api/albums" -H "token: YOUR_TOKEN"

