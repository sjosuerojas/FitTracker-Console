package fittracker.service;

import fittracker.interfaces.Exercise;

import java.util.List;

public interface ExerciseService {
    List<Exercise> getAllExercises();
    void addExercise(Exercise exercise);
    void save(String filePath);
    void load(String filePath);
}
