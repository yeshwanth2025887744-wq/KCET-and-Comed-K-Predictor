package com.example.data.repository

import com.example.data.models.Branch
import com.example.data.models.ChanceCategory
import com.example.data.models.CollegeCutoff
import com.example.data.models.CollegeInfo
import com.example.data.models.CollegeTier
import com.example.data.models.ExamType
import com.example.data.models.PredictionItem
import com.example.data.models.Region
import com.example.data.models.ReservationCategory
import com.example.data.models.StrategyRuleAlert

object CollegeDataRepository {

    val collegesList: List<CollegeInfo> = listOf(
        // Tier 1
        CollegeInfo(
            code = "E001",
            name = "RV College of Engineering",
            shortName = "RVCE",
            tier = CollegeTier.TIER_1,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1963,
            nirfRank = "#89 in India",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 19.5,
            highestPackageLpa = 62.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 275000,
            website = "https://rvce.edu.in",
            address = "Mysore Road, Bengaluru",
            highlights = listOf("Top Placement Record in Karnataka", "Premier Engineering Institute", "Highest Tier-1 Cutoffs"),
            lowestPackageLpa = 6.5,
            campusAreaAcres = "52 Acres Sprawling Campus",
            buildingStructure = "12 Multi-tier Academic Engineering Blocks, Advanced Glass Research Towers, Central Library & Innovation incubation wings"
        ),
        CollegeInfo(
            code = "E003",
            name = "B.M.S. College of Engineering",
            shortName = "BMSCE",
            tier = CollegeTier.TIER_1,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1946,
            nirfRank = "#101 in India",
            naacGrade = "A++",
            campusType = "Private Autonomous Aided",
            avgPackageLpa = 13.8,
            highestPackageLpa = 50.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 260000,
            website = "https://bmsce.ac.in",
            address = "Bull Temple Road, Basavanagudi, Bengaluru",
            highlights = listOf("Oldest Private Engg College in India", "Central Bengaluru Location", "Strong Alumni Network"),
            lowestPackageLpa = 5.2,
            campusAreaAcres = "15 Acres Heritage Campus",
            buildingStructure = "Colonial Red-brick Quadrangle Facade, Platinum Jubilee Multi-story Tech Tower & Modern Center for Creative Learning"
        ),
        CollegeInfo(
            code = "E005",
            name = "Ramaiah Institute of Technology",
            shortName = "MSRIT",
            tier = CollegeTier.TIER_1,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1962,
            nirfRank = "#78 in India",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 14.2,
            highestPackageLpa = 53.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 260000,
            website = "https://msrit.edu",
            address = "MSR Nagar, Mathikere, Bengaluru",
            highlights = listOf("Top Notch Infrastructure", "Excellent Industry Partnerships", "Vibrant Campus Life"),
            lowestPackageLpa = 5.5,
            campusAreaAcres = "25 Acres Smart City Campus",
            buildingStructure = "Modern Glass-Curtain Multi-tiered Engineering Complex, Apex High-Tech Towers & Apex Research Auditoriums"
        ),
        CollegeInfo(
            code = "E002",
            name = "University Visvesvaraya College of Engineering",
            shortName = "UVCE",
            tier = CollegeTier.TIER_1,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1917,
            nirfRank = "State Autonomous",
            naacGrade = "A",
            campusType = "Government Autonomous",
            avgPackageLpa = 11.5,
            highestPackageLpa = 48.0,
            kcetFeeApprox = 42000,
            comedkFeeApprox = 0,
            website = "https://uvce.ac.in",
            address = "KR Circle, Bengaluru",
            highlights = listOf("Century-Old Heritage Institution", "Lowest Tuition Fees", "Prime KR Circle Campus"),
            lowestPackageLpa = 4.2,
            campusAreaAcres = "18 Acres Heritage & JNANA Campus",
            buildingStructure = "Historic Sir MV Heritage Stone Architecture, Central KR Circle Clock Complex & KR Circle Labs"
        ),
        CollegeInfo(
            code = "E060",
            name = "PES University (Ring Road Campus)",
            shortName = "PESU RR",
            tier = CollegeTier.TIER_1,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1988,
            nirfRank = "#100-150 Band",
            naacGrade = "A+",
            campusType = "State Private University",
            avgPackageLpa = 16.0,
            highestPackageLpa = 65.0,
            kcetFeeApprox = 110000,
            comedkFeeApprox = 290000,
            website = "https://pes.edu",
            address = "100 Feet Ring Road, BSK 3rd Stage, Bengaluru",
            highlights = listOf("Industry Leading Tech Placements", "PESSAT & KCET Admissions", "Modern Silicon Valley Curriculum"),
            lowestPackageLpa = 6.0,
            campusAreaAcres = "30 Acres Silicon Corridor Campus",
            buildingStructure = "13-Floor Ultra-Modern Glass Academic Towers, State-of-the-Art Silicon Valley Auditorium & Sports Complex"
        ),

        // Tier 2
        CollegeInfo(
            code = "E008",
            name = "Dayananda Sagar College of Engineering",
            shortName = "DSCE",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1979,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 10.5,
            highestPackageLpa = 42.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 245000,
            website = "https://dayanandasagar.edu",
            address = "Kumaraswamy Layout, Bengaluru",
            highlights = listOf("Large 29-Acre Campus", "Very Strong CS & Circuit Placements", "Multi-disciplinary Hub"),
            lowestPackageLpa = 4.5,
            campusAreaAcres = "29 Acres Hilltop Campus",
            buildingStructure = "Terraced Multi-block Engineering Quadrangles, Modern Heritage Centre & CIL Innovation Incubator"
        ),
        CollegeInfo(
            code = "E006",
            name = "Bangalore Institute of Technology",
            shortName = "BIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1979,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 9.8,
            highestPackageLpa = 38.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 235000,
            website = "https://bit-bangalore.edu.in",
            address = "KR Road, VV Puram, Bengaluru",
            highlights = listOf("Central City Location", "Proven Academic Track Record", "High ROI for KCET Rankers"),
            lowestPackageLpa = 4.2,
            campusAreaAcres = "12 Acres Central Hub",
            buildingStructure = "Multi-story High-Rise Urban Academic Engineering Blocks & Dedicated Research Center"
        ),
        CollegeInfo(
            code = "E089",
            name = "BMS Institute of Technology & Management",
            shortName = "BMSIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2002,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 9.2,
            highestPackageLpa = 44.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 240000,
            website = "https://bmsit.ac.in",
            address = "Yelahanka, Bengaluru",
            highlights = listOf("Sister Campus of BMSCE", "Rapidly Ascending Tech Tier", "Modern Yelahanka Campus"),
            lowestPackageLpa = 4.5,
            campusAreaAcres = "22 Acres Green Campus",
            buildingStructure = "Modern Yelahanka Tech Towers, Smart Classrooms & Incubation Amphitheater"
        ),
        CollegeInfo(
            code = "E098",
            name = "Nitte Meenakshi Institute of Technology",
            shortName = "NMIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "#151-200 Band",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 8.8,
            highestPackageLpa = 40.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 240000,
            website = "https://nmit.ac.in",
            address = "Yelahanka, Bengaluru",
            highlights = listOf("Autonomous Innovation Focus", "Extensive Robotics & AI Labs", "Consistent 85%+ Placement Rate"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "23 Acres Eco Campus",
            buildingStructure = "Contemporary Engineering Wings, Advanced Robotics & Small Satellite Research Complex"
        ),
        CollegeInfo(
            code = "E012",
            name = "Sir M. Visvesvaraya Institute of Technology",
            shortName = "Sir MVIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1986,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 8.5,
            highestPackageLpa = 36.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://sirmvit.edu",
            address = "Hunasamaranahalli, International Airport Road",
            highlights = listOf("Sprawling 133-Acre Green Campus", "Close to Airport", "Strong Biotech & Circuit Branches"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "133 Acres Sprawling Green Campus",
            buildingStructure = "Massive 133-acre Greenfield campus, departmental architectural pavilions & dedicated biotechnology complex"
        ),
        CollegeInfo(
            code = "E066",
            name = "JSS Academy of Technical Education",
            shortName = "JSSATE",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1997,
            nirfRank = "VTU Affiliated",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 8.2,
            highestPackageLpa = 34.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://jssateb.ac.in",
            address = "Uttarahalli-Kengeri Road, Bengaluru",
            highlights = listOf("Part of Prestigious JSS Mahavidyapeetha", "Excellent Faculty", "Active Incubation Center"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "21 Acres Green Campus",
            buildingStructure = "JSS Mahavidyapeetha Architectural Blocks, STEP Technology Incubation Centre & Modern Auditoriums"
        ),
        CollegeInfo(
            code = "E061",
            name = "PES University (Electronic City Campus)",
            shortName = "PESU EC",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1988,
            nirfRank = "State Private",
            naacGrade = "A+",
            campusType = "State Private University",
            avgPackageLpa = 14.5,
            highestPackageLpa = 52.0,
            kcetFeeApprox = 110000,
            comedkFeeApprox = 280000,
            website = "https://pes.edu",
            address = "Hosur Road, Electronic City, Bengaluru",
            highlights = listOf("Direct Access to Electronic City Tech Corridor", "Identical Placements with Ring Road"),
            lowestPackageLpa = 5.5,
            campusAreaAcres = "50 Acres IT Corridor Campus",
            buildingStructure = "Futuristic Multi-Tower Engineering Hub nestled in the Electronic City Information Technology corridor"
        ),
        CollegeInfo(
            code = "E007",
            name = "The National Institute of Engineering",
            shortName = "NIE Mysuru",
            tier = CollegeTier.TIER_2,
            region = Region.MYSURU,
            district = "Mysuru",
            establishedYear = 1946,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "Private Autonomous Aided",
            avgPackageLpa = 9.5,
            highestPackageLpa = 43.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 240000,
            website = "https://nie.ac.in",
            address = "Manandavadi Road, Mysuru",
            highlights = listOf("South Karnataka's Premier Heritage College", "Famous Alumni (Narayan Murthy)", "Separate South Campus for CS"),
            lowestPackageLpa = 4.2,
            campusAreaAcres = "50 Acres Dual Heritage & South Campus",
            buildingStructure = "Historic Diamond Jubilee Mysore Heritage Blocks & Brand New Ultra-Modern South Technology Campus"
        ),
        CollegeInfo(
            code = "E004",
            name = "Sri Jayachamarajendra College of Engineering (JSS STU)",
            shortName = "SJCE Mysuru",
            tier = CollegeTier.TIER_2,
            region = Region.MYSURU,
            district = "Mysuru",
            establishedYear = 1963,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "State Autonomous / JSS STU",
            avgPackageLpa = 10.2,
            highestPackageLpa = 45.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 250000,
            website = "https://jssstuniv.in",
            address = "Manasagangothri, Mysuru",
            highlights = listOf("Sprawling 102-Acre Campus", "Top Choice for South Karnataka", "Massive Placement Drives"),
            lowestPackageLpa = 4.5,
            campusAreaAcres = "102 Acres Sprawling University Estate",
            buildingStructure = "102-Acre Serene Green University Quadrangle, Golden Jubilee Engineering Blocks & High-Voltage Lab Pavilions"
        ),
        CollegeInfo(
            code = "E010",
            name = "Siddaganga Institute of Technology",
            shortName = "SIT Tumakuru",
            tier = CollegeTier.TIER_2,
            region = Region.REST_OF_KARNATAKA,
            district = "Tumakuru",
            establishedYear = 1963,
            nirfRank = "#100 in India",
            naacGrade = "A++",
            campusType = "Private Autonomous",
            avgPackageLpa = 8.5,
            highestPackageLpa = 38.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://sit.ac.in",
            address = "BH Road, Tumakuru",
            highlights = listOf("NAAC A++ Rated", "Only 1 Hour from Bengaluru", "Excellent Hostel & Discipline"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "65 Acres Hillside Campus",
            buildingStructure = "Sprawling 65-acre peaceful hillside estate with stone-crafted academic blocks & extensive research libraries"
        ),
        CollegeInfo(
            code = "E090",
            name = "RNS Institute of Technology",
            shortName = "RNSIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 8.4,
            highestPackageLpa = 35.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 235000,
            website = "https://rnsit.ac.in",
            address = "Rajarajeshwarinagar, Bengaluru",
            highlights = listOf("Top Circuit & CS Stream Focus", "Renowned Cultural & Tech Fests"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "20 Acres RR Nagar Estate",
            buildingStructure = "Elevated Architectural Blocks with Modern Computing Labs, Amphitheater & Green Lawn Quad"
        ),
        CollegeInfo(
            code = "E091",
            name = "BNM Institute of Technology",
            shortName = "BNMIT",
            tier = CollegeTier.TIER_2,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 7.8,
            highestPackageLpa = 32.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://bnmit.org",
            address = "BSK 2nd Stage, Bengaluru",
            highlights = listOf("High Academic Discipline", "Centrally Located in Banashankari", "Strong Soft-Skills Training"),
            lowestPackageLpa = 3.8,
            campusAreaAcres = "10 Acres Banashankari Campus",
            buildingStructure = "Compact Modern Urban Multi-floor Blocks with High-Speed Computing Clusters & Auditorium"
        ),
        CollegeInfo(
            code = "E025",
            name = "NMAM Institute of Technology",
            shortName = "Nitte Karkala",
            tier = CollegeTier.TIER_2,
            region = Region.MANGALURU,
            district = "Udupi",
            establishedYear = 1986,
            nirfRank = "#150-200 Band",
            naacGrade = "A+",
            campusType = "Deemed University (Nitte)",
            avgPackageLpa = 8.0,
            highestPackageLpa = 40.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 235000,
            website = "https://nmamit.nitte.edu.in",
            address = "Nitte, Karkala, Coastal Karnataka",
            highlights = listOf("Premier Institute in Coastal Belt", "Modern Research Labs", "Beautiful Campus"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "125 Acres Coastal Valley Campus",
            buildingStructure = "Scenic 125-acre valley campus with world-class engineering workshops, research facilities & open-air auditoriums"
        ),
        CollegeInfo(
            code = "E123",
            name = "Sahyadri College of Engineering & Management",
            shortName = "Sahyadri",
            tier = CollegeTier.TIER_2,
            region = Region.MANGALURU,
            district = "Mangaluru",
            establishedYear = 2007,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 7.5,
            highestPackageLpa = 35.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://sahyadri.edu.in",
            address = "Adyar, Mangaluru",
            highlights = listOf("Best Startup Incubation in Coastal Zone", "Modern Riverfront Campus", "Strong Coding Culture"),
            lowestPackageLpa = 3.8,
            campusAreaAcres = "30 Acres Riverfront Campus",
            buildingStructure = "Modern Netravati Riverfront Campus with Dreamers Incubation Space, Metalcraft Works & Innovation Lab"
        ),

        // Tier 3
        CollegeInfo(
            code = "E094",
            name = "New Horizon College of Engineering",
            shortName = "NHCE",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 7.4,
            highestPackageLpa = 32.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 235000,
            website = "https://newhorizonindia.edu",
            address = "Outer Ring Road, Kadubisanahalli, Bengaluru",
            highlights = listOf("Located in the Heart of IT Corridor (ORR)", "Japanese Language & Industry Tie-ups", "Lively Modern Campus"),
            lowestPackageLpa = 3.6,
            campusAreaAcres = "12 Acres Outer Ring Road IT Hub",
            buildingStructure = "Ultra-Modern Glass & Steel Engineering Blocks strategically located beside Top Tech MNCs on Outer Ring Road"
        ),
        CollegeInfo(
            code = "E095",
            name = "CMR Institute of Technology",
            shortName = "CMRIT",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2000,
            nirfRank = "VTU Affiliated",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 7.0,
            highestPackageLpa = 30.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://cmrit.ac.in",
            address = "ITPL Main Road, Kundalahalli, Bengaluru",
            highlights = listOf("Heart of Whitefield Tech Hub", "Maker Space & Patent Incentives"),
            lowestPackageLpa = 3.6,
            campusAreaAcres = "10 Acres Whitefield ITPL Hub",
            buildingStructure = "Modern multi-floor tech learning center with MakerSpace, CoE in AI & Data Science"
        ),
        CollegeInfo(
            code = "E096",
            name = "Acharya Institute of Technology",
            shortName = "Acharya",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2000,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.5,
            highestPackageLpa = 26.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 225000,
            website = "https://acharya.ac.in",
            address = "Acharya Dr. S. Radhakrishnan Road, Soladevanahalli",
            highlights = listOf("Massive 120-Acre Multidisciplinary Campus", "Diverse International Student Community"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "120 Acres Massive Eco Campus",
            buildingStructure = "Massive 120-Acre Global Architecture with Central Stadium, Lakeside Promenade & Multi-department Wings"
        ),
        CollegeInfo(
            code = "E011",
            name = "Dr. Ambedkar Institute of Technology",
            shortName = "Dr. AIT",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1980,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Govt Aided Autonomous",
            avgPackageLpa = 6.8,
            highestPackageLpa = 28.0,
            kcetFeeApprox = 75000,
            comedkFeeApprox = 210000,
            website = "https://drait.edu.in",
            address = "Mallathahalli, Bengaluru",
            highlights = listOf("Aided Category Fee Advantage", "Near Bengaluru University Campus"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "26 Acres West Bengaluru Estate",
            buildingStructure = "Spacious Aided Engineering Blocks with Dedicated Research Centers & Green Tree Canopy"
        ),
        CollegeInfo(
            code = "E112",
            name = "Cambridge Institute of Technology",
            shortName = "CIT Tech",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2007,
            nirfRank = "VTU Autonomous",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.2,
            highestPackageLpa = 27.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 225000,
            website = "https://cambridge.edu.in",
            address = "KR Puram, Bengaluru",
            highlights = listOf("KR Puram Connectivity", "Dedicated Samsung & AWS Innovation Wings"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "16 Acres Tech Park Corridor",
            buildingStructure = "Modern KR Puram Academic Complex with AWS Cloud & Samsung IoT specialized tech laboratories"
        ),
        CollegeInfo(
            code = "E013",
            name = "MVJ College of Engineering",
            shortName = "MVJCE",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1982,
            nirfRank = "VTU Autonomous",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.3,
            highestPackageLpa = 26.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://mvjce.edu.in",
            address = "Near ITPB, Whitefield, Bengaluru",
            highlights = listOf("Pioneer in Aeronautical Engg", "Close to ITPB Whitefield"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "15 Acres Whitefield Estate",
            buildingStructure = "Aviation & Tech Blocks near ITPL Whitefield featuring Aircraft Hangar Lab and Robotics Research Facilities"
        ),
        CollegeInfo(
            code = "E014",
            name = "KLE Technological University (BVBCET)",
            shortName = "KLE Tech Hubballi",
            tier = CollegeTier.TIER_3,
            region = Region.NORTH_KARNATAKA,
            district = "Hubballi",
            establishedYear = 1947,
            nirfRank = "#150-200 Band",
            naacGrade = "A",
            campusType = "State Private University",
            avgPackageLpa = 7.8,
            highestPackageLpa = 35.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 230000,
            website = "https://kletech.ac.in",
            address = "Vidyanagar, Hubballi",
            highlights = listOf("North Karnataka's Leading Tech University", "Top Tier Innovation & Startups", "Sudha Murty Alma Mater"),
            lowestPackageLpa = 4.0,
            campusAreaAcres = "64 Acres Innovation University Estate",
            buildingStructure = "Sprawling 64-Acre Landmark Hubballi Campus with CTIE Startup Park, Sudha Murty Tech Centre & Modern Blocks"
        ),
        CollegeInfo(
            code = "E015",
            name = "SDM College of Engineering & Technology",
            shortName = "SDMCET",
            tier = CollegeTier.TIER_3,
            region = Region.NORTH_KARNATAKA,
            district = "Dharwad",
            establishedYear = 1979,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.5,
            highestPackageLpa = 28.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://sdmcet.ac.in",
            address = "Dhavalagiri, Dharwad",
            highlights = listOf("High Academic Reputation", "Serene Campus Environment in Dharwad"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "72 Acres Dhavalagiri Hilltop",
            buildingStructure = "Serene 72-acre Dhavalagiri hilltop stone-paved blocks, open courtyards & research wings"
        ),
        CollegeInfo(
            code = "E104",
            name = "Global Academy of Technology",
            shortName = "GAT",
            tier = CollegeTier.TIER_3,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.4,
            highestPackageLpa = 25.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://gat.ac.in",
            address = "Rajarajeshwarinagar, Bengaluru",
            highlights = listOf("Modern Tech Infrastructure", "South West Bengaluru Location"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "10 Acres RR Nagar Campus",
            buildingStructure = "Modern multi-story academic buildings with spacious auditoriums & sports ground"
        ),
        CollegeInfo(
            code = "E078",
            name = "Vidyavardhaka College of Engineering",
            shortName = "VVCE Mysuru",
            tier = CollegeTier.TIER_3,
            region = Region.MYSURU,
            district = "Mysuru",
            establishedYear = 1997,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.6,
            highestPackageLpa = 28.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://vvce.ac.in",
            address = "Gokulam 3rd Stage, Mysuru",
            highlights = listOf("Mysuru City Favorite", "Strong Placements across All Branches"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "23 Acres Gokulam Campus",
            buildingStructure = "Gokulam Heritage Architecture blocks with modern central computing centers and cultural amphitheaters"
        ),
        CollegeInfo(
            code = "E109",
            name = "St. Joseph Engineering College",
            shortName = "SJEC Mangaluru",
            tier = CollegeTier.TIER_3,
            region = Region.MANGALURU,
            district = "Mangaluru",
            establishedYear = 2002,
            nirfRank = "VTU Autonomous",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 6.2,
            highestPackageLpa = 24.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 220000,
            website = "https://sjec.ac.in",
            address = "Vamanjoor, Mangaluru",
            highlights = listOf("Prestigious Catholic Education Trust", "Modern Facilities"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "25 Acres Scenic Green Valley",
            buildingStructure = "Scenic Vamanjoor valley campus with gothic-modernist academic blocks, lush coconut groves & research towers"
        ),

        // Tier 4
        CollegeInfo(
            code = "E072",
            name = "AMC Engineering College",
            shortName = "AMC",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 1999,
            nirfRank = "VTU Affiliated",
            naacGrade = "B++",
            campusType = "Private",
            avgPackageLpa = 5.2,
            highestPackageLpa = 20.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 200000,
            website = "https://amcgroup.edu.in",
            address = "Bannerghatta Road, Bengaluru",
            highlights = listOf("Bannerghatta Road Corridor", "Decent IT Placement Network"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "52 Acres Bannerghatta Grounds",
            buildingStructure = "Spacious Bannerghatta Road Campus with separate engineering, MBA and research wings"
        ),
        CollegeInfo(
            code = "E105",
            name = "Don Bosco Institute of Technology",
            shortName = "DBIT",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "B++",
            campusType = "Private",
            avgPackageLpa = 5.0,
            highestPackageLpa = 18.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 200000,
            website = "https://dbit.co.in",
            address = "Kumbalagodu, Mysore Road, Bengaluru",
            highlights = listOf("Spacious Mysore Road Campus", "Active Training & Placement Cell"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "36 Acres Mysore Road Grounds",
            buildingStructure = "Expansive 36-acre campus on Mysore Road with multiple multi-floor wings and sports complexes"
        ),
        CollegeInfo(
            code = "E101",
            name = "East West Institute of Technology",
            shortName = "EWIT",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "B++",
            campusType = "Private",
            avgPackageLpa = 4.8,
            highestPackageLpa = 16.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 200000,
            website = "https://ewit.edu",
            address = "Off Magadi Road, Bengaluru",
            highlights = listOf("Accessible West Bengaluru Location"),
            lowestPackageLpa = 3.0,
            campusAreaAcres = "20 Acres Off Magadi Road",
            buildingStructure = "Suburban academic blocks with large computer labs and student library wings"
        ),
        CollegeInfo(
            code = "E128",
            name = "Rajarajeswari College of Engineering",
            shortName = "RRCE",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2006,
            nirfRank = "VTU Autonomous",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 5.2,
            highestPackageLpa = 21.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 200000,
            website = "https://rrce.org",
            address = "Mysore Road, Bengaluru",
            highlights = listOf("Autonomous Status", "Close to Kengeri Metro"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "15 Acres Mysore Road Metro Corridor",
            buildingStructure = "Multi-story contemporary engineering blocks adjacent to Mysore Highway and Kengeri Metro Station"
        ),
        CollegeInfo(
            code = "E070",
            name = "The Oxford College of Engineering",
            shortName = "Oxford",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2000,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private",
            avgPackageLpa = 5.4,
            highestPackageLpa = 22.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 210000,
            website = "https://theoxford.edu",
            address = "Bommanahalli, Hosur Road, Bengaluru",
            highlights = listOf("Adjacent to Silk Board & HSR Layout"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "11 Acres Central Silk Board Corridor",
            buildingStructure = "Multi-tier glass and concrete building complex in prime Bommanahalli IT corridor"
        ),
        CollegeInfo(
            code = "E099",
            name = "Sambhram Institute of Technology",
            shortName = "Sambhram",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "B+",
            campusType = "Private",
            avgPackageLpa = 4.5,
            highestPackageLpa = 15.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 195000,
            website = "https://sambhramit.com",
            address = "MS Palya, Jalahalli East, Bengaluru",
            highlights = listOf("North Bengaluru Location"),
            lowestPackageLpa = 3.0,
            campusAreaAcres = "10 Acres North Bengaluru",
            buildingStructure = "Quiet leafy North Bangalore campus with specialized workshop facilities"
        ),
        CollegeInfo(
            code = "E093",
            name = "Atria Institute of Technology",
            shortName = "Atria",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2000,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 5.5,
            highestPackageLpa = 22.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 215000,
            website = "https://atria.edu",
            address = "Hebbal, Anandnagar, Bengaluru",
            highlights = listOf("Prime Location Next to Hebbal Flyover"),
            lowestPackageLpa = 3.4,
            campusAreaAcres = "17 Acres Hebbal Flyover Corridor",
            buildingStructure = "Modern North Bangalore campus with Atria Centre for Innovation & Design"
        ),
        CollegeInfo(
            code = "E106",
            name = "SJB Institute of Technology",
            shortName = "SJBIT",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Private Autonomous",
            avgPackageLpa = 5.6,
            highestPackageLpa = 20.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 210000,
            website = "https://sjbit.edu.in",
            address = "BGS Health & Education City, Kengeri",
            highlights = listOf("Peaceful Green Campus under BGS Trust"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "50 Acres BGS Education City",
            buildingStructure = "Peaceful 50-acre green canopy campus with BGS Trust research laboratories and central temple grounds"
        ),
        CollegeInfo(
            code = "E100",
            name = "Sapthagiri College of Engineering",
            shortName = "Sapthagiri",
            tier = CollegeTier.TIER_4,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2001,
            nirfRank = "VTU Affiliated",
            naacGrade = "A",
            campusType = "Private",
            avgPackageLpa = 5.1,
            highestPackageLpa = 18.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 205000,
            website = "https://sapthagiri.edu.in",
            address = "Hesaraghatta Main Road, Bengaluru",
            highlights = listOf("Near Sapthagiri Medical College"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "12 Acres Hesaraghatta Campus",
            buildingStructure = "Medical-Engineering integrated campus grounds with multi-story IT labs"
        ),
        CollegeInfo(
            code = "E019",
            name = "KLS Gogte Institute of Technology",
            shortName = "GIT Belagavi",
            tier = CollegeTier.TIER_4,
            region = Region.NORTH_KARNATAKA,
            district = "Belagavi",
            establishedYear = 1979,
            nirfRank = "VTU Autonomous",
            naacGrade = "A+",
            campusType = "Private Autonomous",
            avgPackageLpa = 5.8,
            highestPackageLpa = 25.0,
            kcetFeeApprox = 107000,
            comedkFeeApprox = 210000,
            website = "https://git.edu",
            address = "Udyambag, Belagavi",
            highlights = listOf("Top Choice in Belagavi Region", "Strong Industrial Linkages"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "23 Acres Industrial Belagavi Estate",
            buildingStructure = "Udyambag green industrial tech hub with state-of-the-art mechanical labs and wind tunnel setup"
        ),
        CollegeInfo(
            code = "E009",
            name = "Malnad College of Engineering",
            shortName = "MCE Hassan",
            tier = CollegeTier.TIER_4,
            region = Region.REST_OF_KARNATAKA,
            district = "Hassan",
            establishedYear = 1960,
            nirfRank = "VTU Autonomous",
            naacGrade = "A",
            campusType = "Govt Aided Autonomous",
            avgPackageLpa = 5.6,
            highestPackageLpa = 20.0,
            kcetFeeApprox = 75000,
            comedkFeeApprox = 200000,
            website = "https://mcehassan.ac.in",
            address = "Salagame Road, Hassan",
            highlights = listOf("Aided Engineering Legacy", "Established in 1960"),
            lowestPackageLpa = 3.2,
            campusAreaAcres = "41 Acres Hassan Heritage Estate",
            buildingStructure = "Scenic 41-acre heritage government-aided campus with traditional stone archways and modern CAD labs"
        ),

        // Tier 5
        CollegeInfo(
            code = "E150",
            name = "Alliance University (College of Engineering)",
            shortName = "Alliance",
            tier = CollegeTier.TIER_5,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2010,
            nirfRank = "Private University",
            naacGrade = "A+",
            campusType = "State Private University",
            avgPackageLpa = 5.0,
            highestPackageLpa = 22.0,
            kcetFeeApprox = 120000,
            comedkFeeApprox = 275000,
            website = "https://alliance.edu.in",
            address = "Chikkahagade Cross, Anekal, Bengaluru",
            highlights = listOf("Magnificent 60-Acre Campus", "Liberal Arts & Tech Blend"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "60 Acres Global Campus",
            buildingStructure = "Neoclassical Roman-pillared architecture, central library rotunda & vast manicured lawns"
        ),
        CollegeInfo(
            code = "E160",
            name = "Dayananda Sagar University (School of Engg)",
            shortName = "DSU Harohalli",
            tier = CollegeTier.TIER_5,
            region = Region.BENGALURU_URBAN,
            district = "Bengaluru",
            establishedYear = 2014,
            nirfRank = "Private University",
            naacGrade = "A",
            campusType = "State Private University",
            avgPackageLpa = 5.5,
            highestPackageLpa = 24.0,
            kcetFeeApprox = 120000,
            comedkFeeApprox = 280000,
            website = "https://dsu.edu.in",
            address = "Harohalli, Kanakapura Road, Bengaluru",
            highlights = listOf("Next-Gen Tech Curriculum", "State-of-the-Art Labs"),
            lowestPackageLpa = 3.5,
            campusAreaAcres = "140 Acres Mega Innovation Campus",
            buildingStructure = "Futuristic 140-Acre campus featuring NVIDIA AI Labs, aerospace testing facilities & student residence towers"
        ),
        CollegeInfo(
            code = "E142",
            name = "Government Engineering College, Hassan",
            shortName = "GEC Hassan",
            tier = CollegeTier.TIER_5,
            region = Region.REST_OF_KARNATAKA,
            district = "Hassan",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B+",
            campusType = "Government",
            avgPackageLpa = 4.2,
            highestPackageLpa = 12.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://gechassan.ac.in",
            address = "Dairy Circle, Hassan",
            highlights = listOf("Full Government College", "Minimal Fee Structure", "Hostel Facilities Available"),
            lowestPackageLpa = 2.8,
            campusAreaAcres = "40 Acres Government Campus",
            buildingStructure = "Government educational complex with multi-department laboratories and student hostel facilities"
        ),
        CollegeInfo(
            code = "E144",
            name = "Government Engineering College, Kushalnagar",
            shortName = "GEC Kushalnagar",
            tier = CollegeTier.TIER_5,
            region = Region.REST_OF_KARNATAKA,
            district = "Kodagu",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B",
            campusType = "Government",
            avgPackageLpa = 4.0,
            highestPackageLpa = 10.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://geckushalnagar.ac.in",
            address = "Kushalnagar, Kodagu",
            highlights = listOf("Government Engineering in Scenic Kodagu", "Affordable Education"),
            lowestPackageLpa = 2.8,
            campusAreaAcres = "30 Acres Coorg Foothills",
            buildingStructure = "Scenic Coorg hillside campus surrounded by lush estates and tranquil study halls"
        ),
        CollegeInfo(
            code = "E143",
            name = "Government Engineering College, Haveri",
            shortName = "GEC Haveri",
            tier = CollegeTier.TIER_5,
            region = Region.NORTH_KARNATAKA,
            district = "Haveri",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B",
            campusType = "Government",
            avgPackageLpa = 4.0,
            highestPackageLpa = 10.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://gechaveri.ac.in",
            address = "Devagiri, Haveri",
            highlights = listOf("Government Institution in Central Karnataka"),
            lowestPackageLpa = 2.8,
            campusAreaAcres = "35 Acres Central Karnataka Grounds",
            buildingStructure = "Standardized state government academic blocks and computer workshops"
        ),
        CollegeInfo(
            code = "E141",
            name = "Government Engineering College, Chamarajanagar",
            shortName = "GEC Chamarajanagar",
            tier = CollegeTier.TIER_5,
            region = Region.MYSURU,
            district = "Chamarajanagar",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B",
            campusType = "Government",
            avgPackageLpa = 3.8,
            highestPackageLpa = 9.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://gecchamarajanagar.ac.in",
            address = "Bedagere, Chamarajanagar",
            highlights = listOf("Government Seat Quota with Low Fees"),
            lowestPackageLpa = 2.5,
            campusAreaAcres = "28 Acres Bedagere Campus",
            buildingStructure = "State government collegiate engineering campus with administrative and lab wings"
        ),
        CollegeInfo(
            code = "E146",
            name = "Government Engineering College, Karwar",
            shortName = "GEC Karwar",
            tier = CollegeTier.TIER_5,
            region = Region.MANGALURU,
            district = "Uttara Kannada",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B",
            campusType = "Government",
            avgPackageLpa = 4.0,
            highestPackageLpa = 11.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://geckarwar.ac.in",
            address = "Majali, Karwar",
            highlights = listOf("Coastal Government Engineering College"),
            lowestPackageLpa = 2.8,
            campusAreaAcres = "25 Acres Coastal Majali Grounds",
            buildingStructure = "Scenic Arabian Sea coastal campus with workshop blocks and student facilities"
        ),
        CollegeInfo(
            code = "E145",
            name = "Government Engineering College, Raichur",
            shortName = "GEC Raichur",
            tier = CollegeTier.TIER_5,
            region = Region.NORTH_KARNATAKA,
            district = "Raichur",
            establishedYear = 2007,
            nirfRank = "VTU Affiliated",
            naacGrade = "B",
            campusType = "Government",
            avgPackageLpa = 3.8,
            highestPackageLpa = 10.0,
            kcetFeeApprox = 38000,
            comedkFeeApprox = 0,
            website = "https://gecraichur.ac.in",
            address = "Yeramarus Camp, Raichur",
            highlights = listOf("371J HK Region Government Seats"),
            lowestPackageLpa = 2.5,
            campusAreaAcres = "30 Acres Yeramarus Grounds",
            buildingStructure = "Kalyana Karnataka region government technical institute with workshop halls"
        )
    )

    // Comprehensive Cutoff list covering multiple branches per college across all 5 tiers
    val cutoffsList: List<CollegeCutoff> = buildList {
        // Tier 1 Cutoffs
        add(CollegeCutoff("c1", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 310, 480, 19.5, 62.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c2", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 620, 950, 19.0, 58.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c3", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ISE, 840, 1250, 18.5, 55.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c4", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 1480, 2400, 16.0, 48.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c5", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 3200, 4800, 12.0, 35.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c6", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.MECH, 7800, 11000, 9.5, 28.0, "#89 NIRF", 107000, 275000))
        add(CollegeCutoff("c7", "E001", "RV College of Engineering", "RVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.BIOTECH, 9400, 14000, 8.5, 24.0, "#89 NIRF", 107000, 275000))

        add(CollegeCutoff("c8", "E060", "PES University (Ring Road Campus)", "PESU RR", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 780, 1150, 16.0, 65.0, "#100-150 Band", 110000, 290000))
        add(CollegeCutoff("c9", "E060", "PES University (Ring Road Campus)", "PESU RR", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 1250, 1800, 15.5, 60.0, "#100-150 Band", 110000, 290000))
        add(CollegeCutoff("c10", "E060", "PES University (Ring Road Campus)", "PESU RR", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 2900, 4200, 13.5, 45.0, "#100-150 Band", 110000, 290000))
        add(CollegeCutoff("c11", "E060", "PES University (Ring Road Campus)", "PESU RR", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 5800, 8500, 10.5, 32.0, "#100-150 Band", 110000, 290000))
        add(CollegeCutoff("c12", "E060", "PES University (Ring Road Campus)", "PESU RR", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.MECH, 14000, 18500, 8.5, 22.0, "#100-150 Band", 110000, 290000))

        add(CollegeCutoff("c13", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 950, 1400, 13.8, 50.0, "#101 NIRF", 107000, 260000))
        add(CollegeCutoff("c14", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 1600, 2300, 13.5, 46.0, "#101 NIRF", 107000, 260000))
        add(CollegeCutoff("c15", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ISE, 1850, 2600, 13.0, 44.0, "#101 NIRF", 107000, 260000))
        add(CollegeCutoff("c16", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 3100, 4600, 12.0, 38.0, "#101 NIRF", 107000, 260000))
        add(CollegeCutoff("c17", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 6200, 9200, 9.5, 28.0, "#101 NIRF", 107000, 260000))
        add(CollegeCutoff("c18", "E003", "B.M.S. College of Engineering", "BMSCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CIVIL, 18000, 26000, 7.2, 18.0, "#101 NIRF", 107000, 260000))

        add(CollegeCutoff("c19", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 1150, 1650, 14.2, 53.0, "#78 NIRF", 107000, 260000))
        add(CollegeCutoff("c20", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 1900, 2700, 13.8, 48.0, "#78 NIRF", 107000, 260000))
        add(CollegeCutoff("c21", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ISE, 2200, 3100, 13.2, 45.0, "#78 NIRF", 107000, 260000))
        add(CollegeCutoff("c22", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 3700, 5200, 11.8, 36.0, "#78 NIRF", 107000, 260000))
        add(CollegeCutoff("c23", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 7500, 10500, 9.2, 26.0, "#78 NIRF", 107000, 260000))
        add(CollegeCutoff("c24", "E005", "Ramaiah Institute of Technology", "MSRIT", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.BIOTECH, 12000, 17500, 8.0, 22.0, "#78 NIRF", 107000, 260000))

        add(CollegeCutoff("c25", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 2450, 0, 11.5, 48.0, "State Autonomous", 42000, 0))
        add(CollegeCutoff("c26", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 3400, 0, 11.0, 42.0, "State Autonomous", 42000, 0))
        add(CollegeCutoff("c27", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ISE, 3950, 0, 10.8, 40.0, "State Autonomous", 42000, 0))
        add(CollegeCutoff("c28", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 5600, 0, 9.8, 34.0, "State Autonomous", 42000, 0))
        add(CollegeCutoff("c29", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 10200, 0, 8.5, 24.0, "State Autonomous", 42000, 0))
        add(CollegeCutoff("c30", "E002", "University Visvesvaraya College of Engg", "UVCE", CollegeTier.TIER_1, Region.BENGALURU_URBAN, "Bengaluru", Branch.CIVIL, 24000, 0, 6.8, 16.0, "State Autonomous", 42000, 0))

        // Tier 2 Cutoffs
        add(CollegeCutoff("c31", "E008", "Dayananda Sagar College of Engineering", "DSCE", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 3900, 5400, 10.5, 42.0, "#150-200 Band", 107000, 245000))
        add(CollegeCutoff("c32", "E008", "Dayananda Sagar College of Engineering", "DSCE", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 5100, 7100, 10.0, 38.0, "#150-200 Band", 107000, 245000))
        add(CollegeCutoff("c33", "E008", "Dayananda Sagar College of Engineering", "DSCE", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ISE, 5800, 8200, 9.8, 36.0, "#150-200 Band", 107000, 245000))
        add(CollegeCutoff("c34", "E008", "Dayananda Sagar College of Engineering", "DSCE", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 8900, 12500, 8.8, 30.0, "#150-200 Band", 107000, 245000))
        add(CollegeCutoff("c35", "E008", "Dayananda Sagar College of Engineering", "DSCE", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 15800, 22000, 7.8, 22.0, "#150-200 Band", 107000, 245000))

        add(CollegeCutoff("c36", "E006", "Bangalore Institute of Technology", "BIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 4800, 6800, 9.8, 38.0, "VTU Affiliated", 107000, 235000))
        add(CollegeCutoff("c37", "E006", "Bangalore Institute of Technology", "BIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 6400, 8900, 9.4, 35.0, "VTU Affiliated", 107000, 235000))
        add(CollegeCutoff("c38", "E006", "Bangalore Institute of Technology", "BIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 10500, 15200, 8.4, 28.0, "VTU Affiliated", 107000, 235000))
        add(CollegeCutoff("c39", "E006", "Bangalore Institute of Technology", "BIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.EEE, 18500, 26000, 7.2, 20.0, "VTU Affiliated", 107000, 235000))

        add(CollegeCutoff("c40", "E089", "BMS Institute of Tech & Mgmt", "BMSIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 5500, 7800, 9.2, 44.0, "#150-200 Band", 107000, 240000))
        add(CollegeCutoff("c41", "E089", "BMS Institute of Tech & Mgmt", "BMSIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 7200, 10200, 9.0, 40.0, "#150-200 Band", 107000, 240000))
        add(CollegeCutoff("c42", "E089", "BMS Institute of Tech & Mgmt", "BMSIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 11800, 17000, 8.0, 28.0, "#150-200 Band", 107000, 240000))

        add(CollegeCutoff("c43", "E004", "SJCE Mysuru (JSS STU)", "SJCE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.CSE, 3200, 4600, 10.2, 45.0, "#150-200 Band", 107000, 250000))
        add(CollegeCutoff("c44", "E004", "SJCE Mysuru (JSS STU)", "SJCE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.ISE, 4900, 7100, 9.8, 42.0, "#150-200 Band", 107000, 250000))
        add(CollegeCutoff("c45", "E004", "SJCE Mysuru (JSS STU)", "SJCE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.ECE, 7600, 10800, 9.0, 35.0, "#150-200 Band", 107000, 250000))
        add(CollegeCutoff("c46", "E004", "SJCE Mysuru (JSS STU)", "SJCE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.MECH, 19500, 28000, 7.5, 22.0, "#150-200 Band", 107000, 250000))

        add(CollegeCutoff("c47", "E007", "The National Institute of Engineering", "NIE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.CSE, 4500, 6400, 9.5, 43.0, "#150-200 Band", 107000, 240000))
        add(CollegeCutoff("c48", "E007", "The National Institute of Engineering", "NIE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.ISE, 6200, 8900, 9.0, 38.0, "#150-200 Band", 107000, 240000))
        add(CollegeCutoff("c49", "E007", "The National Institute of Engineering", "NIE Mysuru", CollegeTier.TIER_2, Region.MYSURU, "Mysuru", Branch.ECE, 9800, 14200, 8.4, 30.0, "#150-200 Band", 107000, 240000))

        add(CollegeCutoff("c50", "E012", "Sir M. Visvesvaraya Institute of Tech", "Sir MVIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 7800, 11000, 8.5, 36.0, "VTU Affiliated", 107000, 230000))
        add(CollegeCutoff("c51", "E012", "Sir M. Visvesvaraya Institute of Tech", "Sir MVIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 9900, 14200, 8.2, 32.0, "VTU Affiliated", 107000, 230000))
        add(CollegeCutoff("c52", "E012", "Sir M. Visvesvaraya Institute of Tech", "Sir MVIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 15500, 22000, 7.4, 25.0, "VTU Affiliated", 107000, 230000))

        add(CollegeCutoff("c53", "E098", "Nitte Meenakshi Institute of Tech", "NMIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 8200, 11800, 8.8, 40.0, "#151-200 Band", 107000, 240000))
        add(CollegeCutoff("c54", "E098", "Nitte Meenakshi Institute of Tech", "NMIT", CollegeTier.TIER_2, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 16200, 23000, 7.5, 26.0, "#151-200 Band", 107000, 240000))

        add(CollegeCutoff("c55", "E010", "Siddaganga Institute of Technology", "SIT Tumakuru", CollegeTier.TIER_2, Region.REST_OF_KARNATAKA, "Tumakuru", Branch.CSE, 6800, 9600, 8.5, 38.0, "#100 NIRF", 107000, 230000))
        add(CollegeCutoff("c56", "E010", "Siddaganga Institute of Technology", "SIT Tumakuru", CollegeTier.TIER_2, Region.REST_OF_KARNATAKA, "Tumakuru", Branch.AIML, 8800, 12500, 8.2, 34.0, "#100 NIRF", 107000, 230000))
        add(CollegeCutoff("c57", "E010", "Siddaganga Institute of Technology", "SIT Tumakuru", CollegeTier.TIER_2, Region.REST_OF_KARNATAKA, "Tumakuru", Branch.ECE, 13900, 19800, 7.8, 27.0, "#100 NIRF", 107000, 230000))

        // Tier 3 Cutoffs
        add(CollegeCutoff("c58", "E094", "New Horizon College of Engineering", "NHCE", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 11500, 16200, 7.4, 32.0, "#150-200 Band", 107000, 235000))
        add(CollegeCutoff("c59", "E094", "New Horizon College of Engineering", "NHCE", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 14200, 20500, 7.2, 30.0, "#150-200 Band", 107000, 235000))
        add(CollegeCutoff("c60", "E094", "New Horizon College of Engineering", "NHCE", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 22000, 31000, 6.5, 22.0, "#150-200 Band", 107000, 235000))

        add(CollegeCutoff("c61", "E095", "CMR Institute of Technology", "CMRIT", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 14800, 21000, 7.0, 30.0, "A+ NAAC", 107000, 230000))
        add(CollegeCutoff("c62", "E095", "CMR Institute of Technology", "CMRIT", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 18500, 26000, 6.8, 26.0, "A+ NAAC", 107000, 230000))
        add(CollegeCutoff("c63", "E095", "CMR Institute of Technology", "CMRIT", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 27500, 39000, 6.2, 20.0, "A+ NAAC", 107000, 230000))

        add(CollegeCutoff("c64", "E096", "Acharya Institute of Technology", "Acharya", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 19500, 28000, 6.5, 26.0, "A NAAC", 107000, 225000))
        add(CollegeCutoff("c65", "E096", "Acharya Institute of Technology", "Acharya", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 34000, 48000, 5.8, 19.0, "A NAAC", 107000, 225000))

        add(CollegeCutoff("c66", "E014", "KLE Technological University (BVBCET)", "KLE Tech Hubballi", CollegeTier.TIER_3, Region.NORTH_KARNATAKA, "Hubballi", Branch.CSE, 10500, 15000, 7.8, 35.0, "#150-200 Band", 107000, 230000))
        add(CollegeCutoff("c67", "E014", "KLE Technological University (BVBCET)", "KLE Tech Hubballi", CollegeTier.TIER_3, Region.NORTH_KARNATAKA, "Hubballi", Branch.AIML, 13800, 19500, 7.5, 30.0, "#150-200 Band", 107000, 230000))
        add(CollegeCutoff("c68", "E014", "KLE Technological University (BVBCET)", "KLE Tech Hubballi", CollegeTier.TIER_3, Region.NORTH_KARNATAKA, "Hubballi", Branch.ECE, 21000, 29500, 6.8, 24.0, "#150-200 Band", 107000, 230000))

        add(CollegeCutoff("c69", "E112", "Cambridge Institute of Technology", "CIT Tech", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 22500, 32000, 6.2, 27.0, "A+ NAAC", 107000, 225000))
        add(CollegeCutoff("c70", "E112", "Cambridge Institute of Technology", "CIT Tech", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 38000, 54000, 5.6, 18.0, "A+ NAAC", 107000, 225000))

        add(CollegeCutoff("c71", "E013", "MVJ College of Engineering", "MVJCE", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 24000, 34000, 6.3, 26.0, "A+ NAAC", 107000, 220000))
        add(CollegeCutoff("c72", "E013", "MVJ College of Engineering", "MVJCE", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.AERO, 29000, 42000, 6.0, 22.0, "A+ NAAC", 107000, 220000))

        add(CollegeCutoff("c73", "E104", "Global Academy of Technology", "GAT", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 21000, 30000, 6.4, 25.0, "A NAAC", 107000, 220000))
        add(CollegeCutoff("c74", "E104", "Global Academy of Technology", "GAT", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 36000, 51000, 5.8, 18.0, "A NAAC", 107000, 220000))

        add(CollegeCutoff("c75", "E078", "Vidyavardhaka College of Engineering", "VVCE Mysuru", CollegeTier.TIER_3, Region.MYSURU, "Mysuru", Branch.CSE, 15800, 22500, 6.6, 28.0, "A NAAC", 107000, 220000))
        add(CollegeCutoff("c76", "E078", "Vidyavardhaka College of Engineering", "VVCE Mysuru", CollegeTier.TIER_3, Region.MYSURU, "Mysuru", Branch.ECE, 26500, 37500, 6.0, 20.0, "A NAAC", 107000, 220000))

        // Tier 4 Cutoffs
        add(CollegeCutoff("c77", "E072", "AMC Engineering College", "AMC", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 62000, 88000, 5.2, 20.0, "VTU Affiliated", 107000, 200000))
        add(CollegeCutoff("c78", "E072", "AMC Engineering College", "AMC", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 92000, 125000, 4.5, 14.0, "VTU Affiliated", 107000, 200000))

        add(CollegeCutoff("c79", "E105", "Don Bosco Institute of Technology", "DBIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 58000, 82000, 5.0, 18.0, "VTU Affiliated", 107000, 200000))
        add(CollegeCutoff("c80", "E105", "Don Bosco Institute of Technology", "DBIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 88000, 120000, 4.4, 13.0, "VTU Affiliated", 107000, 200000))

        add(CollegeCutoff("c81", "E101", "East West Institute of Technology", "EWIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 64000, 90000, 4.8, 16.0, "VTU Affiliated", 107000, 200000))
        add(CollegeCutoff("c82", "E101", "East West Institute of Technology", "EWIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 98000, 135000, 4.2, 12.0, "VTU Affiliated", 107000, 200000))

        add(CollegeCutoff("c83", "E128", "Rajarajeswari College of Engineering", "RRCE", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 54000, 76000, 5.2, 21.0, "A+ NAAC", 107000, 200000))
        add(CollegeCutoff("c84", "E128", "Rajarajeswari College of Engineering", "RRCE", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 84000, 115000, 4.6, 15.0, "A+ NAAC", 107000, 200000))

        add(CollegeCutoff("c85", "E070", "The Oxford College of Engineering", "Oxford", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 48000, 68000, 5.4, 22.0, "A NAAC", 107000, 210000))
        add(CollegeCutoff("c86", "E070", "The Oxford College of Engineering", "Oxford", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 76000, 105000, 4.8, 16.0, "A NAAC", 107000, 210000))

        add(CollegeCutoff("c87", "E093", "Atria Institute of Technology", "Atria", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 44000, 62000, 5.5, 22.0, "A NAAC", 107000, 215000))
        add(CollegeCutoff("c88", "E093", "Atria Institute of Technology", "Atria", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 71000, 98000, 4.9, 16.0, "A NAAC", 107000, 215000))

        add(CollegeCutoff("c89", "E106", "SJB Institute of Technology", "SJBIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 42000, 59000, 5.6, 20.0, "A NAAC", 107000, 210000))
        add(CollegeCutoff("c90", "E106", "SJB Institute of Technology", "SJBIT", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 68000, 94000, 5.0, 15.0, "A NAAC", 107000, 210000))

        add(CollegeCutoff("c91", "E019", "KLS Gogte Institute of Tech", "GIT Belagavi", CollegeTier.TIER_4, Region.NORTH_KARNATAKA, "Belagavi", Branch.CSE, 38000, 52000, 5.8, 25.0, "A+ NAAC", 107000, 210000))
        add(CollegeCutoff("c92", "E019", "KLS Gogte Institute of Tech", "GIT Belagavi", CollegeTier.TIER_4, Region.NORTH_KARNATAKA, "Belagavi", Branch.ECE, 64000, 88000, 5.1, 18.0, "A+ NAAC", 107000, 210000))

        add(CollegeCutoff("c93", "E009", "Malnad College of Engineering", "MCE Hassan", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Hassan", Branch.CSE, 35000, 49000, 5.6, 20.0, "Govt Aided", 75000, 200000))
        add(CollegeCutoff("c94", "E009", "Malnad College of Engineering", "MCE Hassan", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Hassan", Branch.MECH, 95000, 130000, 4.8, 14.0, "Govt Aided", 75000, 200000))

        // Tier 5 Cutoffs (150,000+)
        add(CollegeCutoff("c95", "E150", "Alliance University (College of Engg)", "Alliance", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 110000, 155000, 5.0, 22.0, "Private Univ", 120000, 275000))
        add(CollegeCutoff("c96", "E150", "Alliance University (College of Engg)", "Alliance", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 165000, 210000, 4.2, 14.0, "Private Univ", 120000, 275000))

        add(CollegeCutoff("c97", "E160", "Dayananda Sagar University (Engg)", "DSU Harohalli", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 95000, 135000, 5.5, 24.0, "Private Univ", 120000, 280000))
        add(CollegeCutoff("c98", "E160", "Dayananda Sagar University (Engg)", "DSU Harohalli", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 145000, 195000, 4.8, 16.0, "Private Univ", 120000, 280000))

        add(CollegeCutoff("c99", "E142", "Government Engineering College, Hassan", "GEC Hassan", CollegeTier.TIER_5, Region.REST_OF_KARNATAKA, "Hassan", Branch.CSE, 68000, 0, 4.2, 12.0, "Government", 38000, 0))
        add(CollegeCutoff("c100", "E142", "Government Engineering College, Hassan", "GEC Hassan", CollegeTier.TIER_5, Region.REST_OF_KARNATAKA, "Hassan", Branch.ECE, 115000, 0, 3.8, 10.0, "Government", 38000, 0))
        add(CollegeCutoff("c101", "E142", "Government Engineering College, Hassan", "GEC Hassan", CollegeTier.TIER_5, Region.REST_OF_KARNATAKA, "Hassan", Branch.CIVIL, 185000, 0, 3.5, 8.0, "Government", 38000, 0))

        add(CollegeCutoff("c102", "E144", "Government Engineering College, Kushalnagar", "GEC Kushalnagar", CollegeTier.TIER_5, Region.REST_OF_KARNATAKA, "Kodagu", Branch.CSE, 98000, 0, 4.0, 10.0, "Government", 38000, 0))
        add(CollegeCutoff("c103", "E144", "Government Engineering College, Kushalnagar", "GEC Kushalnagar", CollegeTier.TIER_5, Region.REST_OF_KARNATAKA, "Kodagu", Branch.ECE, 160000, 0, 3.6, 8.5, "Government", 38000, 0))

        add(CollegeCutoff("c104", "E143", "Government Engineering College, Haveri", "GEC Haveri", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Haveri", Branch.CSE, 105000, 0, 4.0, 10.0, "Government", 38000, 0))
        add(CollegeCutoff("c105", "E143", "Government Engineering College, Haveri", "GEC Haveri", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Haveri", Branch.ECE, 172000, 0, 3.5, 8.0, "Government", 38000, 0))

        add(CollegeCutoff("c106", "E141", "Government Engineering College, Chamarajanagar", "GEC Chamarajanagar", CollegeTier.TIER_5, Region.MYSURU, "Chamarajanagar", Branch.CSE, 118000, 0, 3.8, 9.0, "Government", 38000, 0))
        add(CollegeCutoff("c107", "E141", "Government Engineering College, Chamarajanagar", "GEC Chamarajanagar", CollegeTier.TIER_5, Region.MYSURU, "Chamarajanagar", Branch.ECE, 185000, 0, 3.4, 7.5, "Government", 38000, 0))
        add(CollegeCutoff("c108", "E141", "Government Engineering College, Chamarajanagar", "GEC Chamarajanagar", CollegeTier.TIER_5, Region.MYSURU, "Chamarajanagar", Branch.CIVIL, 230000, 0, 3.2, 6.0, "Government", 38000, 0))

        add(CollegeCutoff("c109", "E146", "Government Engineering College, Karwar", "GEC Karwar", CollegeTier.TIER_5, Region.MANGALURU, "Uttara Kannada", Branch.CSE, 102000, 0, 4.0, 11.0, "Government", 38000, 0))
        add(CollegeCutoff("c110", "E146", "Government Engineering College, Karwar", "GEC Karwar", CollegeTier.TIER_5, Region.MANGALURU, "Uttara Kannada", Branch.ECE, 168000, 0, 3.6, 8.5, "Government", 38000, 0))

        add(CollegeCutoff("c111", "E145", "Government Engineering College, Raichur", "GEC Raichur", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Raichur", Branch.CSE, 125000, 0, 3.8, 10.0, "Government", 38000, 0))
        add(CollegeCutoff("c112", "E145", "Government Engineering College, Raichur", "GEC Raichur", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Raichur", Branch.ECE, 195000, 0, 3.4, 8.0, "Government", 38000, 0))
        add(CollegeCutoff("c113", "E145", "Government Engineering College, Raichur", "GEC Raichur", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Raichur", Branch.CIVIL, 245000, 0, 3.0, 6.5, "Government", 38000, 0))

        // Additional Comprehensive Cutoffs for Ranks up to 280,000+
        add(CollegeCutoff("c114", "E147", "Government Engineering College, Ramanagara", "GEC Ramanagara", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Ramanagara", Branch.CSE, 85000, 0, 4.2, 12.0, "Government", 38000, 0))
        add(CollegeCutoff("c115", "E147", "Government Engineering College, Ramanagara", "GEC Ramanagara", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Ramanagara", Branch.ECE, 142000, 0, 3.8, 9.5, "Government", 38000, 0))
        add(CollegeCutoff("c116", "E147", "Government Engineering College, Ramanagara", "GEC Ramanagara", CollegeTier.TIER_5, Region.BENGALURU_URBAN, "Ramanagara", Branch.MECH, 210000, 0, 3.2, 7.0, "Government", 38000, 0))

        add(CollegeCutoff("c117", "E148", "Government Engineering College, Mandya", "GEC Mandya", CollegeTier.TIER_5, Region.MYSURU, "Mandya", Branch.CSE, 92000, 0, 4.0, 11.0, "Government", 38000, 0))
        add(CollegeCutoff("c118", "E148", "Government Engineering College, Mandya", "GEC Mandya", CollegeTier.TIER_5, Region.MYSURU, "Mandya", Branch.ECE, 155000, 0, 3.6, 8.5, "Government", 38000, 0))
        add(CollegeCutoff("c119", "E148", "Government Engineering College, Mandya", "GEC Mandya", CollegeTier.TIER_5, Region.MYSURU, "Mandya", Branch.CIVIL, 225000, 0, 3.2, 6.0, "Government", 38000, 0))

        add(CollegeCutoff("c120", "E149", "Government Engineering College, Gangavathi", "GEC Gangavathi", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Koppal", Branch.CSE, 135000, 0, 3.8, 9.0, "Government (371J)", 38000, 0))
        add(CollegeCutoff("c121", "E149", "Government Engineering College, Gangavathi", "GEC Gangavathi", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Koppal", Branch.ECE, 198000, 0, 3.4, 7.5, "Government (371J)", 38000, 0))
        add(CollegeCutoff("c122", "E149", "Government Engineering College, Gangavathi", "GEC Gangavathi", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Koppal", Branch.MECH, 260000, 0, 3.0, 5.5, "Government (371J)", 38000, 0))

        add(CollegeCutoff("c123", "E120", "Reva University (School of Engg)", "Reva Univ", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 21500, 32000, 6.8, 30.0, "Private Univ", 120000, 265000))
        add(CollegeCutoff("c124", "E120", "Reva University (School of Engg)", "Reva Univ", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 26000, 39000, 6.5, 27.0, "Private Univ", 120000, 265000))
        add(CollegeCutoff("c125", "E120", "Reva University (School of Engg)", "Reva Univ", CollegeTier.TIER_3, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 45000, 68000, 5.8, 20.0, "Private Univ", 120000, 265000))

        add(CollegeCutoff("c126", "E028", "SDM Institute of Technology Ujire", "SDMIT Ujire", CollegeTier.TIER_4, Region.MANGALURU, "Dakshina Kannada", Branch.CSE, 52000, 75000, 5.0, 18.0, "VTU Affiliated", 107000, 200000))
        add(CollegeCutoff("c127", "E028", "SDM Institute of Technology Ujire", "SDMIT Ujire", CollegeTier.TIER_4, Region.MANGALURU, "Dakshina Kannada", Branch.ECE, 89000, 122000, 4.4, 13.0, "VTU Affiliated", 107000, 200000))

        add(CollegeCutoff("c128", "E035", "Bapuji Institute of Engg & Tech", "BIET Davangere", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Davangere", Branch.CSE, 46000, 65000, 5.5, 22.0, "VTU Affiliated", 107000, 210000))
        add(CollegeCutoff("c129", "E035", "Bapuji Institute of Engg & Tech", "BIET Davangere", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Davangere", Branch.ECE, 78000, 108000, 4.8, 15.0, "VTU Affiliated", 107000, 210000))
        add(CollegeCutoff("c130", "E035", "Bapuji Institute of Engg & Tech", "BIET Davangere", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Davangere", Branch.CIVIL, 165000, 210000, 3.8, 9.0, "VTU Affiliated", 107000, 210000))

        add(CollegeCutoff("c131", "E036", "Jawaharlal Nehru National College of Engg", "JNNCE Shivamogga", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Shivamogga", Branch.CSE, 41000, 58000, 5.6, 21.0, "VTU Autonomous", 107000, 210000))
        add(CollegeCutoff("c132", "E036", "Jawaharlal Nehru National College of Engg", "JNNCE Shivamogga", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Shivamogga", Branch.ECE, 72000, 99000, 4.9, 16.0, "VTU Autonomous", 107000, 210000))
        add(CollegeCutoff("c133", "E036", "Jawaharlal Nehru National College of Engg", "JNNCE Shivamogga", CollegeTier.TIER_4, Region.REST_OF_KARNATAKA, "Shivamogga", Branch.CIVIL, 175000, 225000, 3.6, 8.5, "VTU Autonomous", 107000, 210000))

        add(CollegeCutoff("c134", "E045", "PDA College of Engineering", "PDA Kalaburagi", CollegeTier.TIER_4, Region.NORTH_KARNATAKA, "Kalaburagi", Branch.CSE, 49000, 70000, 5.2, 19.0, "Govt Aided (371J)", 75000, 200000))
        add(CollegeCutoff("c135", "E045", "PDA College of Engineering", "PDA Kalaburagi", CollegeTier.TIER_4, Region.NORTH_KARNATAKA, "Kalaburagi", Branch.ECE, 85000, 118000, 4.5, 14.0, "Govt Aided (371J)", 75000, 200000))
        add(CollegeCutoff("c136", "E045", "PDA College of Engineering", "PDA Kalaburagi", CollegeTier.TIER_4, Region.NORTH_KARNATAKA, "Kalaburagi", Branch.CIVIL, 185000, 240000, 3.5, 8.0, "Govt Aided (371J)", 75000, 200000))

        add(CollegeCutoff("c137", "E055", "Rao Bahadur Y Mahabaleswarappa Engg", "RYMEC Ballari", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Ballari", Branch.CSE, 78000, 110000, 4.5, 15.0, "VTU Affiliated", 107000, 195000))
        add(CollegeCutoff("c138", "E055", "Rao Bahadur Y Mahabaleswarappa Engg", "RYMEC Ballari", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Ballari", Branch.ECE, 138000, 185000, 3.8, 10.0, "VTU Affiliated", 107000, 195000))
        add(CollegeCutoff("c139", "E055", "Rao Bahadur Y Mahabaleswarappa Engg", "RYMEC Ballari", CollegeTier.TIER_5, Region.NORTH_KARNATAKA, "Ballari", Branch.MECH, 240000, 275000, 3.2, 6.0, "VTU Affiliated", 107000, 195000))

        add(CollegeCutoff("c140", "E115", "Presidency University", "Presidency Univ", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.CSE, 49000, 72000, 5.5, 23.0, "Private Univ", 120000, 260000))
        add(CollegeCutoff("c141", "E115", "Presidency University", "Presidency Univ", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.AIML, 58000, 84000, 5.2, 20.0, "Private Univ", 120000, 260000))
        add(CollegeCutoff("c142", "E115", "Presidency University", "Presidency Univ", CollegeTier.TIER_4, Region.BENGALURU_URBAN, "Bengaluru", Branch.ECE, 95000, 135000, 4.5, 15.0, "Private Univ", 120000, 260000))
    }

    /**
     * Calculates the adjusted cutoff based on exam and reservation category
     */
    fun calculateEffectiveCutoff(
        cutoff: CollegeCutoff,
        examType: ExamType,
        category: ReservationCategory
    ): Int {
        val baseCutoff = if (examType == ExamType.KCET) {
            cutoff.kcetCutoffGM
        } else {
            if (cutoff.comedkCutoffGM > 0) cutoff.comedkCutoffGM else (cutoff.kcetCutoffGM * 1.35).toInt()
        }

        if (baseCutoff == 0) return 0

        // Apply category multiplier for KCET
        val multiplier = if (examType == ExamType.KCET) category.multiplier else {
            if (category == ReservationCategory.KKR_371J) 1.25 else 1.0
        }

        return (baseCutoff * multiplier).toInt()
    }

    /**
     * Categorizes into High Chance (Safe), Moderate Chance (Realistic), Low Chance (Dream)
     */
    fun evaluateChance(studentRank: Int, effectiveCutoff: Int): Pair<ChanceCategory, Int> {
        if (effectiveCutoff <= 0) return Pair(ChanceCategory.LOW_CHANCE, 10)

        val ratio = effectiveCutoff.toDouble() / studentRank.toDouble()

        return when {
            // Cutoff is 20%+ higher than student rank -> High Chance (Safe)
            ratio >= 1.20 -> {
                val prob = (75 + ((ratio - 1.20) * 20)).toInt().coerceIn(75, 99)
                Pair(ChanceCategory.HIGH_CHANCE, prob)
            }
            // Cutoff is within ±15-20% -> Moderate Chance (Realistic)
            ratio >= 0.85 -> {
                val prob = (45 + ((ratio - 0.85) / 0.35 * 30)).toInt().coerceIn(45, 74)
                Pair(ChanceCategory.MODERATE_CHANCE, prob)
            }
            // Cutoff is lower than student rank (up to 20% lower or beyond) -> Low Chance (Dream)
            else -> {
                val prob = (10 + (ratio / 0.85 * 34)).toInt().coerceIn(5, 44)
                Pair(ChanceCategory.LOW_CHANCE, prob)
            }
        }
    }

    /**
     * Generates predictions for the given filters
     */
    fun getPredictions(
        studentRank: Int,
        examType: ExamType,
        category: ReservationCategory,
        selectedBranches: Set<Branch>,
        selectedRegion: Region,
        searchQuery: String = ""
    ): List<PredictionItem> {
        return cutoffsList.filter { cutoff ->
            // Filter by branch
            val matchesBranch = selectedBranches.isEmpty() || selectedBranches.contains(cutoff.branch)

            // Filter by region
            val matchesRegion = selectedRegion == Region.ALL || cutoff.region == selectedRegion

            // Filter by search query
            val matchesSearch = searchQuery.isBlank() ||
                    cutoff.collegeName.contains(searchQuery, ignoreCase = true) ||
                    cutoff.collegeShortName.contains(searchQuery, ignoreCase = true) ||
                    cutoff.collegeCode.contains(searchQuery, ignoreCase = true) ||
                    cutoff.branch.fullName.contains(searchQuery, ignoreCase = true)

            // Valid cutoff for the exam type
            val hasExamSeat = if (examType == ExamType.COMEDK) {
                cutoff.comedkCutoffGM > 0 || cutoff.tier != CollegeTier.TIER_5
            } else {
                cutoff.kcetCutoffGM > 0
            }

            matchesBranch && matchesRegion && matchesSearch && hasExamSeat
        }.map { cutoff ->
            val effectiveCutoff = calculateEffectiveCutoff(cutoff, examType, category)
            val (chance, prob) = evaluateChance(studentRank, effectiveCutoff)

            PredictionItem(
                cutoff = cutoff,
                calculatedCutoff = effectiveCutoff,
                studentRank = studentRank,
                chance = chance,
                probabilityScore = prob,
                examType = examType,
                category = category
            )
        }.sortedWith(
            compareBy(
                { it.chance.ordinal }, // Safe first, or we can sort by Tier then cutoff
                { it.cutoff.tier.tierNumber },
                { it.calculatedCutoff }
            )
        )
    }

    /**
     * Validates the student's priority list against KEA/COMEDK single-seat allotment rules
     */
    fun validateStrategyRules(
        options: List<com.example.data.local.StrategyOptionEntity>
    ): List<StrategyRuleAlert> {
        val alerts = mutableListOf<StrategyRuleAlert>()

        if (options.isEmpty()) return alerts

        // Rule 1: Check if a Safe choice is placed ABOVE a Dream choice (Critical Red Flag)
        for (i in 0 until options.size - 1) {
            val upper = options[i]
            for (j in i + 1 until options.size) {
                val lower = options[j]

                // If upper is Safe (high cutoff rank) and lower is Dream (low cutoff rank with big gap)
                if (upper.cutoffRank > (lower.cutoffRank * 1.5) && upper.chanceLabel.contains("Safe", ignoreCase = true) && lower.chanceLabel.contains("Dream", ignoreCase = true)) {
                    alerts.add(
                        StrategyRuleAlert(
                            isSevere = true,
                            title = "Critical Priority Order Flaw (Option #${i + 1} vs #${j + 1})",
                            message = "Option #${i + 1} (${upper.collegeShortName} - ${upper.branchCode}, Cutoff ${upper.cutoffRank}) is a Safe choice placed ABOVE Option #${j + 1} (${lower.collegeShortName} - ${lower.branchCode}, Cutoff ${lower.cutoffRank} Dream). In KEA/COMEDK, once Option #${i + 1} is allotted, Option #${j + 1} will be PERMANENTLY BLOCKED and never evaluated! Move your Dream colleges higher in the list.",
                            misplacedHigherOptionIndex = i,
                            misplacedLowerOptionIndex = j
                        )
                    )
                    break
                }
            }
        }

        // Rule 2: Check backup depth
        val safeCount = options.count { it.chanceLabel.contains("Safe", ignoreCase = true) }
        if (options.size in 1..4 && safeCount == 0) {
            alerts.add(
                StrategyRuleAlert(
                    isSevere = false,
                    title = "Insufficient Backup Seats Warning",
                    message = "Your list contains zero 'Safe' backup choices. If cutoff ranks shift tighter this year, you risk not being allotted any seat in Round 1. Add at least 3-5 Safe options."
                )
            )
        }

        // Rule 3: Total options count tip
        if (options.size < 5) {
            alerts.add(
                StrategyRuleAlert(
                    isSevere = false,
                    title = "Recommended: Add More Options",
                    message = "KEA allows unlimited option entries. Top counsellors recommend entering at least 25 to 50 options spanning Dream, Realistic, and Safe tiers."
                )
            )
        }

        return alerts
    }

    // Comprehensive Dictionary / Glossary for KCET & COMEDK
    val dictionaryList: List<com.example.data.models.DictionaryTerm> = listOf(
        // KCET Terms
        com.example.data.models.DictionaryTerm(
            term = "Supernumerary Quota",
            acronym = "SNQ",
            examType = ExamType.KCET,
            category = "Fee & Quotas",
            shortDefinition = "100% Tuition Fee Waiver for families with annual income < ₹8 Lakhs.",
            detailedExplanation = "KEA reserves 5% extra seats in every branch across engineering colleges in Karnataka for meritorious economically weaker students. All tuition fees (~₹1,07,000/yr) are completely waived; you only pay a nominal VTU/College fee (~₹4,000 to ₹8,000/yr).",
            studentTip = "Ensure your 21-digit RD Number on your Income Certificate is validated during online verification to be automatically eligible."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Kalyana Karnataka Quota",
            acronym = "Article 371(J) / HKR",
            examType = ExamType.KCET,
            category = "Reservations",
            shortDefinition = "70% local reservation in KK region and 8% statewide quota across Bengaluru colleges.",
            detailedExplanation = "Students belonging to the 7 Hyderabad-Karnataka districts (Bidar, Kalaburagi, Yadgir, Raichur, Koppal, Ballari, Vijayanagara) get massive cutoff relaxation in top colleges like RVCE, BMSCE, and MSRIT.",
            studentTip = "Requires a valid 371(J) eligibility certificate issued by the Assistant Commissioner of the sub-division."
        ),
        com.example.data.models.DictionaryTerm(
            term = "KEA Verification Slip & Secret Key",
            acronym = "KEA Slip",
            examType = ExamType.KCET,
            category = "Process & Portal",
            shortDefinition = "Document containing your Unique Secret Key to unlock Option Entry portal.",
            detailedExplanation = "After successful document verification at KEA or online portal, students receive a Verification Slip containing a 16-character Secret Key. This key is used to generate your user password and login for option entry.",
            studentTip = "NEVER share your Secret Key or password with cyber cafes, agents, or friends to prevent unauthorized option tampering."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Rural Quota",
            acronym = "Rural",
            examType = ExamType.KCET,
            category = "Reservations",
            shortDefinition = "15% reservation for candidates who studied 10 full academic years in rural Karnataka.",
            detailedExplanation = "Requires studying 1st to 10th standard in designated rural panchayat areas (excluding municipal corporation limits), certified by headmaster and counter-signed by the Block Education Officer (BEO).",
            studentTip = "Provides a 15-25% cutoff rank advantage in state engineering seats."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Kannada Medium Quota",
            acronym = "KM",
            examType = ExamType.KCET,
            category = "Reservations",
            shortDefinition = "5% reservation for students who completed 1st to 10th standard in Kannada Medium.",
            detailedExplanation = "Offered to encourage vernacular medium education. Requires 10 full years of Kannada medium study certified by the respective BEO.",
            studentTip = "Gives a distinct edge in competitive government & top autonomous engineering seats."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Study Certificate",
            acronym = "7-Year Proof",
            examType = ExamType.KCET,
            category = "Documents",
            shortDefinition = "Mandatory certificate proving minimum 7 continuous years of study in Karnataka.",
            detailedExplanation = "To establish Karnataka domicile eligibility for government quota seats, candidate must have studied for a minimum of 7 academic years from 1st standard to 12th/PUC in Karnataka, counter-signed by BEO/DDPU.",
            studentTip = "Keep both school-issued original and counter-signed BEO seals intact."
        ),

        // COMEDK Terms
        com.example.data.models.DictionaryTerm(
            term = "COMEDK UGET",
            acronym = "UGET",
            examType = ExamType.COMEDK,
            category = "Examination",
            shortDefinition = "Undergraduate Entrance Test conducted by Consortium of Medical, Engg & Dental Colleges of Karnataka.",
            detailedExplanation = "A centralized single-window counseling test for admission to around 190+ private unaided engineering colleges across Karnataka. Open to students from all across India as well as Karnataka residents.",
            studentTip = "COMEDK seat allotment is purely based on COMEDK All-India Rank, offering equal merit opportunities for all students."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Tatkal Option Entry / Choice Editing",
            acronym = "Tatkal Editing",
            examType = ExamType.COMEDK,
            category = "Process & Portal",
            shortDefinition = "Window opened before each COMEDK round allowing addition, deletion, and re-ordering of choices.",
            detailedExplanation = "COMEDK allows candidates to modify their priority options between rounds (Round 1, Round 2, Round 3). You can re-arrange existing colleges, remove unwanted courses, or add new options that opened up in vacant seat matrices.",
            studentTip = "Always review your choices before locking in each round, especially removing colleges you are no longer willing to pay fee for."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Accept & Freeze (COMEDK Choice 1)",
            acronym = "Freeze",
            examType = ExamType.COMEDK,
            category = "Allotment Rules",
            shortDefinition = "Confirm allotment, pay full tuition fee (approx ₹2.6L), and report to college.",
            detailedExplanation = "When you are 100% satisfied with the allotted college and branch. You must pay the total annual tuition fee online through COMEDK payment gateway, download the Online Allotment Letter, and physical report to the allotted college within deadline.",
            studentTip = "Once you Freeze, you will NOT be eligible for any further COMEDK upgrade rounds."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Accept & Upgrade (COMEDK Choice 2)",
            acronym = "Hold & Upgrade",
            examType = ExamType.COMEDK,
            category = "Allotment Rules",
            shortDefinition = "Hold current seat by paying full fee while competing for higher priority choices in next round.",
            detailedExplanation = "Allows you to secure your Round 1/2 seat by paying the full tuition fee. In the next round, if a higher choice is allotted, your previous seat is automatically transferred; if no upgrade occurs, your existing seat is 100% preserved!",
            studentTip = "The safest strategic option if you are happy with current college but want to try for higher dream branches."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Reject & Upgrade (COMEDK Choice 3)",
            acronym = "Surrender & Upgrade",
            examType = ExamType.COMEDK,
            category = "Allotment Rules",
            shortDefinition = "Reject current seat without full payment and participate in next round for higher options only.",
            detailedExplanation = "Used when you definitely do not want the allotted college and are willing to take the risk. You surrender the current seat (it goes to another student in pool), and move to next round only with higher priority options.",
            studentTip = "Warning: If you get no higher allotment in next round, you will end up with NO seat."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Seat Cancellation & Penalty Clause",
            acronym = "Forfeiture Rule",
            examType = ExamType.COMEDK,
            category = "Fee & Refund",
            shortDefinition = "Strict rule regarding refund deadlines and heavy penalties for blocking seats in final rounds.",
            detailedExplanation = "COMEDK provides a specific 'Seat Surrender / Cancellation' window before Round 3. If a student holds a seat into Round 3 and subsequently abandons it after the deadline, COMEDK levies hefty penalty charges (up to forfeiture of entire tuition fee or ₹2.6 Lakhs).",
            studentTip = "If you secure an IIT/NIT or KCET seat elsewhere, be sure to surrender your COMEDK seat before the official surrender deadline to get a full refund minus nominal admin fee."
        ),
        com.example.data.models.DictionaryTerm(
            term = "Uni-GAUGE",
            acronym = "Uni-GAUGE EMeD",
            examType = ExamType.COMEDK,
            category = "Examination",
            shortDefinition = "Joint entrance exam conducted simultaneously with COMEDK for participating private universities.",
            detailedExplanation = "Students taking the combined COMEDK + Uni-GAUGE exam can apply to both COMEDK consortium colleges and partner private universities (like Reva, Presidency, Alliance, MS Ramaiah University) using their score.",
            studentTip = "Expands your admission options into autonomous private universities across India."
        )
    )

    // KCET Choice 1-4 Rules
    val kcetChoiceRules: List<com.example.data.models.CounselingChoiceRule> = listOf(
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 1,
            name = "Choice 1: Satisfied & Accept",
            actionSummary = "100% Satisfied with allotted seat. Confirm admission and exit counseling.",
            isRetainingSeat = true,
            participatesNextRound = false,
            feePaymentRequired = true,
            description = "Candidate is satisfied with the allotted seat and does NOT wish to participate in subsequent rounds. Pay the prescribed fee via KEA challan/online, download Admission Order, and report to college before reporting deadline.",
            dosAndDonts = "DO: Download admission order immediately. DO NOT: Miss the college physical reporting deadline with original documents.",
            worstCaseScenario = "Missing the reporting date leads to automatic cancellation of the seat and forfeiture of fee."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 2,
            name = "Choice 2: Satisfied & Upgrade in Round 2",
            actionSummary = "Hold current seat as safety, while trying for HIGHER options in next round.",
            isRetainingSeat = true,
            participatesNextRound = true,
            feePaymentRequired = true,
            description = "Candidate is satisfied with the allotted seat but wishes to be considered for higher priority options in Round 2. Candidate MUST pay the prescribed fees for the allotted seat to hold it. If a higher option is allotted in Round 2, the old seat is cancelled automatically; if no upgrade occurs, the old seat is retained.",
            dosAndDonts = "DO: Pay fee on time to hold the seat. DO: Delete any higher options you wouldn't actually prefer over your current seat.",
            worstCaseScenario = "If a higher option is allotted in Round 2, you CANNOT revert back to your Round 1 seat."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 3,
            name = "Choice 3: Not Satisfied & Participate in Round 2",
            actionSummary = "Reject current allotment, surrender seat, and try for higher options in Round 2.",
            isRetainingSeat = false,
            participatesNextRound = true,
            feePaymentRequired = false,
            description = "Candidate is NOT satisfied with the allotted seat and surrenders it. The seat is released to the general pool for other candidates. Candidate participates in Round 2 only for options entered higher than the allotted option.",
            dosAndDonts = "DO: Only choose this if you are absolutely sure you will never attend the allotted college.",
            worstCaseScenario = "If no higher option is allotted in Round 2, you will be left with NO seat at all."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 4,
            name = "Choice 4: Not Satisfied & Quit",
            actionSummary = "Reject allotment and exit completely from KEA counseling.",
            isRetainingSeat = false,
            participatesNextRound = false,
            feePaymentRequired = false,
            description = "Candidate is NOT satisfied with the allotted seat and has no interest in participating in further rounds of KEA engineering counseling. The allotted seat is cancelled and candidate is removed from counseling database.",
            dosAndDonts = "DO: Choose this only if you already secured admission elsewhere (e.g., IIT, NIT, BITS, private university).",
            worstCaseScenario = "You cannot re-enter regular KEA engineering rounds after selecting Choice 4."
        )
    )

    // COMEDK Choice 1-4 Rules
    val comedkChoiceRules: List<com.example.data.models.CounselingChoiceRule> = listOf(
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 1,
            name = "Accept & Freeze (Choice 1)",
            actionSummary = "Accept allotted seat, pay total tuition fee (~₹2.6L), and report to college.",
            isRetainingSeat = true,
            participatesNextRound = false,
            feePaymentRequired = true,
            description = "You are satisfied with the allotted college and branch. You must pay the total annual tuition fee online, print your confirmation slip, and report to the college with original documents before the reporting deadline.",
            dosAndDonts = "DO: Complete online payment before the bank session closes. DO NOT: Miss the physical reporting date.",
            worstCaseScenario = "Missing college reporting date results in seat cancellation and fee penalty."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 2,
            name = "Accept & Upgrade (Choice 2)",
            actionSummary = "Hold current seat by paying tuition fee while competing for higher choices in next round.",
            isRetainingSeat = true,
            participatesNextRound = true,
            feePaymentRequired = true,
            description = "You are satisfied with current allotment but want to upgrade to higher priority options in Round 2 / Round 3. You MUST pay the full tuition fee to lock your seat. If upgraded in the next round, the previous seat is released and fee is adjusted; if not upgraded, your existing seat is 100% secure.",
            dosAndDonts = "DO: Review and delete unwanted higher options during Tatkal choice editing. DO: Ensure sufficient bank card/netbanking transaction limit for ₹2.6L payment.",
            worstCaseScenario = "If a higher option gets allotted, you cannot demand your earlier seat back."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 3,
            name = "Reject & Upgrade (Choice 3)",
            actionSummary = "Reject current seat without paying full tuition, and move to next round for higher options.",
            isRetainingSeat = false,
            participatesNextRound = true,
            feePaymentRequired = false,
            description = "You are not satisfied with the allotted seat and wish to surrender it. You do not pay the full tuition fee. You move to the next round with all options placed higher than the rejected option.",
            dosAndDonts = "DO: Assess whether you have realistic chances in higher options before rejecting.",
            worstCaseScenario = "If you don't get any allotment in Round 2, you have lost your Round 1 seat completely."
        ),
        com.example.data.models.CounselingChoiceRule(
            choiceNumber = 4,
            name = "Reject & Withdraw / Exit (Choice 4)",
            actionSummary = "Reject allotment, do not pay fee, and exit completely from COMEDK counseling.",
            isRetainingSeat = false,
            participatesNextRound = false,
            feePaymentRequired = false,
            description = "You reject the allotted seat and exit from COMEDK engineering counseling entirely. You will not be considered in any further rounds of COMEDK.",
            dosAndDonts = "DO: Use this if you received a preferred seat in KCET, JoSAA (IIT/NIT), or BITS.",
            worstCaseScenario = "Exiting cannot be undone once confirmed."
        )
    )
}
