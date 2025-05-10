package fittracker.service.impl;

import fittracker.interfaces.Exercise;
import fittracker.model.CardioExercise;
import fittracker.model.StrengthExercise;
import fittracker.service.ExerciseService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExerciseServiceImpl implements ExerciseService {
    private final List<Exercise> exercises = new ArrayList<>();

    @Override
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    @Override
    public List<Exercise> getAllExercises() {
        return new ArrayList<>(exercises);
    }

    @Override
    public void save(String filePath) {
        List<String> lines = exercises.stream()
                .map(Exercise::toString)
                .toList();
        try {
            Files.write(Path.of(filePath), lines);
        } catch (IOException e) {
            System.err.println("Error while saving in file:" + e.getMessage());
        }
    }

    @Override
    public void load(String filePath) {
        exercises.clear();

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                Exercise ex = parseLineToExercise(line);
                if (ex != null) {
                    exercises.add(ex);
                }
            }
        } catch (IOException e) {
            System.err.println("Error while loading file: " + e.getMessage());
        }
    }

    private Exercise parseLineToExercise(String line) {
        try {
            if (line.startsWith("[Cardio]")) {
                String[] parts = line.substring(9).split(" - ");
                return new CardioExercise(parts[0].trim(), Integer.parseInt(parts[1].replace("min", "").trim()));
            } else if (line.startsWith("[Strength]")) {
                String[] parts = line.substring(11).split(" - ");
                return new StrengthExercise(parts[0].trim(), Integer.parseInt(parts[1].replace("min", "").trim()));
            }
        } catch (Exception e) {
            System.err.println("Error while parsing : " + line);
        }
        
        return null;
    }
}
