# basic-graphql-java

Code challenge to myself to get a GraphQL server running

Note: doesn't support mutations yet. I haven't had time to loop back and add that.

## Usage

1. Run a Gradle `bootRun` task to run the server
1. Use a client such as Postman to do a POST against `http://localhost:8080/graphql` and use a normal GraphQL query
as the body (note: select GraphQL under after click the Body tab in Postman!), e.g.:
   
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
