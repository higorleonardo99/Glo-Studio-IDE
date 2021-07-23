package bo.hlva.glostudio.ide.adapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;
import java.io.File;
import bo.hlva.glostudio.ide.fragment.EditorFragment;

/*
* @author HL Devs
* Class Manager Adapter Tablayout and ViewPager
*/
public class TabPageAdapter extends FragmentStatePagerAdapter {
    //items 
    private ArrayList<File> listItems = new ArrayList<>();
    
    
    //contructor
    public TabPageAdapter(FragmentManager fm){
        super(fm);
    }
    
    
    @Override
    public Fragment getItem(int position) {
        return new EditorFragment();
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    
    
    /*
    * @method add item tab 
    */
    public void addTabItem(File file){
        listItems.add(file);
        notifyDataSetChanged();
    }
    
    /*
    * @method remove item tab
    */
    public void removeTabItem(int position){
        listItems.remove(position);
        notifyDataSetChanged();
    }
    
    /*
    * @method return list items
    * @return ArrayList<File>
    */
    public ArrayList<File> getListItems(){
        return this.listItems;
    }
}
