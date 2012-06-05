package net.canarymod.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import net.canarymod.Logman;

/**
 * PropsFile class for handling properties with added comments<br>
 * Examples:<br>
 * propsfile.setString("Key", "Value");<br>
 * propsfile.setString("Key", "Value", "Comment1", "Comment2");
 * 
 * @author Jason
 * @author Jos Kuijpers
 */
public final class ConfigurationFile {

    private File propsFile; // The actual file of the properties
    private String filepath; // The path to the propsfile
    private InputStream instream;

    private HashMap<String, String> props = new HashMap<String, String>(); // Stores the properties
    private HashMap<String, String[]> comments = new HashMap<String, String[]>(); // Stores the associated comments

    /**
     * Class constructor.
     * 
     * @param filepath
     *            File path of the configuration file
     */
    public ConfigurationFile(String filepath) throws IOException {
        this(filepath, false);
    }

    /**
     * Class constructor.
     * 
     * @param filepath
     *            File path of the configuration file
     */
    public ConfigurationFile(String filepath, boolean create) throws IOException {
        this.filepath = filepath; //Sets the path

        propsFile = new File(filepath);
        if (propsFile.exists()) {
        	load();
        }
        else if(!create) {
        	throw new FileNotFoundException();
        }
    }

    /**
     * Class constructor.
     * 
     * @param url
     *            URL of the configuration file
     */
    public ConfigurationFile(InputStream stream) throws IOException {
        this.filepath = null;
        this.propsFile = null;
        this.instream = stream;
        load();
    }

    /**
     * Loads the properties
     */
    public void load() throws IOException {
        BufferedReader in = null;
        IOException toThrow = null;
        try {
            if (this.propsFile == null) in = new BufferedReader(new InputStreamReader(instream));
            else in = new BufferedReader(new FileReader(propsFile)); //Reader of the properties file
            String inLine;
            ArrayList<String> inComments = new ArrayList<String>(); //Temporary comment storage
            while ((inLine = in.readLine()) != null) {
                if (inLine.startsWith(";") || inLine.startsWith("#")) { //Line is a comment so prepare it for storage
                    inComments.add(inLine);
                } else {
                    try {
                        String[] propsLine = inLine.split("=");
                        props.put(propsLine[0].trim(), propsLine[1].trim()); //Store the property and trim out any extra whitespace
                        if (!inComments.isEmpty()) { //Check for comments and store them
                            String[] commented = new String[inComments.size()];
                            for (int i = 0; i < inComments.size(); i++) {
                                commented[i] = inComments.get(i);
                            }
                            comments.put(propsLine[0], commented);
                            inComments.clear(); //Comments associated to a property so clear the temp storage
                        }
                    } catch (ArrayIndexOutOfBoundsException AIOOBE) { //Incomplete property
                        inComments.clear();
                        continue;
                    }
                }
            }
        } catch (IOException ioe) {
            //will rethrow later
            toThrow = ioe;
        } finally {
            if (in != null) {
                in.close();
            }
            if (this.propsFile == null) {
                instream.close();
            }
            if (toThrow != null) {
                throw toThrow;
            }
        }
    }

    /**
     * Saves the properties to the file
     */
    public void save() throws IOException {
        if (filepath.lastIndexOf("/") > 0) {
            new File(filepath.substring(0, filepath.lastIndexOf("/") + 1)).mkdirs(); //Make directories
        }
        IOException toThrow = null;
        BufferedWriter out = null;
        try {
            propsFile.delete();
            propsFile = new File(filepath);
            out = new BufferedWriter(new FileWriter(propsFile, true));
            for (String prop : props.keySet()) {
                if (comments.containsKey(prop)) {
                    for (String comment : comments.get(prop)) {
                        out.write(comment);
                        out.newLine();
                    }
                }
                out.write(prop + "=" + props.get(prop));
                out.newLine();
            }
        } catch (IOException ioe) {
            //will rethrow later
            toThrow = ioe;
        } finally {
            if (out != null) {
                out.close();
            }
            if (toThrow != null) {
                throw toThrow;
            }
        }
    }
    
    /**
     * Get the absolute path of this file
     * @return
     */
    public String getPath() {
    	return propsFile.getAbsolutePath();
    }

    /**
     * Check if a key exists
     * 
     * @param key
     * @return true if found
     */
    public boolean containsKey(String key) {
        return props.containsKey(key);
    }

    /**
     * Removes a specified key
     * 
     * @param key
     */
    public void removeKey(String key) {
        if (props.containsKey(key)) {
            props.remove(key);
            if (comments.containsKey(key)) {
                comments.remove(key);
            }
        }
    }

    /**
     * Gets a string associated to specified key
     * 
     * @param key
     * @return value if found, null otherwise
     */
    public String getString(String key) {
        return this.getString(key, null);
    }

    /**
     * Gets a string associated to specified key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise
     */
    public String getString(String key, String defaults) {
        if (containsKey(key)) {
            return props.get(key);
        }
        return defaults;
    }

    /**
     * Sets value for key
     * 
     * @param key
     * @param value
     */
    public void setString(String key, String value) {
        props.put(key, value == null ? "null" : value);
    }

    /**
     * Sets value for key with given comments
     * 
     * @param key
     * @param value
     * @param comment
     */
    public void setString(String key, String value, String... comments) {
        props.put(key, value == null ? "null" : value);
        addComment(key, comments);
    }

    /**
     * Gets integer value for key
     * 
     * @param key
     * @return value if found, -1 if error occurred or not found
     */
    public int getInt(String key) {
        return this.getInt(key, -1);
    }

    /**
     * Gets integer value for key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise
     */
    public int getInt(String key, int defaults) {
        int value = defaults;
        if (containsKey(key)) {
            try {
                value = Integer.parseInt(getString(key));
            } catch (NumberFormatException NFE) {
                value = defaults;
                Logman.logWarning("A NumberFormatException occurred in File: '" + filepath + "' @ KEY: " + key);
            }
        }
        return value;
    }

    /**
     * Sets an integer value for key
     * 
     * @param key
     * @param value
     */
    public void setInt(String key, int value) {
        props.put(key, String.valueOf(value));
    }

    /**
     * Sets an integer value for key with given comments
     * 
     * @param key
     * @param value
     * @param comments
     */
    public void setInt(String key, int value, String... comments) {
        props.put(key, String.valueOf(value));
        addComment(key, comments);
    }

    /**
     * Gets a double value for key
     * 
     * @param key
     * @return value if found, -1 if an error occurred or not found
     */
    public double getDouble(String key) {
        return this.getDouble(key, -1);
    }

    /**
     * Gets a double value for key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise
     */
    public double getDouble(String key, double defaults) {
        double value = defaults;
        if (containsKey(key)) {
            try {
                value = Double.parseDouble(getString(key));
            } catch (NumberFormatException NFE) {
                value = defaults;
                Logman.logWarning("A NumberFormatException occurred in File: '" + filepath + "' @ KEY: " + key);
            }
        }
        return value;
    }

    /**
     * Sets a double value for key
     * 
     * @param key
     * @param value
     */
    public void setDouble(String key, double value) {
        props.put(key, String.valueOf(value));
    }

    /**
     * Sets an double value for key with given comments
     * 
     * @param key
     * @param value
     * @param comments
     */
    public void setDouble(String key, int value, String... comments) {
        props.put(key, String.valueOf(value));
        addComment(key, comments);
    }

    /**
     * Gets a long value for key
     * 
     * @param key
     * @return value if found, -1 if an error occurred or not found
     */
    public long getLong(String key) {
        return this.getLong(key, -1);
    }

    /**
     * Gets a long value for key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise
     */
    public long getLong(String key, long defaults) {
        long value = defaults;
        if (containsKey(key)) {
            try {
                value = Long.parseLong(getString(key));
            } catch (NumberFormatException NFE) {
                value = defaults;
                Logman.logWarning("A NumberFormatException occurred in File: '" + filepath + "' @ KEY: " + key);
            }
        }
        return value;
    }

    /**
     * Sets a long value for key
     * 
     * @param key
     * @param value
     */
    public void setLong(String key, long value) {
        props.put(key, String.valueOf(value));
    }

    /**
     * Sets an long value for key with given comments
     * 
     * @param key
     * @param value
     * @param comment
     */
    public void setLong(String key, long value, String... comments) {
        props.put(key, String.valueOf(value));
        addComment(key, comments);
    }

    /**
     * Gets a float value for key
     * 
     * @param key
     * @return value if found, -1 if an error occurred or not found
     */
    public float getFloat(String key) {
        return this.getFloat(key, -1);
    }

    /**
     * Gets a float value for key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise
     */
    public float getFloat(String key, float defaults) {
        float value = defaults;
        if (containsKey(key)) {
            try {
                value = Float.parseFloat(getString(key));
            } catch (NumberFormatException NFE) {
                value = -1;
                Logman.logWarning("A NumberFormatException occurred in File: '" + filepath + "' @ KEY: " + key);
            }
        }
        return value;
    }

    /**
     * Sets a float value for key
     * 
     * @param key
     * @param value
     */
    public void setFloat(String key, float value) {
        props.put(key, String.valueOf(value));
    }

    /**
     * Sets an float value for key with given comments
     * 
     * @param key
     * @param value
     * @param comment
     */
    public void setFloat(String key, float value, String... comments) {
        props.put(key, String.valueOf(value));
        addComment(key, comments);
    }

    /**
     * Gets a boolean value for key
     * 
     * @param key
     * @return value if found, false if error
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * Gets a boolean value for key
     * 
     * @param key
     * @param defaults
     * @return value if found, defaults otherwise, false if error
     */
    public boolean getBoolean(String key, boolean defaults) {
        if (containsKey(key)) {
            return Boolean.parseBoolean(getString(key));
        }
        return defaults;
    }

    /**
     * Sets a boolean value for key
     * 
     * @param key
     * @param value
     */
    public void setBoolean(String key, boolean value) {
        props.put(key, String.valueOf(value));
    }

    /**
     * Sets an boolean value for key with given comments
     * 
     * @param key
     * @param value
     * @param comment
     */
    public void setBoolean(String key, boolean value, String... comments) {
        props.put(key, String.valueOf(value));
        addComment(key, comments);
    }

    /**
     * Gets a character value for key
     * 
     * @param key
     * @return value if found, null otherwise
     */
    public Character getCharacter(String key) {
        return this.getCharacter(key, null);
    }

    /**
     * Gets a character value for key
     * 
     * @param key
     * @param defaults
     *            value returned when no value is set
     * @return value if found, default otherwise
     */
    public Character getCharacter(String key, Character defaults) {
        String val = getString(key);
        if (val != null && val.length() > 0) {
            return val.charAt(0);
        }
        return defaults;
    }

    /**
     * Sets a character value for key
     * 
     * @param key
     * @param value
     */
    public void setCharacter(String key, Character ch) {
        props.put(key, String.valueOf(ch));
    }

    /**
     * Sets an character value for key with given comments
     * 
     * @param key
     * @param value
     * @param comment
     */
    public void setCharacter(String key, Character ch, String... comments) {
        props.put(key, String.valueOf(ch));
        addComment(key, comments);
    }

    /**
     * Adds a comment near the key specified
     * 
     * @param key
     * @param comment
     */
    private void addComment(String key, String... comments) {
        for (int i = 0; i < comments.length; i++) {
            if (!comments[i].startsWith(";") && !comments[i].startsWith("#")) {
                comments[i] = ";" + comments[i];
            }
        }
        this.comments.put(key, comments);
    }
}
