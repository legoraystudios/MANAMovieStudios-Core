
# MANAMovieStudios-Core

A RESTful API Movie Database made with Java/Spring Boot intended for creating movies and leaving reviews.

I've been created this project for a class in my University. This project intends to reflect knowledge about RESTful API's and the Create, Read, Update and Delete (CRUD) operations in a database.


## Prerequisites

*   Java 17 or newer.
*   Latest version of Apache Maven.
*   Latest version of MySQL or MariaDB.

## Installation

1. Download [here](https://github.com/legoraystudios/MANAMovieStudios-Core) or clone it in a empty folder with the following command (Requires Git)

```bash
git clone https://github.com/legoraystudios/MANAMovieStudios-Clone.git
```

2. Extract all the files on the folder if there's on a `.zip` file.

3. Import the `database.db` file on your MySQL or MariaDB database.

4. Open the `application.properties` file on the project and replace `allowed.origins` with your actual FrontEnd URL.

```
#...
allowed.origins=http://localhost:3000
#...
```
(This is necessary to allow all incoming requests from that specific URL due to CORS Policy).

If you're going to host this API Server inside of a subdirectory, consider to specify the path in `server.serverlet.context-path` and rememeber to uncomment the line (for example: https://example.com/myapiserver):
```
#...
server.servlet.context-path=/myapiserver
#...
```

5. Configure your MySQL or MariaDB credentials in your `application.properties`:

```
#...
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:127.0.0.1}:32780/db
spring.datasource.username=db
spring.datasource.password=db
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#...
```

6. Run `mvn package` to compile your API Server into a `.jar` file.



## API Endpoints

#### Auth

```http
 http://localhost:3000/auth/
```

| Path        | Method   | Description                         |
| :---------- | :------- | :---------------------------------- |
| `/login`    | `POST`   | Login into your account.            |
| `/register` | `POST`   | Register a new account.             |
| `/logout`   | `POST`   | Logout from your current account.   |

#### Category

```http
 http://localhost:3000/category/
```

| Path        | Method   | Description                             |
| :---------- | :------- | :-------------------------------------- |
| `/create`   | `POST`   | Create a new category.                  |
| `/`         | `GET`    | Get all categories.                     |
| `/:id`      | `GET`    | Get category by specific Category ID.   |

#### User

```http
 http://localhost:3000/user/
```

| Path        | Method   | Description                                 |
| :---------- | :------- | :------------------------------------------ |
| `/:id`      | `GET`    | Get user by specific User ID.               |
| `/`         | `GET`    | Get all users.                              |
| `/session`  | `GET`    | Get user already logged in on the session.  |

#### Movies

```http
 http://localhost:3000/movies/
```

| Path           | Method   | Description                                      |
| :------------- | :------- | :----------------------------------------------- |
| `/create`      | `POST`   | Create a new movie.                              |
| `/`            | `GET`    | Get all movies.                                  |
| `/:id`         | `GET`    | Get movie by specific Movie ID.                  |
| `/name/:name`  | `GET`    | Get movie by specific Movie Name.                |
| `/:id`         | `DELETE` | Delete movie by specific Movie ID.               |
| `/user/:id`    | `GET`    | Get movie by specific User ID.                   |
| `/:id`         | `PUT`    | Edit Movie by specific Movie ID.                 |
| `/top`         | `GET`    | Get Top 10 Movies (According by their reviews).  |
| `/category/:id`| `GET`    | Get Movies by Category ID.                       |

#### Reviews

```http
 http://localhost:3000/reviews/
```

| Path           | Method   | Description                          |
| :------------- | :------- | :----------------------------------- |
| `/create`      | `POST`   | Create a new review.                 |
| `/`            | `GET`    | Get all reviews.                     |
| `/:id`         | `GET`    | Get review by specific Review ID.    |
| `/movie/:id`   | `GET`    | Get review by specific Movie ID.     |
| `/:id`         | `DELETE` | Delete review by specific Review ID. |
| `/:id`         | `PUT`    | Edit review by specific Review ID.   |
| `/user/:id`    | `GET`    | Get review by specific User ID.      |
| `/category/:id`| `GET`    | Get Movies by Category ID.           |




    