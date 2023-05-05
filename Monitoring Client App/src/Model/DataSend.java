/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class DataSend implements Serializable{
    
    private File[] roots;
    private String path;
    private int status; // status = 0 : khoi tao ; status = 1: thay doi 
    private FolderInfo folderInfo;

    public DataSend(File[] roots, String path, int status, FolderInfo folderInfo) {
        this.roots = roots;
        this.path = path;
        this.status = status;
        this.folderInfo = folderInfo;
    }

    
    public File[] getRoots() {
        return roots;   
    }
    public void setRoots(File[] roots) {
        this.roots = roots;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FolderInfo getFolderInfo() {
        return folderInfo;
    }

    public void setFolderInfo(FolderInfo folderInfo) {
        this.folderInfo = folderInfo;
    }


    
}
