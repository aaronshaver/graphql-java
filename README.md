# basic-graphql-java

Code challenge to myself to get a GraphQL server running

Note: doesn't support mutations yet. I haven't had time to loop back and add that.

## Usage

1. Run a Gradle `bootRun` task to run the server
1. Use a client such as Postman to do a POST against `http://localhost:8080/graphql`
1. Note: select GraphQL after clicking the Body tab in Postman! (supposedly you can also send Raw with a header of Content-Type:application/graphql but I didn't have luck with that)

### Example queries
   
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

mutation {
    createAuthor(input: {
        firstName: "Aaron",
        lastName: "Shaver",
    }) {
        id
        firstName
        lastName
    }
}

{
    authorById(id: "author-4"){
        id
        firstName
        lastName
    }
}
```
