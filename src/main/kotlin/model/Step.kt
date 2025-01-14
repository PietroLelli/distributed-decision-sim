package model

class Step (
    val idCode: String,
    private val recipeParent: Recipe,
    var type: StepType,
    var state: State = State.TOBEASSIGNED) {

    fun execute() {
        val orderParent = recipeParent.orderParent
        val environment = orderParent.environment

        completeStep(environment)
        if(recipeParent.steps.all { it.state == State.COMPLETE }) {
            completeRecipe(environment)
        }
        if (orderParent.recipes.all { it.state == State.COMPLETE }) {
            completeOrder(environment)
        }
    }

    private fun completeStep(environment: DistributedDecisionEnvironment<Any>) {
        state = State.COMPLETE
        environment.totCost += type.cost
        environment.totTime += type.time
    }
    private fun completeRecipe(environment: DistributedDecisionEnvironment<Any>) {
        recipeParent.state = State.COMPLETE
        environment.results.add(recipeParent.result)
        environment.results.forEach { println("ADDED result: ${it.idCode} ") }
    }
    private fun completeOrder(environment: DistributedDecisionEnvironment<Any>) {
        recipeParent.orderParent.state = State.COMPLETE
        environment.completedOrders.add(recipeParent.orderParent)
        environment.orders.remove(recipeParent.orderParent)
        println("COMPLETED order: ${recipeParent.orderParent.idCode}")
        println("TOT COST: ${environment.totCost} - TOT TIME: ${environment.totTime} - COMPLETED ORDERS: ${environment.completedOrders.size}")
    }
}