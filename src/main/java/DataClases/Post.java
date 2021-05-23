package DataClases;

public class Post {
private  int userId;
private int id;
private String tittle;
private String body;

    public Post(int userId, String tittle, String body) {
        this.userId = userId;
        this.tittle = tittle;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }


    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
