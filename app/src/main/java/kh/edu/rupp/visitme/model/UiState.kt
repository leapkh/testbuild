package kh.edu.rupp.visitme.model

data class UiState<T>(
    val status: UiStateStatus,
    val message: String? = null,
    val data: T? = null
)

enum class UiStateStatus {
    loading, success, error
}