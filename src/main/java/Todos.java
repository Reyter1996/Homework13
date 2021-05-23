import DataClases.Todo;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Todos {
    protected static void printTodos(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format
                        ("%s%s/%d%s", Utils.URL, Utils.USERS_SUFFIX, userId, Utils.TODO_SUFFIX)))
                .GET().build();
        HttpResponse<String> response;
        response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Todo> todos;
        Type listType = new TypeToken<List<Todo>>() {
        }.getType();
        todos = Utils.gson.fromJson(response.body(), listType);
        todos.stream().filter(todo -> !todo.isCompleted())
                .forEach(todo -> {
                    System.out.println(todo.toString());
                });
    }
}
