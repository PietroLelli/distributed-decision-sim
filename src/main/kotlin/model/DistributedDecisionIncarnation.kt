package model

import it.unibo.alchemist.model.*
import it.unibo.alchemist.model.molecules.SimpleMolecule
import it.unibo.alchemist.model.positions.Euclidean2DPosition
import org.apache.commons.math3.random.RandomGenerator

class DistributedDecisionIncarnation : Incarnation<Any?, Euclidean2DPosition> {
    override fun getProperty(node: Node<Any?>?, molecule: Molecule?, property: String?): Double {
        TODO("Not yet implemented")
    }

    override fun createMolecule(s: String): Molecule {
        return SimpleMolecule(s)
    }

    override fun createConcentration(s: Any?): Any? {
        TODO("Not yet implemented")
    }

    override fun createConcentration(): Any = Any()

    override fun createAction(
        randomGenerator: RandomGenerator?,
        environment: Environment<Any?, Euclidean2DPosition>?,
        node: Node<Any?>?,
        time: TimeDistribution<Any?>?,
        actionable: Actionable<Any?>?,
        additionalParameters: Any?
    ): Action<Any?> {
        TODO("Not yet implemented")
    }

    override fun createCondition(
        randomGenerator: RandomGenerator?,
        environment: Environment<Any?, Euclidean2DPosition>?,
        node: Node<Any?>?,
        time: TimeDistribution<Any?>?,
        actionable: Actionable<Any?>?,
        additionalParameters: Any?
    ): Condition<Any?> {
        TODO("Not yet implemented")
    }

    override fun createReaction(
        randomGenerator: RandomGenerator?,
        environment: Environment<Any?, Euclidean2DPosition>?,
        node: Node<Any?>?,
        timeDistribution: TimeDistribution<Any?>?,
        parameter: Any?
    ): Reaction<Any?> {
        TODO("Not yet implemented")
    }

    override fun createTimeDistribution(
        randomGenerator: RandomGenerator?,
        environment: Environment<Any?, Euclidean2DPosition>?,
        node: Node<Any?>?,
        parameter: Any?
    ): TimeDistribution<Any?> {
        TODO("Not yet implemented")
    }

    override fun createNode(
        randomGenerator: RandomGenerator?,
        environment: Environment<Any?, Euclidean2DPosition>?,
        parameter: Any?
    ): Node<Any?> {
        TODO("Not yet implemented")
    }
}