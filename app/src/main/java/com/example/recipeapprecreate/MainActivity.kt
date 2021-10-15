package com.example.recipeapprecreate

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiInterface=ApiClint().getClient()?.create(APIInterface::class.java)
    lateinit var progressDialog:ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }

    fun fetchData(){
        funProgressDialog()
        if (apiInterface!=null){
            apiInterface.getData()?.enqueue(object : Callback<Array<DataBookItem>?> {
                override fun onResponse(call: Call<Array<DataBookItem>?>, response: Response<Array<DataBookItem>?>) {
                    progressDialog.dismiss()
                    var text=""
                    for (book in response.body()!!){
                        text+="${book.title}\n${book.author}\n${book.ingredients}\n${book.instructions}\n\n"
                    }
                    textView.text=text

                }

                override fun onFailure(call: Call<Array<DataBookItem>?>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()


                }
            })
        }
    }

    fun funProgressDialog(){
        progressDialog=ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

    }
    fun toSecondPage(view: android.view.View) {
        intent= Intent(applicationContext,BookDetails::class.java)
        startActivity(intent)
    }

}