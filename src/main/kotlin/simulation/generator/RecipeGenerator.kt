package simulation.generator

import simulation.model.Order
import simulation.model.Recipe
import simulation.model.Result
import simulation.model.Step

object RecipeGenerator {
    fun generateRecipe(orderParent: Order, resourceIds: List<String>): Recipe {
        val stepList = mutableListOf<Step>()
        val randomRecipeValue = (0..10).random()
        val recipe = Recipe("Recipe$randomRecipeValue", orderParent, stepList, result = Result("Result$randomRecipeValue"))

        for (i in 0..(0..5).random()) {
            stepList.addLast(StepGenerator.generateStep(recipe, resourceIds))
        }
        return recipe
    }
}