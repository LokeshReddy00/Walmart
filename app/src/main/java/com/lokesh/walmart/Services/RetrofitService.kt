import com.lokesh.walmart.Models.Capital
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @GET("peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    fun getCountries(): Call<List<Capital>>
}