package bo.hlva.glostudio.ide.activies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import bo.hlva.glostudio.R;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import bo.hlva.glostudio.ide.dialog.CreateNewProjectDialog;
import bo.hlva.glostudio.project.AndroidProject;
import bo.hlva.glostudio.utils.Permissions;
import android.widget.Toast;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import bo.hlva.glostudio.ide.fragment.ProjectStructureFragment;
import bo.hlva.glostudio.ide.ProjectPresenter;

public class ProjectManagerActivity extends AppCompatActivity implements CreateNewProjectDialog.OnCreateProjectDialogListener {

    //toolbar
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    
    //****
    private ProjectStructureFragment projectStructureFragment;
    private ProjectPresenter projectPresenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Permissions.permissionStorage(this);
        
        getConfigToolbar();
        setupProjectFragment(savedInstanceState);
        
    }
    
    private void getConfigToolbar(){
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        mDrawerLayout = findViewById(R.id.main_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
    }
    
    private void setupProjectFragment(Bundle saveInstanceState){
        
        if(projectStructureFragment == null){
            
            projectStructureFragment = ProjectStructureFragment.getInstance();
        }
        else{
            
            
        }
        
        getSupportFragmentManager().
        beginTransaction().
        replace(R.id.container_project_structure_fragment,projectStructureFragment,ProjectStructureFragment.TAG).commit();
        
        projectPresenter = new ProjectPresenter(projectStructureFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        switch(item.getItemId()){
            
            case android.R.id.home:
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            
            case R.id.item_menu_main_new_project:
                //show dialog
                CreateNewProjectDialog.getInstance().show(getSupportFragmentManager(),CreateNewProjectDialog.TAG);
                return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
    
    
    @Override
    public void onCreateProject(AndroidProject project) {
        
        Toast.makeText(this,project.getNameProject(),Toast.LENGTH_SHORT).show();
    }
    
}

