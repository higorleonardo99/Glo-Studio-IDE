package bo.hlva.glostudio.ide.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import bo.hlva.glostudio.R;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import bo.hlva.glostudio.ide.dialog.CreateNewProjectDialog;

public class MainActivity extends AppCompatActivity {
    
    private Toolbar mToolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
        getConfigToolbar();
		
    }
    
    private void getConfigToolbar(){
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch(item.getItemId()){
            
            case R.id.item_menu_main_new_project:
                CreateNewProjectDialog.getInstance().show(getSupportFragmentManager(),CreateNewProjectDialog.TAG);
                return true;
        }
        
        
        return super.onOptionsItemSelected(item);
    }
    
    
    
}

