package digi.coders.intagramcloneapp.Adafter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class ViewPagerAdafter (fm:FragmentManager):FragmentPagerAdapter (fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    val fregmentlist=ArrayList<Fragment>()
    val fregmentTitle=ArrayList<String>()
    override fun getCount(): Int {
      return fregmentlist.size

    }

    override fun getItem(position: Int): Fragment {
        return fregmentlist.get(position)
    }
    override fun getPageTitle(position: Int): CharSequence? {
        return fregmentTitle.get(position)
    }
    fun addfregment(fragment: Fragment,title:String){
        fregmentlist.add(fragment)
        fregmentTitle.add(title)

    }
}