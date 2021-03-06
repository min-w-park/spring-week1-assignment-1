package com.codesoom.assignment.resources;

import com.codesoom.assignment.HttpStatusCode;
import com.codesoom.assignment.models.Response;
import com.codesoom.assignment.models.Task;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class PutTaskResource extends TaskResource {

    @Override
    public Response handleRequest(List<Task> tasks, String path, String body) throws IOException {

        if (body.isBlank()) {
            return new Response("Body is empty, nothing to update", HttpStatusCode.OK);
        }

        if (path.startsWith(TASKS_ID)) {
            String param = path.substring(TASKS_ID.length());
            Long id = parseParam(param);
            Optional<Task> taskOpt = getTaskById(tasks, id);
            Task source = taskOpt.orElse(null);
            if (source != null) {
                Task updateTask = updateTaskDetail(source, body);
                return new Response(taskToJSON(updateTask), HttpStatusCode.OK);
            }
        }

        return new Response(HttpStatusCode.NOT_FOUND.getStatus(), HttpStatusCode.NOT_FOUND);
    }

    private Task updateTaskDetail(Task source, String body) throws JsonProcessingException {
        Task task = super.toTask(body);
        source.setTitle(task.getTitle());
        return source;
    }



}
