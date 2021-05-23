import DataClases.User;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Users {
    static List<User> getUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s%s", Utils.URL, Utils.USERS_SUFFIX)))
                .GET().build();
        HttpResponse<String> response;
        response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users;
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        users = Utils.gson.fromJson(response.body(), listType);
        return users;
    }

    protected static User getUserByID(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s%s/%d", Utils.URL, Utils.USERS_SUFFIX, userId)))
                .GET().build();
        HttpResponse<String> response;
        response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return Utils.gson.fromJson(response.body(), User.class);
    }

    protected static User getUserByName(String name) throws IOException, InterruptedException {
        for (User user : getUsers()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        return null;
    }

    protected static User getUserByUsername(String name) throws IOException, InterruptedException {
        for (User user : getUsers()) {
            if (user.getUsername().equals(name)) {
                return user;
            }
        }

        return null;
    }

    protected static void deleteUserByID(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(String.format("%s%s/%d", Utils.URL, Utils.USERS_SUFFIX, userId)))
                .DELETE()
                .build();
        HttpResponse response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }

    protected static void addUser(User user) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(String.format("%s%s", Utils.URL, Utils.USERS_SUFFIX)))
                .POST(HttpRequest.BodyPublishers.ofString(Utils.gson.toJson(user)))
                .build();
        HttpResponse response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    static void updateUser(User user, int userID) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(String.format("%s%s/%d", Utils.URL, Utils.USERS_SUFFIX, userID)))
                .PUT(HttpRequest.BodyPublishers.ofString(Utils.gson.toJson(user)))
                .build();
        HttpResponse response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        System.out.println(response.statusCode());
    }
}
