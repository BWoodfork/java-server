package main.java.company.Response.Headers;

import java.util.ArrayList;

public class Options {
    private String methods;
    
    public Options() {

    }
    
    private ArrayList<String> methodOptions() {
        ArrayList<String> options = new ArrayList<>();
        options.add("GET");
        options.add("POST");
        options.add("PUT");
        options.add("DELETE");
        options.add("OPTIONS");
        options.add("PATCH");
        options.add("HEAD");

        return options;
    }
    
    public void setOptions(String methods) {
        this.methods = methods;
    }

    public String formatOptionsString(String methodString) {
        return methodString.replaceAll("\\s", "").toUpperCase();
    }
    
    public String[] splitOptions(String options) {
        return options.split(",");
    }
    
    public String getMethodOption(String optionString) {
        for (String methodOption : methodOptions()) {
            try {
                if (optionString.equals(methodOption)) {
                    return methodOption;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "Method Not Valid";
    }
    
    public boolean isAValidOption(String[] optionsList) {
        for (String option : optionsList) {
            if (!option.equals(getMethodOption(option))) {
                return false;
            }
        }
        
        return true;
    }
    
     
    public String getFormattedOptionsString() {
        String formattedOptionsString = formatOptionsString(methods);
        String[] splitStrings = splitOptions(formattedOptionsString);
        
        try {
            if (isAValidOption(splitStrings)) {
                return formattedOptionsString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Method Is Invalid";
    }

    public String getOptions() {
        return "Allow: " + getFormattedOptionsString() + "\r\n";
    }
}