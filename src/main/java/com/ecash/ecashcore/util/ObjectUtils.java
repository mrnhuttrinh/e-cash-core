package com.ecash.ecashcore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;

public class ObjectUtils {

  protected static final Logger LOGGER = LoggerFactory.getLogger(ObjectUtils.class);

  public static boolean isCustomerEqual(Customer source, Customer target) {
    return isEqual(source.getFirstName(), target.getFirstName()) && isEqual(source.getLastName(), target.getLastName())
        && isEqual(source.getGender(), target.getGender()) && isEqual(source.getDateOfBirth(), target.getDateOfBirth())
        && isEqual(source.getPhone1(), target.getPhone1()) && isEqual(source.getPhone2(), target.getPhone2())
        && isEqual(source.getEmail(), target.getEmail()) && isEqual(source.getCountryCode(), target.getCountryCode())
        && isEqual(source.getOccupation(), target.getOccupation()) && isEqual(source.getTitle(), target.getTitle())
        && isEqual(source.getPosition(), target.getPosition());
  }

  public static boolean isAddressEqual(Address source, Address target) {
    return isEqual(source.getCountry(), target.getCountry()) && isEqual(source.getLine1(), target.getLine1())
        && isEqual(source.getLine2(), target.getLine2());
  }

  public static boolean isIdentifyDocumentEqual(IdentifyDocument source, IdentifyDocument target) {
    return isEqual(source.getNumber(), target.getNumber()) && isEqual(source.getDateOfIssue(), target.getDateOfIssue())
        && isEqual(source.getDateOfExpiry(), target.getDateOfExpiry())
        && isEqual(source.getPlaceOfIssue(), target.getPlaceOfIssue());
  }

  public static boolean isCardEqual(Card source, Card target) {
    return isEqual(source.getEffectiveDate(), target.getEffectiveDate())
        && isEqual(source.getExpiryDate(), target.getExpiryDate()) && isEqual(source.getStatus(), target.getStatus());
  }

  public static boolean isEqual(Object source, Object target) {
    if (source == null) {
      if (target == null) {
        return true;
      }
    } else {
      if (source instanceof Date) {
        return DateTimeUtils.toDefaultFormatString((Date) source)
            .equals(DateTimeUtils.toDefaultFormatString((Date) target));
      }
      if (source.equals(target)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Serialize the given object to a byte array.
   * @param object the object to serialize
   * @return an array of bytes representing the object in a portable fashion
   */
  public static byte[] serialize(Object object) {
    if (object == null) {
      return null;
    }
    ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
    try {
      ObjectOutputStream oos = new ObjectOutputStream(baos);
      oos.writeObject(object);
      oos.flush();
    }
    catch (IOException ex) {
      throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), ex);
    }
    return baos.toByteArray();
  }

  /**
   * Deserialize the byte array into an object.
   * @param bytes a serialized object
   * @return the result of deserializing the bytes
   */
  public static Object deserialize(byte[] bytes) {
    if (bytes == null) {
      return null;
    }
    try {
      ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
      return ois.readObject();
    }
    catch (IOException ex) {
      throw new IllegalArgumentException("Failed to deserialize object", ex);
    }
    catch (ClassNotFoundException ex) {
      throw new IllegalStateException("Failed to deserialize object type", ex);
    }
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T clone(Object obj, Class<T> clazz) {
    return (T) deserialize(serialize(obj));
  }
}
