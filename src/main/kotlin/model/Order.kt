package model

class Order (val idCode: String, var recipes: List<Recipe>, var state: State = State.TOBEASSIGNED) {
    fun printOrder() {
        println("Order $idCode - $state")
        recipes.forEach { recipe ->
            println("\tRecipe ${recipe.idCode} - ${recipe.state}")
            recipe.steps.forEach { step ->
                println("\t\tStep ${step.idCode} - ${step.state}")
            }
        }
    }
}