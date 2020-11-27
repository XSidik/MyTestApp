package com.alodok.testapp.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alodok.testapp.R
import com.alodok.testapp.databinding.ActivityDetailBinding
import com.alodok.testapp.databinding.DetailSliderPagerBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var mDetailAdapter: DetailAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Photo Detail"

        setupViewPager()

    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp() and super.onSupportNavigateUp()
    }

    private fun setupViewPager() {

        mDetailAdapter = DetailAdapter(supportFragmentManager)
        binding.viewPager.adapter = mDetailAdapter
        addBottomDots(binding.layoutDots, 3, 0)
        binding.viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                pos: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(pos: Int) {
                addBottomDots(binding.layoutDots, 3, pos)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    inner class DetailAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return SliderImage.newInstance(position + 1)
        }

        override fun getCount(): Int {
            return 3
        }
    }

    class SliderImage : Fragment() {

        private lateinit var bindingPager: DetailSliderPagerBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            bindingPager = DetailSliderPagerBinding.inflate(layoutInflater)
            val rootView = bindingPager.root
            if (requireArguments().getInt(ARG_SECTION_NUMBER) == 1) {
                bindingPager.images.setImageResource(R.drawable.kucing1)
            } else if (requireArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                bindingPager.images.setImageResource(R.drawable.kucing2)
            } else if (requireArguments().getInt(ARG_SECTION_NUMBER) == 3) {
                bindingPager.images.setImageResource(R.drawable.kucing3)
            }
            return rootView
        }

        companion object {
            private const val ARG_SECTION_NUMBER = "section_number"

            fun newInstance(sectionNumber: Int): SliderImage {
                val fragment = SliderImage()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    private fun addBottomDots(layout_dots: LinearLayout, size: Int, current: Int) {
        val dots = arrayOfNulls<ImageView>(size)
        layout_dots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            val width_height = 36
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(width_height, width_height))
            params.setMargins(8, 0, 8, 0)
            dots[i]!!.layoutParams = params
            dots[i]!!.setImageResource(R.drawable.shape_circle_solidcolor3)
            layout_dots.addView(dots[i])
        }
        if (dots.isNotEmpty()) {
            dots[current]!!.setImageResource(R.drawable.shape_circle_primary)
        }
    }
}