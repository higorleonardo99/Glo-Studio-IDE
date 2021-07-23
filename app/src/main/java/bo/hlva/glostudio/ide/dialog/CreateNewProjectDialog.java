package bo.hlva.glostudio.ide.dialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import bo.hlva.glostudio.R;
import android.widget.EditText;
import android.widget.Button;
import android.text.TextWatcher;
import android.text.Editable;
import bo.hlva.glostudio.project.AndroidProject;
import bo.hlva.glostudio.utils.FileUtils;
import java.io.File;
import android.content.Context;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import org.apache.commons.io.IOUtils;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import android.util.Log;
import java.io.InputStream;

public class CreateNewProjectDialog extends DialogFragment {
    
    public static final String TAG = "tag create project dialog";
    
    private EditText edtNameProject,edtNamePackage;
    private static CreateNewProjectDialog instance;
    
    private AndroidProject mAndroidProject;
    
    private OnCreateProjectDialogListener listener;
    
    
    //contructor
    public static CreateNewProjectDialog getInstance(){
        
        if(instance == null){
            instance = new CreateNewProjectDialog();
        }
        
        return instance;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("New Project");
        builder.setCancelable(false);
        
        //buttons
        
        //positive button null 
        builder.setPositiveButton("Create",null);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface p1, int p2) {
                    
                    dismiss();
                }
            });
            
         //inflate view
         View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_create_new_project,null);
         edtNameProject = view.findViewById(R.id.dialog_create_new_project_edt_name_project);
         edtNamePackage = view.findViewById(R.id.dialog_create_new_project_edt_name_package);

         edtNameProject.addTextChangedListener(getOnTextChange());
         builder.setView(view);
        
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        
        AlertDialog dialog = (AlertDialog) getDialog();
        if(dialog != null){
            
            Button positiveButton = dialog.getButton(getDialog().BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        
                        if(inputVerication()){
                            
                            mAndroidProject = new AndroidProject(edtNameProject.getText().toString(),edtNamePackage.getText().toString());
                            mAndroidProject.builderProject();
                            mAndroidProject.buildResources();
                            createMainActivity();
                            copyManifest();
                            copyResources();
                            listener.onCreateProject(mAndroidProject);
                            dismiss();
                        }
                    }
                });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        
        try{
            listener = (OnCreateProjectDialogListener) context;
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    private boolean inputVerication(){
        
        if(edtNameProject.getText().toString().isEmpty()){
            
            edtNameProject.setError("Invalid Name");
            return false;
        }
        if(edtNamePackage.getText().toString().isEmpty()){
            
            edtNamePackage.setError("Invalid Name");
            return false;
        }
        
        return true;
    }
    
    //control edittext package
    private TextWatcher getOnTextChange(){
        
        return new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4) {
            }

            @Override
            public void onTextChanged(CharSequence p1, int p2, int p3, int p4) {
                
            }

            @Override
            public void afterTextChanged(Editable editable) {
                
                edtNamePackage.setText(editable.toString().toLowerCase());
            }
        };
    }
    
    private void createMainActivity(){
        
        File dirPackageJava = FileUtils.createNewFolder(mAndroidProject.getDirJava(),mAndroidProject.getNamePackage().replace(".",File.separator));
        File mainActivity = FileUtils.createNewFile(dirPackageJava,"MainActivity.java");
        FileUtils.copyAssets(getContext(),"templates/MainActivity.java",mainActivity);
        String content = FileUtils.readFile(mainActivity).toString();
        content = content.replace("$package_name$",mAndroidProject.getNamePackage());
        FileUtils.writerFile(mainActivity,content);
        
        File fileLayout = FileUtils.createNewFile(mAndroidProject.getResources().getDirLayout(),"activity_main.xml");
        FileUtils.copyAssets(getContext(),"templates/activity_main.xml",fileLayout);
        
    }
    
    private void copyManifest(){
        
        FileUtils.copyAssets(getContext(),"templates/AndroidManifest.xml",mAndroidProject.getAndroidManifest());
        String contentManifest = FileUtils.readFile(mAndroidProject.getAndroidManifest()).toString();
        contentManifest = contentManifest.replace("$package_name$",mAndroidProject.getNamePackage());
        FileUtils.writerFile(mAndroidProject.getAndroidManifest(),contentManifest);
    }
    
    private void copyResources(){
        
        //copy icon
       File icon = FileUtils.createNewFile(mAndroidProject.getResources().getDirMipMap(),"ic_launcher.png");
       FileUtils.copyAssets(getContext(),"templates/ic_launcher.png",icon);
       
       //copy styles.xml
       FileUtils.copyAssets(getContext(),"templates/styles.xml",mAndroidProject.getResources().getStyles());
       //copy colors.xml
       FileUtils.copyAssets(getContext(),"templates/colors.xml",mAndroidProject.getResources().getColors());
       
       //copy strings.xml
       FileUtils.copyAssets(getContext(),"templates/strings.xml",mAndroidProject.getResources().getStrings());
       String contentStrings = FileUtils.readFile(mAndroidProject.getResources().getStrings()).toString();
       contentStrings = contentStrings.replace("$project_name$",mAndroidProject.getNameProject());
       FileUtils.writerFile(mAndroidProject.getResources().getStrings(),contentStrings);
       
    }
   
    
    public interface OnCreateProjectDialogListener{
        
        void onCreateProject(AndroidProject project)
    }
}
