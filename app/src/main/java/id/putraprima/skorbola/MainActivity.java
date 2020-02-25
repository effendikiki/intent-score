package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();
    private EditText input_home_team, input_away_team;
    private ImageView homeImage, awayImage;
    private Bitmap homeBitmap, awayBitmap;
    private String homeUri, awayUri;

    public static final String IMAGE_HOME_KEY = "image_home_key";
    public static final String IMAGE_AWAY_KEY = "image_away_key";
    public static final String HOME_KEY = "home_key";
    public static final String AWAY_KEY = "away_key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_home_team = findViewById(R.id.home_team);
        input_away_team = findViewById(R.id.away_team);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

    }

    public void HandleNext(View view) {

        String output_home_team = input_home_team.getText().toString();
        String output_away_team = input_away_team.getText().toString();

        if (!(output_home_team).equals("") && !(output_away_team).equals("")) {
            Intent intent = new Intent(this, MatchActivity.class);

            intent.putExtra(HOME_KEY, output_home_team);
            intent.putExtra(AWAY_KEY, output_away_team);
            intent.putExtra(IMAGE_HOME_KEY, homeUri);
            intent.putExtra(IMAGE_AWAY_KEY, awayUri);

            startActivity(intent);


        } else {
            Toast.makeText(this, "Isi semua Data!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri home = data.getData();
                    homeUri = home.toString();
                    homeBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), home);
                    homeImage.setImageBitmap(homeBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Tidak bisa memuat gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }else if (requestCode == 2){
            if (data != null) {
                try {
                    Uri away = data.getData();
                    awayUri = away.toString();
                    awayBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), away);
                    awayImage.setImageBitmap(awayBitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Tidak bisa memuat gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    public void handleChangeHomeTeamImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAwayTeamImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);

    }

}
