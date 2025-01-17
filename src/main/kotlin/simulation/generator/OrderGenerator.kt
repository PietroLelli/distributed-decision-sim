package simulation.generator

import simulation.DistributedDecisionEnvironment
import simulation.model.Order
import simulation.model.Recipe
import simulation.model.State

object OrderGenerator {
    fun generateOrder(environment: DistributedDecisionEnvironment<Any>, resourceIds: List<String>): Order {
        val recipeList = mutableListOf<Recipe>()
        val order = Order(
            "Order" + (0..100).random(),
            recipes = listOf(),
            priority = (1..2).random(),
            environment = environment,
            state = State.TOBEASSIGNED
        )
        for (i in 0..(0..5).random()) {
            recipeList.addLast(RecipeGenerator.generateRecipe(order, resourceIds))
        }
        order.recipes = recipeList
        return order
    }
}