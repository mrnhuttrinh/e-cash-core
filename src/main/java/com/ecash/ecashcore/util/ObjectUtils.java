package com.ecash.ecashcore.util;

import java.util.Date;

import com.ecash.ecashcore.model.cms.Address;
import com.ecash.ecashcore.model.cms.Card;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.IdentifyDocument;

public class ObjectUtils {

  public static boolean isCustomerEqual(Customer source, Customer target) {
    return isEqual(source.getScmsMemberCode(), target.getScmsMemberCode())
        && isEqual(source.getFirstName(), target.getFirstName()) && isEqual(source.getLastName(), target.getLastName())
        && isEqual(source.getGender(), target.getGender()) && isEqual(source.getDateOfBirth(), target.getDateOfBirth())
        && isEqual(source.getPhone1(), target.getPhone1()) && isEqual(source.getPhone2(), target.getPhone2())
        && isEqual(source.getEmail(), target.getEmail())
        && isEqual(source.getDateBecameCustomer(), target.getDateBecameCustomer())
        && isEqual(source.getCountryCode(), target.getCountryCode())
        && isEqual(source.getOccupation(), target.getOccupation()) && isEqual(source.getTitle(), target.getTitle())
        && isEqual(source.getPosition(), target.getPosition());
  }

  public static boolean isAddressEqual(Address source, Address target) {
    return isEqual(source.getCountry(), target.getCountry()) && isEqual(source.getLine1(), target.getLine1())
        && isEqual(source.getLine2(), target.getLine2());
  }

  public static boolean isIdentifyDocumentEqual(IdentifyDocument source, IdentifyDocument target) {
    return isEqual(source.getDateOfIssue(), target.getDateOfIssue())
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
}
