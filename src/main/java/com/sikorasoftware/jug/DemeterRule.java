package com.sikorasoftware.jug;

import org.springframework.util.Assert;

import java.util.ArrayList;

/**
 * Created by robertsikora on 05.03.2016.
 */
public class DemeterRule {
    public static final int INT = 0;

    /*

    "Don't talk to strangers." a.x.getFoo() -> a.getXFoo()

    alt + ctrl + M -> excatre method
    alt + ctrl + C  -> make static final
    */

    private class Description {
        private String text;
    }

    private class Context {
        private Description description;

        public String getTextOfDescription() {
            return description.text;
        }
    }

    //bad practice
    private void showDescription(final Context context) {
        Assert.notNull(context);
        System.out.println(context.description.text);
    }

    //good practice
    private void showDescription2(final Context context) {
        Assert.notNull(context);
        final ArrayList<Object> objects = new ArrayList<>(); // ctrl + alt + V
        System.out.println(context.getTextOfDescription());
    }
}
