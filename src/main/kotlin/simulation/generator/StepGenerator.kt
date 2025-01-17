package simulation.generator

import simulation.model.Recipe
import simulation.model.Resource
import simulation.model.Step
import simulation.model.StepType

object StepGenerator {
    fun generateStep(recipeParent: Recipe, resourceIds: List<String>): Step {
        val requiredResources: MutableMap<Resource, Int> = mutableMapOf()
        if (resourceIds.isNotEmpty()) {
            requiredResources[Resource(resourceIds.random())] = (1..5).random()
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