package cuexpo.chulaexpo.adapter;


import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cuexpo.chulaexpo.R;
import cuexpo.chulaexpo.dao.PlaceItemDao;
import cuexpo.chulaexpo.manager.HttpManager;
import cuexpo.chulaexpo.view.ActivityListItem;
import cuexpo.chulaexpo.view.EventListItem;
import retrofit2.Call;

public class ZoneDetailListAdapter extends BaseAdapter implements OnMapReadyCallback{
    private static LayoutInflater inflater;
    private Context context;
    private String id, type, website, description;
    public double lat, lng;
    private FrameLayout relatedHeader;

    private ActivityListItem activityListItem;

    public ZoneDetailListAdapter(Context context, String id, String type, String website,
                                  String description, double lat, double lng) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.id = id;
        this.type = type;
        this.website = website;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        relatedHeader = (FrameLayout) (inflater.inflate(R.layout.fragment_zone_main_page, null)).findViewById(R.id.related_header);
//        this.imageUrls = imageUrls;

//        Call<RoundDao> roundCall = HttpManager.getInstance().getService().loadRoundsById(id);
//        roundCall.enqueue(callbackPlace);
    }


    @Override
    public int getCount() {
        return 5;
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
        View zoneDetailView;


        switch (position) {

            case 0:
                convertView = inflater.inflate(R.layout.item_zone_detail_detail, null);
                TextView detail = (TextView) convertView.findViewById(R.id.detail);
                detail.setText(description);
                MapView mapView = (MapView) convertView.findViewById(R.id.mapView);
                mapView.onCreate(null);
                mapView.onResume();
                mapView.getMapAsync(this);
                break;
            case 1:
                convertView = relatedHeader;
                break;
            default:
                activityListItem = new ActivityListItem(parent.getContext());
                activityListItem.setNameText("วิศวกรรมศาสตร์");
                activityListItem.setImageUrl("http://staff.chulaexpo.com" + "/public/img/activity/6a4ddb44ad0cf345e5ecba2e7ef929df1487688136450.jpeg");
                convertView = activityListItem;
                break;
        }
        zoneDetailView = convertView;

        return zoneDetailView;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * ((float)displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
//        googleMap.setOnMarkerClickListener(this);
//        googleMap.setOnMapClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                new CameraPosition.Builder()
                        .target(new LatLng(lat, lng))
                        .zoom(18)
                        .build()
        ));
        googleMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(lat, lng))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_21))
        );
    }
}
