package model

import it.unibo.alchemist.model.Action
import it.unibo.alchemist.model.Context
import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.Node.Companion.asProperty
import it.unibo.alchemist.model.Reaction
import it.unibo.alchemist.model.actions.AbstractAction

class OrderGenerator (node: Node<Any>) : AbstractAction<Any>(node) {
    private val prodUnit: ProdUnit = node.asProperty()
    private val orders = mutableListOf<Order>()

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
        //order.printOrder()
        return order
    }

    //TODO DA METTERE IN UN'ALTRA ACTION
    private fun getNextStepToAssign(): Step? {
        val order = orders.firstOrNull { order -> order.state != State.COMPLETE }
        val recipe = order?.recipes?.firstOrNull { recipe -> recipe.state != State.COMPLETE }
        val step = recipe?.steps?.firstOrNull { step -> step.state == State.TOBEASSIGNED }

        if (step != null) {
            println("StepToAssign: "+step.idCode)
        }
        return step
    }

    override fun cloneAction(p0: Node<Any>?, p1: Reaction<Any>?): Action<Any> {
        TODO("Not yet implemented")
    }

    override fun execute() {
        val order = generateOrder()
        orders.addLast(order)
        //TODO DA METTERE IN UN'ALTRA ACTION
        val stepToAssign = getNextStepToAssign()
        if (stepToAssign != null) {
            prodUnit.addStepToWaitingList(stepToAssign)
        }
        orders.forEach { o -> o.printOrder() }
    }

    override fun getContext(): Context {
        return Context.LOCAL
    }
}