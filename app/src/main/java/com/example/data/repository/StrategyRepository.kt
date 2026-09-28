package com.example.data.repository

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.MockRankDao
import com.example.data.local.MockRankEntryEntity
import com.example.data.local.StrategyDao
import com.example.data.local.StrategyOptionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class StrategyRepository private constructor(
    private val dao: StrategyDao,
    private val mockDao: MockRankDao
) {

    val allOptions: Flow<List<StrategyOptionEntity>> = dao.getAllOptions()
    val optionCount: Flow<Int> = dao.getCount()

    val allMockEntries: Flow<List<MockRankEntryEntity>> = mockDao.getAllMockEntries()
    val mockCount: Flow<Int> = mockDao.getMockCount()

    suspend fun addOption(option: StrategyOptionEntity) = withContext(Dispatchers.IO) {
        // Auto assign next priorityOrder
        val currentList = dao.getAllOptionsList()
        val nextOrder = if (currentList.isEmpty()) 1 else (currentList.maxOf { it.priorityOrder } + 1)
        dao.insertOption(option.copy(priorityOrder = nextOrder))
    }

    suspend fun deleteOption(id: Long) = withContext(Dispatchers.IO) {
        dao.deleteOption(id)
        // Renumber orders
        normalizeOrder()
    }

    suspend fun moveOptionUp(index: Int) = withContext(Dispatchers.IO) {
        val list = dao.getAllOptionsList().toMutableList()
        if (index > 0 && index < list.size) {
            val itemA = list[index]
            val itemB = list[index - 1]
            val orderA = itemA.priorityOrder
            val orderB = itemB.priorityOrder

            dao.updateOption(itemA.copy(priorityOrder = orderB))
            dao.updateOption(itemB.copy(priorityOrder = orderA))
        }
    }

    suspend fun moveOptionDown(index: Int) = withContext(Dispatchers.IO) {
        val list = dao.getAllOptionsList().toMutableList()
        if (index >= 0 && index < list.size - 1) {
            val itemA = list[index]
            val itemB = list[index + 1]
            val orderA = itemA.priorityOrder
            val orderB = itemB.priorityOrder

            dao.updateOption(itemA.copy(priorityOrder = orderB))
            dao.updateOption(itemB.copy(priorityOrder = orderA))
        }
    }

    suspend fun moveToTop(id: Long) = withContext(Dispatchers.IO) {
        val list = dao.getAllOptionsList().toMutableList()
        val itemIndex = list.indexOfFirst { it.id == id }
        if (itemIndex > 0) {
            val item = list.removeAt(itemIndex)
            list.add(0, item)
            // Reassign 1..N
            val updated = list.mapIndexed { idx, entity -> entity.copy(priorityOrder = idx + 1) }
            dao.insertAll(updated)
        }
    }

    suspend fun autoOptimizeOrder() = withContext(Dispatchers.IO) {
        val list = dao.getAllOptionsList()
        if (list.isEmpty()) return@withContext

        // Sort: Dream options first (lowest cutoff / highest ambition), then Realistic, then Safe
        val sorted = list.sortedWith(
            compareBy(
                {
                    when {
                        it.chanceLabel.contains("Dream", ignoreCase = true) -> 1
                        it.chanceLabel.contains("Realistic", ignoreCase = true) -> 2
                        else -> 3
                    }
                },
                { it.cutoffRank },
                { it.tierNumber }
            )
        ).mapIndexed { index, item ->
            item.copy(priorityOrder = index + 1)
        }

        dao.insertAll(sorted)
    }

    suspend fun clearAll() = withContext(Dispatchers.IO) {
        dao.clearAll()
    }

    private suspend fun normalizeOrder() {
        val list = dao.getAllOptionsList()
        val updated = list.mapIndexed { index, entity -> entity.copy(priorityOrder = index + 1) }
        dao.insertAll(updated)
    }

    // Mock Rank History Operations
    suspend fun addMockEntry(entry: MockRankEntryEntity) = withContext(Dispatchers.IO) {
        mockDao.insertMockEntry(entry)
    }

    suspend fun deleteMockEntry(id: Long) = withContext(Dispatchers.IO) {
        mockDao.deleteMockEntry(id)
    }

    suspend fun clearAllMockEntries() = withContext(Dispatchers.IO) {
        mockDao.clearAllMockEntries()
    }

    suspend fun seedDefaultMockEntriesIfEmpty() = withContext(Dispatchers.IO) {
        val current = mockDao.getAllMockEntriesList()
        if (current.isEmpty()) {
            val defaults = listOf(
                MockRankEntryEntity(
                    testName = "KEA Full Length Model Test 4",
                    examType = "KCET",
                    rankAchieved = 11450,
                    scoreObtained = 146,
                    totalScore = 180,
                    dateFormatted = "Aug 2026",
                    timestamp = System.currentTimeMillis() - 2 * 86400000L,
                    weight = 1.3f,
                    notes = "Strong performance in Mathematics (54/60) & Physics (48/60)",
                    targetBranch = "CSE / ISE / AIML",
                    mathScore = 54,
                    physicsScore = 48,
                    chemistryScore = 44
                ),
                MockRankEntryEntity(
                    testName = "Deeksha / Allen State Grand Mock 3",
                    examType = "KCET",
                    rankAchieved = 13200,
                    scoreObtained = 138,
                    totalScore = 180,
                    dateFormatted = "Jul 2026",
                    timestamp = System.currentTimeMillis() - 15 * 86400000L,
                    weight = 1.1f,
                    notes = "Good pace, missed a few negative-free questions in Chemistry",
                    targetBranch = "CSE / AIML",
                    mathScore = 50,
                    physicsScore = 46,
                    chemistryScore = 42
                ),
                MockRankEntryEntity(
                    testName = "Karnataka PU Board Prelim Cumulative",
                    examType = "KCET",
                    rankAchieved = 15800,
                    scoreObtained = 130,
                    totalScore = 180,
                    dateFormatted = "Jun 2026",
                    timestamp = System.currentTimeMillis() - 40 * 86400000L,
                    weight = 1.0f,
                    notes = "Initial baseline test before starting targeted circuit branch prep",
                    targetBranch = "CSE / ECE",
                    mathScore = 45,
                    physicsScore = 43,
                    chemistryScore = 42
                ),
                MockRankEntryEntity(
                    testName = "COMEDK UGET All-India Mock 1",
                    examType = "COMEDK",
                    rankAchieved = 9800,
                    scoreObtained = 141,
                    totalScore = 180,
                    dateFormatted = "Aug 2026",
                    timestamp = System.currentTimeMillis() - 5 * 86400000L,
                    weight = 1.2f,
                    notes = "Fast solving speed, good for top Bangalore colleges (BMSCE/MSRIT)",
                    targetBranch = "CSE / AI & DS",
                    mathScore = 52,
                    physicsScore = 46,
                    chemistryScore = 43
                )
            )
            mockDao.insertAll(defaults)
        }
    }

    companion object {
        @Volatile
        private var instance: StrategyRepository? = null

        fun getInstance(context: Context): StrategyRepository {
            return instance ?: synchronized(this) {
                instance ?: run {
                    val db = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "kcet_strategy.db"
                    ).fallbackToDestructiveMigration().build()
                    StrategyRepository(db.strategyDao(), db.mockRankDao()).also { instance = it }
                }
            }
        }
    }
}
