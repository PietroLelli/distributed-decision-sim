package model

object StepGenerator {
    fun generateStep(recipeParent: Recipe): Step {
        return Step(
            "Step" + (0..100).random(), recipeParent, randomStepType()
        )
    }

    private fun randomStepType(): StepType {
        val random = (0..2).random()
        return when (random) {
            0 -> StepType.TYPE_A
            1 -> StepType.TYPE_B
            2 -> StepType.TYPE_C
            else -> StepType.TYPE_A
        }
    }
}