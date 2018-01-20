package com.example.a1.myapplication.domain.mapper

import ru.napoleonit.oas.domain.model.mapper.ReverseMapper

/**
 * @author Ilyas Shafigin
 * @since 13.03.17
 */
interface TwoWayMapper<From, To> : Mapper<From, To>, ReverseMapper<From, To>