package com.example.recipeapprecreate

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_book_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetails : AppCompatActivity() {
    val apiInterface=ApiClint().getClient()?.create(APIInterface::class.java)
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        btnSave.setOnClickListener {
            var title=edTitle.text.toString()
            var author=edAuthor.text.toString()
            var ingredients=edIngredients.text.toString()
            var instructions=edInstructions.text.toString()
            var book= DataBookItem(author,ingredients,instructions,title)
            addBook(book,onResult = {
                edTitle.setText("")
                edAuthor.setText("")
                edIngredients.setText("")
                edInstructions.setText("")
                Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show()
            })
        }
    }


    fun addBook(Book:DataBookItem,onResult: ()->Unit) {

        funProgressDialog()
        if (apiInterface!=null){
            apiInterface.addBook(Book).enqueue(object : Callback<DataBookItem?> {
                override fun onResponse(call: Call<DataBookItem?>, response: Response<DataBookItem?>) {
                    progressDialog.dismiss()
                    onResult()
                }

                override fun onFailure(call: Call<DataBookItem?>, t: Throwable) {
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_SHORT).show();

                }
            })
        }
    }
    fun funProgressDialog(){
        progressDialog=ProgressDialog(this@BookDetails)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

    }
    fun goBack(view: android.view.View) {
        startActivity(Intent(applicationContext,MainActivity::class.java))

    }
}