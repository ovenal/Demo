package com.github.ovenal.demo;

/**
 * Contains a set of configuration parameters
 */
public class Config {
    public final static String WEB_HOST = System.getProperty("webhost", "http://www.zalando.co.uk");
    public final static String SECTION = System.getProperty("section", "Maxi Skirts");
    public final static String FOLDER = System.getProperty("storage.folder", ".") + "/";
    public final static String LABEL = System.getProperty("storage.label", "output.csv");
}
