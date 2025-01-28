package simulation
import it.unibo.alchemist.model.Incarnation
import it.unibo.alchemist.model.environments.Continuous2DEnvironment
import it.unibo.alchemist.model.positions.Euclidean2DPosition
import simulation.model.Order
import simulation.model.Recipe
import simulation.model.State
import simulation.model.Warehouse
import simulation.model.Step
import simulation.model.Result
import simulation.model.Resource
import simulation.model.StepType

class DistributedDecisionEnvironment(incarnation: Incarnation<Any, Euclidean2DPosition>): Continuous2DEnvironment<Any>(incarnation) {
    val orders = mutableListOf<Order>()
    val completedOrders = mutableListOf<Order>()
    val warehouse : Warehouse = Warehouse("Warehouse1")
    var totCost : Double = 0.0
    var totTime : Double = 0.0

    fun generateOrder(resourceIds: List<String>, numRecipesInOrder: Int, numStepInRecipe: Int) {
        val recipeList = mutableListOf<Recipe>()
        val order = Order(
            "Order" + (0..100).random(),
            recipes = listOf(),
            environment = this,
            state = State.TOBEASSIGNED
        )
        //todo numero fisso di ricette (parametro file di configurazione yaml?)
        for (i in 1..numRecipesInOrder) {
            recipeList.addLast(generateRecipe(order, resourceIds, numStepInRecipe))
        }
        order.recipes = recipeList
        orders.add(order)
    }

    private fun generateRecipe(orderParent: Order, resourceIds: List<String>, numStepInRecipe: Int): Recipe {
        val stepList = mutableListOf<Step>()
        val randomRecipeValue = (0..10).random()
        val recipe = Recipe("Recipe$randomRecipeValue", orderParent, stepList, result = Result("Result$randomRecipeValue"))

        //todo numero fisso di step (parametro file di configurazione yaml?)
        for (i in 1 .. numStepInRecipe) {
            stepList.addLast(generateStep(recipe, resourceIds))
        }
        return recipe
    }

    private fun generateStep(recipeParent: Recipe, resourceIds: List<String>): Step {
        val requiredResources: MutableMap<Resource, Int> = mutableMapOf()
        if (resourceIds.isNotEmpty()) {
            requiredResources[Resource(resourceIds.random())] = (1..5).random()
        }

        return Step(
            "Step" + (0..100).random(),
            recipeParent,
            randomStepType(),
            recipeParent.orderParent.environment,
            requiredResources
        )
    }

    private fun randomStepType(): StepType {
        val random = (0..2).random()
        return when (random) {
            0 -> StepType.TYPE_A
            1 -> StepType.TYPE_B
            2 -> StepType.TYPE_C
            else -> StepType.TYPE_A
        }
    }
}