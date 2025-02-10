package simulation.model

enum class StepType(val idCode: String, var time: Double, var cost: Double) {
    TYPE_A("A", 1.0, 10.0),
    TYPE_B("B", 20.0, 20.0),
    TYPE_C("C", 50.0, 30.0),
}
