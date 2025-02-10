package simulation.model

import simulation.DistributedDecisionEnvironment

class Order(
    val idCode: String, var recipes: List<Recipe>,
    val environment: DistributedDecisionEnvironment,
    var state: State = State.TOBEASSIGNED
) {
    fun completeOrder() {
        state = State.COMPLETE
        environment.completedOrders.add(this)
        environment.orders.remove(this)
        println("COMPLETED order: $idCode")
        println("TOT COST: ${environment.totCost} - TOT TIME: ${environment.totTime} - COMPLETED ORDERS: ${environment.completedOrders.size}")
    }
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