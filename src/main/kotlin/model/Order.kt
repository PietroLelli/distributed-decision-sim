package model

class Order (val idCode: String, var recipes: List<Recipe>) {
    fun addRecipe(recipe: Recipe) {
        recipes += recipe
    }

    fun removeRecipe(recipe: Recipe) {
        recipes -= recipe
    }
}