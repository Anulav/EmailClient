package com.learning.view;

public enum FontSize {
    LARGE,MEDIUM,SMALL;

    public static String getCssFile(FontSize fontSize){
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
        final String path = currentDir+"/src/com/learning/view/";
        switch (fontSize){
            case MEDIUM:
                return "css/fontMedium.css";
            case LARGE:
                return "css/fontLarge.css";
            case SMALL:
                return "css/fontSmall.css";
            default:
                return null;
        }
    }
}
