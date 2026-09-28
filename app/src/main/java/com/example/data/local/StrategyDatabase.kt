package com.example.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "strategy_options")
data class StrategyOptionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val priorityOrder: Int,
    val collegeCode: String,
    val collegeName: String,
    val collegeShortName: String,
    val tierNumber: Int,
    val regionName: String,
    val branchCode: String,
    val branchFullName: String,
    val examType: String,
    val categoryCode: String,
    val cutoffRank: Int,
    val studentRank: Int,
    val chanceLabel: String,
    val avgPackageLpa: Double,
    val feePerYear: Int,
    val userNote: String = "",
    val addedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "mock_rank_entries")
data class MockRankEntryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val testName: String,
    val examType: String = "KCET",
    val rankAchieved: Int,
    val scoreObtained: Int = 0,
    val totalScore: Int = 180,
    val dateFormatted: String = "",
    val timestamp: Long = System.currentTimeMillis(),
    val weight: Float = 1.0f,
    val notes: String = "",
    val targetBranch: String = "Computer Science / AIML",
    val mathScore: Int = 0,
    val physicsScore: Int = 0,
    val chemistryScore: Int = 0
)

@Dao
interface StrategyDao {
    @Query("SELECT * FROM strategy_options ORDER BY priorityOrder ASC")
    fun getAllOptions(): Flow<List<StrategyOptionEntity>>

    @Query("SELECT * FROM strategy_options ORDER BY priorityOrder ASC")
    suspend fun getAllOptionsList(): List<StrategyOptionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOption(option: StrategyOptionEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(options: List<StrategyOptionEntity>)

    @Update
    suspend fun updateOption(option: StrategyOptionEntity)

    @Query("DELETE FROM strategy_options WHERE id = :id")
    suspend fun deleteOption(id: Long)

    @Query("DELETE FROM strategy_options")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM strategy_options")
    fun getCount(): Flow<Int>
}

@Dao
interface MockRankDao {
    @Query("SELECT * FROM mock_rank_entries ORDER BY timestamp DESC")
    fun getAllMockEntries(): Flow<List<MockRankEntryEntity>>

    @Query("SELECT * FROM mock_rank_entries ORDER BY timestamp DESC")
    suspend fun getAllMockEntriesList(): List<MockRankEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMockEntry(entry: MockRankEntryEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entries: List<MockRankEntryEntity>)

    @Update
    suspend fun updateMockEntry(entry: MockRankEntryEntity)

    @Query("DELETE FROM mock_rank_entries WHERE id = :id")
    suspend fun deleteMockEntry(id: Long)

    @Query("DELETE FROM mock_rank_entries")
    suspend fun clearAllMockEntries()

    @Query("SELECT COUNT(*) FROM mock_rank_entries")
    fun getMockCount(): Flow<Int>
}

@Database(entities = [StrategyOptionEntity::class, MockRankEntryEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun strategyDao(): StrategyDao
    abstract fun mockRankDao(): MockRankDao
}
