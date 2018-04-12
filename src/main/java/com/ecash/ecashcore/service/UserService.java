package com.ecash.ecashcore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecash.ecashcore.constants.StringConstant;
import com.ecash.ecashcore.exception.ValidationException;
import com.ecash.ecashcore.model.cms.Account;
import com.ecash.ecashcore.model.cms.Customer;
import com.ecash.ecashcore.model.cms.Role;
import com.ecash.ecashcore.model.cms.User;
import com.ecash.ecashcore.model.cms.UserHistory;
import com.ecash.ecashcore.model.cms.UserHistoryType;
import com.ecash.ecashcore.pojo.CustomerPOJO;
import com.ecash.ecashcore.pojo.GeneralInformationPOJO;
import com.ecash.ecashcore.repository.PermissionRepository;
import com.ecash.ecashcore.repository.RoleRepository;
import com.ecash.ecashcore.repository.UserHistoryRepository;
import com.ecash.ecashcore.repository.UserHistoryTypeRepository;
import com.ecash.ecashcore.repository.UserRepository;
import com.ecash.ecashcore.util.JsonUtils;
import com.ecash.ecashcore.vo.HistoryVO;
import com.ecash.ecashcore.vo.UserVO;
import com.querydsl.core.types.Predicate;

@Service
@Transactional
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserHistoryRepository userHistoryRepository;

   @Autowired
   private RoleRepository roleRepository;

  @Autowired
  UserHistoryTypeRepository userHistoryTypeRepository;

  @Autowired
  PermissionRepository permissionRepository;

  public User getById(String id) {
    return userRepository.getOne(id);
  }

  public User getByEmail(String email) {
    User user = userRepository.findByEmail(email);
    // TODO: should be remvoe
    if (user == null) {
      user = userRepository.findByUsername(email);
    }
    return user;
  }

  public User getByUsername(String username) {
    User user = userRepository.findByUsername(username);
    // TODO: should be remvoe
    if (user == null) {
      user = userRepository.findByEmail(username);
    }
    return user;
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public Iterable<User> findAll(Predicate predicate, Pageable pageable) {
    return userRepository.findAll(predicate, pageable);
  }
  
  public User updateInformation(UserVO data, User createdBy) {
    User user = userRepository.findOne(data.getId());
    if (user == null) {
      throw new ValidationException("User is not exist.");
    }
    // create user history
    UserHistoryType historyType = userHistoryTypeRepository.findOne(UserHistoryType.UPDATED);
    HistoryVO history = new HistoryVO();

    history.getPrevious().put("username", user.getUsername());
    history.getNext().put("username", data.getUsername());
    user.setUsername(data.getUsername());
    
    history.getPrevious().put("lastName", user.getLastName());
    history.getNext().put("lastName", data.getLastName());
    user.setFirstName(data.getFirstName());
    
    history.getPrevious().put("lastName", user.getLastName());
    history.getNext().put("lastName", data.getLastName());
    user.setLastName(data.getLastName());
    
    history.getPrevious().put("email", user.getEmail());
    history.getNext().put("email", data.getEmail());
    user.setEmail(data.getEmail());
    
    List<String>  previousRole = new ArrayList<>();
    for (Role r : user.getRoles()) {
	previousRole.add(r.getName());
    }
    List<String>  nextRole = new ArrayList<>();
    for (Role r : data.getRoles()) {
	nextRole.add(r.getName());
    }
    
    history.getPrevious().put("roles", previousRole);
    history.getNext().put("roles", nextRole);

    user.setRoles(data.getRoles());
    
    userRepository.save(user);

    UserHistory userHistory = new UserHistory(user, createdBy, historyType, JsonUtils.objectToJsonString(history));
    userHistoryRepository.save(userHistory);

    return user;
  }

  public User changePassword(User user, String newPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(newPassword);
    user.encodePassword(passwordEncoder);

    // create user history
    UserHistoryType historyType = userHistoryTypeRepository.findOne(UserHistoryType.PASSWORD_CHANGED);
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.PASSWORD, StringConstant.OLD_PASSWORD);
    history.getNext().put(StringConstant.PASSWORD, StringConstant.NEW_PASSWORD);
    UserHistory userHistory = new UserHistory(user, null, historyType, JsonUtils.objectToJsonString(history));
    userHistoryRepository.save(userHistory);

    return userRepository.save(user);
  }

  public User resetPassword(User user, User createdBy, String newPassword) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(newPassword);
    user.encodePassword(passwordEncoder);

    // create user history
    UserHistoryType historyType = userHistoryTypeRepository.findOne(UserHistoryType.PASSWORD_CHANGED);
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.PASSWORD, StringConstant.OLD_PASSWORD);
    history.getNext().put(StringConstant.PASSWORD, StringConstant.NEW_PASSWORD);
    UserHistory userHistory = new UserHistory(user, createdBy, historyType, JsonUtils.objectToJsonString(history));

    userHistoryRepository.save(userHistory);

    return userRepository.save(user);
  }

  public User updateStatus(User user, String status, User createdBy) {
    String historyStringType = null;

    String newStatus = null;
    if (status.equals("ACTIVE")) {
      user.setEnabled(false);
      historyStringType = UserHistoryType.LOCKED;
      newStatus = "INACTIVE";
    } else {
      user.setEnabled(true);
      historyStringType = UserHistoryType.UNLOCKED;
      newStatus = "ACTIVE";
    }

    // create user history
    UserHistoryType historyType = userHistoryTypeRepository.findOne(historyStringType);
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
    UserHistoryType historyType = userHistoryTypeRepository.findOne(UserHistoryType.UPDATED);
    HistoryVO history = new HistoryVO();
    history.getPrevious().put(StringConstant.SETTING, oldSetting);
    history.getNext().put(StringConstant.SETTING, newSetting);
    UserHistory userHistory = new UserHistory(user, null, historyType, JsonUtils.objectToJsonString(history));

    userHistoryRepository.save(userHistory);

    user.setSetting(newSetting);
    return userRepository.save(user);
  }
  
  public User addNewUser(User newUser, String currentUsername) throws Exception {
      
      User emailUser = userRepository.findByEmail(newUser.getEmail());
      if (emailUser != null) {
          throw new Exception("Email is exists");
      }
      User usernameUser = userRepository.findByUsername(newUser.getUsername());
      if (usernameUser != null) {
          throw new Exception("Username is exists");
      }

      newUser = this.save(newUser);
      
      // create user history
      User currentUser = userRepository.findByUsername(currentUsername);
      String userJson = JsonUtils.objectToJsonString(newUser);
      UserHistoryType historyType = userHistoryTypeRepository.findOne(UserHistoryType.CREATED);
      HistoryVO history = new HistoryVO();
      history.getPrevious().put(StringConstant.PREVIOUS, "");
      history.getNext().put(StringConstant.NEXT, userJson);
      UserHistory userHistory = new UserHistory(newUser, currentUser, historyType, JsonUtils.objectToJsonString(history));
      userHistoryRepository.save(userHistory);
      return newUser;
  }
  
  public GeneralInformationPOJO generalInformation(String currentUsername) throws Exception {
    User user = userRepository.findByUsername(currentUsername);
    if (user == null) {
        throw new Exception("User isn't exists");
    }
    List<CustomerPOJO> customers = new ArrayList<CustomerPOJO>();
    for (Customer customer : user.getCustomers()) {
      // find account
      List<Account> accounts = new ArrayList<Account>();
      for (Account account : customer.getAccounts()) {
        accounts.add(account);
      }
      CustomerPOJO customerVO = new CustomerPOJO(customer, accounts);
      customers.add(customerVO);
    }
    GeneralInformationPOJO generalInformation = new GeneralInformationPOJO(user, customers);
    return generalInformation;
    
  }

}
