package com.example.myapplicationpractica04permisos02dsm104la181955

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

private val TAG = "Practica 04 - Permisos"
private val CODIGOSOLICITUDGRABAR = 101

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarPermisos()
    }

    private fun configurarPermisos() {
        val permission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permiso denegado para grabar")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.RECORD_AUDIO)) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Permiso para acceder al micrófono es requerido por esta App para grabar audio.").setTitle("Permission required")
                builder.setPositiveButton("OK") {
                    dialog, id -> Log.i(TAG, "Seleccionado")
                    hacerSolicitudPermiso()
                }
                val dialog = builder.create()
                dialog.show()
            } else {
                hacerSolicitudPermiso()
            }
        }
    }

    private fun hacerSolicitudPermiso() {
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.RECORD_AUDIO), CODIGOSOLICITUDGRABAR)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            CODIGOSOLICITUDGRABAR -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permiso ha sido denegado por el usuario.")
                    Toast.makeText(applicationContext, "Permiso ha sido denegado por el usuario", Toast.LENGTH_SHORT).show()
                    /*Escribir código de permiso denegado, según se requiera*/
                } else {
                    Log.i(TAG, "Permiso concedido por el usuario")
                    Toast.makeText(applicationContext, "Permiso concedido por el usuario", Toast.LENGTH_SHORT).show()
                    /*Escribir código de permiso concedido, según se requiera*/
                }
            }
        }
    }
}