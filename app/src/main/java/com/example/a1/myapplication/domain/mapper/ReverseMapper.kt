package ru.napoleonit.oas.domain.model.mapper

/**
 * @author Ilyas Shafigin
 * @since 13.03.17
 */
interface ReverseMapper<From, To> {

    fun reverseMap(from: To): From

    fun reverseMap(fromList: List<To>): List<From> {
        return fromList.map { from -> reverseMap(from) }
    }

}