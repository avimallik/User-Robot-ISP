package com.armavi_bsd.viewDefinition

import android.app.Activity
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.armavi_bsd.userrobotisp.R

class ViewDefinationPackage(packageActivity: Activity) {

    val packageRecycler: RecyclerView = packageActivity.findViewById(R.id.packageRecycler)
    val testView: ImageView = packageActivity.findViewById(R.id.testView)

}