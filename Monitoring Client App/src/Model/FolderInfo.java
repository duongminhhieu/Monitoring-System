/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class FolderInfo implements Serializable{

    private String path;
    private LocalDateTime time;
    private String action;
    private String description;

    public FolderInfo(String path, LocalDateTime time, String action, String description) {
        this.path = path;
        this.time = time;
        this.action = action;
        this.description = description;
    }
 
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
        
}
