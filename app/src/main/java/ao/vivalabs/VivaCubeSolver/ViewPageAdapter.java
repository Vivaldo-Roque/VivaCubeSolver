package ao.vivalabs.VivaCubeSolver;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPageAdapter extends PagerAdapter {

    Context context;
    ArrayList<String> images = new ArrayList<String>();
    LayoutInflater mLayoutInflater;

    public ViewPageAdapter(Context context, ArrayList<String> images)
    {
        this.context = context;
        this.images = images;

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount()
    {

        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView_page = itemView.findViewById(R.id.imageView_page);
        TextView textView_page = itemView.findViewById(R.id.textView_page);
        Uri imgURI = Uri.parse("file:///"+images.get(position));
        imageView_page.setImageURI(imgURI);
        textView_page.setText(images.get(position).split("/")[images.get(position).split("/").length-1]);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
