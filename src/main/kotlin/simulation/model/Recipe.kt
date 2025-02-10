package simulation.model

class Recipe (
    val idCode: String,
    val orderParent: Order,
    var steps: List<Step>,
    var state: State = State.TOBEASSIGNED,
    var result: Result
) {
    fun completeRecipe() {
        state = State.COMPLETE
        orderParent.environment.warehouse.addResult(result)
        //environment.results.forEach { println("ADDED result: ${it.idCode} ") }
    }
}