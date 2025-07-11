
data class Material(
    val id: Long = System.currentTimeMillis(),
    var nombre: String,
    var descripcion: String,
    var cantidad: Int,
    var unidad: String,
    var valorUnitario: Double
)
