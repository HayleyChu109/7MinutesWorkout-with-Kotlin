package com.example.a7minutesworkout

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel> {
        val exerciseList = ArrayList<ExerciseModel>()

        val ex1 = ExerciseModel(
            1,
            "Abdominal crunch",
            R.drawable.ic_abdominal_crunch,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(ex1)

        val ex2 = ExerciseModel(
            2,
            "High knees running in place",
            R.drawable.ic_high_knees_running_in_place,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(ex2)

        val ex3 = ExerciseModel(
            3, "Jumping jacks", R.drawable.ic_jumping_jacks, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex3)

        val ex4 = ExerciseModel(
            4, "Lunge", R.drawable.ic_lunge, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex4)

        val ex5 = ExerciseModel(
            5, "Plank", R.drawable.ic_plank, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex5)

        val ex6 = ExerciseModel(
            6, "Push up", R.drawable.ic_push_up, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex6)

        val ex7 = ExerciseModel(
            7,
            "Push Up and rotation",
            R.drawable.ic_push_up_and_rotation,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(ex7)

        val ex8 = ExerciseModel(
            8, "Side plank", R.drawable.ic_side_plank, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex8)

        val ex9 = ExerciseModel(
            9, "Squat", R.drawable.ic_squat, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex9)

        val ex10 = ExerciseModel(
            10,
            "Step up onto chair",
            R.drawable.ic_step_up_onto_chair,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(ex10)

        val ex11 = ExerciseModel(
            11,
            "Triceps dip on chair",
            R.drawable.ic_triceps_dip_on_chair,
            isCompleted = false,
            isSelected = false
        )
        exerciseList.add(ex11)

        val ex12 = ExerciseModel(
            12, "Wall sit", R.drawable.ic_wall_sit, isCompleted = false, isSelected = false
        )
        exerciseList.add(ex12)

        return exerciseList
    }
}