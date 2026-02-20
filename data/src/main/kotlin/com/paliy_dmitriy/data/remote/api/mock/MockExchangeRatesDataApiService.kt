package com.paliy_dmitriy.data.remote.api.mock

import com.paliy_dmitriy.data.remote.api.ExchangeRatesDataApiService
import com.paliy_dmitriy.data.remote.model.response.QuoteResponseDto
import com.paliy_dmitriy.data.remote.model.response.SymbolResponseDto
import retrofit2.Response

class MockExchangeRatesDataApiService : ExchangeRatesDataApiService {

  private var requestCounter = 0

  override suspend fun getQuotes(
    date: String,
    base: String,
    symbols: String?
  ): Response<QuoteResponseDto> {
    requestCounter++

    return when (requestCounter % 20) {
      1 -> getSuccessResponse1(base, date)
      2 -> getSuccessResponse2(base, date)
      3 -> getSuccessResponse3(base, date)
      4 -> getSuccessResponse4(base, date)
      5 -> getSuccessResponse5(base, date)
      6 -> getSuccessResponse6(base, date)
      7 -> getSuccessResponse7(base, date)
      8 -> getSuccessResponse8(base, date)
      9 -> getSuccessResponse9(base, date)
      10 -> getSuccessResponse10(base, date)
      11 -> getSuccessResponse11(base, date)
      12 -> getSuccessResponse12(base, date)
      13 -> getSuccessResponse13(base, date)
      14 -> getSuccessResponse14(base, date)
      15 -> getSuccessResponse15(base, date)
      16 -> getSuccessResponse16(base, date)
      17 -> getSuccessResponse17(base, date)
      18 -> getSuccessResponse18(base, date)
      19 -> getSuccessResponse19(base, date)
      0 -> getSuccessResponse20(base, date)
      else -> getSuccessResponse1(base, date)
    }
  }

  override suspend fun getSymbols(): Response<SymbolResponseDto> {
    return Response.success(
      SymbolResponseDto(
        success = true,
        symbols = mapOf(
          "AED" to "United Arab Emirates Dirham",
          "AFN" to "Afghan Afghani",
          "ALL" to "Albanian Lek",
          "AMD" to "Armenian Dram",
          "ANG" to "Netherlands Antillean Guilder",
          "AOA" to "Angolan Kwanza",
          "ARS" to "Argentine Peso",
          "AUD" to "Australian Dollar",
          "AWG" to "Aruban Florin",
          "AZN" to "Azerbaijani Manat",
          "BAM" to "Bosnia-Herzegovina Convertible Mark",
          "BBD" to "Barbadian Dollar",
          "BDT" to "Bangladeshi Taka",
          "BGN" to "Bulgarian Lev",
          "BHD" to "Bahraini Dinar",
          "BIF" to "Burundian Franc",
          "BMD" to "Bermudian Dollar",
          "BND" to "Brunei Dollar",
          "BOB" to "Bolivian Boliviano",
          "BRL" to "Brazilian Real",
          "BSD" to "Bahamian Dollar",
          "BTN" to "Bhutanese Ngultrum",
          "BWP" to "Botswana Pula",
          "BYN" to "Belarusian Ruble",
          "BZD" to "Belize Dollar",
          "CAD" to "Canadian Dollar",
          "CDF" to "Congolese Franc",
          "CHF" to "Swiss Franc",
          "CLP" to "Chilean Peso",
          "CNY" to "Chinese Yuan"
        )
      )
    )
  }

  private fun getSuccessResponse1(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.85,
          "GBP" to 0.73,
          "JPY" to 110.25,
          "CAD" to 1.25,
          "AUD" to 1.35,
          "CHF" to 0.92,
          "CNY" to 6.45,
          "RUB" to 75.50,
          "KZT" to 425.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse2(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.88,
          "GBP" to 0.76,
          "JPY" to 112.50,
          "CAD" to 1.28,
          "AUD" to 1.38,
          "CHF" to 0.95,
          "CNY" to 6.52,
          "RUB" to 78.20,
          "KZT" to 435.0,
          "TRY" to 13.50,
          "MXN" to 20.30
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse3(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.82,
          "GBP" to 0.70,
          "JPY" to 108.75,
          "CAD" to 1.22,
          "AUD" to 1.32,
          "CHF" to 0.89,
          "CNY" to 6.38,
          "RUB" to 72.80,
          "KZT" to 415.0,
          "INR" to 74.50,
          "BRL" to 5.20,
          "ZAR" to 15.80
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse4(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.91,
          "GBP" to 0.79,
          "JPY" to 115.30,
          "CAD" to 1.31,
          "AUD" to 1.41,
          "CHF" to 0.98,
          "CNY" to 6.58,
          "RUB" to 79.90,
          "KZT" to 445.0,
          "KRW" to 1200.0,
          "SGD" to 1.35,
          "NZD" to 1.48,
          "SEK" to 8.90
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse5(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.87,
          "GBP" to 0.75,
          "JPY" to 109.90,
          "CAD" to 1.26,
          "AUD" to 1.36,
          "CHF" to 0.93,
          "CNY" to 6.42,
          "RUB" to 74.30,
          "KZT" to 420.0,
          "UAH" to 27.50,
          "PLN" to 3.90,
          "CZK" to 21.50,
          "HUF" to 310.0,
          "ILS" to 3.20
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse6(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.84,
          "GBP" to 0.72,
          "JPY" to 107.80,
          "CAD" to 1.23,
          "AUD" to 1.33,
          "CHF" to 0.90,
          "CNY" to 6.35,
          "RUB" to 71.50,
          "KZT" to 410.0,
          "AED" to 3.67,
          "SAR" to 3.75,
          "EGP" to 15.70,
          "QAR" to 3.64,
          "KWD" to 0.30
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse7(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.89,
          "GBP" to 0.77,
          "JPY" to 111.40,
          "CAD" to 1.27,
          "AUD" to 1.37,
          "CHF" to 0.94,
          "CNY" to 6.48,
          "RUB" to 76.80,
          "KZT" to 430.0,
          "VND" to 22700.0,
          "THB" to 33.50,
          "MYR" to 4.20,
          "IDR" to 14300.0,
          "PHP" to 51.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse8(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.86,
          "GBP" to 0.74,
          "JPY" to 109.20,
          "CAD" to 1.24,
          "AUD" to 1.34,
          "CHF" to 0.91,
          "CNY" to 6.40,
          "RUB" to 73.90,
          "KZT" to 418.0,
          "DKK" to 6.40,
          "NOK" to 8.80,
          "ISK" to 129.0,
          "RSD" to 104.0,
          "BGN" to 1.68
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse9(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.92,
          "GBP" to 0.80,
          "JPY" to 116.10,
          "CAD" to 1.32,
          "AUD" to 1.42,
          "CHF" to 0.99,
          "CNY" to 6.62,
          "RUB" to 80.50,
          "KZT" to 448.0,
          "ARS" to 105.0,
          "CLP" to 820.0,
          "COP" to 3900.0,
          "PEN" to 3.90,
          "UYU" to 43.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse10(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.83,
          "GBP" to 0.71,
          "JPY" to 106.90,
          "CAD" to 1.21,
          "AUD" to 1.31,
          "CHF" to 0.88,
          "CNY" to 6.32,
          "RUB" to 70.80,
          "KZT" to 408.0,
          "AFN" to 86.0,
          "PKR" to 175.0,
          "BDT" to 85.0,
          "LKR" to 200.0,
          "NPR" to 120.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse11(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.90,
          "GBP" to 0.78,
          "JPY" to 113.80,
          "CAD" to 1.29,
          "AUD" to 1.39,
          "CHF" to 0.96,
          "CNY" to 6.55,
          "RUB" to 77.50,
          "KZT" to 438.0,
          "GEL" to 3.10,
          "AMD" to 480.0,
          "AZN" to 1.70,
          "BYN" to 2.50,
          "MDL" to 17.80
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse12(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.87,
          "GBP" to 0.75,
          "JPY" to 110.50,
          "CAD" to 1.26,
          "AUD" to 1.36,
          "CHF" to 0.93,
          "CNY" to 6.45,
          "RUB" to 75.20,
          "KZT" to 425.0,
          "MNT" to 2850.0,
          "KGS" to 84.0,
          "TJS" to 11.30,
          "UZS" to 10700.0,
          "TMT" to 3.50
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse13(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.85,
          "GBP" to 0.73,
          "JPY" to 108.90,
          "CAD" to 1.25,
          "AUD" to 1.35,
          "CHF" to 0.92,
          "CNY" to 6.38,
          "RUB" to 74.10,
          "KZT" to 418.0,
          "NGN" to 415.0,
          "GHS" to 6.10,
          "KES" to 113.0,
          "TZS" to 2300.0,
          "UGX" to 3550.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse14(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.88,
          "GBP" to 0.76,
          "JPY" to 112.0,
          "CAD" to 1.28,
          "AUD" to 1.38,
          "CHF" to 0.95,
          "CNY" to 6.52,
          "RUB" to 77.80,
          "KZT" to 432.0,
          "FJD" to 2.10,
          "XPF" to 105.0,
          "TOP" to 2.25,
          "WST" to 2.55,
          "VUV" to 112.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse15(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.86,
          "GBP" to 0.74,
          "JPY" to 109.50,
          "CAD" to 1.24,
          "AUD" to 1.34,
          "CHF" to 0.91,
          "CNY" to 6.42,
          "RUB" to 73.50,
          "KZT" to 420.0,
          "JMD" to 153.0,
          "BBD" to 2.0,
          "TTD" to 6.78,
          "BSD" to 1.0,
          "BZD" to 2.01
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse16(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.91,
          "GBP" to 0.79,
          "JPY" to 114.80,
          "CAD" to 1.31,
          "AUD" to 1.41,
          "CHF" to 0.98,
          "CNY" to 6.60,
          "RUB" to 79.20,
          "KZT" to 442.0,
          "CUP" to 24.0,
          "DOP" to 56.50,
          "HTG" to 98.0,
          "PAB" to 1.0,
          "AWG" to 1.79
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse17(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.84,
          "GBP" to 0.72,
          "JPY" to 107.40,
          "CAD" to 1.22,
          "AUD" to 1.32,
          "CHF" to 0.89,
          "CNY" to 6.35,
          "RUB" to 71.80,
          "KZT" to 412.0,
          "MOP" to 8.04,
          "HKD" to 7.80,
          "TWD" to 27.80,
          "BND" to 1.34,
          "KHR" to 4070.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse18(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.89,
          "GBP" to 0.77,
          "JPY" to 111.80,
          "CAD" to 1.27,
          "AUD" to 1.37,
          "CHF" to 0.94,
          "CNY" to 6.48,
          "RUB" to 76.50,
          "KZT" to 428.0,
          "MVR" to 15.40,
          "LKR" to 198.0,
          "NPR" to 118.0,
          "BTN" to 74.0,
          "BIF" to 1990.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse19(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.87,
          "GBP" to 0.75,
          "JPY" to 110.20,
          "CAD" to 1.25,
          "AUD" to 1.35,
          "CHF" to 0.92,
          "CNY" to 6.44,
          "RUB" to 74.80,
          "KZT" to 422.0,
          "MUR" to 42.80,
          "SCR" to 13.80,
          "MGA" to 3980.0,
          "MWK" to 815.0,
          "ZMW" to 17.50
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }

  private fun getSuccessResponse20(base: String, date: String): Response<QuoteResponseDto> {
    return Response.success(
      QuoteResponseDto(
        base = base,
        date = date,
        historical = true,
        rates = mapOf(
          "USD" to 1.0,
          "EUR" to 0.92,
          "GBP" to 0.81,
          "JPY" to 116.50,
          "CAD" to 1.33,
          "AUD" to 1.43,
          "CHF" to 1.01,
          "CNY" to 6.65,
          "RUB" to 81.20,
          "KZT" to 450.0,
          "SYP" to 2510.0,
          "LBP" to 1510.0,
          "JOD" to 0.71,
          "OMR" to 0.38,
          "YER" to 250.0
        ),
        success = true,
        timestamp = System.currentTimeMillis() / 1000
      )
    )
  }
}