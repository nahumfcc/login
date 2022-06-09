package com.coppel.login.ingreso

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.coppel.login.MenuActivity
import com.coppel.login.R
import com.coppel.login.entities.UserLoginRequest
import com.coppel.login.entities.UserModel
import com.coppel.login.service.StructureResponse
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViewModel()
        btnLogin.setOnClickListener {
            hideKeyboard()
            loginUser()
        }
    }
    private fun loginUser(){
        val request = UserLoginRequest(etUsuario.text.toString(), etPassword.text.toString())
        viewModel.logginUser(request)
    }
    private fun initViewModel(){
        txtResult.text = ""
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.getLoggedUserObserver().observe(this, Observer <StructureResponse<UserModel>?>{
            if (it==null){
                Toast.makeText(this@LoginActivity, "Failed to loggin user", Toast.LENGTH_LONG).show()
            }
            else{
                //guardar sesión
                Toast.makeText(this@LoginActivity, "Succesfully loggin user", Toast.LENGTH_LONG).show()
                txtResult.text ="Bienvenido ${it.data.usuario} : ${it.data.nombre} ${it.data.apellidoPaterno} ${it.data.apellidoMaterno}"
                //intent = Intent(this, NombreModuloActivity::class.java)
                startActivity(Intent(this, NombreModuloActivity::class.java))
            }
        })
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(loginviewroot.windowToken, 0)
    }
}