package simulation.generator

import simulation.model.Recipe
import simulation.model.Resource
import simulation.model.Step
import simulation.model.StepType

object StepGenerator {
    fun generateStep(recipeParent: Recipe, useResourceBoolean: Boolean): Step {
        var requiredResources: MutableMap<Resource, Int> = mutableMapOf()
        if (useResourceBoolean) {
            val randomResource = listOf(Resource("Resource1"), Resource("Resource2"), Resource("Resource3")).random()
            requiredResources = mutableMapOf(randomResource to (1..5).random())
        }

        return Step(
            "Step" + (0..100).random(),
            recipeParent,
            randomStepType(),
            recipeParent.orderParent.environment,
            requiredResources
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