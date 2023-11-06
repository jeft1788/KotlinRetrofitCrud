package ir.almasapps.kotlinretrofitcrud

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ir.almasapps.kotlinretrofitcrud.API.Client
import ir.almasapps.kotlinretrofitcrud.API.Service
import ir.almasapps.kotlinretrofitcrud.Adapter.RecyclerViewAdapter
import ir.almasapps.kotlinretrofitcrud.Model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog :ProgressDialog
    lateinit var recyclerView: RecyclerView
    val listaNotas: MutableList<Note> = ArrayList()
    lateinit var mAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnInsert = findViewById<FloatingActionButton>(R.id.btnInsert)
        recyclerView = findViewById(R.id.recyclerView)
        progressDialog = ProgressDialog(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter  = RecyclerViewAdapter(this, listaNotas )
        recyclerView.adapter = mAdapter
        btnInsert.setOnClickListener {
            startActivity(Intent(baseContext, InsertActivity::class.java))
        }

        fetchInformation()
    }

    private fun fetchInformation() {
        val apiInterface: Service = Client().getApiClient()!!
        val call: Call<List<Note>> = apiInterface.getNote()
        call.enqueue(object : Callback<List<Note>> {

            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                progressDialog.dismiss()
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                var noteList = response.body()
                recyclerView.adapter = noteList?.let { RecyclerViewAdapter(this@MainActivity, it) }
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                progressDialog.dismiss()
                Log.e("zzzzzzzzzz", t.toString())
                Toast.makeText(baseContext, t.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            progressDialog.setMessage("Cargando Datos ... \n Por favor Espera ...")
            progressDialog.show()
            fetchInformation()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        progressDialog.setMessage("Cargando Datos ... \n Por favor Espera ...")
        progressDialog.show()
        fetchInformation()
    }
}

