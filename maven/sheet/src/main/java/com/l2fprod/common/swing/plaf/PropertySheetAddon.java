package com.l2fprod.common.swing.plaf;

import com.l2fprod.common.propertysheet.PropertySheetTable;

import java.util.Arrays;
import java.util.List;

/**
 * Addon to ensure `PropertySheetTable` has a UI class registered.
 */
public class PropertySheetAddon extends AbstractComponentAddon {

  public PropertySheetAddon() {
    super("PropertySheetTable");
  }

  protected void addBasicDefaults(LookAndFeelAddons addon, List defaults) {
    defaults.addAll(Arrays.asList(new Object[] { PropertySheetTable.UI_CLASS_ID,
      "javax.swing.plaf.basic.BasicTableUI" }));
  }

}
