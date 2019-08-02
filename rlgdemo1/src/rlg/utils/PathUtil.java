package rlg.utils;

public class PathUtil {
    public static String getPath(String path){
        String[] s = path.split("[/ .]");
        return s[1];
    }
    public static String getPaths(String path){
        String s = path.replace(".", "/");
        String[] split = s.split("/");
        return split[1];
    }
}
