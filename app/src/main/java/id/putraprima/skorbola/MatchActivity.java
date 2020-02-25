package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.AWAY_KEY;
import static id.putraprima.skorbola.MainActivity.HOME_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGE_AWAY_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGE_HOME_KEY;

public class MatchActivity extends AppCompatActivity {

    private TextView Text_home_team, Text_away_team, homeScoreText, awayScoreText;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private Uri homeUri, awayUri;
    private int homeScore = 0;
    private int awayScore = 0;

    public static final String WINNER_KEY = "winner_key";
//    public static final String NAME_AWAY_KEY = "name_away_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Text_home_team = findViewById(R.id.txt_home);
        Text_away_team = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        homeScoreText = findViewById(R.id.score_home);
        awayScoreText = findViewById(R.id.score_away);


        Bundle extras = getIntent().getExtras();

        if (extras != null){
            Text_home_team.setText(getIntent().getStringExtra(HOME_KEY));
            Text_away_team.setText(getIntent().getStringExtra(AWAY_KEY));
            homeUri = Uri.parse(extras.getString(IMAGE_HOME_KEY));
            awayUri = Uri.parse(extras.getString(IMAGE_AWAY_KEY));
            try {
                homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(homeBitmap);
            awayImage.setImageBitmap(awayBitmap);
        }

        }

    public void AddHomeScore(View view) {
        homeScore += 1;
        homeScoreText.setText(Integer.toString(homeScore));

    }

    public void AddAwayScore(View view) {
        awayScore +=1 ;
        awayScoreText.setText(Integer.toString(awayScore));
    }

    public void HandleResult(View view) {

        String nameHomeTeam = getIntent().getStringExtra(HOME_KEY);
        String nameAwayTeam = getIntent().getStringExtra(AWAY_KEY);
        String nameDrawTeam = "Draw";

        Intent intent = new Intent(this, ResultActivity.class);

        if (homeScore > awayScore ){
            intent.putExtra(WINNER_KEY, nameHomeTeam);
            startActivity(intent);
        }
        else if(homeScore == awayScore){
            intent.putExtra(WINNER_KEY, nameDrawTeam);
            startActivity(intent);
        }
        else {
            intent.putExtra(WINNER_KEY, nameAwayTeam);
            startActivity(intent);
        }

    }

    //TODO

        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }
