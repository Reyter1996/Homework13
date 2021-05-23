import DataClases.Comment;
import DataClases.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.List;

public class Utils {
    protected static final String URL = "https://jsonplaceholder.typicode.com";
    protected static final String TODO_SUFFIX = "/todos";
    protected static final String USERS_SUFFIX = "/users";
    protected static final String POSTS_SUFFIX = "/posts";
    protected static final String COMMENTS_SUFFIX = "/comments";
    private static final String USER_FILE_PATH = "src\\main\\resources\\user.json";
    private static final String RES_PATH = "src\\main\\resources\\";
    private static final String JSON_SUFFIX = ".json";
    protected static HttpClient httpClient = HttpClient.newBuilder().build();
    protected static Gson gson = new Gson();

    protected static User readfromfile() {
        File file = new File(USER_FILE_PATH);
        if (!file.exists()) {
            System.out.println("file not found!");
            return null;
        }
        try (FileReader reader = new FileReader(file)) {
            StringBuilder builder = new StringBuilder("");
            char[] buf = new char[1024];
            int c;
            while ((c = reader.read(buf)) > 0) {
                if (c < 1024) {
                    buf = Arrays.copyOf(buf, c);
                }
                builder.append(buf);
            }
            User user = new Gson().fromJson(builder.toString(), User.class);
            return user;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    protected static void writeComments(int userId, int postId, List<Comment> comments) {
        String path = String.format("%suser-%d-post-%d-comments.json", RES_PATH, userId, postId)
                /*"src\\main\\resources\\comments.json"*/;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(comments));
            fileWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
