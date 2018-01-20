package com.example.a1.myapplication.domain.mapper

/**
 * Author 1
 * Since 20.01.2018.
 */
interface Mapper<From, To> {

    fun map(from: From): To

    fun map(fromList: List<From>): List<To> {
        return fromList.map { from -> map(from) }
    }

}