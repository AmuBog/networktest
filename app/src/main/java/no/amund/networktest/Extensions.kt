package no.amund.networktest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import no.amund.networktest.network.ErrorMessage
import okhttp3.ResponseBody
import org.json.JSONObject

/**
 * Created by Amund Bogetvedt on 28/08/2020.
 */

fun ResponseBody.getError(): ErrorMessage {
    val jObjError = JSONObject(string()).getJSONObject("error")
    val message = jObjError.getString("message")
    val status = jObjError.getInt("status")
    val type = jObjError.getString("type")
    return ErrorMessage(status, message, type)
}

fun ViewGroup.inflate(layout: Int) : View {
    return LayoutInflater.from(context).inflate(layout, this, false)
}