package ru.mygames.classicsnake.data.local.datastore

import android.widget.Toast
import androidx.datastore.core.Serializer
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


class UserSettingsSerializer() : Serializer<UserSettings> {

    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        try {
            input.use { stream ->

                return Json.decodeFromString(
                    deserializer = UserSettings.serializer(),
                    string = stream.readBytes().decodeToString()
                )
            }
        } catch (e: Exception) {
            return defaultValue
        }
    }

    override suspend fun writeTo(settings: UserSettings, output: OutputStream) {
        try {
            output.use { stream ->
                stream.write(

                    Json.encodeToString(
                        serializer = UserSettings.serializer(),
                        value = settings
                    ).encodeToByteArray()
                )
            }
        } catch (e: Exception) {

        }
    }
}
