package com.bignerbranch.android.zd_3_2_demin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.OkHttp
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TV_5 : AppCompatActivity() {
    fun CloseFrag(view: View)
    {
        val fragment = supportFragmentManager.findFragmentByTag("settings")
        if (fragment != null && fragment.isAdded) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commit()
        }

    }
    data class Quests(val image: String,val title: String,val text: String)

    class MyObj{
        var list = arrayListOf<Quests>()

    }

    class QuestRecycler(
        val context: Context,
        val list: List<Quests>,
        private val onItemClicked: (Quests) -> Unit // ← колбэк
    ) : RecyclerView.Adapter<QuestRecycler.MyVH>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestRecycler.MyVH {
        val root = LayoutInflater.from(context).inflate(R.layout.recycitem,parent,false)
            return MyVH(root)
        }
        class MyVH(val itemView: View): RecyclerView.ViewHolder(itemView){
            val imageView: ImageView=itemView.findViewById(R.id.image_quest)
            val title: TextView=itemView.findViewById(R.id.title_quest)
            val descr: TextView=itemView.findViewById(R.id.desc)

        }

        override fun onBindViewHolder(holder: MyVH, position: Int) {
            val quest = list[position]
            Picasso.get().load(quest.image).into(holder.imageView)
            holder.title.text = quest.title
            holder.descr.setOnClickListener {
                onItemClicked(quest)
            }
            holder.itemView.setOnClickListener {
                onItemClicked(quest)
            }
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }
    private  suspend fun connectByName(name: String):String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie/search?page=1&limit=10&query=${name}")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Такого фильма нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }

    private  suspend fun connect():String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie?page=1&limit=20&notNullFields=name&notNullFields=description")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Очень нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }

    private  suspend fun connectDram():String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie?page=1&limit=20&genres.name=драма&notNullFields=name&notNullFields=description")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Очень нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }
    private  suspend fun connectCom():String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie?page=1&limit=20&genres.name=комедия&notNullFields=name&notNullFields=description")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Очень нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }
    private  suspend fun connectMel():String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie?page=1&limit=20&genres.name=мелодрама&notNullFields=name&notNullFields=description")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Очень нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }
    private  suspend fun connectHor():String{
        return withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url( "https://api.poiskkino.dev/v1.4/movie?page=1&limit=20&genres.name=ужасы&notNullFields=name&notNullFields=description")
                    .addHeader("X-API-KEY", "JVGWW5W-QMG4RD1-M15XJCS-9RM4R32")
                    .build()
                val client = OkHttpClient()
                client.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) {
                        throw Exception("HTTP error ${response.code}")

                    }
                    response.body?.string() ?: throw Exception("Empety resonse")
                }
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Очень нет",Snackbar.LENGTH_SHORT).show()
                Log.e("API_ERROR","Connection Failed",e)
                "Error: ${e.message}"
            }
        }
    }

    fun hasJsonField(jsonString: JSONObject, fieldName: String): Boolean {
        return try {
            val jsonObject = jsonString
            jsonObject.has(fieldName)
        } catch (e: Exception) {
            false
        }
    }

    lateinit var spin: Spinner
    lateinit var filmname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tv5)
        val rec: RecyclerView=findViewById(R.id.recyclers)
        spin = findViewById(R.id.spinn)
        filmname=findViewById(R.id.found)
        var q = MyObj().list
        val items = mutableListOf("ужасы","мелодрама","комедия","драма","обычный")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        )
        spin.adapter=adapter
        spin.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                q.clear()
                if(selectedItem=="ужасы"){
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var a =10
                            val result = connectHor()
                            val obj = JSONObject(result)
                            val main:JSONArray = obj.getJSONArray("docs")
                            for(i in 0..a) {

                                val name = main.getJSONObject(i).getString("name")
                                val desc = main.getJSONObject(i).getString("description")
                                if(hasJsonField(main.getJSONObject(i),"poster")) {
                                    val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                                   //q.add(Quests(imgs, name, desc))
                                }
                                else{
                                    if(a<20)
                                        a++;

                                }
                            }
                            rec.layoutManager= GridLayoutManager(this@TV_5,3)
                            rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                                    .addToBackStack("settings")
                                    .commit()
                            }
                            Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                if(selectedItem=="мелодрама"){
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var a =10
                            val result = connectMel()
                            val obj = JSONObject(result)
                            val main:JSONArray = obj.getJSONArray("docs")
                            for(i in 0..a) {

                                val name = main.getJSONObject(i).getString("name")
                                val desc = main.getJSONObject(i).getString("description")
                                if(hasJsonField(main.getJSONObject(i),"poster")) {
                                    val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                                    q.add(Quests(imgs, name, desc))
                                }
                                else{
                                    if(a<20)
                                        a++;

                                }
                            }
                            rec.layoutManager= GridLayoutManager(this@TV_5,3)
                            rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                                    .addToBackStack("settings")
                                    .commit()
                            }
                            Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                if(selectedItem=="комедия"){
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var a =10
                            val result = connectCom()
                            val obj = JSONObject(result)
                            val main:JSONArray = obj.getJSONArray("docs")
                            for(i in 0..a) {

                                val name = main.getJSONObject(i).getString("name")
                                val desc = main.getJSONObject(i).getString("description")
                                if(hasJsonField(main.getJSONObject(i),"poster")) {
                                    val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                                    q.add(Quests(imgs, name, desc))
                                }
                                else{
                                    if(a<20)
                                        a++;

                                }
                            }
                            rec.layoutManager= GridLayoutManager(this@TV_5,3)
                            rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                                    .addToBackStack("settings")
                                    .commit()
                            }
                            Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                if(selectedItem=="драма"){
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var a =10
                            val result = connectDram()
                            val obj = JSONObject(result)
                            val main:JSONArray = obj.getJSONArray("docs")
                            for(i in 0..a) {

                                val name = main.getJSONObject(i).getString("name")
                                val desc = main.getJSONObject(i).getString("description")
                                if(hasJsonField(main.getJSONObject(i),"poster")) {
                                    val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                                    q.add(Quests(imgs, name, desc))
                                }
                                else{
                                    if(a<20)
                                        a++;

                                }
                            }
                            rec.layoutManager= GridLayoutManager(this@TV_5,3)
                            rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                                    .addToBackStack("settings")
                                    .commit()
                            }
                            Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                if(selectedItem=="обычный"){
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var a =10
                            val result = connect()
                            val obj = JSONObject(result)
                            val main:JSONArray = obj.getJSONArray("docs")
                            for(i in 0..a) {

                                val name = main.getJSONObject(i).getString("name")
                                val desc = main.getJSONObject(i).getString("description")
                                if(hasJsonField(main.getJSONObject(i),"poster")) {
                                    val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                                    q.add(Quests(imgs, name, desc))
                                }
                                else{
                                    if(a<20)
                                        a++;

                                }
                            }
                            rec.layoutManager= GridLayoutManager(this@TV_5,3)
                            rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                                supportFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                                    .addToBackStack("settings")
                                    .commit()
                            }
                            Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                println("Ничего не выбрано")
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            try {
                var a =10
                val result = connectCom()
                val obj = JSONObject(result)
                val main:JSONArray = obj.getJSONArray("docs")
                for(i in 0..a) {

                    val name = main.getJSONObject(i).getString("name")
                    val desc = main.getJSONObject(i).getString("description")
                    if(hasJsonField(main.getJSONObject(i),"poster")) {
                        val imgs = main.getJSONObject(i).getJSONObject("poster").getString("url")
                        q.add(Quests(imgs, name, desc))
                    }
                    else{
                        if(a<20)
                        a++;

                    }
                }
                rec.layoutManager= GridLayoutManager(this@TV_5,3)
                rec.adapter = QuestRecycler(this@TV_5, q) { quest ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MainFragment.newInstance(quest.title, quest.text, quest.image), "settings")
                        .addToBackStack("settings")
                        .commit()
                }
                Snackbar.make(findViewById(android.R.id.content),"Всё хорошо",Snackbar.LENGTH_SHORT).show()
            }
            catch (e:Exception){
                Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun FoundFilm(view: View)
    {
        try {
            var imgs: String=""
            if (filmname.text.isNotEmpty()) {
                GlobalScope.launch(Dispatchers.Main) {
                    try {
                        val result = connectByName(filmname.text.toString())
                        val obj = JSONObject(result)
                        val main:JSONArray = obj.getJSONArray("docs")
                            val name = main.getJSONObject(0).getString("name")
                            val desc = main.getJSONObject(0).getString("description")
                            if(hasJsonField(main.getJSONObject(0),"poster")) {
                                imgs = main.getJSONObject(0).getJSONObject("poster").getString("url")
                            }
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(filmname.windowToken, 0)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, MainFragment.newInstance(name, desc, imgs), "settings")
                            .addToBackStack("settings") // опционально
                            .commit()
                    }
                    catch (e:Exception){
                        Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
                    }
                }
            } else {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Введите название фильма",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        catch(e:Exception) {
            Snackbar.make(findViewById(android.R.id.content),"Ошибка",Snackbar.LENGTH_SHORT).show()
        }
    }

}