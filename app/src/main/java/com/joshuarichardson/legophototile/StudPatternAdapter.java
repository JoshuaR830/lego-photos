package com.joshuarichardson.legophototile;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class StudPatternAdapter extends RecyclerView.Adapter<StudPatternAdapter.StudViewHolder> {

    LayoutInflater inflater;

    int[] colours = new int[] {
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            2, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            3, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            4, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            5, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            2, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            2, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            2, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            3, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            3, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            4, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            4, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            5, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            5, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            4, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            3, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            2, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 4,
            1, 2, 1, 3, 1, 1, 1, 1, 1, 2, 2, 3, 5, 4, 5, 6, 7, 5, 5, 4, 3, 2, 3, 5
    };

    public StudPatternAdapter(Context context, int[] list) {
        this.inflater = LayoutInflater.from(context);
        if(list != null) {
            this.colours = list;
        }
    }

    @NonNull
    @Override
    public StudViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = this.inflater.inflate(R.layout.stud_grid_view, parent, false);
        return new StudViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudViewHolder holder, int position) {
        holder.onBind(colours[position], position);
    }

    @Override
    public int getItemCount() {
        return colours.length;
    }

    public class StudViewHolder extends RecyclerView.ViewHolder {
        TextView studView;
        View view;

        public StudViewHolder(@NonNull View itemView) {
            super(itemView);
            Drawable unwrappedDrawable = AppCompatResources.getDrawable(itemView.getContext(), R.drawable.circle);
            this.view = itemView; //  DrawableCompat.wrap(unwrappedDrawable);
//            this.studView = itemView.findViewById(R.id.studTextView);
        }

        public void onBind(int number, int position) {
//            this.studView.setText(String.format(Locale.getDefault(), "%d", number));

//            this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[0].red, LegoColours.studColors[0].green, LegoColours.studColors[0].blue));

//            DrawableCompat.setTint(this.view, Color.RED);
            Drawable drawable = ContextCompat.getDrawable(this.view.getContext(), R.drawable.circle);
            drawable.setColorFilter(new PorterDuffColorFilter(0xff2196F3, PorterDuff.Mode.SRC_IN));

            this.view.getResources().getDrawable(R.drawable.circle).setTint(Color.BLUE);

            switch(number) {
                case 1:
//                    this.view.getResources().getDrawable(R.drawable.circle).setTint(Color.argb(255, LegoColours.studColors[0].red, LegoColours.studColors[0].green, LegoColours.studColors[0].blue));
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[0].red, LegoColours.studColors[0].green, LegoColours.studColors[0].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[0].red, LegoColours.studColors[0].green, LegoColours.studColors[0].blue));
                    break;
                case 2:
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[1].red, LegoColours.studColors[1].green, LegoColours.studColors[1].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[1].red, LegoColours.studColors[1].green, LegoColours.studColors[1].blue));
                    break;
                case 3:
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[2].red, LegoColours.studColors[2].green, LegoColours.studColors[2].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[2].red, LegoColours.studColors[2].green, LegoColours.studColors[2].blue));
                    break;
                case 4:
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[3].red, LegoColours.studColors[3].green, LegoColours.studColors[3].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[3].red, LegoColours.studColors[3].green, LegoColours.studColors[3].blue));
                    break;
                case 5:
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[4].red, LegoColours.studColors[4].green, LegoColours.studColors[4].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[4].red, LegoColours.studColors[4].green, LegoColours.studColors[4].blue));
                    break;
                default:
                    this.view.setBackgroundColor(Color.argb(255, LegoColours.studColors[5].red, LegoColours.studColors[5].green, LegoColours.studColors[5].blue));
//                    this.studView.setBackgroundColor(Color.argb(255, LegoColours.studColors[5].red, LegoColours.studColors[5].green, LegoColours.studColors[5].blue));
            }
            this.view.setBackgroundColor(colours[position]);

        }
    }
}
