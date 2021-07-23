package bo.hlva.glostudio.ide.adapter;
import java.io.File;
import com.unnamed.b.atv.model.TreeNode;
import android.view.View;
import android.content.Context;
import android.view.LayoutInflater;
import bo.hlva.glostudio.R;
import android.widget.ImageView;
import android.widget.TextView;

public class FileItemNodeAdapter extends TreeNode.BaseNodeViewHolder<FileItemNodeAdapter.FileItem> {

    private ImageView itemArrow,itemIcon;
    private TextView itemTitle;
    
    
    public FileItemNodeAdapter(Context context){
        super(context);
    }
    
    
    @Override
    public View createNodeView(TreeNode node, FileItemNodeAdapter.FileItem value) {
        
        View view = LayoutInflater.from(context).inflate(R.layout.item_file_node,null);
        itemArrow = view.findViewById(R.id.item_file_node_arrow);
        itemIcon = view.findViewById(R.id.item_file_node_icon);
        itemTitle = view.findViewById(R.id.item_file_node_title);
        
        //set title item
        itemTitle.setText(value.getFileItem().getName());
        
        setIcon(value.getFileItem());
        
        return view;
    }
    
    private void setIcon(File file){
        
        if(file.isFile()){
            itemIcon.setImageResource(R.drawable.ic_file);
        }
        else if(file.isDirectory()){
            itemIcon.setImageResource(R.drawable.ic_folder);
        }
    }
    
    
    
    public static class FileItem{
        
        private File fileItem;
        
        public FileItem(File file){
            this.fileItem = file;
        }
        
        public File getFileItem(){
            return this.fileItem;
        }
    }
}
