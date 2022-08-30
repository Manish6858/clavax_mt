package com.newupdate.activity

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.android.volley.AuthFailureError
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mahindra.database.AppDatabase
import com.newupdate.common.Constants
import com.newupdate.model.PincodeItemModel
import org.json.JSONException
import org.json.JSONObject


class MainViewModel(application : Application) : AndroidViewModel(application) {

    private var pincode_list : MutableList<PincodeItemModel> = ArrayList()
    private var pincodelist: MutableLiveData<List<PincodeItemModel>>? = null


    fun getPinCodes(): MutableLiveData<List<PincodeItemModel>>? {
        if(pincodelist==null){
            pincodelist = MutableLiveData<List<PincodeItemModel>>()
            loadPincodes()
        }
         return pincodelist

    }

    val db = Room.databaseBuilder(
        getApplication(),
        AppDatabase::class.java, "zipcodes"
    ).build()
    val pincodeDao = db.pincodeDao()


    private fun loadPincodes() {
        // do async operation to fetch users

        val stringRequest: StringRequest = object : StringRequest(
            Method.POST, Constants.BASE_URL + "zipcodes/",
            com.android.volley.Response.Listener { response: String? ->
                /*Toast.makeText(
                    getApplication(), response.toString(),
                    Toast.LENGTH_SHORT
                ).show()*/

                try {
                    val jsonObject = JSONObject(response)

                        pincodeDao.deleteAll()
                        val jsonArray = jsonObject.getJSONObject("data").getJSONArray("list")
                        for (i in 0 until jsonArray.length()) {



                            // Save data using your Model
                            pincode_list.add(PincodeItemModel(
                                jsonArray.getJSONObject(i).getInt("id"),
                                jsonArray.getJSONObject(i).getInt("zipcode"),

                                ))


                            pincodeDao.insertAll(PincodeItemModel(
                                jsonArray.getJSONObject(i).getInt("id"),
                                jsonArray.getJSONObject(i).getInt("zipcode"),

                                ))
                            pincodelist!!.value = pincode_list

                            // Notify the adapter
                        }



                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                //You can handle error here if you want
                Toast.makeText(getApplication(), error.toString(), Toast.LENGTH_LONG)
                    .show()
                //  Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_LONG).show();
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers: MutableMap<String, String> = HashMap()
                headers["Source"] = "100f8bcd4626d373cade4e832633b5f7"
                headers["Authorization"] = "ANDROID"
                return headers
            }
        }


        stringRequest.retryPolicy = DefaultRetryPolicy(
            0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        //Adding the string request to the queue
        //Adding the string request to the queue
        var requestQueue = Volley.newRequestQueue(getApplication())
        requestQueue.cache.clear()
        /*val cache = DiskBasedCache(lruCache(), 16 * 1024 * 1024)
        requestQueue = RequestQueue(cache, BasicNetwork(HurlStack()))*/
        requestQueue.start()
        requestQueue.add(stringRequest)

    }




}