package com.aaronshaver.simplegraphql;

import com.google.common.collect.ImmutableMap;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    private final static List<Map<String, String>> authors = Arrays.asList(
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
    );

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

}
