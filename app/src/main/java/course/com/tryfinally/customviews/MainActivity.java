package course.com.tryfinally.customviews;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CirclesView circlesView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         circlesView = (CirclesView) findViewById(R.id.circles);
    }

    public void enlarge(View view) {
        growCirclesBy(5);
    }

    private void growCirclesBy(int m) {
        int radius = circlesView.getRadius();
        circlesView.setRadius(radius + m);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        menu.add("Dyna");


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_about:
                showAbout();
                return true;
            case R.id.mnu_call:
                new TimePickerDialog(this, null, 19, 20, false).show();
                return true;
            case R.id.mnu_map:
                growCirclesBy(20);
                return true;
            default:
                Log.d("VA", "onOptionsItemSelected: " + item.getTitle() + " " + item.getItemId());
                return super.onOptionsItemSelected(item);
        }
    }

    void showAbout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Demo Dialog content")
                .setTitle(R.string.app_name)
                .setIcon(android.R.drawable.ic_btn_speak_now)

                .setPositiveButton("Engage", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        growCirclesBy(50);
                    }
                })
                .setNegativeButton("Dismis", null)
                //.setOnCancelListener()
        ;

        AlertDialog dialog = builder.create();
        dialog.show();

        Log.d("VA", "showAbout: after show");

    }
}
