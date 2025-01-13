package model

class Step (
    val idCode: String,
    private val recipeParent: Recipe,
    var type: StepType,
    var state: State = State.TOBEASSIGNED) {

    fun execute() {
        state = State.RUNNING
        //Thread.sleep(type.time.toDuration(DurationUnit.SECONDS).inWholeMilliseconds)
        state = State.COMPLETE
        if(recipeParent.steps.all { it.state == State.COMPLETE }) {
            recipeParent.state = State.COMPLETE
            recipeParent.orderParent.environment.results.add(recipeParent.result)
            recipeParent.orderParent.environment.results.forEach { println("\nADDED result: ${it.idCode} \n") }
        }
        if (recipeParent.orderParent.recipes.all { it.state == State.COMPLETE }) {
            recipeParent.orderParent.state = State.COMPLETE
        }
    }
}