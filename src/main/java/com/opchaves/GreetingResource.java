package com.opchaves;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api")
public class GreetingResource {

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/posts")
    public List<Post> posts() {
        var posts = List.of(
                new Post("First Post", "This is my first post"),
                new Post("Second Post", "This is my second post"));
        return posts;
    }
}
