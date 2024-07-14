package com.maverick.groww.utlis

import com.maverick.groww.utlis.Constants.BASE_URL

object URLs {
    const val GET_TOP_GAINER_LOSERS = BASE_URL + "query?function=TOP_GAINERS_LOSERS"
    const val GET_COMPANY_DETAIL = BASE_URL + "query?function=OVERVIEW"
    const val GET_SEARCHED_STOCK = BASE_URL + "query?function=SYMBOL_SEARCH"
}