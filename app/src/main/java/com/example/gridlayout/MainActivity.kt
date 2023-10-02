package com.example.gridlayout

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gridlayout.adapter.GridItemAdapter
import com.example.gridlayout.adapter.SimpleItemAdapter
import com.example.gridlayout.databinding.ActivityMainBinding
import com.example.gridlayout.model.ItemModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = mutableListOf<String>()
    val gridItemAdapter = GridItemAdapter()
    private val simpleItemAdapter = SimpleItemAdapter()
    private val itemsPair = arrayListOf<ItemModel>()
    private var listItems = mutableListOf<String>()
    private val simpleItemList = mutableListOf<ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = gridItemAdapter
        binding.normalRv.layoutManager = LinearLayoutManager(this)
        binding.normalRv.adapter = simpleItemAdapter

        list.add("Junaid Jamshed")
        list.add("Armani")
        list.add("Versace")
        list.add("Loius Vitton")
        list.add("Lacoste")
        list.add("Gucci")
        list.add("Wijdan")
        list.add("Ideas")
        list.add("Bonanza Satrangi")

        list.forEach {
            simpleItemList.add(ItemModel(it))

        }
        simpleItemAdapter.submitList(simpleItemList)

        Log.d("TAG", "width: " + getScreenWidth(this))

        val observer = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                var totalWidth = 0
                var index = 0

                while (index < list.size) {

                    val firstItem =
                        (binding.normalRv.layoutManager as LinearLayoutManager).findViewByPosition(index)
                    val itemWidth = firstItem?.width
                    if (itemWidth != null) {

                        totalWidth += itemWidth

                        if (totalWidth <= getScreenWidth(this@MainActivity)) {
                            listItems.add(list[index])

                            if (index == list.size - 1) {
                                itemsPair.add(ItemModel(listItems = listItems))
                                listItems = mutableListOf()
                            }
                            index++

                        } else {
                            totalWidth = 0
                            itemsPair.add(ItemModel(listItems = listItems))
                            listItems = mutableListOf()
                        }
                    }
                }
                Log.d("TAG", "list: $itemsPair")
                gridItemAdapter.submitList(itemsPair)
                binding.normalRv.viewTreeObserver.removeOnGlobalLayoutListener(this)

            }
        }
        binding.normalRv.viewTreeObserver.addOnGlobalLayoutListener(observer)

    }

}

fun getScreenWidth(context: Context): Int {
    val displayMetrics = DisplayMetrics()
    val windowManager =
        context.getSystemService(Context.WINDOW_SERVICE) as android.view.WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}
