package com.example.espino.inventorykotlin


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.espino.inventorykotlin.fragments.ListProductFragment
import com.example.espino.inventorykotlin.fragments.ManageProductFragment

class MainActivity : AppCompatActivity(), ListProductFragment.ListProductListener, ManageProductFragment.ManageProductListener {


    lateinit var productFragment: ListProductFragment
    lateinit var mngProductFragment: ManageProductFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            productFragment = ListProductFragment.newInstance(null)
            supportFragmentManager.beginTransaction().add(R.id.main_container, productFragment).commit()
        }
    }

    override fun onAddProductListener() {
        mngProductFragment = ManageProductFragment.newInstance(null)
        supportFragmentManager.beginTransaction().replace(R.id.main_container, mngProductFragment).addToBackStack(null).commit()
    }

    override fun onManageProductListener() {
       supportFragmentManager.popBackStackImmediate()
    }
}
