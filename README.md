# basic-graphql-java

Code challenge to myself to get a GraphQL server running

## Usage

1. Run a Gradle `bootRun` task to run the server
1. Use a REST client such as Postman to do a POST against `http://localhost:8080/graphql` and use a normal GraphQL query
as the post body, e.g.:
   
```graphql
{
  bookById(id: "book-1"){
    id
    name
    pageCount
    author {
      firstName
      lastName
    }
  }
}
```
