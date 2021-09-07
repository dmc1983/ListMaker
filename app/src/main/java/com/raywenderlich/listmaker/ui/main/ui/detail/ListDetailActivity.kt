package com.raywenderlich.listmaker.ui.main.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.raywenderlich.listmaker.MainActivity
import com.raywenderlich.listmaker.MainViewModel
import com.raywenderlich.listmaker.R
import com.raywenderlich.listmaker.TaskList
import com.raywenderlich.listmaker.databinding.ListDetailActivityBinding
import com.raywenderlich.listmaker.ui.main.MainViewModelFactory
import com.raywenderlich.listmaker.ui.main.ui.detail.ui.detail.ListDetailFragment
import com.raywenderlich.listmaker.ui.main.ui.detail.ui.detail.ListDetailViewModel

class ListDetailActivity : AppCompatActivity() {



    lateinit var binding: ListDetailActivityBinding

    lateinit var viewModel: MainViewModel

    lateinit var fragment: ListDetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ListDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
        viewModel = ViewModelProvider(this).get(ListDetailViewModel::class.java)
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = viewModel.list.name


        fragment = ListDetailFragment.newInstance()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    private fun showCreateTaskDialog() {

        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT


            AlertDialog.Builder(this).setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->

            val task = taskEditText.text.toString()
            viewModel.addTask(task)

            dialog.dismiss()
        }


        .create()
        .show()
    }
}