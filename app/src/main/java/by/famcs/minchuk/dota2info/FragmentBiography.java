package by.famcs.minchuk.dota2info;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.SeekBar;

/**
 * Created by USER on 22.11.2015.
 */
public class FragmentBiography extends Fragment {
    JustifiedTextView justifiedTextView;
    SeekBar seekBar;
    Hero hero;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_biography, container, false);
        hero = (Hero) getArguments().getSerializable("Hero");

        justifiedTextView = (JustifiedTextView) v.findViewById(R.id.webView);

        justifiedTextView.setText(hero.getBiography());

        justifiedTextView.setTextColor("FF202020");
        justifiedTextView.setTextSize(20);

        seekBar = (SeekBar) v.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                justifiedTextView.setTextSize(progress + 10);
                justifiedTextView.reload();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return v;
    }


}

class JustifiedTextView extends WebView {
    private String core      = "<html><body bgcolor=\"#EEEEEE\" style='text-align:justify;color:rgba(%s);font-size:%dpx;margin: 0px 0px 0px 0px;'>%s</body></html>";
    private String textColor = "0,0,0,255";
    private String text      = "";
    private int textSize     = 12;
    private int backgroundColor= Color.TRANSPARENT;

    public JustifiedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWebChromeClient(new WebChromeClient(){});
    }

    public void setText(String s){
        this.text = s;
        reloadData();
    }

    @SuppressLint("NewApi")
    private void reloadData(){

        // loadData(...) has a bug showing utf-8 correctly. That's why we need to set it first.
        this.getSettings().setDefaultTextEncodingName("utf-8");

        this.loadData(String.format(core,textColor,textSize,text), "text/html","utf-8");

        // set WebView's background color *after* data was loaded.
        super.setBackgroundColor(backgroundColor);

        // Hardware rendering breaks background color to work as expected.
        // Need to use software renderer in that case.
        if(android.os.Build.VERSION.SDK_INT >= 11)
            this.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
    }

    public void setTextColor(String hex){
        int a = Integer.parseInt(hex.substring(0, 2),16);
        int r = Integer.parseInt(hex.substring(2, 4),16);
        int g = Integer.parseInt(hex.substring(4, 6),16);
        int b = Integer.parseInt(hex.substring(6, 8),16);
        textColor = String.format("%d,%d,%d,%d", r, g, b, a);
        reloadData();
    }

    public void setBackgroundColor(int hex){
        backgroundColor = hex;
        reloadData();
    }

    public void setTextSize(int textSize){
        this.textSize = textSize;
        reloadData();
    }
}
