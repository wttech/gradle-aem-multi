package com.company.aem.example.common

import com.google.gson.GsonBuilder

object JsonUtils {

  val GSON = GsonBuilder().serializeNulls().setPrettyPrinting().create()

}
