package com.example.data.models

import com.example.R

enum class ExamType(val displayName: String, val description: String) {
    KCET("KCET", "Karnataka Examination Authority (State Quota)"),
    COMEDK("COMEDK UGET", "Consortium of Medical, Engg & Dental Colleges (All India)")
}

enum class ReservationCategory(val code: String, val displayName: String, val multiplier: Double) {
    GM("GM", "General Merit", 1.0),
    CAT_2A("2A", "Category 2A (OBC)", 1.22),
    CAT_2B("2B", "Category 2B (Muslim)", 1.18),
    CAT_3A("3A", "Category 3A (Vokkaliga)", 1.12),
    CAT_3B("3B", "Category 3B (Lingayat)", 1.08),
    SC("SC", "Scheduled Caste", 1.85),
    ST("ST", "Scheduled Tribe", 1.95),
    RURAL("Rural", "Rural Quota (1-10th Std)", 1.15),
    KANNADA_MEDIUM("KM", "Kannada Medium (1-10th)", 1.12),
    KKR_371J("KKR", "Kalyana Karnataka (371J)", 1.35)
}

enum class Branch(val code: String, val fullName: String, val category: String) {
    CSE("CSE", "Computer Science & Engineering", "Circuit / Tech"),
    AIML("AI & ML", "Artificial Intelligence & Machine Learning", "Circuit / Tech"),
    ISE("ISE", "Information Science & Engineering", "Circuit / Tech"),
    AIDS("AI & DS", "Artificial Intelligence & Data Science", "Circuit / Tech"),
    CYBER("CY", "Computer Science (Cyber Security)", "Circuit / Tech"),
    ECE("ECE", "Electronics & Communication Engineering", "Circuit"),
    EEE("EEE", "Electrical & Electronics Engineering", "Core / Circuit"),
    MECH("MECH", "Mechanical Engineering", "Core"),
    CIVIL("CIVIL", "Civil Engineering", "Core"),
    BIOTECH("BT", "Biotechnology Engineering", "Specialized"),
    AERO("AERO", "Aerospace / Aeronautical Engineering", "Specialized")
}

enum class Region(val displayName: String, val iconName: String) {
    ALL("All Regions", "map"),
    BENGALURU_URBAN("Bengaluru Urban", "location_city"),
    MYSURU("Mysuru Region", "account_balance"),
    MANGALURU("Mangaluru / Coastal", "sailing"),
    NORTH_KARNATAKA("North Karnataka", "terrain"),
    REST_OF_KARNATAKA("Rest of Karnataka", "explore")
}

enum class CollegeTier(val tierNumber: Int, val title: String, val rankRangeText: String, val badgeColor: Long) {
    TIER_1(1, "Tier 1 (Elite)", "Rank 1 - 5,000", 0xFFFF1744), // Crimson Red
    TIER_2(2, "Tier 2 (Top Reputed)", "Rank 5,000 - 20,000", 0xFFFF6D00), // Electric Orange
    TIER_3(3, "Tier 3 (Established)", "Rank 20,000 - 60,000", 0xFFFFAB00), // Saffron Yellow
    TIER_4(4, "Tier 4 (Moderate)", "Rank 60,000 - 150,000", 0xFF00C853), // Vivid Green
    TIER_5(5, "Tier 5 (Accessible)", "Rank 150,000+", 0xFF78716C) // Warm Stone
}

enum class ChanceCategory(val label: String, val subtext: String, val colorHex: Long) {
    HIGH_CHANCE("Safe (High Chance)", "Closing rank is 20%+ higher than your rank", 0xFF00C853), // Vivid Emerald Green
    MODERATE_CHANCE("Realistic (Moderate)", "Closing rank is within ±15% of your rank", 0xFFFF9100), // Radiant Amber Yellow
    LOW_CHANCE("Dream (Low Chance)", "Closing rank is up to 20% lower (Aspirational)", 0xFFFF1744) // Eye-Beam Crimson Red
}

data class CollegeInfo(
    val code: String,
    val name: String,
    val shortName: String,
    val tier: CollegeTier,
    val region: Region,
    val district: String,
    val establishedYear: Int,
    val nirfRank: String,
    val naacGrade: String,
    val campusType: String,
    val avgPackageLpa: Double,
    val highestPackageLpa: Double,
    val kcetFeeApprox: Int,
    val comedkFeeApprox: Int,
    val website: String,
    val address: String,
    val highlights: List<String>,
    val lowestPackageLpa: Double = 4.5,
    val campusAreaAcres: String = "Spacious Campus",
    val buildingStructure: String = "Multi-story academic engineering blocks, specialized research laboratories, central seminar halls and landscaped student grounds."
)

data class CollegeCutoff(
    val id: String,
    val collegeCode: String,
    val collegeName: String,
    val collegeShortName: String,
    val tier: CollegeTier,
    val region: Region,
    val district: String,
    val branch: Branch,
    val kcetCutoffGM: Int,
    val comedkCutoffGM: Int,
    val avgPackageLpa: Double,
    val highestPackageLpa: Double,
    val nirfRank: String,
    val kcetFeePerYear: Int,
    val comedkFeePerYear: Int,
    val lowestPackageLpa: Double = 4.5,
    val buildingStructure: String = "Modern academic blocks with tech research wings and campus facilities."
)

data class PredictionItem(
    val cutoff: CollegeCutoff,
    val calculatedCutoff: Int,
    val studentRank: Int,
    val chance: ChanceCategory,
    val probabilityScore: Int,
    val examType: ExamType,
    val category: ReservationCategory
)

data class StrategyRuleAlert(
    val isSevere: Boolean, // True = Red warning, False = Info/Yellow
    val title: String,
    val message: String,
    val misplacedHigherOptionIndex: Int = -1,
    val misplacedLowerOptionIndex: Int = -1
)

data class DictionaryTerm(
    val term: String,
    val acronym: String = "",
    val examType: ExamType,
    val category: String, // e.g. "Quota & Reservation", "Fee & Payment", "Rules & Process"
    val shortDefinition: String,
    val detailedExplanation: String,
    val studentTip: String = ""
)

data class CounselingChoiceRule(
    val choiceNumber: Int,
    val name: String,
    val actionSummary: String,
    val isRetainingSeat: Boolean,
    val participatesNextRound: Boolean,
    val feePaymentRequired: Boolean,
    val description: String,
    val dosAndDonts: String,
    val worstCaseScenario: String
)

data class UserSession(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val targetExam: ExamType = ExamType.KCET,
    val category: ReservationCategory = ReservationCategory.GM,
    val expectedRank: String = "15000",
    val isLoggedIn: Boolean = false,
    val isGuest: Boolean = false,
    val verifiedTimestamp: Long = 0L
)
