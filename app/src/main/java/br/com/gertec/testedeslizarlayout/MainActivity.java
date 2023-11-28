package br.com.gertec.testedeslizarlayout;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
@SuppressLint("MissingInflatedId")
public class MainActivity extends AppCompatActivity {
    private ConstraintLayout panelLayout;
    private ConstraintLayout constraintLayout;
    private boolean isPanelExpanded = false;
    private int panelHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        panelLayout = findViewById(R.id.panelLayout);
        constraintLayout = findViewById(R.id.baseLayout);



        int[] location = new int[2];
        panelLayout.getLocationOnScreen(location);

        int x = location[0];
        int y = location[1];

         ImageView imgMore = findViewById(R.id.imgMore);
         imgMore.setOnClickListener(view->{
             panelHeight = panelLayout.getHeight();

             int baseHeight = constraintLayout.getHeight();
             double percent =  (panelHeight * 100.0)/ baseHeight ;
             double parte = (percent * baseHeight) / 100.0;

             if(!isPanelExpanded){
                 isPanelExpanded = true;
                 translationY(panelLayout,(float) -parte);
             }else {
                 isPanelExpanded = false;
                 translationY(panelLayout, (float) y);
             }
         });

    }

    private void translationY(ConstraintLayout layout, Float value) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(layout, "translationY", value);
        animator.setDuration(100);
        animator.setStartDelay(100);
        animator.start();
    }

    public void setPanelHeight() {
        final int heightPanelBase = constraintLayout.getHeight();
        int novaAlturaEmPixels = (int) (heightPanelBase * 0.8);
        ViewGroup.LayoutParams params = panelLayout.getLayoutParams();
        params.height = novaAlturaEmPixels;
        panelLayout.setLayoutParams(params);
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> {
            setPanelHeight();
        },100);
    }
}

