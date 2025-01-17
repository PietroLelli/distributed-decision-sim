package simulation.model

import simulation.DistributedDecisionEnvironment

class Order (val idCode: String, var recipes: List<Recipe>, val environment: DistributedDecisionEnvironment<Any>, var state: State = State.TOBEASSIGNED) {

    fun printOrder() {
        println("Order $idCode")
        recipes.forEach { recipe ->
            println("\tRecipe ${recipe.idCode}")
            recipe.steps.forEach { step ->
                println("\t\tStep ${step.idCode} - ${step.state} - ${step.type} ")
                step.requiredResources.forEach { (resource, quantity) ->
                    println("\t\t\t${resource.idCode} - $quantity")
                }
            }
        }
    }
}