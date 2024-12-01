/*
//package utils
//
//import java.io.File
//
//object JsonParser {
//
//    inline fun <reified T> fromJsonFile(filePath: String, constructor: (Map<String, Any>) -> T): List<T> {
//        val fileContent = File(filePath).readText()
//        val jsonObjects = this.parseJsonArray(fileContent)
//        return jsonObjects.map { constructor(it) }
//    }
//
//    private fun parseJsonArray(json: String): List<Map<String, Any>> {
//        val trimmedJson = json.trim()
//        require(trimmedJson.startsWith("[") && trimmedJson.endsWith("]")) {
//            "Invalid JSON array format."
//        }
//
//        val objectStrings = trimmedJson
//            .substring(1, trimmedJson.length - 1)
//            .split("},")
//            .map { it.trim().let { obj -> if (!obj.endsWith("}")) "$obj}" else obj } }
//
//        return objectStrings.map { parseJsonObject(it) }
//    }
//
//    private fun parseJsonObject(json: String): Map<String, Any> {
//        val trimmedJson = json.trim()
//        require(trimmedJson.startsWith("{") && trimmedJson.endsWith("}")) {
//            "Invalid JSON object format."
//        }
//
//        val keyValuePairs = trimmedJson
//            .substring(1, trimmedJson.length - 1)
//            .split(",")
//            .map { it.split(":").map { part -> part.trim().removeSurrounding("\"") } }
//
//        return keyValuePairs.associate { (key, value) ->
//            key to parseValue(value)
//        }
//    }
//
//    private fun parseValue(value: String): Any {
//        return when {
//            value == "true" || value == "false" -> value.toBoolean()
//            value.toIntOrNull() != null -> value.toInt()
//            value.toDoubleOrNull() != null -> value.toDouble()
//            else -> value
//        }
//    }
//}
*/
