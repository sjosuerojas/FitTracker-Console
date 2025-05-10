package fittracker.model;

import fittracker.interfaces.Exercise;

public record StrengthExercise(String name, int duration) implements Exercise {

    @Override
    public String getType() {
        return "Strength";
    }

    @Override
    public String toString() {
        return "[Strength] " + name + " - " + duration + " min";
    }
}
