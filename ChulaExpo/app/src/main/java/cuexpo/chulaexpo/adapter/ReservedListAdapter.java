package cuexpo.chulaexpo.adapter;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cuexpo.chulaexpo.manager.FavouriteManager;
import cuexpo.chulaexpo.manager.ReservedManager;
import cuexpo.chulaexpo.view.DateSection;
import cuexpo.chulaexpo.view.FavouriteListItem;
import cuexpo.chulaexpo.view.ReservedListItem;

/**
 * Created by TEST on 1/11/2017.
 */
public class ReservedListAdapter extends BaseAdapter {


    @Override
    public int getCount() {
        return FavouriteManager.getInstance().count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ReservedListItem item;
        DateSection date = new DateSection(parent.getContext());
        ReservedManager manager = ReservedManager.getInstance();

        for(int i = 15;i < 20;i++) {
            if (position == manager.offset[i-15]) {
                date.setDate(i + "");
                return date;
            } else if (position == manager.offset[i-15] + 1) {
                if (manager.getReservedDate(i-15) == 0) {
                    TextView tv = new TextView(parent.getContext());
                    tv.setText("No Reserved Event On This Day");
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                    final float scale = parent.getResources().getDisplayMetrics().density;
                    int pixels = (int) (15 * scale + 0.5f);
                    tv.setPadding(0,pixels,0,pixels);
                    return tv;
                } else {
                    item = new ReservedListItem(parent.getContext());
                    return item;
                }
            }
        }

        item = new ReservedListItem(parent.getContext());
        return item;
    }

    @Override
    public boolean isEnabled(int position) {
        ReservedManager manager = ReservedManager.getInstance();
        for(int i = 0 ;i < 5 ;i++){
            if(position==manager.offset[i]+1 && manager.getReservedDate(i) == 0){
                return false;
            }
        }
        return true;
    }
}
