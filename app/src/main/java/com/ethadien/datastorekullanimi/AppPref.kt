package com.ethadien.datastorekullanimi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref (var context :Context){
val Context.ds : DataStore<Preferences> by preferencesDataStore("bilgiler")

    companion object {
        val NAME_KEY = stringPreferencesKey("Name")
        val COUNTER_KEY = intPreferencesKey("Counter")
    }

    suspend fun saveName(ad:String){
        context.ds.edit {
            it[NAME_KEY] = ad
        }
    }

    suspend fun saveCounter(sayac : Int){
        context.ds.edit {
            it[COUNTER_KEY] = sayac
        }
    }

    suspend fun readName() : String{
        val p = context.ds.data.first()
        return p[NAME_KEY] ?: "İsim yok"
    }

    suspend fun readCounter() : Int{
        val p = context.ds.data.first()
        return p[COUNTER_KEY] ?: 0
    }

    suspend fun deleteName(){
        context.ds.edit {
            it.remove(NAME_KEY)
        }
    }

    suspend fun resetCounter(){
        context.ds.edit {
            it[COUNTER_KEY] = 0
        }
    }
}