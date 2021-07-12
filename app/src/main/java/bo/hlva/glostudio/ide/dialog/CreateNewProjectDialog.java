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

public class CreateNewProjectDialog extends DialogFragment {
    
    public static final String TAG = "tag create project dialog";
    
    private EditText edtNameProject,edtNamePackage;
    private static CreateNewProjectDialog instance;
    
    private AndroidProject mAndroidProject;
    
    
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
                            dismiss();
                        }
                    }
                });
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
    
}
