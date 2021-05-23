import DataClases.Comment;
import DataClases.Post;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Posts {


    protected static void printComments(int userId) throws IOException, InterruptedException {
        int postId = getLastPostID(userId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format
                        ("%s%s/%d%s", Utils.URL, Utils.POSTS_SUFFIX, postId, Utils.COMMENTS_SUFFIX)))
                .GET().build();
        HttpResponse<String> response;
        response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments;
        Type listType = new TypeToken<List<Comment>>() {
        }.getType();
        comments = Utils.gson.fromJson(response.body(), listType);
        Utils.writeComments(userId, postId, comments);
    }

    private static int getLastPostID(int userId) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format
                        ("%s%s/%d%s", Utils.URL, Utils.USERS_SUFFIX, userId, Utils.POSTS_SUFFIX)))
                .GET().build();
        HttpResponse<String> response;
        response = Utils.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post> posts;
        Type listType = new TypeToken<List<Post>>() {
        }.getType();
        posts = Utils.gson.fromJson(response.body(), listType);
        return posts.get(posts.size() - 1).getId();
    }


}
