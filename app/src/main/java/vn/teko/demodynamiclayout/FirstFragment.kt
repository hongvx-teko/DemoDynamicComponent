package vn.teko.demodynamiclayout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import vn.teko.dynamiclayout.parser.Parser
import vn.teko.dynamiclayout.parser.VnShopParser
import vn.teko.model.block.Block
import vn.teko.model.listing.Product

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), ContentController.Callbacks {

    private val contentController = ContentController(this)

    private lateinit var parser: Parser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parser = VnShopParser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        recyclerView.clear()
        recyclerView.adapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setController(contentController)

        lifecycleScope.launch(Dispatchers.IO) {
            val fetchData = parser.fetchData(mapOf())
            withContext(Dispatchers.Main) {
                contentController.setData(fetchData)
                loading.isVisible = false
            }
        }


    }

    override fun gotoDetail(index: Int, product: Product) {
        Log.d("Hong", "gotoDetail: $index, $product")
    }


}
