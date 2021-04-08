package com.aaronshaver.simplegraphql;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GraphQLDataFetchers {

    private final static List<Map<String, String>> books = Arrays.asList(
            ImmutableMap.of(
                    "id", "book-1",
                    "name", "The Way of Kings",
                    "pageCount", "1180",
                    "authorId", "author-1"
            ),
            ImmutableMap.of(
                    "id", "book-2",
                    "name", "The Hobbit",
                    "pageCount", "289",
                    "authorId", "author-2"
            ),
            ImmutableMap.of(
                    "id", "book-3",
                    "name", "Leviathan Wakes",
                    "pageCount", "700",
                    "authorId", "author-3"
            )
    );

    private int id = 3;
    private final ArrayList<Map<String, String>> authors = new ArrayList<>(Arrays.asList(
            ImmutableMap.of(
                    "id", "author-1",
                    "firstName", "Brandon",
                    "lastName", "Sanderson"
            ),
            ImmutableMap.of(
                    "id", "author-2",
                    "firstName", "John",
                    "lastName", "Tolkien"
            ),
            ImmutableMap.of(
                    "id", "author-3",
                    "firstName", "James",
                    "lastName", "Corey"
            )
    ));

    public DataFetcher getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return books
                    .stream()
                    .filter(book -> book.get("id").equals(bookId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, String> book = dataFetchingEnvironment.getSource();
            String authorId = book.get("authorId");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher getAuthorWithoutBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(authorId))
                    .findFirst()
                    .orElse(null);
        };
    }

    public DataFetcher createAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Map<String, Object> input =  dataFetchingEnvironment.getArgument("input");
            String firstName = (String) input.get("firstName");
            String lastName = (String) input.get("lastName");

            // obviously this isn't thread-safe / concurrency-safe but this is just a proof of concept / toy project
            id++;
            final String newId = "author-" + id;

            authors.add(
                    ImmutableMap.of(
                            "id", newId,
                            "firstName", firstName,
                            "lastName", lastName
                    ));

            return authors
                    .stream()
                    .filter(author -> author.get("id").equals(newId))
                    .findFirst()
                    .orElse(null);
        };
    }
}
