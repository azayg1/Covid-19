package `in`.company.covid_19.repository.api.network



enum class Status {
    SUCCESS,
    ERROR,
    LOADING;

    fun isSuccessful() = this == SUCCESS
    fun isLoading() = this == LOADING
    fun isError() = this == ERROR
}