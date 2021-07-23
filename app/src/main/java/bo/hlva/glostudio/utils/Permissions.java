package bo.hlva.glostudio.utils;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;

public class Permissions {
    
    public static final int RESQUET_CODE_STORAGE = 100;
    
    public static void permissionStorage(Activity context){
        
        if(ActivityCompat.checkSelfPermission(context,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            
            ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},RESQUET_CODE_STORAGE);
        }
    }
    
}
