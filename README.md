# Getting Started

To run this project:

`cd backend && mvn clean install spring-boot:run`

Navigate to [localhost:8080/index.html](http://localhost:8080/index.html)

To run backend tests:

`mvn clean test`

A startup warning will appear about index creation of MongoDB.
This is expected and a bugreport has been made: https://github.com/spring-projects/spring-boot/issues/19236.

# The application

The application has three endpoints:

`HTTP GET` `/resume` to view all resumes
`HTTP POST` `/resume` to add a new resume (JSON request body)
`HTTP POST` `/resume/find` to find a resume based on an example document

An example of a new resume in JSON format:

`HTTP POST` `/resume`
```json
{
  "id": "124",
  "name": "John Doe",
  "phonenumber": "0612341234",
  "experience": [
    { 
      "companyName": "something", 
      "yearsExperience": 2 
    }
  ]
}
```

To find all resumes with name John Doe:

`HTTP POST` `/resume/find`
```json
{
  "name": "John Doe"
}
```

The UI is in the folder: `resources/public/index.html`.
Maybe if I had more time I would build a proper frontend, but
this will have to do for now. I used Lit-html as frontend framework
to make it easy to work with webcomponents without mush hassle.

# Testing

Unit tests are added to test functionality.

To do acceptance testing as a user you can refer to the
`Developers.postman_collection.json` file for Postman request settings.

That file contains all required URL's, users and requestbodies
and you can simply import it into postman to navigate the application.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/maven-plugin/)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.2.6.RELEASE/reference/htmlsingle/#boot-features-mongodb)

