package com.example.guia08app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
//import com.google.android.material.float

class MainActivity : AppCompatActivity(), AdaptadorPersona.OnPersonaAction {

    private lateinit var dbRef: DatabaseReference
    private lateinit var adapter: AdaptadorPersona
    private lateinit var tvVacio: TextView
    private lateinit var rvPersonas: RecyclerView
    private lateinit var fabAgregar: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbRef = FirebaseDatabase.getInstance().reference.child("personas")
        tvVacio = findViewById(R.id.tvVacio)
        rvPersonas = findViewById(R.id.rvPersonas)
        fabAgregar = findViewById(R.id.fabAgregar)
        adapter = AdaptadorPersona(mutableListOf(), this)
        rvPersonas.layoutManager = LinearLayoutManager(this)
        rvPersonas.adapter = adapter

        fabAgregar.setOnClickListener {
            val intent = Intent(this, AddPersonaActivity::class.java)
            startActivity(intent)
        }

        escucharCambios()

    }

    private fun escucharCambios() {

        dbRef.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val lista = mutableListOf<Persona>()
                for (hijo in snapshot.children) {
                    val persona = hijo.getValue(Persona::class.java)
                    if (persona != null) lista.add(persona)
                }
                adapter.setData(lista)
                tvVacio.visibility = if (lista.isEmpty()) TextView.VISIBLE else TextView.GONE
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Error: ${error.message}",
                    Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onEditar(persona: Persona) {

        val intent = Intent(this, AddPersonaActivity::class.java)
        intent.putExtra(AddPersonaActivity.EXTRA_ID, persona.id)
        intent.putExtra(AddPersonaActivity.EXTRA_NOMBRE, persona.nombre)
        intent.putExtra(AddPersonaActivity.EXTRA_DUI, persona.dui)
        startActivity(intent)

    }

    override fun onEliminar(persona: Persona) {

        AlertDialog.Builder(this)
            .setTitle(R.string.eliminar)
            .setMessage("¿Eliminar a ${persona.nombre}?")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                persona.id?.let { dbRef.child(it).removeValue() }
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()

    }

}