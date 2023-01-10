package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

//        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car MercedesBenz = new Car();
        MercedesBenz.setModel("S");
        MercedesBenz.setSeries(600);
        User user1 = new User("John", "Ivanov", "ivanov@gmail.com");
        MercedesBenz.setUser(user1);
        user1.setCar(MercedesBenz);

        Car Porsche = new Car();
        Porsche.setModel("Turbo S");
        Porsche.setSeries(1000);
        User user2 = new User("Mike", "Tayson", "tayson@gmail.com");
        Porsche.setUser(user2);
        user2.setCar(Porsche);

        userService.add(user1);
        userService.add(user2);

        System.out.println(userService.getCar("S", 600));
        System.out.println(userService.getCar("Turbo S", 1000));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        context.close();
    }
}