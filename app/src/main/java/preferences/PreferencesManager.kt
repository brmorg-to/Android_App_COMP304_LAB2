package preferences

import android.content.Context

class PreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("UserSelections", Context.MODE_PRIVATE)

    fun getSelectedOptions(): Map<String, String> {
        val selectedOptions = mutableMapOf<String, String>()
        val allEntries = sharedPreferences.all
        for ((key, value) in allEntries) {
            selectedOptions[key] = value as String
        }
        return selectedOptions
    }

    fun addSelectedOption(key: String, value: String) {
        val selectedOptions = getSelectedOptions().toMutableMap()
        selectedOptions[key] = value
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun removeSelectedOption(key: String) {
        val selectedOptions = getSelectedOptions().toMutableMap()
        selectedOptions.remove(key)
        sharedPreferences.edit().remove(key).apply()
    }

    fun clearSelectedOptions() {
        sharedPreferences.edit().clear().apply()
    }

    private fun getSelectedOptionsString(): String {
        val selectedOptions = getSelectedOptions()
        val sb = StringBuilder()
        for ((key, value) in selectedOptions) {
            sb.append("$key: $value\n")
        }
        return sb.toString()
    }

    override fun toString(): String {
        return "selectedOptions=\n${getSelectedOptionsString()})"
    }
}