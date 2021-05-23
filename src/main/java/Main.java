import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Users.getUsers().stream().forEach(user -> {
            System.out.println(String.format("%d. %s -- %s", user.getId(), user.getName(), user.getUsername()));
        });
        System.out.println(Users.getUserByID(2).getName());
        System.out.println(Users.getUserByName("Clementine Bauch").getId());
        System.out.println(Users.getUserByUsername("Karianne").getName());
        Users.addUser(Utils.readfromfile());
        Users.deleteUserByID(3);
        Users.updateUser(Utils.readfromfile(), 6);

        Posts.printComments(2);

        Todos.printTodos(3);
    }
}
