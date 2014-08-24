package ffp.faillight;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.hardware.Camera;
import android.widget.Button;


public class MyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        final Camera cam = Camera.open();
        final Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();

        Button button = (Button) findViewById(R.id.toggleButton);
        button.setOnClickListener(new View.OnClickListener() {
            boolean flag = false;

            public void onClick(View v) {
                if (flag) {
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                }
                else {
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    cam.setParameters(p);
                    cam.startPreview();
                }
                flag = !flag;
            }
        });
    }
    protected void onDestroy() {
        final Camera cam = Camera.open();
        final Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        cam.setParameters(p);
        cam.stopPreview();
        cam.release();
    }
}
