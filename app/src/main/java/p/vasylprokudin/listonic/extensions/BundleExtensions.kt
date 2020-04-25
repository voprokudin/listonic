package p.vasylprokudin.listonic.extensions

import android.os.Bundle

fun Bundle?.getPrintableString(): String {
    this ?: return "null"

    val sb = StringBuilder()
    val keys = this.keySet()
    for (key in keys) {
        sb.append('[').append(key).append('=').append(this.get(key)).append(']')
    }

    return sb.toString()
}