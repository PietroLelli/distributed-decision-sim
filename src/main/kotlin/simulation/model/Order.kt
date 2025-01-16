package simulation.model

import simulation.DistributedDecisionEnvironment

class Order (val idCode: String, var recipes: List<Recipe>, var priority: Int = 1, val environment: DistributedDecisionEnvironment<Any>, var state: State = State.TOBEASSIGNED) {

    fun printOrder() {
        println("Order $idCode - $state - Priority: $priority")
        recipes.forEach { recipe ->
            println("\tRecipe ${recipe.idCode} - ${recipe.state}")
            recipe.steps.forEach { step ->
                println("\t\tStep ${step.idCode} - ${step.state} - ${step.type} ")
                step.requiredResources.forEach { (resource, quantity) ->
                    println("\t\t\t${resource.idCode} - $quantity")
                }
            }
        }
    }
}