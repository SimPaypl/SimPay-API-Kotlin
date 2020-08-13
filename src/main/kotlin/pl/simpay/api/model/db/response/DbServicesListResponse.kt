package model.db.response

import model.db.DbService

data class DbServicesListResponse(var status: String, var services: List<DbService>)
