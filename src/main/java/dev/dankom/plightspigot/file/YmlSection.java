package dev.dankom.plightspigot.file;

import java.util.List;

public class YmlSection {
    private YmlFile file;
    private String sectionPath;

    public YmlSection(YmlFile file, String sectionPath) {
        this.file = file;
        this.sectionPath = sectionPath;
    }

    public String getFullPath(String path) {
        return sectionPath + "." + path;
    }

    public <T> T getGeneric(String path) {
        return (T) get(path);
    }

    public Object get(String path) {
        return file.get(getFullPath(path));
    }

    public List<String> getStringList(String path) {
        return file.getStringList(getFullPath(path));
    }

    public YmlSection getYmlSection(String path) {
        return new YmlSection(file, getFullPath(path));
    }

    public void set(String key, Object value) {
        file.set(getFullPath(key), value);
    }

    public void addDataKey(String key, Object dval) {
        if (get(key) == null) {
            set(key, dval);
        }
    }

    public void save() {
        file.saveConfig();
        file.reloadConfig();
    }
}
