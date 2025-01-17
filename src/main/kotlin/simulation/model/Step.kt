package simulation.model

import simulation.DistributedDecisionEnvironment

class Step (
    val idCode: String,
    private val recipeParent: Recipe,
    var type: StepType,
    private val environment: DistributedDecisionEnvironment<Any>,
    val requiredResources: MutableMap<Resource, Int> = mutableMapOf(),
    var state: State = State.TOBEASSIGNED
) {

    fun execute() {
        val orderParent = recipeParent.orderParent

        completeStep()
        if(recipeParent.steps.all { it.state == State.COMPLETE }) {
            completeRecipe()
        }
        if (orderParent.recipes.all { it.state == State.COMPLETE }) {
            completeOrder()
        }
    }

    fun checkResources(): Pair<Boolean, MutableMap<Resource, Int>> {
        var areEnoughResources = true
        val neededResources = mutableMapOf<Resource, Int>()
        requiredResources.forEach { (resource, quantity) ->
            val matchingResource = environment.warehouse.resources.keys.find { it.idCode == resource.idCode }
            if (matchingResource != null && environment.warehouse.resources[matchingResource]!! >= quantity) {
                neededResources += Pair(matchingResource, quantity)
            } else {
                areEnoughResources = false
            }
        }
        return Pair(areEnoughResources, neededResources)
    }

    private fun completeStep() {
        val (areEnoughResources, neededResources) = checkResources()
        if (areEnoughResources) {
            neededResources.forEach { (resource, quantity) ->
                environment.warehouse.getResource(resource, quantity)
            }
            state = State.COMPLETE
            environment.totCost += type.cost
            environment.totTime += type.time
        }
    }

    private fun completeRecipe() {
        recipeParent.state = State.COMPLETE
        environment.warehouse.addResult(recipeParent.result)
        //environment.results.forEach { println("ADDED result: ${it.idCode} ") }
    }

    private fun completeOrder() {
        recipeParent.orderParent.state = State.COMPLETE
        environment.completedOrders.add(recipeParent.orderParent)
        environment.orders.remove(recipeParent.orderParent)
        println("COMPLETED order: ${recipeParent.orderParent.idCode}")
        println("TOT COST: ${environment.totCost} - TOT TIME: ${environment.totTime} - COMPLETED ORDERS: ${environment.completedOrders.size}")
    }
}