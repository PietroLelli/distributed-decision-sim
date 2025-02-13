import it.unibo.alchemist.Alchemist

class Main {}
fun main() {
    Alchemist.main(arrayOf("run --override launcher: { parameters: { batch: assign_mod, showProgress: true, autoStart: true } }", "src/main/yaml/simulation.yml"))
}