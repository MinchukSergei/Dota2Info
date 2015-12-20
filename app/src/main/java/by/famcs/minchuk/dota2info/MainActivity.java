package by.famcs.minchuk.dota2info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button buttonHero;
    Button buttonItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonHero = (Button) findViewById(R.id.buttonHeroes);
        buttonHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityHeroList.class);
                startActivity(intent);
            }
        });

        buttonItem = (Button) findViewById(R.id.buttonItems);
        buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityItemList.class);
                startActivity(intent);
            }
        });
    }

}
