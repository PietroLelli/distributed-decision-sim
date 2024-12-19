package model

object StepGenerator {
    fun generateStep(recipeParent: Recipe): Step {
        return Step("Step"+(0..100).random(), recipeParent, StepType("stepType"+(0..100).random(), 2.0, 1.0))
    }
}