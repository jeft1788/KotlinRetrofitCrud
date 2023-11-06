package ir.almasapps.kotlinretrofitcrud

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.API.Service
import ir.almasapps.kotlinretrofitcrud.Model.ResponseResult
//import kotlinx.android.synthetic.main.activity_insert.*
//import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertActivity : AppCompatActivity(), View.OnClickListener {
    var strColor = "#FFEBEE"
    lateinit var progressDialog: ProgressDialog

    //lateinit var insert_txtTitle: EditText
    //lateinit var insert_txtNote: EditText
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        progressDialog = ProgressDialog(this)

        val insert_txtTitle = findViewById<TextView>(R.id.insert_txtTitle)
        val insert_txtNote = findViewById<TextView>(R.id.insert_txtNote)
        val insert_btnInsert = findViewById<Button>(R.id.insert_btnInsert)
        val img1 = findViewById<ImageView>(R.id.img1)
        val img2 = findViewById<ImageView>(R.id.img2)
        val img3 = findViewById<ImageView>(R.id.img3)
        val img4 = findViewById<ImageView>(R.id.img4)
        val img5 = findViewById<ImageView>(R.id.img5)
        val img6 = findViewById<ImageView>(R.id.img6)
        val img7 = findViewById<ImageView>(R.id.img7)
        val img8 = findViewById<ImageView>(R.id.img8)
        val img9 = findViewById<ImageView>(R.id.img9)
        val img10 = findViewById<ImageView>(R.id.img10)
        img1.setOnClickListener(this)
        img2.setOnClickListener(this)
        img3.setOnClickListener(this)
        img4.setOnClickListener(this)
        img5.setOnClickListener(this)
        img6.setOnClickListener(this)
        img7.setOnClickListener(this)
        img8.setOnClickListener(this)
        img9.setOnClickListener(this)
        img10.setOnClickListener(this)

        insert_btnInsert.setOnClickListener {

            insertUser(insert_txtTitle.text.toString(), insert_txtNote.text.toString(), strColor)
            progressDialog.setMessage("Insertando ... \n Por Favor Esepre ...")
            progressDialog.show()


        }
    }
    private fun insertUser(username: String, password: String, email: String) {
        val apiInterface: Service = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.insertUser(username, password, email)
        call.enqueue(object : Callback<ResponseResult?> {
            override fun onResponse(
                call: Call<ResponseResult?>,
                response: Response<ResponseResult?>
            ) {
                progressDialog.dismiss()
                finish()
            }

            override fun onFailure(call: Call<ResponseResult?>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getColor(view: View){
        val insert_txtTitle = findViewById<TextView>(R.id.insert_txtTitle)
        val insert_txtNote = findViewById<TextView>(R.id.insert_txtNote)
        val viewColor = view.background as ColorDrawable
        val colorId = viewColor.color
        strColor = String.format("#%06X", 0xFFFFFF and colorId)
        insert_txtTitle.setBackgroundColor(colorId)
        insert_txtNote.setBackgroundColor(colorId)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.img1-> getColor(v)
            R.id.img2-> getColor(v)
            R.id.img3-> getColor(v)
            R.id.img4-> getColor(v)
            R.id.img5-> getColor(v)
            R.id.img6-> getColor(v)
            R.id.img7-> getColor(v)
            R.id.img8-> getColor(v)
            R.id.img9-> getColor(v)
            R.id.img10-> getColor(v)
        }
    }
}