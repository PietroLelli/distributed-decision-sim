package model

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction

class OrderGenerator (node: Node<Any>, val environment: DistributedDecisionEnvironment<Any>) : AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()

    private fun generateOrder(): Order {
        val recipeList = mutableListOf<Recipe>()
        val order = Order(
            "Order" + (0..100).random(),
            recipes = listOf(),
            state = State.TOBEASSIGNED,
        )
        for (i in 0..(0..5).random()) {
            recipeList.addLast(RecipeGenerator.generateRecipe(order))
        }
        order.recipes = recipeList
        return order
    }

    //TODO DA METTERE IN UN'ALTRA ACTION
    private fun getNextStepToAssign(): Step? {
//        val order = orders.firstOrNull { order -> order.state != State.COMPLETE }
//        val recipe = order?.recipes?.firstOrNull { recipe -> recipe.state != State.COMPLETE }
//        val step = recipe?.steps?.firstOrNull { step -> step.state == State.TOBEASSIGNED }
//
//        if (step != null) {
//            println("StepToAssign: "+step.idCode)
//        }
//        return step
        return null
    }

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        println("\nEXECUTION ORDER GENERATOR PROD-UNIT: " + node.id + "\n")
        val order = generateOrder()
        environment.addOrder(order)
        environment.orders.forEach(Order::printOrder)
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}