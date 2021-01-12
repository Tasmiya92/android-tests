package com.tasmiya.peopleinteractivedemoapp.binding

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.tasmiya.peopleinteractivedemoapp.R


class RecyclerRowBinding {
    companion object{


        //Loading recipe image
        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView : ImageView, imageUrl : String){
            imageView.load(imageUrl){
                crossfade(100)
                error(R.drawable.ic_error_placeholder)

            }
        }

// Parses Javascript into text
//        @BindingAdapter("parseHtml")
//        @JvmStatic
//        fun parseHtml(textView : TextView, description : String?){
//            if(description != null){
//                val desc= Jsoup.parse(description).text()
//                textView.text = desc
//            }
//        }

        @BindingAdapter("setAge")
        @JvmStatic
        fun funSetAge(textView: TextView, age: Int){
            textView.text=age.toString()
        }


        @BindingAdapter("customAcceptVisibility")
        @JvmStatic
        fun setVisibility(view : View, visible : Boolean) {
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
//
//        fun click(button : Button, txt: String){
//            button.setOnClickListener(
//
//            )
//        }

    }


}