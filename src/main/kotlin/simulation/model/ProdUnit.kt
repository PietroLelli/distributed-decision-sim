package simulation.model

import it.unibo.alchemist.model.Node
import it.unibo.alchemist.model.NodeProperty
import it.unibo.alchemist.model.molecules.SimpleMolecule
import simulation.DistributedDecisionEnvironment
import simulation.policy.ExecuteFirstPolicy
import simulation.policy.QueuingPolicy

class ProdUnit (
    override val node: Node<Any>,
    val environment: DistributedDecisionEnvironment,
    val idCode: String,
    private val capabilities: List<String>
) : NodeProperty<Any> {

    override fun cloneOnNewNode(node: Node<Any>): NodeProperty<Any> {
        return ProdUnit(node, environment, idCode, capabilities)
    }

    var waitingList: List<Step> = listOf()
    private val queuingPolicy: QueuingPolicy<Step> = ExecuteFirstPolicy()
    var executedSteps: List<Step> = listOf()
    var resultsSize: Int = 0

    fun isCapableOfExecute(step: Step): Boolean {
        return when {
            "ALL" in capabilities -> true
            "A" in capabilities && step.type == StepType.TYPE_A -> true
            "B" in capabilities && step.type == StepType.TYPE_B -> true
            "C" in capabilities && step.type == StepType.TYPE_C -> true
            else -> false
        }
    }

    fun addStepToWaitingList(step: Step) {
        if(step.state == State.TOBEASSIGNED) {
            waitingList = waitingList + step
            step.state = State.ASSIGNED
        }
    }

    fun executeFromWaitingList() {
        val step = queuingPolicy.getNext(waitingList) { step -> step.state == State.ASSIGNED && step.checkResources().first }
        if (step != null) {
            step.execute()
            executedSteps += step
            waitingList = waitingList.filter { it.idCode != step.idCode }
            val resultsSize = environment.warehouse.results.size
            node.setConcentration(SimpleMolecule("ResultsSize"), resultsSize)
        }
    }

    private fun printExecutedSteps() {
        println("ProdUnit $idCode executed steps:")
        println("\t StepTypeA: ${executedSteps.count { it.type == StepType.TYPE_A }}")
        println("\t StepTypeB: ${executedSteps.count { it.type == StepType.TYPE_B }}")
        println("\t StepTypeC: ${executedSteps.count { it.type == StepType.TYPE_C }}")
    }
}