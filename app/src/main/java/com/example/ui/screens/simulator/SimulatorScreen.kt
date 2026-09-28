package com.example.ui.screens.simulator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Rule
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.local.StrategyOptionEntity
import com.example.data.models.ExamType
import com.example.data.repository.CollegeDataRepository
import com.example.ui.theme.DreamRed
import com.example.ui.theme.DreamRedContainer
import com.example.ui.theme.RealisticAmber
import com.example.ui.theme.RealisticAmberContainer
import com.example.ui.theme.SafeGreen
import com.example.ui.theme.SafeGreenContainer
import com.example.viewmodel.AppTab
import com.example.viewmodel.PredictorViewModel

@Composable
fun SimulatorScreen(
    viewModel: PredictorViewModel,
    modifier: Modifier = Modifier
) {
    val simulatorState by viewModel.simulatorState.collectAsStateWithLifecycle()
    val strategyList by viewModel.strategyList.collectAsStateWithLifecycle()
    val selectedExam by viewModel.selectedExam.collectAsStateWithLifecycle()

    val isComedk = selectedExam == ExamType.COMEDK

    // Mock option matched to exam
    val effectiveAllottedOption = simulatorState.allottedOption ?: strategyList.firstOrNull() ?: if (isComedk) {
        StrategyOptionEntity(
            id = 999,
            priorityOrder = 3,
            collegeCode = "E005",
            collegeName = "M.S. Ramaiah Institute of Technology",
            collegeShortName = "MSRIT",
            tierNumber = 1,
            regionName = "Bengaluru",
            branchCode = "ISE",
            branchFullName = "Information Science & Engineering",
            examType = "COMEDK",
            categoryCode = "GM",
            cutoffRank = 4200,
            studentRank = 3900,
            chanceLabel = "Realistic",
            avgPackageLpa = 13.5,
            feePerYear = 260000
        )
    } else {
        StrategyOptionEntity(
            id = 998,
            priorityOrder = 4,
            collegeCode = "E003",
            collegeName = "B.M.S. College of Engineering",
            collegeShortName = "BMSCE",
            tierNumber = 1,
            regionName = "Bengaluru",
            branchCode = "ECE",
            branchFullName = "Electronics & Communication Engg",
            examType = "KCET",
            categoryCode = "GM",
            cutoffRank = 3100,
            studentRank = 3450,
            chanceLabel = "Realistic",
            avgPackageLpa = 12.0,
            feePerYear = 107000
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("simulator_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header with Exam Switcher
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (isComedk) "COMEDK Choice 1-4 Simulator" else "KEA KCET Choice 1-4 Simulator",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (isComedk) "Simulate Accept & Freeze, Upgrade & Surrender Rules" else "Interactive Karnataka Counselling Allotment Rules",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primaryContainer
                        ) {
                            Icon(
                                imageVector = Icons.Default.Rule,
                                contentDescription = "Rules",
                                modifier = Modifier
                                    .padding(8.dp)
                                    .size(20.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Exam Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (!isComedk) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (!isComedk) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setExam(ExamType.KCET) }
                                .testTag("sim_toggle_kcet")
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "🌟 KCET Simulator",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (!isComedk) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }

                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (isComedk) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isComedk) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { viewModel.setExam(ExamType.COMEDK) }
                                .testTag("sim_toggle_comedk")
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 8.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "⚡ COMEDK Simulator",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isComedk) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }

        // Active Allotment Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("mock_allotment_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.primary
                        ) {
                            Text(
                                text = if (isComedk) "COMEDK ROUND 1 MOCK ALLOTMENT" else "KEA ROUND 1 MOCK ALLOTMENT",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Text(
                            text = "Option #${effectiveAllottedOption.priorityOrder}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "${effectiveAllottedOption.collegeShortName} (${effectiveAllottedOption.collegeCode})",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = effectiveAllottedOption.collegeName,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Branch: ${effectiveAllottedOption.branchFullName} (${effectiveAllottedOption.branchCode})",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "Tuition Fee: ₹${effectiveAllottedOption.feePerYear}",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }

        // Choice 1-4 Selector Cards
        item {
            Text(
                text = if (isComedk) "Select COMEDK Decision Option to Test:" else "Select KEA Decision Option to Test:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Choice 1
        item {
            val title = if (isComedk) "Choice 1: Accept & Freeze" else "Choice 1: Accept & Freeze (Satisfied)"
            val subtitle = if (isComedk) "Confirm seat, pay full tuition fee, and download Allotment Letter." else "You are completely happy with this college and seat."
            val action = if (isComedk)
                "• Pay COMEDK annual tuition fee (₹${effectiveAllottedOption.feePerYear})\n• Download Online Allotment Letter\n• Report to ${effectiveAllottedOption.collegeShortName} with original documents\n• You EXIT all further COMEDK rounds."
            else
                "• Pay KEA tuition fee (₹${effectiveAllottedOption.feePerYear})\n• Download KEA Admission Order\n• Report to college before deadline\n• You EXIT subsequent rounds completely."

            ChoiceOptionCard(
                choiceNumber = 1,
                title = title,
                subtitle = subtitle,
                actionText = action,
                color = SafeGreen,
                bgColor = SafeGreenContainer,
                isSelected = simulatorState.selectedChoice == 1,
                onSelect = { viewModel.selectSimulatorChoice(1) }
            )
        }

        // Choice 2
        item {
            val title = if (isComedk) "Choice 2: Accept & Upgrade (Hold Seat)" else "Choice 2: Hold & Upgrade in Round 2"
            val subtitle = if (isComedk) "Lock this seat with fee payment and participate in Round 2 for higher options." else "Satisfied with this seat, but want to try for higher options in Round 2."
            val action = if (isComedk)
                "• Pay full tuition fee (₹${effectiveAllottedOption.feePerYear}) to lock Option #${effectiveAllottedOption.priorityOrder}\n• You participate in COMEDK Round 2 for higher options\n• If upgraded: Old seat is forfeited and transferred automatically\n• If no upgrade: This seat is 100% SECURE."
            else
                "• Pay fee (₹${effectiveAllottedOption.feePerYear}) to hold Option #${effectiveAllottedOption.priorityOrder} as safety net\n• Only Options higher than #${effectiveAllottedOption.priorityOrder} evaluated in Round 2\n• If a higher option is allotted: Earlier seat released\n• If no higher option is allotted: This seat is GUARANTEED!"

            ChoiceOptionCard(
                choiceNumber = 2,
                title = title,
                subtitle = subtitle,
                actionText = action,
                color = MaterialTheme.colorScheme.primary,
                bgColor = MaterialTheme.colorScheme.primaryContainer,
                isSelected = simulatorState.selectedChoice == 2,
                onSelect = { viewModel.selectSimulatorChoice(2) }
            )
        }

        // Choice 3
        item {
            val title = if (isComedk) "Choice 3: Reject & Upgrade" else "Choice 3: Reject & Re-enter (Unsatisfied)"
            val subtitle = if (isComedk) "Surrender this seat without paying tuition fee and try for higher choices." else "Not satisfied with this seat and do NOT want to hold it."
            val action = if (isComedk)
                "• Forfeit Option #${effectiveAllottedOption.priorityOrder} permanently\n• No tuition fee paid right now\n• Re-enter COMEDK Round 2 for higher choices\n• CAUTION: If no higher seat allotted in Round 2, you have NO seat left!"
            else
                "• You forfeit Option #${effectiveAllottedOption.priorityOrder} permanently\n• No fee paid right now\n• Re-enter Round 2 with remaining options\n• RISK: If you don't get any seat in Round 2, you have NO seat left."

            ChoiceOptionCard(
                choiceNumber = 3,
                title = title,
                subtitle = subtitle,
                actionText = action,
                color = RealisticAmber,
                bgColor = RealisticAmberContainer,
                isSelected = simulatorState.selectedChoice == 3,
                onSelect = { viewModel.selectSimulatorChoice(3) }
            )
        }

        // Choice 4
        item {
            val title = if (isComedk) "Choice 4: Reject & Withdraw (Exit)" else "Choice 4: Exit KEA Counselling"
            val subtitle = if (isComedk) "Withdraw from COMEDK counselling entirely." else "Reject the seat and withdraw from Karnataka engineering admissions."
            val action = if (isComedk)
                "• Forfeit seat and exit COMEDK\n• You will not be considered in Round 2, Round 3, or any future COMEDK round."
            else
                "• Forfeit seat and exit KEA entirely\n• Candidate will not be considered in any future rounds (Round 2, Extended Round, Mop-up)."

            ChoiceOptionCard(
                choiceNumber = 4,
                title = title,
                subtitle = subtitle,
                actionText = action,
                color = DreamRed,
                bgColor = DreamRedContainer,
                isSelected = simulatorState.selectedChoice == 4,
                onSelect = { viewModel.selectSimulatorChoice(4) }
            )
        }

        // Round 2 Simulation Action
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("simulate_round2_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isComedk) "COMEDK Round 2 Engine" else "KEA Round 2 Engine",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        if (simulatorState.selectedChoice > 0) {
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "Choice ${simulatorState.selectedChoice} Active",
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = simulatorState.finalSeatStatus,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = { viewModel.simulateRound2() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("run_round2_simulation_button"),
                        shape = RoundedCornerShape(10.dp),
                        enabled = simulatorState.selectedChoice > 0
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Simulate",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (simulatorState.selectedChoice == 2) "Simulate Round 2 Upgrade Result" else "Execute Round 2 Simulation",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Simulation Outcome Result Banner
                    AnimatedVisibility(visible = simulatorState.isRound2Simulated) {
                        Column(
                            modifier = Modifier
                                .padding(top = 14.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (simulatorState.selectedChoice == 2) SafeGreenContainer else MaterialTheme.colorScheme.surface
                                )
                                .padding(14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Success",
                                    tint = if (simulatorState.selectedChoice == 2) SafeGreen else MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Round 2 Simulation Outcome",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (simulatorState.selectedChoice == 2) SafeGreen else MaterialTheme.colorScheme.primary
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = simulatorState.round2StatusMessage,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }

        // Rules Reference Guide Box
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.HelpOutline,
                            contentDescription = "Guide",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isComedk) "Golden Rules of COMEDK Counselling" else "Golden Rules of KEA Allotment",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (isComedk) {
                        Text(
                            text = "1. Single Fee Rule: COMEDK requires full tuition fee payment (~₹2.6 Lakhs) during seat acceptance.\n" +
                                    "2. Choice 2 Upgrade: Held seat is preserved. If upgraded to higher choice in Round 2, fee is adjusted automatically.\n" +
                                    "3. Surrender Policy: If you choose to exit after Round 2, surrender seat strictly within the official cancellation period to receive full refund.\n" +
                                    "4. Tatkal Editing: You can modify choice order before every round starts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    } else {
                        Text(
                            text = "1. Single Seat Principle: You can hold at most ONE seat across all rounds.\n" +
                                    "2. Choice 2 Safety: Fee paid for Choice 2 will be adjusted if you get upgraded to a new college in Round 2.\n" +
                                    "3. Choice 3 Penalty: You permanently forfeit the allotted seat. Other students can claim it in Round 2.\n" +
                                    "4. Option Deletion in Round 2: You can reorder or delete higher options, but you cannot add newly created options above your held seat in standard Round 2.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChoiceOptionCard(
    choiceNumber: Int,
    title: String,
    subtitle: String,
    actionText: String,
    color: Color,
    bgColor: Color,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() }
            .testTag("choice_option_card_$choiceNumber"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) bgColor else MaterialTheme.colorScheme.surfaceVariant
        ),
        border = if (isSelected) androidx.compose.foundation.BorderStroke(2.dp, color) else null
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = color
                ) {
                    Text(
                        text = "CHOICE $choiceNumber",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                if (isSelected) {
                    Surface(
                        shape = CircleShape,
                        color = color
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(4.dp)
                                .size(16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            Surface(
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = actionText,
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
