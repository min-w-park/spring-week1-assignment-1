package com.codesoom.assignment.resources;

import com.codesoom.assignment.HttpMethod;

public class ResourceFactory {

    public TaskResource createResource(HttpMethod method) {
        switch (method) {
            case GET:
                return new GetTaskResource();
            case POST:
                return new PostTaskResource();
            case PUT:
            case PATCH:
                return new PutTaskResource();
            case DELETE:
                return new DeleteTaskResource();
        }
        return new GetTaskResource();
    }
}
