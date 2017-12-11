package com.ecash.ecashcore.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.enums.HistoryTypeEnum;
import com.ecash.ecashcore.model.HistoryType;
import com.ecash.ecashcore.model.User;
import com.ecash.ecashcore.model.UserHistory;
import com.ecash.ecashcore.repository.HistoryTypeRepository;
import com.ecash.ecashcore.repository.PermissionRepository;
import com.ecash.ecashcore.repository.UserHistoryRepository;
import com.ecash.ecashcore.repository.UserRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.vo.HistoryVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserHistoryRepository userHistoryRepository;

//  @Autowired
//  private RoleRepository roleRepository;
  
  @Autowired
  HistoryTypeRepository historyTypeRepository;

  @Autowired
  PermissionRepository permissionRepository;

  public User getById(String id) {
    return userRepository.getOne(id);
  }

  public User getByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  
  public User getByUsername(String username) {
    return userRepository.findByUsername(username);
  }
  
  public User save(User user) {
    return userRepository.save(user);
  }
  
  public Iterable<User> findAll(Predicate predicate, Pageable pageable) {
    return userRepository.findAll(predicate, pageable);
  }
  
  public User changePassword(User user, String newPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(newPassword);
    user.encodePassword(passwordEncoder);
    // create user history
    HistoryType historyType = historyTypeRepository.findOne(HistoryTypeEnum.PASSWORD_CHANGED.toString());
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.PASSWORD, "old password");
    history.getNext().put(StringConstant.PASSWORD, "new password");
    UserHistory userHistory = new UserHistory(user, null, historyType, JsonUtils.objectToJsonString(history));
    userHistoryRepository.save(userHistory);

    return userRepository.save(user);
  }
  
  public User resetPassword(User user, String currentUsername, String newPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(newPassword);
    user.encodePassword(passwordEncoder);
    
    // create user history
    User createdBy = userRepository.findByUsername(currentUsername);
    HistoryType historyType = historyTypeRepository.findOne(HistoryTypeEnum.PASSWORD_CHANGED.toString());
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.PASSWORD, "old password");
    history.getNext().put(StringConstant.PASSWORD, "new password");
    UserHistory userHistory = new UserHistory(user, createdBy, historyType, JsonUtils.objectToJsonString(history));

    userHistoryRepository.save(userHistory);

    return userRepository.save(user);
  }
  
  public User updateStatus(User user, String status, String currentUsername) {
    String historyStringType = null;
    String newStatus = null;
    if (status.equals("ACTIVE")) {
      user.setEnabled(false);
      historyStringType = HistoryTypeEnum.LOCKED.toString();
      newStatus = "INACTIVE";
    } else {
      user.setEnabled(true);
      historyStringType = HistoryTypeEnum.UNLOCKED.toString();
      newStatus = "ACTIVE";
    }
    // create user history
    User createdBy = userRepository.findByUsername(currentUsername);
    HistoryType historyType = historyTypeRepository.findOne(historyStringType);
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.STATUS, status);
    history.getNext().put(StringConstant.STATUS, newStatus);
    UserHistory userHistory = new UserHistory(user, createdBy, historyType, JsonUtils.objectToJsonString(history));

    userHistoryRepository.save(userHistory);

    return userRepository.save(user); 
  }

  @SuppressWarnings("unchecked") 
  public User updateSetting(User user, String key, String value) {
    String oldSetting = user.getSetting();
    if (oldSetting == null || oldSetting.equals("")) {
      oldSetting = "{}";
    }
    Map<String, Object> jsonObject = JsonUtils.jsonStringToObject(oldSetting, Map.class);
    jsonObject.put(key, value);
    String newSetting = JsonUtils.objectToJsonString(jsonObject);
    // create user history
    HistoryType historyType = historyTypeRepository.findOne(HistoryTypeEnum.UPDATED.toString());
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.SETTING, oldSetting);
    history.getNext().put(StringConstant.SETTING, newSetting);
    UserHistory userHistory = new UserHistory(user, null, historyType, JsonUtils.objectToJsonString(history));

    userHistoryRepository.save(userHistory);

    user.setSetting(newSetting);
    return userRepository.save(user); 
  }
}
