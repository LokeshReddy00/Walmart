import com.lokesh.walmart.Models.Capital
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CapitalRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val countryService = retrofit.create(CountryService::class.java)

    fun fetchCountries(callback: (List<Capital>?) -> Unit) {
        countryService.getCountries().enqueue(object : retrofit2.Callback<List<Capital>> {
            override fun onResponse(call: Call<List<Capital>>, response: retrofit2.Response<List<Capital>>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<List<Capital>>, t: Throwable) {
                callback(null)
            }
        })
    }
}
