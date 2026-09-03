package com.l2fprod.common.demo;

import com.l2fprod.common.beans.BaseBeanInfo;
import com.l2fprod.common.beans.editor.ComboBoxPropertyEditor;
import com.l2fprod.common.propertysheet.PropertySheet;
import com.l2fprod.common.propertysheet.PropertySheetPanel;
import com.l2fprod.common.demo.BeanBinder;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.beans.BeanInfo;
import java.awt.Color;
import java.io.File;
import java.util.Date;

/**
 * Small demo to show a PropertySheetPanel with mock data.
 * Run this class to launch a simple Swing demo window.
 */
public class PropertySheetDemo {

  public static void main(String[] args) {
    System.out.println("Starting PropertySheetDemo...");
    SwingUtilities.invokeLater(() -> {
      // Prefer FlatLaf if available, otherwise fallback to system L&F
      String[] flatLafCandidates = new String[] {
        "com.formdev.flatlaf.FlatLightLaf",
        "com.formdev.flatlaf.FlatLaf",
        "com.formdev.flatlaf.FlatLafLookAndFeel"
      };
      boolean lafSet = false;
      for (String candidate : flatLafCandidates) {
        try {
          UIManager.setLookAndFeel(candidate);
          lafSet = true;
          break;
        } catch (Exception e) {
          // ignore and try next
        }
      }
      if (!lafSet) {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
      }

      try {
        JFrame frame = new JFrame("PropertySheet Demo - Mock Data");
        
        // create GUI and show
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      PropertySheetPanel sheet = new PropertySheetPanel();
      sheet.setMode(PropertySheet.VIEW_AS_CATEGORIES);
      sheet.setDescriptionVisible(true);
      sheet.setSortingCategories(true);
      sheet.setSortingProperties(true);

      // create mock bean with sample data
      DemoBean bean = new DemoBean();
      bean.setName("Alice Example");
      bean.setAge(34);
      bean.setActive(true);
      bean.setColor(Color.MAGENTA);
      bean.setFile(new File("."));
      bean.setTimestamp(new Date());
      bean.setGender("male");

      // bind bean to sheet
      BeanInfo beanInfo = new DemoBeanBeanInfo();
      new BeanBinder(bean, sheet, beanInfo);

        frame.getContentPane().add(sheet);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      } catch (Throwable t) {
        t.printStackTrace();
      }
    });
  }

  public static class DemoBean {
    private String name;
    private int age;
    private boolean active;
    private Color color;
    private File file;
    private Date timestamp;
    private String gender;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Color getColor() { return color; }
    public void setColor(Color color) { this.color = color; }

    public File getFile() { return file; }
    public void setFile(File file) { this.file = file; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
  }

  public static class DemoBeanBeanInfo extends BaseBeanInfo {
    public DemoBeanBeanInfo() {
      super(DemoBean.class);
      addProperty("name").setCategory("General");
      addProperty("age").setCategory("General");
      addProperty("active").setCategory("General");
      addProperty("gender").setCategory("General").setPropertyEditorClass(GenderEditor.class);
      addProperty("color").setCategory("Appearance");
      addProperty("file").setCategory("Details");
      addProperty("timestamp").setCategory("Details");
    }
  }

  public static class GenderEditor extends ComboBoxPropertyEditor {
    public GenderEditor() {
      setAvailableValues(new Object[] {
        new Value("male", "Male"),
        new Value("female", "Female")
      });
    }
  }

}
