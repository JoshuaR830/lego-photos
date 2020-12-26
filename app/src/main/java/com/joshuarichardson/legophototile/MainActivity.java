package com.joshuarichardson.legophototile;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_IMAGE_ID = 1;

    public static final int STUD_WIDTH = 24;
    public static final int STUD_HEIGHT = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    public void onGetImageButtonClicked(View v) {
        Intent something = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);

        startActivityForResult(something, SELECT_IMAGE_ID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == SELECT_IMAGE_ID) {
            Uri imageUri = data.getData();


            ImageView legoImage = findViewById(R.id.imageToConvert);

//            legoImage.setImageURI(imageUri);

            if(imageUri == null) {
                return;
            }

            ImageDecoder.Source src = ImageDecoder.createSource(this.getContentResolver(), imageUri);

            Bitmap bitmap = null;

            try {
                bitmap = ImageDecoder.decodeBitmap(src);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(bitmap == null) {
                return;
            }

            Bitmap bitmap2 = bitmap.copy(Bitmap.Config.ARGB_8888, true);

//            Bitmap bitmap2 = Bitmap.createBitmap(bitmap);
            legoImage.setImageBitmap(bitmap2);

            int picHeight = bitmap2.getHeight();
            int picWidth = bitmap2.getWidth();

            Log.d("Height", String.valueOf(picHeight));
            Log.d("Width", String.valueOf(picWidth));

            int[] pixels = new int[picHeight * picWidth];

            bitmap2.getPixels(pixels, 0, picWidth, 0, 0, picWidth, picHeight);



            // This gets the RGB values back
            // https://stackoverflow.com/a/13583925/13496270




            this.getColors(pixels, picHeight, picWidth);
        }


    }

    public void getColors(int[] pixels, int height, int width) {

        int[] compressedPixels = new int[STUD_HEIGHT * STUD_WIDTH];

        int subsetHeight = height / STUD_HEIGHT;
        int subsetWidth = width / STUD_WIDTH;
        for (int h = 0; h < subsetHeight; h++) {
            for (int w = 0; w < subsetWidth; w++) {

                

                Log.d("Something", String.valueOf(pixels[w + (h * STUD_WIDTH)]));
            }
        }

        int pixel = pixels[0];

        int R = (pixel >> 16) & 0xff;
        int G = (pixel >> 8) & 0xff;
        int B = pixel & 0xff;

        Log.d("Pixels", String.format(Locale.getDefault(), "%d,%d,%d", R, G, B));
//
//        LegoColours.redList;
//        LegoColours.greenList;
//        LegoColours.blueList;
    }
}