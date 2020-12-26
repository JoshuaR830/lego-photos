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
import java.util.ArrayList;
import java.util.Arrays;
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
            ImageView legoConvert = findViewById(R.id.imageCompressed);
            ImageView legoStuds = findViewById(R.id.imageInStuds);

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


            Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, STUD_WIDTH, STUD_HEIGHT, true);
            Bitmap bitmap2 = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true);
//            Bitmap bitmap2 = Bitmap.createBitmap(bitmap);
//            legoImage.setImageBitmap(bitmap2);

            int picHeight = bitmap2.getHeight();
            int picWidth = bitmap2.getWidth();

            Log.d("Height", String.valueOf(picHeight));
            Log.d("Width", String.valueOf(picWidth));

            int[] pixels = new int[picHeight * picWidth];

            bitmap2.getPixels(pixels, 0, picWidth, 0, 0, picWidth, picHeight);




            // This gets the RGB values back
            // https://stackoverflow.com/a/13583925/13496270




            Bitmap studBitmap = this.getColors(pixels, picHeight, picWidth);
            legoImage.setImageBitmap(bitmap);
            legoConvert.setImageBitmap(bitmap2);
            legoStuds.setImageBitmap(studBitmap);
        }


    }

    public Bitmap getColors(int[] pixels, int height, int width) {

        int subsetHeight = height / STUD_HEIGHT;
        int subsetWidth = width / STUD_WIDTH;


        for (int h = 0; h < subsetHeight; h++) {
            for (int w = 0; w < subsetWidth; w++) {

                

                Log.d("Something", String.valueOf(pixels[w + (h * STUD_WIDTH)]));
            }
        }

        Bitmap studBitmap = Bitmap.createBitmap(STUD_WIDTH, STUD_HEIGHT, Bitmap.Config.ARGB_8888);


        for (int i = 0; i < pixels.length; i++) {
            int pixel = pixels[i];
            int R = (pixel >> 16) & 0xff;
            int G = (pixel >> 8) & 0xff;
            int B = pixel & 0xff;


            ArrayList<Integer> reds = new ArrayList<Integer>();

            for(int item : LegoColours.redList) {
                reds.add(item);
            }

            ArrayList<Integer> greens = new ArrayList<Integer>();

            for(int item : LegoColours.greenList) {
                greens.add(item);
            }

            ArrayList<Integer> blues = new ArrayList<Integer>();

            for(int item : LegoColours.blueList) {
                blues.add(item);
            }
            int closestRed = getClosestColor(reds, R);
            int closestGreen = getClosestColor(greens, G);
            int closestBlue = getClosestColor(blues, B);

            int redIndex = reds.indexOf(closestRed);
            int greenIndex = reds.indexOf(closestGreen);
            int blueIndex = reds.indexOf(closestBlue);

            int redRed = reds.get(redIndex) ;
            int redGreen = greens.get(redIndex);
            int redBlue = blues.get(redIndex);

            int cr = (0xff) << 24 | (redBlue & 0xff) << 16 | (redGreen & 0xff) << 8 | (redRed & 0xff);

            int greenRed = reds.get(greenIndex);
            int greenGreen = greens.get(greenIndex);
            int greenBlue = blues.get(greenIndex);

            int cg = (0xff) << 24 | (greenBlue & 0xff) << 16 | (greenGreen & 0xff) << 8 | (greenRed & 0xff);


            int blueRed = reds.get(blueIndex);
            int blueGreen = greens.get(blueIndex);
            int blueBlue = blues.get(blueIndex);

            int cb = (0xff) << 24 | (blueBlue & 0xff) << 16 | (blueGreen & 0xff) << 8 | (blueRed & 0xff);


            Log.d("Pixel", String.valueOf(pixel));
            Log.d("Red", String.valueOf(cr));
            Log.d("Green", String.valueOf(cg));
            Log.d("Blue", String.valueOf(cb));
            int redPix = Math.abs(pixel - cr);

            int greenPix = Math.abs(pixel - cg);

            int bluePix = Math.abs(pixel - cb);

            if(redPix <= greenPix && redPix <= bluePix) {
                pixels[i] = cr;
            }
            else if(greenPix <= redPix && greenPix <= bluePix) {
                pixels[i] = cg;
            }
            else {
                pixels[i] = cb;
            }



            Log.d("Original red", String.valueOf(R));
            Log.d("Closest red", String.valueOf(closestRed));

            Log.d("Pixels", String.format(Locale.getDefault(), "%d,%d,%d", R, G, B));
        }

        studBitmap.setPixels(pixels, 0, STUD_WIDTH, 0, 0, STUD_WIDTH, STUD_HEIGHT);
        return studBitmap;
    }

    public int getClosestColor(ArrayList<Integer> list, int colorToMatch) {
        int smallestDiff = 256;
        int closest = 0;
        for (int color : LegoColours.redList) {
            int diff = Math.abs(color - colorToMatch);
            if (diff < smallestDiff) {
                closest = color;
                smallestDiff = diff;
            }
        }

        return closest;
    }



//

//        LegoColours.greenList;
//        LegoColours.blueList;
}
