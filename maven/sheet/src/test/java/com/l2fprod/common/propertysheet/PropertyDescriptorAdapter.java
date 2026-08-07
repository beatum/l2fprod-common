/**
 * Test helper PropertyDescriptorAdapter for package-correct test sources.
 */
package com.l2fprod.common.propertysheet;

import com.l2fprod.common.beans.ExtendedPropertyDescriptor;

import java.beans.PropertyDescriptor;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * PropertyDescriptorAdapter.<br>
 *
 */
class PropertyDescriptorAdapter extends AbstractProperty {

  private PropertyDescriptor descriptor;
  
  public PropertyDescriptorAdapter() {
    super();
  }
  
  public PropertyDescriptorAdapter(PropertyDescriptor descriptor) {
    this();
    setDescriptor(descriptor);
  }

  public void setDescriptor(PropertyDescriptor descriptor) {
    this.descriptor = descriptor;
  }
  
  public PropertyDescriptor getDescriptor() {
    return descriptor;
  }
  
  public String getName() {
    return descriptor.getName();
  }
  
  public String getDisplayName() {
    return descriptor.getDisplayName();
  }
  
  public String getShortDescription() {
    return descriptor.getShortDescription();
  }

  public Class<?> getType() {
    return descriptor.getPropertyType();
  }

  public Object clone() {
    PropertyDescriptorAdapter clone = new PropertyDescriptorAdapter(descriptor);
    clone.setValue(getValue());
    return clone;
  }
  
  public void readFromObject(Object object) {
    try {
      Method method = descriptor.getReadMethod();
      if (method != null) {
        setValue(method.invoke(object));
      }
    } catch (Exception e) {
      String message = "Got exception when reading property " + getName();
      if (object == null) {
        message += ", object was 'null'";
      } else {
        message += ", object was " + object;
      }
      throw new RuntimeException(message, e);
    }
  }
  
  public void writeToObject(Object object) {
    try {
      Method method = descriptor.getWriteMethod();
      if (method != null) {
        method.invoke(object, getValue());
      }
    } catch (Exception e) {
      // let PropertyVetoException go to the upper level without logging
      if (e instanceof InvocationTargetException &&
        ((InvocationTargetException)e).getTargetException() instanceof PropertyVetoException) {
        throw new RuntimeException(((InvocationTargetException)e).getTargetException());
      }

      String message = "Got exception when writing property " + getName();
      if (object == null) {
        message += ", object was 'null'";
      } else {
        message += ", object was " + object;
      }
      throw new RuntimeException(message, e);
    }
  }
  
  public boolean isEditable() {
    return descriptor.getWriteMethod() != null;
  }

  public String getCategory() {
    if (descriptor instanceof ExtendedPropertyDescriptor) {
      return ((ExtendedPropertyDescriptor)descriptor).getCategory();
    } else {
      return null;
    }
  }
  
}

