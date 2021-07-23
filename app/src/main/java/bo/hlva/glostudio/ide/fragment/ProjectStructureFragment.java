package bo.hlva.glostudio.ide.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import bo.hlva.glostudio.R;
import bo.hlva.glostudio.ide.ProjectContract;
import com.unnamed.b.atv.model.TreeNode;
import bo.hlva.glostudio.project.AndroidProject;
import java.util.ArrayList;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import bo.hlva.glostudio.ide.adapter.FileItemNodeAdapter;

public class ProjectStructureFragment extends Fragment implements ProjectContract.View {

  
    
    //constant
    public static final String TAG = "project_structure_fragment";
    
    private static ProjectStructureFragment instance;
    
    //contructor
    public static ProjectStructureFragment getInstance(){
        if(instance == null){
            instance = new ProjectStructureFragment();
        }
        
        return instance;
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_project_structure,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    
    @Override
    public void refresh(AndroidProject project) {
        
        TreeNode root = TreeNode.root();
       
        
    }
    
    //return list nodes
    private ArrayList<TreeNode> getNode(File dirProject){
        
        ArrayList<TreeNode> nodes = new ArrayList<>();
        
        if(dirProject != null){
            
            //organize files A-Z
            ArrayList<File> files = new ArrayList<>();
            
            for(File file : dirProject.listFiles()){
                files.add(file);
            }
            
            Collections.sort(files, new Comparator<File>(){

                    @Override
                    public int compare(File o1, File o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            
           //add child nodes
           for(File file: files){
               
               if(file.isFile()){
                   
                   TreeNode node = new TreeNode(new FileItemNodeAdapter.FileItem(file));
                   nodes.add(node);
               }
               else if(file.isDirectory()){
                   
                   TreeNode node = new TreeNode(new FileItemNodeAdapter.FileItem(file));
                   node.addChildren(getNode(file));
                   nodes.add(node);
                   
               }
           }
        }
        
        return nodes;
        
    }
    
}
