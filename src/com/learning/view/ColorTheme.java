package com.learning.view;

public enum ColorTheme {
    DEFAULT,LIGHT,BLACK;

    public static String getCssPath(ColorTheme colorTheme){
        String currentDir = System.getProperty("user.dir");
      //  final String path = currentDir+"/src/com/learning/view/";
        switch (colorTheme){
            case DEFAULT:
                return /*path+*/"css/themeDefault.css";
            case BLACK:
                return /*path+*/"css/themeDark.css";
            case LIGHT:
                return /*path+*/"css/themeLight.css";
            default:
                return null;
        }
    }
}
