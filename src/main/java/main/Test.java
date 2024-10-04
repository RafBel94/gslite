package main;

import com.formdev.flatlaf.FlatLightLaf;

import views.GUICatalog;
import views.GUIConnect;

public class Test {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        new GUIConnect();
    }
}
