package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Exercise;
import model.ExerciseSet;

/*
 * - THIS CLASS IS A TEMPORARY REPRESENTATION OF DATA ONLY FOR PHASE 1
 * - OBJECTS DECLARED IN THIS CLASS ARE USED FOR MODEL USAGE PURPOSES
 * - ONCE PHASE 2 BEGINS, THIS CLASS WILL BE DELETED AND DATA WILL BE 
 *   REPRESENTED USING JSON FILES
 */

public class ModelExerciseData {

    // CHEST + FRONT DELT EXERCISES
    public static final Exercise BENCH_PRESS_BARBELL = new Exercise(
            "Bench Press (Barbell)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise BENCH_PRESS_DUMBBELL = new Exercise(
            "Bench Press (Dumbbell)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise BENCH_PRESS_SMITH = new Exercise(
            "Bench Press (Smith Machine)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise PEC_DECK = new Exercise(
            "Butterfly (Pec Deck)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise CABLE_FLY = new Exercise(
            "Cable Fly Crossovers",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise INCLINED_PRESS_BARBELL = new Exercise(
            "Inclined Bench Press (Barbell)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise INCLINED_PRESS_DUMBBELL = new Exercise(
            "Inclined Bench Press (Dumbbell)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise INCLINED_PRESS_SMITH = new Exercise(
            "Inclined Bench Press (Smith Machine)",
            new ArrayList<>(Arrays.asList("Chest", "Front Delts", "Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // SIDE DELT EXERCISES
    public static final Exercise LATERAL_RAISE_DUMBBELL = new Exercise(
            "Lateral Raise (Dumbbell)",
            new ArrayList<>(Arrays.asList("Side Delts")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise LATERAL_RAISE_CABLE = new Exercise(
            "Lateral Raise (Cable)",
            new ArrayList<>(Arrays.asList("Side Delts")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // REAR DELT EXERCISES
    public static final Exercise FACE_PULL = new Exercise(
            "Face Pull (Cable)",
            new ArrayList<>(Arrays.asList("Rear Delts")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // TRICEP EXERCISES
    public static final Exercise SKULLCRUSHER_BARBELL = new Exercise(
            "Skullcrusher (Barbell)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise SKULLCRUSHER_DUMBBELL = new Exercise(
            "Skullcrusher (Dumbbell)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise TRICEP_EXTENSION_DUMBBELL = new Exercise(
            "Tricep Extension (Dumbbell)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise TRICEP_EXTENSION_CABLE = new Exercise(
            "Tricep Extension (Cable)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise TRICEP_PUSHDOWN_ROPE = new Exercise(
            "Tricep Pushdown (Rope)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise TRICEP_PUSHDOWN_BAR = new Exercise(
            "Tricep Pushdown (Bar)",
            new ArrayList<>(Arrays.asList("Triceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // LATS EXERCISES
    public static final Exercise LAT_PULLDOWN = new Exercise(
            "Lat Pulldown",
            new ArrayList<>(Arrays.asList("Lats", "Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise ISOLAT_ROW = new Exercise(
            "Iso-Lateral Row (Machine)",
            new ArrayList<>(Arrays.asList("Lats", "Traps", "Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // UPPER BACK EXERCISES
    public static final Exercise ROW_BARBELL = new Exercise(
            "Bent Over Row (Barbell)",
            new ArrayList<>(Arrays.asList("Upper Back", "Lats", "Traps", "Rear Delts", "Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise ROW_DUMBBELL = new Exercise(
            "Bent Over Row (Dumbbell)",
            new ArrayList<>(Arrays.asList("Upper Back", "Lats", "Traps", "Rear Delts", "Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise SEATED_CABLE_ROW = new Exercise(
            "Seated Cable Row (Machine)",
            new ArrayList<>(Arrays.asList("Upper Back", "Lats", "Traps", "Rear Delts", "Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise REVERSE_FLY = new Exercise(
            "Reverse Fly (Cable)",
            new ArrayList<>(Arrays.asList("Upper Back", "Rear Delts", "Traps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // LOWER BACK EXERCISES
    public static final Exercise BACK_EXTENSION = new Exercise(
            "Back Extension (Machine)",
            new ArrayList<>(Arrays.asList("Lower Back", "Glutes")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // BICEP EXERCISES
    public static final Exercise BICEP_CURL_BARBELL = new Exercise(
            "Bicep Curl (Barbell)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise BICEP_CURL_DUMBBELL = new Exercise(
            "Bicep Curl (Dumbbell)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise BICEP_CURL_CABLE = new Exercise(
            "Bicep Curl (Cable)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise BICEP_CURL_MACHINE = new Exercise(
            "Bicep Curl (Machine)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise HAMMER_CURL = new Exercise(
            "Hammer Curl)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise PREACHER_CURL_MACHINE = new Exercise(
            "Preacher Curl (Machine)",
            new ArrayList<>(Arrays.asList("Biceps")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // CORE EXERCISES
    public static final Exercise CABLE_CRUNCH = new Exercise(
            "Crunch (Cable)",
            new ArrayList<>(Arrays.asList("Abs")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise MACHINE_CRUNCH = new Exercise(
            "Crunch (Machine)",
            new ArrayList<>(Arrays.asList("Abs")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    // LEG EXERCISES
    public static final Exercise SQUAT = new Exercise(
            "Squat (Barbell)",
            new ArrayList<>(Arrays.asList("Quads", "Hamstrings", "Glutes")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise LEG_EXTENSION = new Exercise(
            "Seated Leg Extension (Machine)",
            new ArrayList<>(Arrays.asList("Quads")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise LEG_CURL = new Exercise(
            "Seated Leg Curl (Machine)",
            new ArrayList<>(Arrays.asList("Hamstrings")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final Exercise CALF_RAISE = new Exercise(
            "Seated Calf Raise",
            new ArrayList<>(Arrays.asList("Calves")),
            new ArrayList<>(Arrays.asList(new ExerciseSet(0, 0))));

    public static final List<Exercise> LEGS_LIST = new ArrayList<>(
            Arrays.asList(SQUAT, LEG_CURL, LEG_EXTENSION, CALF_RAISE));
    public static final List<Exercise> ABS_LIST = new ArrayList<>(
            Arrays.asList(CABLE_CRUNCH, MACHINE_CRUNCH));

    public static final List<Exercise> BICEP_LIST = new ArrayList<>(
            Arrays.asList(BICEP_CURL_BARBELL, BICEP_CURL_DUMBBELL, BICEP_CURL_CABLE, BICEP_CURL_MACHINE, HAMMER_CURL,
                    PREACHER_CURL_MACHINE));
    public static final List<Exercise> LOWER_BACK_LIST = new ArrayList<>(
            Arrays.asList(BACK_EXTENSION));
    public static final List<Exercise> UPPER_BACK_LIST = new ArrayList<>(
            Arrays.asList(ROW_BARBELL, ROW_DUMBBELL, SEATED_CABLE_ROW, REVERSE_FLY));
    public static final List<Exercise> LAT_LIST = new ArrayList<>(
            Arrays.asList(LAT_PULLDOWN, ISOLAT_ROW));
    public static final List<Exercise> REAR_DELT_LIST = new ArrayList<>(
            Arrays.asList(FACE_PULL));

    public static final List<Exercise> CHEST_LIST = new ArrayList<>(
            Arrays.asList(BENCH_PRESS_BARBELL, BENCH_PRESS_DUMBBELL, BENCH_PRESS_SMITH, PEC_DECK, CABLE_FLY,
                    INCLINED_PRESS_BARBELL, INCLINED_PRESS_DUMBBELL, INCLINED_PRESS_SMITH));
    public static final List<Exercise> SIDE_DELT_LIST = new ArrayList<>(
            Arrays.asList(LATERAL_RAISE_DUMBBELL, LATERAL_RAISE_CABLE));
    public static final List<Exercise> TRICEP_LIST = new ArrayList<>(
            Arrays.asList(SKULLCRUSHER_BARBELL, SKULLCRUSHER_DUMBBELL, TRICEP_EXTENSION_DUMBBELL,
                    TRICEP_EXTENSION_CABLE, TRICEP_PUSHDOWN_ROPE, TRICEP_PUSHDOWN_BAR));

    private ModelExerciseData() {

    }
}
