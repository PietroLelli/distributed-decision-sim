package simulation.model

class Warehouse (val idCode: String) {
    var results : MutableMap<Result, Int> = mutableMapOf()
    var resources : MutableMap<Resource, Int> = mutableMapOf()

    fun addResult(result: Result) {
        if (results.containsKey(result)) {
            val oldQuantity = results.getValue(result)
            results += Pair(result, oldQuantity + 1)
        } else {
            results += Pair(result, 1)
        }    }

    fun addResource(resource: Resource) {
        if (resources.containsKey(resource)) {
            val oldQuantity = resources.getValue(resource)
            resources += Pair(resource, oldQuantity + 1)
        } else {
            resources += Pair(resource, 1)
        }
    }

    fun getResource(resource: Resource, quantity: Int) : Resource? {
        if (resources.containsKey(resource) && resources[resource]!! >= quantity) {
            if (resources[resource]!! > quantity) {
                resources += Pair(resource, resources[resource]!! - quantity)
                return resource
            } else if (resources[resource]!! == quantity) {
                resources.remove(resource)
                return resource
            }
        }
        return null
    }

    fun getResult(result: Result, quantity: Int) : Result? {
        if (results.containsKey(result) && results[result]!! >= quantity) {
            if (results[result]!! > quantity) {
                results += Pair(result, results[result]!! - quantity)
                return result
            } else if (results[result]!! == quantity) {
                results.remove(result)
                return result
            }
        }
        return null
    }

   fun getResourceById(resourceId: String, quantity: Int) : Resource? {
        if (resources.any {it.key.idCode == resourceId}) {
            val resource = resources.filter { it.key.idCode == resourceId }.keys.first()
            if (resources[resource]!! > quantity) {
                resources += Pair(resource, resources[resource]!! - quantity)
                return resource
            } else {
                resources.remove(resource)
                return resource
            }
        }
        return null
    }


    fun getResultById(resultId: String, quantity: Int) : Result? {
        if (results.any {it.key.idCode == resultId}) {
            val result = results.filter { it.key.idCode == resultId }.keys.first()
            if (results[result]!! > quantity) {
                results += Pair(result, results[result]!! - quantity)
                return result
            } else {
                results.remove(result)
                return result
            }
        }
        return null
    }

    fun printResources() {
        println("\nWarehouse Resources:")
        resources.forEach { (resource, quantity) -> println("Resource: ${resource.idCode} - Quantity: $quantity") }
    }

    fun printResults() {
        println("\nWarehouse Results:")
        results.forEach { (result, quantity) -> println("Result: ${result.idCode} - Quantity: $quantity") }
    }
}