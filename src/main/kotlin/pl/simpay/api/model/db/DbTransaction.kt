package model.db

data class DbTransaction(
    var id: Int = 0, var varuenet: Double = 0.0,
    var varuenet_gross: Double = 0.0, var varuenet_partner: Double = 0.0,
    var control: String = "", var number_from: String = "",
    var sign: String = "", var status: String = ""
)
