package ao.vivalabs.VivaCubeSolver;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

public class AboutActivity extends AppCompatActivity {

    TextView show_license;
    TextView github_project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        show_license = findViewById(R.id.licences);
        show_license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, OssLicensesMenuActivity.class);
                startActivity(intent);
            }
        });

        github_project = findViewById(R.id.github);
        github_project.setText(Html.fromHtml("<a href=https://github.com/Vivaldo-Roque/VivaCubeSolver_2> GITHUB PROJECT "));
        github_project.setMovementMethod(LinkMovementMethod.getInstance());
    }
}