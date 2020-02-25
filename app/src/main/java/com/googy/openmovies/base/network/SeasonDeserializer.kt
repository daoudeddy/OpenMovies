package com.googy.openmovies.base.network

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.googy.openmovies.main.data.model.Episodes
import com.googy.openmovies.main.data.model.Season
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken




class SeasonDeserializer : JsonDeserializer<Season> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Season {
        val jsonObject = json.asJsonObject
        val episodes = mutableListOf<Episodes>()
        for (entry in jsonObject.entrySet()) {
            val episode = Gson().fromJson<List<Episodes>>(
                entry.value,
                object : TypeToken<ArrayList<Episodes>>() {}.type
            )
            episodes.addAll(episode)
        }
        return Season(episodes)
    }
}