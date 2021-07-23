package bo.hlva.glostudio.ide;
import com.unnamed.b.atv.model.TreeNode;
import bo.hlva.glostudio.project.AndroidProject;

public class ProjectContract {
    
    public static final String TAG = "ProjectContract";
    
    public interface View{
        
        void refresh(AndroidProject project);
        
    }
    
    public interface Presenter{
        
        void refresh(AndroidProject project)
    }
    
}
