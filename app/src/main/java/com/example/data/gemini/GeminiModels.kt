package com.example.data.gemini

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

enum class AiModelChoice(val modelId: String, val displayName: String, val badge: String) {
    FLASH("gemini-3.5-flash", "Gemini 3.5 Flash", "Ultra-Fast & Smart"),
    PRO("gemini-3.1-pro-preview", "Gemini 3.1 Pro", "Deep Reasoning & Strategy"),
    FLASH_LITE("gemini-3.1-flash-lite-preview", "Gemini Flash Lite", "Instant Response")
}

enum class MessageSender {
    USER, AI, SYSTEM
}

data class WebSource(
    val title: String?,
    val uri: String?
)

data class MapSource(
    val placeName: String?,
    val address: String?
)

data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val sender: MessageSender,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val imageBase64: String? = null,
    val searchSources: List<WebSource> = emptyList(),
    val mapSources: List<MapSource> = emptyList(),
    val isStreaming: Boolean = false
)

// REST API Request / Response schemas for Gemini API

@JsonClass(generateAdapter = true)
data class GeminiGenerateContentRequest(
    val contents: List<Content>,
    val systemInstruction: Content? = null,
    val tools: List<Tool>? = null,
    val generationConfig: GenerationConfig? = null
)

@JsonClass(generateAdapter = true)
data class Content(
    val role: String? = null,
    val parts: List<Part>
)

@JsonClass(generateAdapter = true)
data class Part(
    val text: String? = null,
    val inlineData: InlineData? = null
)

@JsonClass(generateAdapter = true)
data class InlineData(
    val mimeType: String,
    val data: String
)

@JsonClass(generateAdapter = true)
data class Tool(
    val googleSearch: GoogleSearchTool? = null,
    val googleMaps: GoogleMapsTool? = null
)

@JsonClass(generateAdapter = true)
class GoogleSearchTool

@JsonClass(generateAdapter = true)
class GoogleMapsTool

@JsonClass(generateAdapter = true)
data class GenerationConfig(
    val temperature: Float? = null,
    val maxOutputTokens: Int? = null,
    val responseMimeType: String? = null
)

@JsonClass(generateAdapter = true)
data class GeminiGenerateContentResponse(
    val candidates: List<Candidate>?,
    val promptFeedback: PromptFeedback?,
    val usageMetadata: UsageMetadata?
)

@JsonClass(generateAdapter = true)
data class Candidate(
    val content: Content?,
    val finishReason: String?,
    val groundingMetadata: GroundingMetadata?
)

@JsonClass(generateAdapter = true)
data class GroundingMetadata(
    val webSearchQueries: List<String>?,
    val searchEntryPoint: SearchEntryPoint?,
    val groundingChunks: List<GroundingChunk>?
)

@JsonClass(generateAdapter = true)
data class SearchEntryPoint(
    val renderedContent: String?
)

@JsonClass(generateAdapter = true)
data class GroundingChunk(
    val web: WebChunk?,
    val maps: MapsChunk?
)

@JsonClass(generateAdapter = true)
data class WebChunk(
    val uri: String?,
    val title: String?
)

@JsonClass(generateAdapter = true)
data class MapsChunk(
    val uri: String?,
    val title: String?,
    val address: String?
)

@JsonClass(generateAdapter = true)
data class PromptFeedback(
    val blockReason: String?
)

@JsonClass(generateAdapter = true)
data class UsageMetadata(
    val promptTokenCount: Int?,
    val candidatesTokenCount: Int?,
    val totalTokenCount: Int?
)
