package com.nikoprayogaw.pokedex.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class TypeConverter {

    @TypeConverter
    fun objectToString(value: Any?): String {
        return value.toString()
    }

    @TypeConverter
    fun stringToObject(value: String?): Any? {
        return GsonBuilder().create().fromJson(
            value,
            TypeToken.getParameterized(Any::class.java).type
        )
    }

//    @TypeConverter
//    fun fromArrayList(list: ArrayList<UserDaoItem?>?): String? {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//
//    @TypeConverter
//    fun fromString(value: String?): ArrayList<UserDaoItem?>? {
//        return GsonBuilder().create().fromJson(
//            value,
//            TypeToken.getParameterized(ArrayList::class.java, UserDaoItem::class.java).type
//        )
//    }

}