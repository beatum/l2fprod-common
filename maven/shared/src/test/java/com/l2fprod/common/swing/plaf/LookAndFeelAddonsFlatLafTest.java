package com.l2fprod.common.swing.plaf;

import static org.junit.Assert.*;

import javax.swing.UIManager;

import org.junit.Test;

import com.l2fprod.common.swing.plaf.metal.MetalLookAndFeelAddons;

/**
 * Tests that FlatLaf look and feel is mapped to MetalLookAndFeelAddons.
 */
public class LookAndFeelAddonsFlatLafTest {

  @Test
  public void testFlatLafMappedToMetalAddon() throws Exception {
    // set a fake FlatLaf look and feel class name
    UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLafLookAndFeel());
    String addon = LookAndFeelAddons.getBestMatchAddonClassName();
    assertEquals(MetalLookAndFeelAddons.class.getName(), addon);
  }
}

