package com.example.midtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView show;
    private  String[] meal = {"美味蟹堡", "辣腸堡", "鳳梨堡", "潛艇堡", "麥香雞堡"};
    private boolean[] mealSeclect = {false, false, false, false, false};
    private int[] mealImages = {R.drawable.st1, R.drawable.st2, R.drawable.st3, R.drawable.st4, R.drawable.st5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }

    public void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("您點的餐點有：");

        builder.setMultiChoiceItems(meal, mealSeclect, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                mealSeclect[which] = isChecked;
            }
        });

        builder.setPositiveButton("確認", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 將選擇的餐點顯示在 show TextView 上
                SpannableStringBuilder selectedMeals = new SpannableStringBuilder();
                for (int i = 0; i < meal.length; i++) {
                    if (mealSeclect[i]) {
                        selectedMeals.append(meal[i]).append(" "); // 在插入圖片之前先插入一個空格

                        // 插入圖片
                        Drawable drawable = getResources().getDrawable(mealImages[i]); // 從陣列中取出對應的圖片
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                        selectedMeals.setSpan(imageSpan, selectedMeals.length() - 1, selectedMeals.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

                        selectedMeals.append("\n");
                    }
                }
                show.setText(selectedMeals);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Close the dialog and return to the main screen
                dialog.dismiss();
            }

        });

        builder.show();
    }
    private void findViews() {
        show = findViewById(R.id.tvshow);
    }
}