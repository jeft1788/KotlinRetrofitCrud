package ir.almasapps.kotlinretrofitcrud

//import kotlinx.android.synthetic.main.activity_update.*
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.Model.Note
import ir.almasapps.kotlinretrofitcrud.Model.ResponseResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity(), View.OnClickListener  {
    lateinit var progressDialog: ProgressDialog
    private lateinit var strId: String
    private lateinit var strColor:String

    lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        val update_txtNote = findViewById<EditText>(R.id.update_txtNote)
        val update_txtTitle = findViewById<EditText>(R.id.update_txtTitle)
        progressDialog = ProgressDialog(this)
        progressDialog = ProgressDialog(this)
        val update_btnUpdate = findViewById<Button>(R.id.update_btnUpdate)
        val update_btnDelete = findViewById<Button>(R.id.update_btnDelete)
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
        var note = Note(
            intent.getStringExtra("itemId").toString(),
            intent.getStringExtra("itemTitle").toString(),
            intent.getStringExtra("itemNote").toString(),
            intent.getStringExtra("itemColor").toString(),
            "")


        strColor = intent.getStringExtra("itemColor").toString()

        strId = note.getId()

        update_txtTitle.setText(note.getTitle())

        update_txtNote.setText(note.getNote())
        update_txtTitle.setBackgroundColor(Color.parseColor(strColor))
        update_txtNote.setBackgroundColor(Color.parseColor(strColor))



        update_btnUpdate.setOnClickListener {
            updateUser(strId, update_txtTitle.text.toString(), update_txtNote.text.toString(), strColor)
            progressDialog.setMessage("Updating ... \n Please wait ...")
            progressDialog.show()
        }


        update_btnDelete.setOnClickListener {
            deleteUser(note.getId())
            progressDialog.setMessage("Deleting ... \n Please wait ...")
            progressDialog.show()
        }

    }


    private fun updateUser(id: String, title: String, note: String, color: String) {
        var apiInterface = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.updateUser(id, title, note, color)
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
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteUser(id: String) {
        var apiInterface = Client().getApiClient()!!
        val call: Call<ResponseResult> = apiInterface.deletetUser(id)
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
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getColor(view: View) {
        val update_txtNote = findViewById<EditText>(R.id.update_txtNote)
        val update_txtTitle = findViewById<EditText>(R.id.update_txtTitle)
        val viewColor = view.background as ColorDrawable
        val colorId = viewColor.color
        strColor = String.format("#%06X", 0xFFFFFF and colorId)
        update_txtTitle.setBackgroundColor(colorId)
        update_txtNote.setBackgroundColor(colorId)
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