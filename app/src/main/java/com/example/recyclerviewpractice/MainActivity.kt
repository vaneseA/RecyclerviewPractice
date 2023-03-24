package com.example.recyclerviewpractice

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewpractice.databinding.ActivityMainBinding
import com.example.recyclerviewpractice.databinding.AlertdialogEdittextBinding

class MainActivity : AppCompatActivity() {
    // ViewBinding
    private lateinit var binding : ActivityMainBinding

    // RecyclerView 가 불러올 목록
    private var adapter: MyAdapter? = null
    private val data:MutableList<Member> = mutableListOf()
    var i = 4

    init{
        instance = this
    }

    companion object{
        private var instance:MainActivity? = null
        fun getInstance(): MainActivity? {
            return instance
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // ViewBinding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initialize() // data 값 초기화
        adapter = MyAdapter()
        adapter!!.listData = data
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // FAB 을 누르면 Member + 숫자의 문자열이 data 배열에 추가됨
        binding.fab.setOnClickListener {
            val string = "Member$i"
            i++
            data.add(Member(string))
            adapter?.notifyDataSetChanged()
        }
    }

    private fun initialize(){
        with(data){
            add(Member("Member1"))
            add(Member("Member2"))
            add(Member("Member3"))
        }
    }

    fun deleteMember(member: Member){
        data.remove(member)
        adapter?.notifyDataSetChanged()
    }

    fun editMember(position: Int, member: Member){

        val builder = AlertDialog.Builder(this)
        val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
        val editText = builderItem.editText

        with(builder){
            setTitle("Input Name")
            setMessage("이름을 입력 하세요")
            setView(builderItem.root)
            setPositiveButton("OK"){ _: DialogInterface, _: Int ->
                if(editText.text.toString() != null){
                    member.name = editText.text.toString()
                    data[position] = member
                    adapter?.notifyDataSetChanged()
                }
            }
            show()
        }
    }
}