package fittracker.main;

import fittracker.model.CardioExercise;
import fittracker.model.StrengthExercise;
import fittracker.service.ExerciseService;
import fittracker.service.impl.ExerciseServiceImpl;

import java.util.Scanner;

public class ConsoleMenu {
    private final ExerciseService service = new ExerciseServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private final String filePath = "exercises.txt";

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Select one option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1" -> register();
                case "2" -> show();
                case "3" -> save();
                case "4" -> load();
                case "0" -> {
                    System.out.println("Thanks for using FitTracker");
                    running = false;
                }
                default -> System.out.println("Invalid option");
            }

            System.out.println();
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("""
                ==== FitTracker ====
                1. Register new exercise
                2. Watch all exercise
                3. Save
                4. Load
                0. Exit
                ====================
                """);
    }

    private void register() {
        System.out.print("Name of the exercise: ");
        String name = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = scanner.nextInt();

        System.out.print("Type (Cardio/Strength): ");
        String type = scanner.nextLine().trim().toLowerCase();

        switch (type) {
            case "cardio" -> service.addExercise(new CardioExercise(name, duration));
            case "strength" -> service.addExercise(new StrengthExercise(name, duration));
            default -> System.out.println("Typo not recognize. Use `cardio` or `strength`");
        }
    }

    private void show() {
        var exercises = service.getAllExercises();
        if (exercises.isEmpty()) {
            System.out.println("No exercises");
        } else {
            System.out.println("Exercises registered");
            exercises.forEach(System.out::println);
        }
    }

    private void save() {
        service.save(filePath);
        System.out.println("Exercise saved successfully - Filename [" + filePath + "]");
    }

    private void load() {
        service.load(filePath);
        System.out.println("Exercises successfully loaded from: [" + filePath + "]");
    }
}
