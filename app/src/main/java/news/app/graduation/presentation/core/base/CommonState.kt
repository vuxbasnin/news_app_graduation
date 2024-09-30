package news.app.graduation.presentation.core.base

sealed class CommonState<T> private constructor() {
    class Success<T>(val data: T) : CommonState<T>()
    class Loading<T> : CommonState<T>()
    class Fail<T>(val msg: String = "Có lỗi xảy ra") : CommonState<T>()
}