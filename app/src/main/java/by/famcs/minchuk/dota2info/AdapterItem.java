package by.famcs.minchuk.dota2info;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * Created by USER on 23.11.2015.
 */
public class AdapterItem extends ArrayAdapter<String> {
    private Context context;
    private String[] heroNames;
    private String[] imagesPath;
    private String suffix;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setStrings(String[] heroNames, String[] imagesPath) {
        this.heroNames = heroNames;
        this.imagesPath = imagesPath;
    }

    @Override
    public void clear() {
        super.clear();
    }

    public AdapterItem(Context context, String[] heroNames, String[] imagesPath) {
        super(context, R.layout.list_view_item, heroNames);
        this.context = context;
        this.heroNames = heroNames;
        this.imagesPath = imagesPath;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_view_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textViewItem);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewItem);

        int id =  context.getResources().getIdentifier(imagesPath[position] + suffix, "drawable", context.getPackageName());
        textView.setText(heroNames[position]);
        imageView.setBackgroundResource(id);

        return rowView;
    }


    @Override
    public void addAll(Collection<? extends String> collection) {
        super.addAll(collection);
    }
}
