package p.vasylprokudin.listonic.util

import java.util.Random

class RowInfoProvider {

    companion object {
        private const val STRING_LENGTH = 8
    }

    private val rowColors: List<String> = listOf(
        "#fcd116", "#ce1126", "#0038a8", "#ffba63", "#854410"
    )

    fun provideRandomName(): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        return (1..STRING_LENGTH)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun provideColor(): String {
        val random = Random()
        return rowColors[random.nextInt(rowColors.size)]
    }
}