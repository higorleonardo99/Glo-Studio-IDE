package bo.hlva.glostudio.project;
import java.io.File;
import bo.hlva.glostudio.utils.FileUtils;

public class Resources {
    
    private File dirRes;
    
    //values resource
    private File dirDrawable;
    private File dirLayout;
    private File dirMenu;
    private File dirMipmap;
    private File dirValues;
    
    private File styles;
    private File strings;
    private File colors;
    
    // contructor
    public Resources(File dirRes){
        this.dirRes = dirRes;
        
    }
    
    public void builderResources(){
        
        dirDrawable = FileUtils.createNewFolder(dirRes,"drawable");
        dirLayout = FileUtils.createNewFolder(dirRes,"layout");
        dirMenu = FileUtils.createNewFolder(dirRes,"menu");
        dirMipmap = FileUtils.createNewFolder(dirRes,"mipmap");
        dirValues = FileUtils.createNewFolder(dirRes,"values");
        
        colors = FileUtils.createNewFile(dirValues,"colors.xml");
        styles = FileUtils.createNewFile(dirValues,"styles.xml");
        strings = FileUtils.createNewFile(dirValues,"strings.xml");
    }
    
    /*
    * @methods return files manager
    * @return File
    */
    public File getStyles(){
        return this.styles;
    }
    
    public File getStrings(){
        return this.strings;
    }
    
    public File getColors(){
        return this.colors;
    }
    
    /*******************************/
    
    
    /*
    * @methods return folder
    * @return File
    */
    public File getDirDrawble(){
        return this.dirDrawable;
    }
    
    public File getDirLayout(){
        return this.dirLayout;
    }
    
    public File getDirMenu(){
        return this.dirMenu;
    }
    
    public File getDirMipMap(){
        return this.dirMipmap;
    }
    
    public File getDirValues(){
        return this.dirValues;
    }
    
    /****************************/
}
