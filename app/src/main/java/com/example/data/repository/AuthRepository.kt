package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.data.models.ExamType
import com.example.data.models.ReservationCategory
import com.example.data.models.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("kcet_comedk_auth_prefs", Context.MODE_PRIVATE)

    private val _sessionState = MutableStateFlow(loadSession())
    val sessionState: StateFlow<UserSession> = _sessionState.asStateFlow()

    private fun loadSession(): UserSession {
        val isLoggedIn = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        val isGuest = prefs.getBoolean(KEY_IS_GUEST, false)
        val name = prefs.getString(KEY_NAME, "") ?: ""
        val email = prefs.getString(KEY_EMAIL, "") ?: ""
        val phone = prefs.getString(KEY_PHONE, "") ?: ""
        val examStr = prefs.getString(KEY_EXAM, ExamType.KCET.name) ?: ExamType.KCET.name
        val catStr = prefs.getString(KEY_CATEGORY, ReservationCategory.GM.name) ?: ReservationCategory.GM.name
        val rankStr = prefs.getString(KEY_RANK, "15000") ?: "15000"
        val timestamp = prefs.getLong(KEY_TIMESTAMP, 0L)

        val exam = try { ExamType.valueOf(examStr) } catch (e: Exception) { ExamType.KCET }
        val category = try { ReservationCategory.valueOf(catStr) } catch (e: Exception) { ReservationCategory.GM }

        return UserSession(
            name = name,
            email = email,
            phoneNumber = phone,
            targetExam = exam,
            category = category,
            expectedRank = rankStr,
            isLoggedIn = isLoggedIn,
            isGuest = isGuest,
            verifiedTimestamp = timestamp
        )
    }

    fun saveLoginSession(
        name: String,
        email: String,
        phone: String,
        exam: ExamType,
        category: ReservationCategory,
        rank: String
    ) {
        val timestamp = System.currentTimeMillis()
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putBoolean(KEY_IS_GUEST, false)
            .putString(KEY_NAME, name.trim())
            .putString(KEY_EMAIL, email.trim())
            .putString(KEY_PHONE, phone.trim())
            .putString(KEY_EXAM, exam.name)
            .putString(KEY_CATEGORY, category.name)
            .putString(KEY_RANK, rank.trim())
            .putLong(KEY_TIMESTAMP, timestamp)
            .apply()

        _sessionState.value = UserSession(
            name = name.trim(),
            email = email.trim(),
            phoneNumber = phone.trim(),
            targetExam = exam,
            category = category,
            expectedRank = rank.trim(),
            isLoggedIn = true,
            isGuest = false,
            verifiedTimestamp = timestamp
        )
    }

    fun continueAsGuest(exam: ExamType, category: ReservationCategory, rank: String) {
        val timestamp = System.currentTimeMillis()
        prefs.edit()
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .putBoolean(KEY_IS_GUEST, true)
            .putString(KEY_NAME, "Guest Candidate")
            .putString(KEY_EMAIL, "guest@karnataka.gov.in")
            .putString(KEY_PHONE, "9876543210")
            .putString(KEY_EXAM, exam.name)
            .putString(KEY_CATEGORY, category.name)
            .putString(KEY_RANK, rank.trim())
            .putLong(KEY_TIMESTAMP, timestamp)
            .apply()

        _sessionState.value = UserSession(
            name = "Guest Candidate",
            email = "guest@karnataka.gov.in",
            phoneNumber = "9876543210",
            targetExam = exam,
            category = category,
            expectedRank = rank.trim(),
            isLoggedIn = true,
            isGuest = true,
            verifiedTimestamp = timestamp
        )
    }

    fun logout() {
        prefs.edit().clear().apply()
        _sessionState.value = UserSession()
    }

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_IS_GUEST = "is_guest"
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"
        private const val KEY_PHONE = "user_phone"
        private const val KEY_EXAM = "user_exam"
        private const val KEY_CATEGORY = "user_category"
        private const val KEY_RANK = "user_rank"
        private const val KEY_TIMESTAMP = "login_timestamp"

        @Volatile
        private var instance: AuthRepository? = null

        fun getInstance(context: Context): AuthRepository {
            return instance ?: synchronized(this) {
                instance ?: AuthRepository(context.applicationContext).also { instance = it }
            }
        }
    }
}
