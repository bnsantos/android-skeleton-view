package com.bnsantos.view.skeleton.model

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

object PersonDeserializer : JsonDeserializer<Person> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Person? {
        if (json != null) {
            val jsonObject = json.asJsonObject
            val name = jsonObject.getAsJsonObject("name")
            val picture = jsonObject.getAsJsonObject("picture")

            return Person(
                    name = name["first"].asString + " " + name["last"].asString,
                    email = jsonObject["email"].asString,
                    gender = jsonObject["gender"].asString,
                    picture = picture["large"].asString
            )
        }else {
            return null
        }
    }
}