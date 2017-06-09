package com.bnsantos.view.skeleton.api

import com.bnsantos.view.skeleton.model.Person
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ApiResponseDeserializer : JsonDeserializer<ApiResponse>{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ApiResponse? {
        if (json != null) {
            val jsonObject = json.asJsonObject
            val people : List<Person>? = context?.deserialize(jsonObject["results"], object : TypeToken<List<Person>>() { }.type)

            return ApiResponse(people)
        }else {
            return null
        }
    }
}