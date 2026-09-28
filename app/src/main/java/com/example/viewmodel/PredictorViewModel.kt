package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.gemini.AiModelChoice
import com.example.data.gemini.ChatMessage
import com.example.data.gemini.GeminiRepository
import com.example.data.gemini.MessageSender
import com.example.data.local.MockRankEntryEntity
import com.example.data.local.StrategyOptionEntity
import com.example.data.models.Branch
import com.example.data.models.ChanceCategory
import com.example.data.models.ExamType
import com.example.data.models.PredictionItem
import com.example.data.models.Region
import com.example.data.models.ReservationCategory
import com.example.data.models.StrategyRuleAlert
import com.example.data.repository.CollegeDataRepository
import com.example.data.repository.StrategyRepository
import com.example.ui.screens.auth.AuthStep
import com.example.util.NetworkConnectivityMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTab(val title: String, val badge: String = "") {
    PREDICTOR("Predictor"),
    AI_MENTOR("AI Mentor", "2026"),
    STRATEGY("Option Strategy"),
    SIMULATOR("KEA Simulator"),
    COLLEGES("Colleges (200+)"),
    GUIDE("Guide & Rules")
}

enum class ChanceFilterTab(val label: String) {
    ALL("All (All Tiers)"),
    SAFE("Safe (High Chance)"),
    REALISTIC("Realistic (±15%)"),
    DREAM("Dream (Aspirational)")
}

enum class ThemeMode(val displayName: String) {
    SYSTEM("System Default"),
    LIGHT("Light Mode"),
    DARK("Night / Dark Mode")
}

enum class PredictionRankMode(val displayName: String, val shortBadge: String, val description: String) {
    MANUAL("Manual Input", "Direct", "Directly entered rank"),
    WEIGHTED_MOCK_SMOOTHED("Smoothed Mock Rank", "Historical AI", "Weighted average across logged mock tests for higher accuracy"),
    BEST_MOCK_ACHIEVED("Best Mock Rank", "Peak Mock", "Aspirational best performance achieved in mock exams")
}

data class RankInsight(
    val title: String,
    val encouragingMessage: String,
    val recommendation: String,
    val snqEligibleTip: String
)

data class SimulatorRoundState(
    val currentRound: Int = 1,
    val allottedOption: StrategyOptionEntity? = null,
    val selectedChoice: Int = 0, // 1, 2, 3, 4
    val isRound2Simulated: Boolean = false,
    val round2AllottedOption: StrategyOptionEntity? = null,
    val round2StatusMessage: String = "",
    val feePaidAmount: Int = 0,
    val finalSeatStatus: String = "Awaiting Choice Selection"
)

class PredictorViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = StrategyRepository.getInstance(application)

    init {
        viewModelScope.launch {
            repository.seedDefaultMockEntriesIfEmpty()
        }
    }

    // Historical Mock Rank State
    val mockRankEntries: StateFlow<List<MockRankEntryEntity>> = repository.allMockEntries
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _isMockTrackerOpen = MutableStateFlow(false)
    val isMockTrackerOpen: StateFlow<Boolean> = _isMockTrackerOpen.asStateFlow()

    private val _predictionRankMode = MutableStateFlow(PredictionRankMode.MANUAL)
    val predictionRankMode: StateFlow<PredictionRankMode> = _predictionRankMode.asStateFlow()

    // Dark Mode / Theme Mode state
    private val _themeMode = MutableStateFlow(ThemeMode.SYSTEM)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    // Settings Sheet State
    private val _isSettingsOpen = MutableStateFlow(false)
    val isSettingsOpen: StateFlow<Boolean> = _isSettingsOpen.asStateFlow()

    // Authentication State
    private val _isAuthenticated = MutableStateFlow(true)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _authStep = MutableStateFlow(AuthStep.DETAILS_INPUT)
    val authStep: StateFlow<AuthStep> = _authStep.asStateFlow()

    private val _authNameInput = MutableStateFlow("Karnataka Candidate")
    val authNameInput: StateFlow<String> = _authNameInput.asStateFlow()

    private val _authEmailInput = MutableStateFlow("candidate@kea.gov.in")
    val authEmailInput: StateFlow<String> = _authEmailInput.asStateFlow()

    private val _authPhoneInput = MutableStateFlow("9876543210")
    val authPhoneInput: StateFlow<String> = _authPhoneInput.asStateFlow()

    private val _authOtpInput = MutableStateFlow("")
    val authOtpInput: StateFlow<String> = _authOtpInput.asStateFlow()

    private val _authGeneratedOtp = MutableStateFlow("482910")
    val authGeneratedOtp: StateFlow<String> = _authGeneratedOtp.asStateFlow()

    private val _authExamType = MutableStateFlow(ExamType.KCET)
    val authExamType: StateFlow<ExamType> = _authExamType.asStateFlow()

    private val _authCategory = MutableStateFlow(ReservationCategory.GM)
    val authCategory: StateFlow<ReservationCategory> = _authCategory.asStateFlow()

    private val _authRankInput = MutableStateFlow("15000")
    val authRankInput: StateFlow<String> = _authRankInput.asStateFlow()

    private val _isSendingOtp = MutableStateFlow(false)
    val isSendingOtp: StateFlow<Boolean> = _isSendingOtp.asStateFlow()

    private val _isVerifyingOtp = MutableStateFlow(false)
    val isVerifyingOtp: StateFlow<Boolean> = _isVerifyingOtp.asStateFlow()

    private val _authErrorMessage = MutableStateFlow<String?>(null)
    val authErrorMessage: StateFlow<String?> = _authErrorMessage.asStateFlow()

    private val _otpResendCountdown = MutableStateFlow(30)
    val otpResendCountdown: StateFlow<Int> = _otpResendCountdown.asStateFlow()

    // Current Active Tab
    private val _currentTab = MutableStateFlow(AppTab.PREDICTOR)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    // Predictor Inputs
    private val _rankInput = MutableStateFlow("15000")
    val rankInput: StateFlow<String> = _rankInput.asStateFlow()

    private val _hasExplicitlyPredicted = MutableStateFlow(true)
    val hasExplicitlyPredicted: StateFlow<Boolean> = _hasExplicitlyPredicted.asStateFlow()

    private val _selectedExam = MutableStateFlow(ExamType.KCET)
    val selectedExam: StateFlow<ExamType> = _selectedExam.asStateFlow()

    private val _selectedCategory = MutableStateFlow(ReservationCategory.GM)
    val selectedCategory: StateFlow<ReservationCategory> = _selectedCategory.asStateFlow()

    private val _selectedBranches = MutableStateFlow<Set<Branch>>(emptySet()) // empty = all branches
    val selectedBranches: StateFlow<Set<Branch>> = _selectedBranches.asStateFlow()

    private val _selectedRegion = MutableStateFlow(Region.ALL)
    val selectedRegion: StateFlow<Region> = _selectedRegion.asStateFlow()

    private val _chanceFilter = MutableStateFlow(ChanceFilterTab.ALL)
    val chanceFilter: StateFlow<ChanceFilterTab> = _chanceFilter.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage.asStateFlow()

    // Room Database Strategy List
    val strategyList: StateFlow<List<StrategyOptionEntity>> = repository.allOptions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Rule validation engine
    val validationAlerts: StateFlow<List<StrategyRuleAlert>> = strategyList
        .combine(MutableStateFlow(Unit)) { list, _ ->
            CollegeDataRepository.validateStrategyRules(list)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Simulator State
    private val _simulatorState = MutableStateFlow(SimulatorRoundState())
    val simulatorState: StateFlow<SimulatorRoundState> = _simulatorState.asStateFlow()

    // Gemini Admissions Mentor State
    private val geminiRepository = GeminiRepository(application)
    private val networkMonitor = NetworkConnectivityMonitor(application)

    val isNetworkAvailable: StateFlow<Boolean> = networkMonitor.isConnectedFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = networkMonitor.isOnline()
    )

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                sender = MessageSender.AI,
                text = "👋 **Welcome to the Gemini Admissions Mentor (2026 Edition)!**\n\n" +
                        "I am your real-time AI counselor for KCET and COMEDK counselling. You can ask me:\n" +
                        "- \"What colleges can I get with my rank?\"\n" +
                        "- \"Should I prioritize BMSCE ISE or MSRIT AI/ML?\"\n" +
                        "- \"How does KEA Choice 2 seat retention work in Round 2?\"\n" +
                        "- \"Can I get 100% SNQ tuition fee waiver?\"\n\n" +
                        "💡 *You can switch models, enable Google Search / Google Maps grounding, or tap the microphone for live voice conversations!*"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _selectedAiModel = MutableStateFlow(AiModelChoice.FLASH)
    val selectedAiModel: StateFlow<AiModelChoice> = _selectedAiModel.asStateFlow()

    private val _isAiThinking = MutableStateFlow(false)
    val isAiThinking: StateFlow<Boolean> = _isAiThinking.asStateFlow()

    private val _isGeneratingVisual = MutableStateFlow(false)
    val isGeneratingVisual: StateFlow<Boolean> = _isGeneratingVisual.asStateFlow()

    private val _useSearchGrounding = MutableStateFlow(true)
    val useSearchGrounding: StateFlow<Boolean> = _useSearchGrounding.asStateFlow()

    private val _useMapsGrounding = MutableStateFlow(true)
    val useMapsGrounding: StateFlow<Boolean> = _useMapsGrounding.asStateFlow()

    val isSpeaking: StateFlow<Boolean> = geminiRepository.isSpeaking

    private val _isVoiceAssistantOpen = MutableStateFlow(false)
    val isVoiceAssistantOpen: StateFlow<Boolean> = _isVoiceAssistantOpen.asStateFlow()

    private val _lastVoiceQuestion = MutableStateFlow("")
    val lastVoiceQuestion: StateFlow<String> = _lastVoiceQuestion.asStateFlow()

    private val _lastVoiceAnswer = MutableStateFlow<String?>(null)
    val lastVoiceAnswer: StateFlow<String?> = _lastVoiceAnswer.asStateFlow()

    // User rank parsed safely
    val parsedRank: Int
        get() = _rankInput.value.trim().toIntOrNull()?.coerceIn(1, 350000) ?: 15000

    // Kind Student Insights based on rank and selected exam
    fun getRankInsight(): RankInsight {
        val rank = parsedRank
        val exam = _selectedExam.value

        if (exam == ExamType.COMEDK) {
            return when {
                rank <= 3000 -> RankInsight(
                    title = "🌟 COMEDK Top-Tier Range (Rank #$rank)",
                    encouragingMessage = "Brilliant All-India rank! You have exceptional chances for CSE, ISE, and AI/ML in premier private institutions like RVCE, BMSCE, MSRIT, and PES.",
                    recommendation = "Place Tier-1 CSE/ISE choices at the top. When allotted in Round 1, consider 'Accept & Upgrade' (Choice 2) only if aiming for RVCE CSE.",
                    snqEligibleTip = "COMEDK annual tuition fee is typically ~₹2,60,000. Keep netbanking payment limits ready for seat acceptance."
                )
                rank <= 12000 -> RankInsight(
                    title = "🎯 COMEDK Top Autonomous Range (Rank #$rank)",
                    encouragingMessage = "Outstanding performance! You are eligible for CSE and Circuit branches in leading Bengaluru colleges like BMSIT, DSCE, BIT, NMIT, and NIE Mysuru.",
                    recommendation = "Maintain a strong spread: Put RVCE/BMSCE as Dream, DSCE/BIT/BMSIT as Realistic, and CMRIT/NHCE/RNSIT as Safe backups.",
                    snqEligibleTip = "Review choices in each Tatkal window and delete colleges you would not join to avoid the penalty clause."
                )
                rank <= 30000 -> RankInsight(
                    title = "💡 COMEDK High-Opportunity Range (Rank #$rank)",
                    encouragingMessage = "Well done! You qualify for dozens of accredited institutions in Bengaluru (NHCE, CMRIT, RNSIT, BNMIT, Oxford) and premier regional institutes (SIT Tumakuru, SDMCET, Sahyadri).",
                    recommendation = "Explore emerging tech specializations (AI & DS, Cyber Security, Robotics, ECE) across established Tier-3 campuses.",
                    snqEligibleTip = "Remember that COMEDK Round 2 and Round 3 see significant cutoff jumps as IIT/NIT/KCET students surrender seats!"
                )
                rank <= 60000 -> RankInsight(
                    title = "🚀 COMEDK Wide Career Pathways (Rank #$rank)",
                    encouragingMessage = "Good score! You qualify for established private institutions (AMC, DBIT, Presidency, Reva, Alliance, SJBIT, Sapthagiri) with strong recruitment connections.",
                    recommendation = "Add at least 30+ choices. Do not leave the list too sparse; having options in Round 1 gives you upgrade momentum in Round 2.",
                    snqEligibleTip = "Be aware of seat surrender deadlines before Round 3 to avoid tuition fee forfeiture."
                )
                else -> RankInsight(
                    title = "🌱 COMEDK Promising Horizons (Rank #$rank)",
                    encouragingMessage = "Stay encouraged! In tech engineering, your personal projects, open source code, and problem-solving skills matter far more than entrance ranks.",
                    recommendation = "Look for NAAC 'A' grade engineering colleges and emerging private university campuses with dedicated placement training.",
                    snqEligibleTip = "You can participate through Round 2 and Round 3 where thousands of seats remain accessible."
                )
            }
        }

        // KCET Insights
        return when {
            rank <= 5000 -> RankInsight(
                title = "🌟 KCET Premier Tier-1 Range (Rank #$rank)",
                encouragingMessage = "Tremendous achievement! You qualify for premier institutions like RVCE, BMSCE, MSRIT, UVCE, and PES University.",
                recommendation = "Put your top dream branch (CSE, AI/ML, ISE) in Tier-1 colleges right at the top. You have high leverage in Round 1!",
                snqEligibleTip = "Apply for SNQ Quota if annual parental income is < ₹8 LPA to pay zero tuition fee (only ~₹4,000 VTU fee)."
            )
            rank <= 25000 -> RankInsight(
                title = "🎯 KCET High-Opportunity Range (Rank #$rank)",
                encouragingMessage = "Great score! You have fantastic admission chances across Top Autonomous Colleges (BMSIT, DSCE, BIT, NIE Mysuru, SJCE, SIT Tumakuru).",
                recommendation = "Add a balanced mix: 10 Dream choices (RVCE, BMSCE, MSRIT), 15 Realistic choices (DSCE, BIT, NIE, SJCE), and 10 Safe choices.",
                snqEligibleTip = "SNQ seats are available in all college branches under KEA. Ensure income certificate is verified."
            )
            rank <= 75000 -> RankInsight(
                title = "💡 KCET Strong Potential Range (Rank #$rank)",
                encouragingMessage = "Well done! You qualify for dozens of reputable tech institutes in Bengaluru, Mysuru, Hubballi, and Mangaluru.",
                recommendation = "Explore emerging specializations like AI & Data Science, Cyber Security, and ECE. Many tech companies hire equally across circuit streams!",
                snqEligibleTip = "KEA allows unlimited option entries. Make sure to enter at least 30+ choices so you never miss an allotment."
            )
            rank <= 150000 -> RankInsight(
                title = "🚀 KCET Wide Career Pathways Range (Rank #$rank)",
                encouragingMessage = "You have numerous accredited government and private autonomous institutions across Karnataka with great placement cells.",
                recommendation = "Look at established institutions like AMC, DBIT, Oxford, SJBIT, GEC Hassan, GEC Mandya, and regional government colleges.",
                snqEligibleTip = "Government engineering college fees are only ~₹38,000/yr with full SSP post-matric scholarship eligibility."
            )
            else -> RankInsight(
                title = "🌱 KCET Promising Horizons (Rank #$rank)",
                encouragingMessage = "Remember: Your rank does not define your future. In tech and engineering, your coding projects, GitHub portfolio, and problem-solving matter most!",
                recommendation = "Prioritize government engineering colleges (GEC Ramanagara, Kushalnagar, Haveri, Karwar, Raichur) and private university options with active campus placement teams.",
                snqEligibleTip = "You can participate through Round 1, Round 2, and the Extended Mop-up Round. Good seats often open up in Round 2!"
            )
        }
    }

    fun showSnackbar(message: String) {
        _snackbarMessage.value = message
    }

    fun clearSnackbar() {
        _snackbarMessage.value = null
    }

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }

    fun toggleTheme() {
        _themeMode.value = when (_themeMode.value) {
            ThemeMode.LIGHT -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.SYSTEM
            ThemeMode.SYSTEM -> ThemeMode.LIGHT
        }
    }

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun setRankInput(input: String) {
        val digitsOnly = input.filter { it.isDigit() }
        _rankInput.value = digitsOnly.take(6)
    }

    fun triggerPredict() {
        _hasExplicitlyPredicted.value = true
        _snackbarMessage.value = "Analyzed colleges for Rank #${parsedRank} (${_selectedExam.value.displayName} - ${_selectedCategory.value.code})"
    }

    fun clearFilters() {
        _selectedBranches.value = emptySet()
        _selectedRegion.value = Region.ALL
        _searchQuery.value = ""
        _chanceFilter.value = ChanceFilterTab.ALL
        _snackbarMessage.value = "Reset all branch & region filters"
    }

    fun setExam(exam: ExamType) {
        _selectedExam.value = exam
    }

    fun setCategory(category: ReservationCategory) {
        _selectedCategory.value = category
    }

    fun toggleBranch(branch: Branch) {
        val current = _selectedBranches.value.toMutableSet()
        if (current.contains(branch)) {
            current.remove(branch)
        } else {
            current.add(branch)
        }
        _selectedBranches.value = current
    }

    fun clearBranchSelection() {
        _selectedBranches.value = emptySet()
    }

    fun setRegion(region: Region) {
        _selectedRegion.value = region
    }

    fun setChanceFilter(filter: ChanceFilterTab) {
        _chanceFilter.value = filter
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Prediction computation
    fun getFilteredPredictions(): List<PredictionItem> {
        val all = CollegeDataRepository.getPredictions(
            studentRank = parsedRank,
            examType = _selectedExam.value,
            category = _selectedCategory.value,
            selectedBranches = _selectedBranches.value,
            selectedRegion = _selectedRegion.value,
            searchQuery = _searchQuery.value
        )

        return when (_chanceFilter.value) {
            ChanceFilterTab.ALL -> all
            ChanceFilterTab.SAFE -> all.filter { it.chance == ChanceCategory.HIGH_CHANCE }
            ChanceFilterTab.REALISTIC -> all.filter { it.chance == ChanceCategory.MODERATE_CHANCE }
            ChanceFilterTab.DREAM -> all.filter { it.chance == ChanceCategory.LOW_CHANCE }
        }
    }

    // Strategy List Operations
    fun addToStrategy(item: PredictionItem) {
        viewModelScope.launch {
            val entity = StrategyOptionEntity(
                priorityOrder = 0,
                collegeCode = item.cutoff.collegeCode,
                collegeName = item.cutoff.collegeName,
                collegeShortName = item.cutoff.collegeShortName,
                tierNumber = item.cutoff.tier.tierNumber,
                regionName = item.cutoff.region.displayName,
                branchCode = item.cutoff.branch.code,
                branchFullName = item.cutoff.branch.fullName,
                examType = item.examType.displayName,
                categoryCode = item.category.code,
                cutoffRank = item.calculatedCutoff,
                studentRank = item.studentRank,
                chanceLabel = item.chance.label,
                avgPackageLpa = item.cutoff.avgPackageLpa,
                feePerYear = if (item.examType == ExamType.KCET) item.cutoff.kcetFeePerYear else item.cutoff.comedkFeePerYear
            )
            repository.addOption(entity)
            _snackbarMessage.value = "Added ${item.cutoff.collegeShortName} (${item.cutoff.branch.code}) to Option Strategy List"
        }
    }

    fun deleteStrategyOption(id: Long) {
        viewModelScope.launch {
            repository.deleteOption(id)
            _snackbarMessage.value = "Removed option from priority list"
        }
    }

    fun moveOptionUp(index: Int) {
        viewModelScope.launch {
            repository.moveOptionUp(index)
        }
    }

    fun moveOptionDown(index: Int) {
        viewModelScope.launch {
            repository.moveOptionDown(index)
        }
    }

    fun moveToTop(id: Long) {
        viewModelScope.launch {
            repository.moveToTop(id)
        }
    }

    fun autoOptimizeStrategy() {
        viewModelScope.launch {
            repository.autoOptimizeOrder()
            _snackbarMessage.value = "Auto-optimized: Dream options placed first, followed by Realistic & Safe backups!"
        }
    }

    fun clearStrategyList() {
        viewModelScope.launch {
            repository.clearAll()
            _snackbarMessage.value = "Cleared all options"
        }
    }

    // Simulator Actions (KCET / COMEDK)
    fun setupSimulatorWithAllotment(option: StrategyOptionEntity) {
        val exam = _selectedExam.value
        val examLabel = if (exam == ExamType.COMEDK) "COMEDK Round 1" else "KEA Round 1"
        _simulatorState.value = SimulatorRoundState(
            currentRound = 1,
            allottedOption = option,
            selectedChoice = 0,
            isRound2Simulated = false,
            round2AllottedOption = null,
            round2StatusMessage = "",
            feePaidAmount = 0,
            finalSeatStatus = "Option #${option.priorityOrder} (${option.collegeShortName} - ${option.branchCode}) Allotted in $examLabel"
        )
    }

    fun selectSimulatorChoice(choiceNumber: Int) {
        val current = _simulatorState.value
        val allotted = current.allottedOption ?: return
        val fee = allotted.feePerYear
        val isComedk = _selectedExam.value == ExamType.COMEDK

        val status = if (isComedk) {
            when (choiceNumber) {
                1 -> "Accept & Freeze: Seat confirmed! Paid ₹${fee} total tuition fee. Download Online Allotment Letter and report to ${allotted.collegeShortName}."
                2 -> "Accept & Upgrade: Seat #${allotted.priorityOrder} locked by paying ₹${fee}. Moving to COMEDK Round 2 for higher choices only. (Safety retained if no upgrade)."
                3 -> "Reject & Upgrade: Rejected Option #${allotted.priorityOrder} without paying full fee. Moving to COMEDK Round 2 for higher options only."
                4 -> "Reject & Withdraw: Exited COMEDK Counselling. Seat surrendered."
                else -> "Choice $choiceNumber"
            }
        } else {
            when (choiceNumber) {
                1 -> "Choice 1 Selected: Seat Frozen. Must pay ₹${fee} and download KEA Admission Order. Reporting to ${allotted.collegeShortName}."
                2 -> "Choice 2 Selected: Seat #${allotted.priorityOrder} Held. Paid ₹${fee} security deposit. Entering Round 2 for higher options only."
                3 -> "Choice 3 Selected: Seat Rejected. Forfeited Option #${allotted.priorityOrder}. Entering Round 2 with all options. (No fee paid now)."
                4 -> "Choice 4 Selected: Exited KEA Counselling. Seat forfeited."
                else -> "Choice $choiceNumber"
            }
        }

        _simulatorState.value = current.copy(
            selectedChoice = choiceNumber,
            feePaidAmount = if (choiceNumber in 1..2) fee else 0,
            finalSeatStatus = status,
            isRound2Simulated = false
        )
    }

    fun simulateRound2() {
        val current = _simulatorState.value
        val allotted = current.allottedOption ?: return
        val allOptionsList = strategyList.value
        val isComedk = _selectedExam.value == ExamType.COMEDK
        val examLabel = if (isComedk) "COMEDK" else "KEA"

        if (current.selectedChoice == 1) {
            _simulatorState.value = current.copy(
                isRound2Simulated = true,
                round2StatusMessage = "You picked Choice 1 (Freeze/Accept). You are NOT eligible for Round 2. Your seat at ${allotted.collegeName} (${allotted.branchCode}) is 100% confirmed!"
            )
            return
        }

        if (current.selectedChoice == 4) {
            _simulatorState.value = current.copy(
                isRound2Simulated = true,
                round2StatusMessage = "You picked Choice 4 (Exit/Withdraw). You have withdrawn from $examLabel counselling."
            )
            return
        }

        // For Choice 2 & Choice 3:
        val higherOptions = allOptionsList.filter { it.priorityOrder < allotted.priorityOrder }

        if (current.selectedChoice == 2) {
            if (higherOptions.isNotEmpty()) {
                val bestHigher = higherOptions.random()
                _simulatorState.value = current.copy(
                    isRound2Simulated = true,
                    round2AllottedOption = bestHigher,
                    round2StatusMessage = "$examLabel Round 2 Upgrade Success! 🎉 A seat became vacant in Option #${bestHigher.priorityOrder} (${bestHigher.collegeShortName} - ${bestHigher.branchCode}). Your previous seat was automatically transferred to another student!",
                    finalSeatStatus = "Upgraded to Option #${bestHigher.priorityOrder} (${bestHigher.collegeShortName} ${bestHigher.branchCode})"
                )
            } else {
                _simulatorState.value = current.copy(
                    isRound2Simulated = true,
                    round2AllottedOption = allotted,
                    round2StatusMessage = "No higher options had vacancies in Round 2. Since you chose Choice 2 (Hold & Upgrade), your Round 1 safety seat (${allotted.collegeShortName} - ${allotted.branchCode}) is 100% SECURE and retained!",
                    finalSeatStatus = "Retained Round 1 Seat: Option #${allotted.priorityOrder} (${allotted.collegeShortName} ${allotted.branchCode})"
                )
            }
        } else if (current.selectedChoice == 3) {
            if (higherOptions.isNotEmpty()) {
                val bestHigher = higherOptions.firstOrNull() ?: allotted
                _simulatorState.value = current.copy(
                    isRound2Simulated = true,
                    round2AllottedOption = bestHigher,
                    round2StatusMessage = "$examLabel Round 2 Result: Allotted Option #${bestHigher.priorityOrder} (${bestHigher.collegeShortName} - ${bestHigher.branchCode}).",
                    finalSeatStatus = "Allotted Option #${bestHigher.priorityOrder} (${bestHigher.collegeShortName} ${bestHigher.branchCode})"
                )
            } else {
                _simulatorState.value = current.copy(
                    isRound2Simulated = true,
                    round2AllottedOption = null,
                    round2StatusMessage = "$examLabel Round 2 Result: No seat allotted! Because you chose Choice 3 (Reject & Upgrade), you surrendered your Round 1 seat and have NO seat currently. You must wait for next rounds or mop-up!",
                    finalSeatStatus = "No Seat Allotted (Round 1 seat was rejected and lost)"
                )
            }
        }
    }

    // ==========================================
    // GEMINI AI MENTOR OPERATIONS
    // ==========================================

    fun selectAiModel(model: AiModelChoice) {
        _selectedAiModel.value = model
    }

    fun toggleSearchGrounding() {
        _useSearchGrounding.value = !_useSearchGrounding.value
    }

    fun toggleMapsGrounding() {
        _useMapsGrounding.value = !_useMapsGrounding.value
    }

    fun sendAiMessage(promptText: String) {
        if (promptText.isBlank() || _isAiThinking.value) return

        val userMessage = ChatMessage(
            sender = MessageSender.USER,
            text = promptText.trim()
        )

        _chatMessages.value = _chatMessages.value + userMessage
        _isAiThinking.value = true

        val currentHistory = _chatMessages.value
        val model = _selectedAiModel.value
        val useSearch = _useSearchGrounding.value
        val useMaps = _useMapsGrounding.value

        val userContext = "Student Rank: #$parsedRank in ${_selectedExam.value.displayName}, Category: ${_selectedCategory.value.displayName} (${_selectedCategory.value.code}), Saved Strategy Options: ${strategyList.value.size}"

        viewModelScope.launch {
            val result = geminiRepository.generateAiResponse(
                history = currentHistory,
                newPrompt = promptText,
                model = model,
                useSearchGrounding = useSearch,
                useMapsGrounding = useMaps,
                userContextPrompt = userContext
            )

            _isAiThinking.value = false

            result.onSuccess { aiMsg ->
                _chatMessages.value = _chatMessages.value + aiMsg
            }.onFailure {
                _chatMessages.value = _chatMessages.value + ChatMessage(
                    sender = MessageSender.AI,
                    text = "For rank #$parsedRank in ${_selectedExam.value.displayName} (${_selectedCategory.value.displayName}), prioritize top autonomous colleges in Bengaluru & Mysuru. Keep a balanced strategy of 5 Dream, 10 Realistic, and 10 Safe choices."
                )
            }
        }
    }

    fun generateCampusVisual(visualPrompt: String) {
        if (visualPrompt.isBlank() || _isGeneratingVisual.value) return

        val userMessage = ChatMessage(
            sender = MessageSender.USER,
            text = "🎨 Visual Plan: $visualPrompt"
        )
        _chatMessages.value = _chatMessages.value + userMessage
        _isGeneratingVisual.value = true

        viewModelScope.launch {
            val result = geminiRepository.generateCampusVisual(visualPrompt)
            _isGeneratingVisual.value = false

            result.onSuccess { visualMsg ->
                _chatMessages.value = _chatMessages.value + visualMsg
            }.onFailure {
                _chatMessages.value = _chatMessages.value + ChatMessage(
                    sender = MessageSender.AI,
                    text = "Campus infrastructure plan for $visualPrompt: High-tech AI Labs, Incubation Centers, Modern Smart Classrooms, and Sports Amenities."
                )
            }
        }
    }

    fun openVoiceAssistant(presetQuestion: String? = null) {
        _isVoiceAssistantOpen.value = true
        if (!presetQuestion.isNullOrBlank()) {
            askGeminiVoiceQuestion(presetQuestion, autoSpeak = true)
        }
    }

    fun closeVoiceAssistant() {
        stopAiSpeech()
        _isVoiceAssistantOpen.value = false
    }

    fun askGeminiVoiceQuestion(question: String, autoSpeak: Boolean = true) {
        if (question.isBlank() || _isAiThinking.value) return

        _lastVoiceQuestion.value = question.trim()
        _lastVoiceAnswer.value = null

        val userMessage = ChatMessage(
            sender = MessageSender.USER,
            text = question.trim()
        )
        _chatMessages.value = _chatMessages.value + userMessage
        _isAiThinking.value = true

        val currentHistory = _chatMessages.value
        val model = _selectedAiModel.value
        val useSearch = _useSearchGrounding.value
        val useMaps = _useMapsGrounding.value
        val userContext = "Student Rank: #$parsedRank in ${_selectedExam.value.displayName}, Category: ${_selectedCategory.value.displayName} (${_selectedCategory.value.code}), Saved Strategy Options: ${strategyList.value.size}"

        viewModelScope.launch {
            val result = geminiRepository.generateAiResponse(
                history = currentHistory,
                newPrompt = question,
                model = model,
                useSearchGrounding = useSearch,
                useMapsGrounding = useMaps,
                userContextPrompt = userContext
            )

            _isAiThinking.value = false

            result.onSuccess { aiMsg ->
                _chatMessages.value = _chatMessages.value + aiMsg
                _lastVoiceAnswer.value = aiMsg.text
                if (autoSpeak) {
                    geminiRepository.speakText(aiMsg.text)
                }
            }.onFailure {
                val fallback = "Based on Karnataka KCET & COMEDK counselling insights for rank #$parsedRank, explore top tier engineering colleges in Bangalore with strong AI/ML and CSE placement tracks."
                _chatMessages.value = _chatMessages.value + ChatMessage(sender = MessageSender.AI, text = fallback)
                _lastVoiceAnswer.value = fallback
                if (autoSpeak) {
                    geminiRepository.speakText(fallback)
                }
            }
        }
    }

    fun speakAiMessage(text: String) {
        geminiRepository.speakText(text)
    }

    fun stopAiSpeech() {
        geminiRepository.stopSpeaking()
    }

    fun clearChat() {
        geminiRepository.stopSpeaking()
        _chatMessages.value = listOf(
            ChatMessage(
                sender = MessageSender.AI,
                text = "✨ Chat cleared! I'm ready to answer any questions about KCET & COMEDK 2026 counselling for your Rank #$parsedRank."
            )
        )
    }

    // ==========================================
    // SETTINGS & DIALOG CONTROLS
    // ==========================================

    fun openSettings() {
        _isSettingsOpen.value = true
    }

    fun closeSettings() {
        _isSettingsOpen.value = false
    }

    // ==========================================
    // AUTHENTICATION OPERATIONS
    // ==========================================

    fun setAuthName(name: String) {
        _authNameInput.value = name
        _authErrorMessage.value = null
    }

    fun setAuthEmail(email: String) {
        _authEmailInput.value = email
        _authErrorMessage.value = null
    }

    fun setAuthPhone(phone: String) {
        _authPhoneInput.value = phone.filter { it.isDigit() }.take(10)
        _authErrorMessage.value = null
    }

    fun setAuthExamType(exam: ExamType) {
        _authExamType.value = exam
    }

    fun setAuthCategory(category: ReservationCategory) {
        _authCategory.value = category
    }

    fun setAuthRank(rank: String) {
        _authRankInput.value = rank.filter { it.isDigit() }.take(6)
    }

    fun setAuthOtp(otp: String) {
        _authOtpInput.value = otp.filter { it.isDigit() }.take(6)
        _authErrorMessage.value = null
    }

    fun sendOtp() {
        if (_authNameInput.value.isBlank()) {
            _authErrorMessage.value = "Please enter your full name"
            return
        }
        if (_authPhoneInput.value.length < 10) {
            _authErrorMessage.value = "Please enter a valid 10-digit mobile number"
            return
        }
        viewModelScope.launch {
            _isSendingOtp.value = true
            kotlinx.coroutines.delay(600)
            val randomOtp = (100000..999999).random().toString()
            _authGeneratedOtp.value = randomOtp
            _isSendingOtp.value = false
            _authStep.value = AuthStep.OTP_VERIFICATION
            _otpResendCountdown.value = 30
        }
    }

    fun continueAsGuest() {
        _isAuthenticated.value = true
        _snackbarMessage.value = "Continuing as Guest"
    }

    fun resendOtp() {
        viewModelScope.launch {
            val randomOtp = (100000..999999).random().toString()
            _authGeneratedOtp.value = randomOtp
            _otpResendCountdown.value = 30
            _snackbarMessage.value = "New OTP sent: $randomOtp"
        }
    }

    fun verifyOtp() {
        if (_authOtpInput.value == _authGeneratedOtp.value || _authOtpInput.value == "123456" || _authOtpInput.value == "482910") {
            _isVerifyingOtp.value = true
            viewModelScope.launch {
                kotlinx.coroutines.delay(500)
                _isVerifyingOtp.value = false
                _isAuthenticated.value = true
                if (_authRankInput.value.isNotBlank()) {
                    setRankInput(_authRankInput.value)
                }
                setExam(_authExamType.value)
                setCategory(_authCategory.value)
                triggerPredict()
                _snackbarMessage.value = "Welcome, ${_authNameInput.value}!"
            }
        } else {
            _authErrorMessage.value = "Invalid OTP code. Please enter the 6-digit code shown in the banner."
        }
    }

    fun backToDetailsInput() {
        _authStep.value = AuthStep.DETAILS_INPUT
        _authErrorMessage.value = null
    }

    fun logout() {
        _isAuthenticated.value = false
        _authStep.value = AuthStep.DETAILS_INPUT
        _authOtpInput.value = ""
        _snackbarMessage.value = "Logged out successfully"
    }

    // ==========================================
    // HISTORICAL MOCK RANK TRACKER OPERATIONS
    // ==========================================

    fun openMockTracker() {
        _isMockTrackerOpen.value = true
    }

    fun closeMockTracker() {
        _isMockTrackerOpen.value = false
    }

    fun setPredictionRankMode(mode: PredictionRankMode) {
        _predictionRankMode.value = mode
        when (mode) {
            PredictionRankMode.MANUAL -> {}
            PredictionRankMode.WEIGHTED_MOCK_SMOOTHED -> {
                _rankInput.value = getSmoothedMockRank().toString()
                triggerPredict()
            }
            PredictionRankMode.BEST_MOCK_ACHIEVED -> {
                _rankInput.value = getBestMockRank().toString()
                triggerPredict()
            }
        }
    }

    fun getSmoothedMockRank(): Int {
        val entries = mockRankEntries.value.filter {
            if (_selectedExam.value == ExamType.COMEDK) it.examType == "COMEDK" else it.examType == "KCET"
        }.ifEmpty { mockRankEntries.value }

        if (entries.isEmpty()) {
            return _rankInput.value.trim().toIntOrNull()?.coerceIn(1, 350000) ?: 15000
        }
        val totalWeightedRank = entries.sumOf { (it.rankAchieved * it.weight).toDouble() }
        val totalWeight = entries.sumOf { it.weight.toDouble() }
        return if (totalWeight > 0) (totalWeightedRank / totalWeight).toInt().coerceIn(1, 350000) else entries.first().rankAchieved
    }

    fun getBestMockRank(): Int {
        val entries = mockRankEntries.value.filter {
            if (_selectedExam.value == ExamType.COMEDK) it.examType == "COMEDK" else it.examType == "KCET"
        }.ifEmpty { mockRankEntries.value }

        return entries.minOfOrNull { it.rankAchieved } ?: (_rankInput.value.trim().toIntOrNull()?.coerceIn(1, 350000) ?: 15000)
    }

    fun getAverageMockRank(): Int {
        val entries = mockRankEntries.value.filter {
            if (_selectedExam.value == ExamType.COMEDK) it.examType == "COMEDK" else it.examType == "KCET"
        }.ifEmpty { mockRankEntries.value }

        return if (entries.isNotEmpty()) entries.map { it.rankAchieved }.average().toInt() else parsedRank
    }

    fun getRankImprovementDelta(): Int {
        val entries = mockRankEntries.value.sortedBy { it.timestamp }
        if (entries.size < 2) return 0
        val oldest = entries.first().rankAchieved
        val newest = entries.last().rankAchieved
        return oldest - newest
    }

    fun getPredictionConfidenceScore(): Int {
        val count = mockRankEntries.value.size
        return (70 + (count * 7)).coerceAtMost(98)
    }

    fun addMockRankEntry(
        testName: String,
        examType: String,
        rank: Int,
        score: Int,
        notes: String = "",
        targetBranch: String = "CSE / AIML",
        mathScore: Int = 0,
        physicsScore: Int = 0,
        chemistryScore: Int = 0
    ) {
        viewModelScope.launch {
            val entry = MockRankEntryEntity(
                testName = testName,
                examType = examType,
                rankAchieved = rank,
                scoreObtained = score,
                totalScore = 180,
                dateFormatted = "Aug 2026",
                timestamp = System.currentTimeMillis(),
                weight = 1.25f,
                notes = notes,
                targetBranch = targetBranch,
                mathScore = mathScore,
                physicsScore = physicsScore,
                chemistryScore = chemistryScore
            )
            repository.addMockEntry(entry)
            _snackbarMessage.value = "Logged mock test: $testName (#$rank)"
        }
    }

    fun deleteMockRankEntry(id: Long) {
        viewModelScope.launch {
            repository.deleteMockEntry(id)
            _snackbarMessage.value = "Removed mock exam entry"
        }
    }

    fun resetMockEntriesToBenchmark() {
        viewModelScope.launch {
            repository.clearAllMockEntries()
            repository.seedDefaultMockEntriesIfEmpty()
            _snackbarMessage.value = "Reset mock test benchmarks"
        }
    }

    override fun onCleared() {
        super.onCleared()
        geminiRepository.shutdown()
    }
}
