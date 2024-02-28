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

      userService.add(new User("Sam", "Will", "sam@mail.ru", new Car(5, "BMW M")));
      userService.add(new User("Max", "Wood", "wood@mail.ru", new Car(4, "Mercedes-Benz")));
      userService.add(new User("Alice", "Price", "price99@mail.ru", new Car(75, "Toyota Camry")));
      userService.add(new User("Alina", "Gazmanova", "gazy27@mail.ru", new Car(8, "BMW i")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      List<User> usersWithCar = userService.getUserByCarModelAndSeries(5, "BMW M");
      for (User user : usersWithCar) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("user's car's series = "+user.getCar().getSeries());
         System.out.println("user's car's model = "+user.getCar().getModel());
         System.out.println();
      }
      context.close();
   }
}
