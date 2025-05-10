package fittracker.model;

import fittracker.interfaces.Exercise;

public record CardioExercise(String name, int duration) implements Exercise {

    @Override
    public String getType() {
        return "Cardio";
    }

    @Override
    public String toString() {
        return "[Cardio] " + name + " - " + duration + " min";
    }
}
