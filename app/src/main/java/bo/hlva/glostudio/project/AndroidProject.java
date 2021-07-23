package bo.hlva.glostudio.project;
import java.io.File;
import android.os.Environment;
import bo.hlva.glostudio.utils.FileUtils;

/*
* Class Create Project Android
* @author HL Devs
*/

public class AndroidProject {
    
    //values project
    private String nameProject;
    private String namePackage;
    
    //project
    private File dirProject;
    private File dirApp;
    private File dirLibs;
    private File dirSrcMain;
    private File dirJava;
    private File dirRes;
    
    private File manifest;
    //resources
    private Resources resources;
    
    
    //contructor 
    public AndroidProject(String nameProject,String namePackage){
        
        this.nameProject = nameProject;
        this.namePackage = namePackage;
    }
    
    /*
    * @method build project android
    */
    public void builderProject(){
        
        dirProject = FileUtils.createNewFolder(getStorageSd(),nameProject);
        dirApp = FileUtils.createNewFolder(dirProject,"app");
        dirLibs = FileUtils.createNewFolder(dirApp,"libs");
        dirSrcMain = FileUtils.createNewFolder(dirApp,"src"+File.separator+"main");
        
        dirJava = FileUtils.createNewFolder(dirSrcMain,"java");
        dirRes = FileUtils.createNewFolder(dirSrcMain,"res");
        
        manifest = FileUtils.createNewFile(dirSrcMain,"AndroidManifest.xml");
        
        resources = new Resources(dirRes);
        
    }
    
    /*
    * @method build resources
    */
    public void buildResources(){
        this.resources.builderResources();
    }
    
    
    /*
    * @method return name project
    * @return String
    */
    public String getNameProject(){
        return this.nameProject;
    }
    
    /*
    * @method return name package
    * @return String
    */
    public String getNamePackage(){
        return this.namePackage;
    }
    
    /*
    * @method return AndroidManofest
    * @return File
    */
    public File getAndroidManifest(){
        return this.manifest;
    }
    
    /*
    * @method return resources project
    * @return Resources
    */
    public Resources getResources(){
        return this.resources;
    }
    
    
    //* Getters folders project
    public File getDirProject(){
        return this.dirProject;
    }
    
    public File getDirApp(){
        return this.dirApp;
    }
    
    public File getDirLibs(){
        return this.dirLibs;
    }
    
    public File getDirSrcMain(){
        return this.dirSrcMain;
    }
    
    public File getDirRes(){
        return this.dirRes;
    }
    
    public File getDirJava(){
        return this.dirJava;
    }
    
    //***************
    
    
    /*
    * @method storage save project
    * @return File;
    */
    private File getStorageSd(){
        
        File root = new File(Environment.getExternalStorageDirectory(),"AndroidProjects");
        if(!root.exists()) root.mkdir();
        
        return root;
    }
    
    
    public class ConfigProject {
        
        private int minSdk;
        private int targetSdk;
        private int debug;


        public void setMinSdk(int minSdk) {
            this.minSdk = minSdk;
        }

        public int getMinSdk() {
            return minSdk;
        }

        public void setTargetSdk(int targetSdk) {
            this.targetSdk = targetSdk;
        }

        public int getTargetSdk() {
            return targetSdk;
        }

        public void setDebug(int debug) {
            this.debug = debug;
        }

        public int getDebug() {
            return debug;
        }}
}
