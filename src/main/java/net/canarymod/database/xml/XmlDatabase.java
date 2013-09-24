package net.canarymod.database.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseAccessException;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseTableInconsistencyException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Represent access to an XML database
 *
 * @author Chris (damagefilter)
 */
public class XmlDatabase extends Database {

    private XmlDatabase() {
        File path = new File("db/");

        if (!path.exists()) {
            path.mkdirs();
        }
    }

    private static XmlDatabase instance;

    public static XmlDatabase getInstance() {
        if (instance == null) {
            instance = new XmlDatabase();
        }
        return instance;
    }

    /** Used to serialize the XML data into a bytestream */
    private XMLOutputter xmlSerializer = new XMLOutputter(Format.getPrettyFormat().setExpandEmptyElements(true).setOmitDeclaration(true).setOmitEncoding(true).setLineSeparator("\n"));

    private SAXBuilder fileBuilder = new SAXBuilder();

    @Override
    public void insert(DataAccess data) throws DatabaseWriteException {
        File file = new File("db/" + data.getName() + ".xml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                initFile(file, data.getName());
            }
            catch (IOException e) {
                throw new DatabaseWriteException(e.getMessage());
            }
        }
        Document dbTable;

        try {
            FileInputStream in = new FileInputStream(file);
            dbTable = fileBuilder.build(in);
            in.close();
            insertData(file, data, dbTable);
        }
        catch (JDOMException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (DatabaseTableInconsistencyException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
    }

    @Override
    public void load(DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        File file = new File("db/" + data.getName() + ".xml");

        if (!file.exists()) {
            throw new DatabaseReadException("Table " + data.getName() + " does not exist!");
        }
        if (fieldNames.length != fieldValues.length) {
            throw new DatabaseReadException("Field and Value field lenghts are inconsistent!");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            Document table = fileBuilder.build(in);
            in.close();

            loadData(data, table, fieldNames, fieldValues);
        }
        catch (JDOMException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }
        catch (DatabaseAccessException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }
    }

    @Override
    public void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, String[] fieldNames, Object[] fieldValues) throws DatabaseReadException {
        File file = new File("db/" + typeTemplate.getName() + ".xml");

        if (!file.exists()) {
            throw new DatabaseReadException("Table " + typeTemplate.getName() + " does not exist!");
        }
        if (fieldNames.length != fieldValues.length) {
            throw new DatabaseReadException("Field and Value field lenghts are inconsistent!");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            Document table = fileBuilder.build(in);
            in.close();

            loadAllData(typeTemplate, datasets, table, fieldNames, fieldValues);
        }
        catch (JDOMException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }
        catch (DatabaseAccessException e) {
            throw new DatabaseReadException(e.getMessage(), e);
        }

    }

    @Override
    public void update(DataAccess data, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        File file = new File("db/" + data.getName() + ".xml");

        if (!file.exists()) {
            throw new DatabaseWriteException("Table " + data.getName() + " does not exist!");
        }
        if (fieldNames.length != fieldValues.length) {
            throw new DatabaseWriteException("Field and Value field lenghts are inconsistent!");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            Document table = fileBuilder.build(in);
            in.close();

            updateData(file, table, data, fieldNames, fieldValues);
        }
        catch (JDOMException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (DatabaseTableInconsistencyException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(String tableName, String[] fieldNames, Object[] fieldValues) throws DatabaseWriteException {
        File file = new File("db/" + tableName + ".xml");

        if (!file.exists()) {
            throw new DatabaseWriteException("Table " + tableName + " does not exist!");
        }
        if (fieldNames.length != fieldValues.length) {
            throw new DatabaseWriteException("Field and Value field lenghts are inconsistent!");
        }
        try {
            FileInputStream in = new FileInputStream(file);
            Document table = fileBuilder.build(in);
            in.close();

            removeData(file, table, fieldNames, fieldValues);
        }
        catch (JDOMException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
    }

    @Override
    public void updateSchema(DataAccess data) throws DatabaseWriteException {
        File file = new File("db/" + data.getName() + ".xml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                initFile(file, data.getName());
            }
            catch (IOException e) {
                throw new DatabaseWriteException(e.getMessage(), e);
            }
        }
        try {
            FileInputStream in = new FileInputStream(file);
            Document table = fileBuilder.build(in);
            in.close();

            HashSet<Column> tableLayout = data.getTableLayout();

            for (Element element : table.getRootElement().getChildren()) {
                addFields(element, tableLayout);
                removeFields(element, tableLayout);
            }
            write(file.getPath(), table);
        }
        catch (JDOMException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
        catch (DatabaseTableInconsistencyException e) {
            throw new DatabaseWriteException(e.getMessage(), e);
        }
    }

    private void initFile(File file, String rootName) throws IOException {
        Document doc = new Document();

        doc.setRootElement(new Element(rootName));
        write(file.getPath(), doc);
    }

    /**
     * Adds new fields to the Element, according to the given layout set.
     *
     * @param element
     * @param layout
     */
    private void addFields(Element element, HashSet<Column> layout) {
        for (Column column : layout) {
            boolean found = false;

            for (Element child : element.getChildren()) {
                if (child.getName().equals(column.columnName())) {
                    found = true;
                }
            }
            if (!found) {
                Element col = new Element(column.columnName());

                col.setAttribute("auto-increment", String.valueOf(column.autoIncrement()));
                col.setAttribute("data-type", column.dataType().name());
                col.setAttribute("column-type", column.columnType().name());
                col.setAttribute("is-list", String.valueOf(column.isList()));
                element.addContent(col);
            }
        }
    }

    /**
     * Removes fields from the given element that are not contained in the given layout
     *
     * @param element
     * @param layout
     */
    private void removeFields(Element element, HashSet<Column> layout) {
        for (Element child : element.getChildren()) {
            boolean found = false;

            for (Column column : layout) {
                if (child.getName().equals(column.columnName())) {
                    found = true;
                }
            }
            if (!found) {
                child.detach();
            }
        }
    }

    /**
     * Inserts data into the XML file. This does NOT update data.
     * It will create a new entry if there isn't the exact same already present
     *
     * @param file
     * @param data
     * @param dbTable
     *
     * @throws IOException
     * @throws DatabaseTableInconsistencyException
     *
     */
    private void insertData(File file, DataAccess data, Document dbTable) throws IOException, DatabaseTableInconsistencyException {
        HashMap<Column, Object> entry = data.toDatabaseEntryList();

        if (data.isInconsistent()) {
            // Just an extra precaution
            throw new DatabaseTableInconsistencyException("DataAccess is marked inconsistent!");
        }
        Element set = new Element("entry");

        for (Column column : entry.keySet()) {

            Element col = new Element(column.columnName());

            col.setAttribute("auto-increment", String.valueOf(column.autoIncrement()));
            col.setAttribute("data-type", column.dataType().name());
            col.setAttribute("column-type", column.columnType().name());
            col.setAttribute("is-list", String.valueOf(column.isList()));
            addToElement(dbTable, col, entry.get(column), column);
            set.addContent(col);

            boolean foundDupe = false;

            for (Element c : dbTable.getRootElement().getChildren()) {
                if (elementEquals(set, c)) {
                    foundDupe = true;
                }
            }
            if (!foundDupe) {
            }
        }
        dbTable.getRootElement().addContent(set);
        write(file.getPath(), dbTable);
    }

    /**
     * Updates an already existing element in the document.
     * IMPORTANT: the lengths of fields and content array must have been checked before this method is called!
     *
     * @param file
     * @param table
     * @param fields
     * @param values
     *
     * @throws IOException
     * @throws DatabaseTableInconsistencyException
     *
     * @throws DatabaseWriteException
     */
    private void updateData(File file, Document table, DataAccess data, String[] fields, Object[] values) throws IOException, DatabaseTableInconsistencyException, DatabaseWriteException {
        boolean hasUpdated = false;
        for (Element element : table.getRootElement().getChildren()) {

            int equalFields = 0;

            for (int i = 0; i < fields.length; ++i) {
                Element child = element.getChild(fields[i]);

                if (child != null) {
                    if (child.getText().equals(String.valueOf(values[i]))) {
                        equalFields++;
                    }
                }
            }
            if (equalFields != fields.length) {
                continue; // Not the entry we're looking for
            }

            if (data.isInconsistent()) {
                // Just an extra precaution
                throw new DatabaseTableInconsistencyException("DataAccess is marked inconsistent!");
            }

            HashMap<Column, Object> dataSet = data.toDatabaseEntryList();
            for (Column column : dataSet.keySet()) {
                Element child = element.getChild(column.columnName());

                if (child == null) {
                    throw new DatabaseTableInconsistencyException("Column " + column.columnName() + " does not exist. Update table schema or fix DataAccess!");
                }
                // Do not change auto-increment fields
                if (column.autoIncrement()) {
                    continue;
                }
                addToElement(table, child, dataSet.get(column), column);
                hasUpdated = true;
            }
        }
        if (hasUpdated) {
            write(file.getPath(), table);
        }
        else {
            // No fields found, that means it is a new entry
            insert(data);
        }
    }

    private void removeData(File file, Document table, String[] fields, Object[] values) throws IOException {
        ArrayList<Element> toremove = new ArrayList<Element>();
        for (Element element : table.getRootElement().getChildren()) {
            int equalFields = 0;

            for (int i = 0; i < fields.length; ++i) {
                Element child = element.getChild(fields[i]);

                if (child != null) {
                    if (child.getText().equals(String.valueOf(values[i]))) {
                        equalFields++;
                    }
                }
            }
            if (equalFields != fields.length) {
                continue; // Not the entry we're looking for
            }
            // table.getRootElement().removeContent(element);
            toremove.add(element);
        }
        for (Element e : toremove) {
            e.detach();
        }
        write(file.getPath(), table);
    }

    private void loadData(DataAccess data, Document table, String[] fields, Object[] values) throws DatabaseAccessException {
        for (Element element : table.getRootElement().getChildren()) {
            int equalFields = 0;

            for (int i = 0; i < fields.length; ++i) {
                Element child = element.getChild(fields[i]);

                if (child != null) {
                    if (child.getText().equals(String.valueOf(values[i]))) {
                        equalFields++;
                    }
                }
            }
            if (equalFields != fields.length) {
                continue; // Not the entry we're looking for
            }
            HashMap<String, Object> dataSet = new HashMap<String, Object>();
            for (Element child : element.getChildren()) {
                DataType type = DataType.fromString(child.getAttributeValue("data-type"));
                addTypeToMap(child, dataSet, type);
            }
            data.load(dataSet);
            return;
        }
    }

    private void loadAllData(DataAccess template, List<DataAccess> datasets, Document table, String[] fields, Object[] values) throws DatabaseAccessException {
        for (Element element : table.getRootElement().getChildren()) {
            int equalFields = 0;

            for (int i = 0; i < fields.length; ++i) {
                Element child = element.getChild(fields[i]);

                if (child != null) {
                    if (child.getText().equals(String.valueOf(values[i]))) {
                        equalFields++;
                    }
                }
            }
            if (equalFields != fields.length) {
                continue; // Not the entry we're looking for
            }
            HashMap<String, Object> dataSet = new HashMap<String, Object>();

            for (Element child : element.getChildren()) {
                DataType type = DataType.fromString(child.getAttributeValue("data-type"));

                addTypeToMap(child, dataSet, type);
            }
            DataAccess da = template.getInstance();

            da.load(dataSet);
            datasets.add(da);
        }
    }

    /**
     * Performs a field-by-field comparison for the two given Contents.
     * First they must be of type Element and then the fields are checked against each other.
     * If the number of equal fields does not match the number of child elements ot Content a,
     * this method will return false, true otherwise
     *
     * @param a
     * @param b
     *
     * @return
     */
    private boolean elementEquals(Content a, Content b) {
        if (!(b instanceof Element)) {
            return false;
        }
        if (!(a instanceof Element)) {
            return false;
        }
        if (a == b) {
            return true;
        }

        Element elB = (Element) b;
        Element elA = (Element) a;

        if (elA.getContentSize() != elB.getContentSize()) {
            return false;
        }
        int equalHits = 0;

        for (Element el : elA.getChildren()) {
            for (Element other : elB.getChildren()) {
                if (el.getName().equals(other.getName())) {
                    if (el.getText().equalsIgnoreCase(other.getText())) {
                        equalHits++;
                    }
                }
            }
        }
        return equalHits == elA.getChildren().size();
    }

    /**
     * Generates the next auto-increment ID for this table
     *
     * @param doc
     * @param col
     *
     * @return
     *
     * @throws DatabaseTableInconsistencyException
     *
     */
    private int getIncrementId(Document doc, Column col) throws DatabaseTableInconsistencyException {
        // Search from last to first content entry for a valid element
        int id;
        int index = doc.getRootElement().getChildren().size() - 1;

        if (index < 0) {
            // No data in this document yet, start at 1
            return 1;
        }
        Element c = doc.getRootElement().getChildren().get(index);

        try {
            String num = c.getChildText(col.columnName());

            if (num != null) {
                id = Integer.valueOf(num);
                id++;
                return id;
            }
            else {
                // That means there is no data;
                return 1;
            }
        }
        catch (NumberFormatException e) {
            throw new DatabaseTableInconsistencyException(col.columnName() + "is not an incrementable field. Fix your DataAccess!");
        }
    }

    /**
     * Add data to a data set from the given xml element and type
     *
     * @param child
     * @param dataSet
     * @param type
     */
    private void addTypeToMap(Element child, HashMap<String, Object> dataSet, DataType type) {
        switch (type) {
            case BYTE:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Byte> byteList = new ArrayList<Byte>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Byte.parseByte(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Byte.parseByte(child.getText()));
                }
                break;

            case DOUBLE:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Double> byteList = new ArrayList<Double>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Double.parseDouble(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Double.parseDouble(child.getText()));
                }
                break;

            case FLOAT:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Float> byteList = new ArrayList<Float>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Float.parseFloat(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Float.parseFloat(child.getText()));
                }
                break;

            case INTEGER:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Integer> byteList = new ArrayList<Integer>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Integer.parseInt(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Integer.parseInt(child.getText()));
                }
                break;

            case LONG:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Long> byteList = new ArrayList<Long>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Long.parseLong(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Long.parseLong(child.getText()));
                }
                break;

            case SHORT:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Short> byteList = new ArrayList<Short>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Short.parseShort(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Short.parseShort(child.getText()));
                }
                break;

            case STRING:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<String> byteList = new ArrayList<String>();

                    for (Element el : child.getChildren()) {
                        byteList.add(el.getText());
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), child.getText());
                }
                break;

            case BOOLEAN:
                if (Boolean.valueOf(child.getAttributeValue("is-list"))) {
                    ArrayList<Boolean> byteList = new ArrayList<Boolean>();

                    for (Element el : child.getChildren()) {
                        byteList.add(Boolean.valueOf(el.getText()));
                    }
                    dataSet.put(child.getName(), byteList);
                }
                else {
                    dataSet.put(child.getName(), Boolean.valueOf(child.getText()));
                }
                break;

            default:
                break;
        }
    }

    /**
     * Adds data to an element
     *
     * @param element
     * @param obj
     *
     * @throws DatabaseTableInconsistencyException
     *
     */
    private void addToElement(Document doc, Element element, Object obj, Column col) throws DatabaseTableInconsistencyException {
        if (col.autoIncrement()) {
            element.setText(String.valueOf(getIncrementId(doc, col)));
        }
        else if (col.isList()) {
            List<?> entries = (List<?>) obj;

            // First detach everything so there won't be dupes
            for (Element el : element.getChildren()) {
                el.detach();
            }
            if (obj == null) {
                return;
            }
            // Add fresh data
            for (Object entry : entries) {
                element.addContent(new Element("list-element").setText(String.valueOf(entry)));
            }
        }
        else {
            element.setText(String.valueOf(obj));
        }
    }

    private void write(String path, Document doc) throws IOException {
        sortElements(doc);
        File file = new File(path);
        RandomAccessFile f = new RandomAccessFile(file.getPath(), "rw");
        f.getChannel().lock();
        f.setLength(0);
        f.write(xmlSerializer.outputString(doc).getBytes(Charset.forName("UTF-8")));
        f.close();
    }

    private void sortElements(Document doc) {
        for (Element e : doc.getRootElement().getChildren()) {
            e.sortChildren(new Comparator<Element>() {
                @Override
                public int compare(Element o1, Element o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        }
    }

}
