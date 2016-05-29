package com.fredfama.weparty;

/**
 * Created by root on 29/05/16.
 */
public class Song {

    String uuid = null;
    String name = null;
    String path = null;
    boolean selected = false;

    public Song(String uuid, String name, String path, boolean selected) {
        super();
        this.uuid = uuid;
        this.name = name;
        this.path = path;
        this.selected = selected;
    }

    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
