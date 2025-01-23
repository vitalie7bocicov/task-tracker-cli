import application.App;
import model.Operation;
import model.Status;
import service.TaskService;
import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        try {
            app.run(args);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong. Please try again.");
        }
    }
}