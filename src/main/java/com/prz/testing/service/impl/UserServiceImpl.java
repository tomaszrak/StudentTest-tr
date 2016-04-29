package com.prz.testing.service.impl;

import com.prz.testing.criteria.Criteria;
import com.prz.testing.domain.Role;
import com.prz.testing.domain.User;
import com.prz.testing.enumerate.RoleName;
import com.prz.testing.enumerate.Status;
import com.prz.testing.repository.RoleRepository;
import com.prz.testing.repository.UserRepository;
import com.prz.testing.service.MailSendService;
import com.prz.testing.service.UserService;
import com.prz.testing.util.LangUtil;
import com.prz.testing.util.LogUtil;
import com.prz.testing.util.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Roman on 12.09.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private LogUtil logger;

    @Autowired
    private LangUtil langUtil;

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) throws SQLException {
        return userRepository.getById(id);
    }

    public User getUserByIndex(Integer indexNumber) throws SQLException {
        return userRepository.getByIndexNumber(indexNumber);
    }

    public void saveOrUpdateUser(User user) throws SQLException {
        if (null == user.getId()) {
            user.setRole(roleRepository.getByName(user.getRole().getName()));
            user.setCreateDate(new Date());
            user.setStatus(Status.ACTIVE);
            PasswordGenerator pg = new PasswordGenerator();
            String phash = pg.generateMD5Hash();
            user.setPassword(phash);
            userRepository.save(user);
            mailSendService.sendMail(user.getEmail(), langUtil.getProperty("st.mail.register.title"),
                    langUtil.getProperty("st.mail.userWasRegistered", pg.password));
        } else {
            userRepository.update(user);
        }
    }

    public void saveRole() throws SQLException {
        Role role = new Role();
        role.setCreateDate(new Date());
        role.setName(RoleName.STUDENT);
        roleRepository.save(role);
    }

    public void blockUser(User user) throws SQLException {
    }

    public void activateUser(User user) throws SQLException {

    }

    public List<User> getAllUsersPaginated(Criteria criteria) throws SQLException {
        return userRepository.getPaginated(criteria);
    }

    public List<User> getUsersWithCriteriaPaginated(Criteria criteria) {
        if (null == criteria.getOrderWay() || null == criteria.getOrderParam()) {
            criteria.setOrderWay("ASC");
            criteria.setOrderParam("lastName");
        }
        return userRepository.getWithCriteriaPaginated(criteria);
    }

    public Integer countUsersWithCriteria(Criteria criteria) {
        return userRepository.countWithCriteria(criteria);
    }

    public void updateUser(User user) throws SQLException {
        userRepository.update(user);
    }

    public void deleteUser(Long id) throws SQLException {
        userRepository.delete(id);
    }

    public void resetPassword(User user) throws SQLException {
        String password = new PasswordGenerator().password;
        mailSendService.sendMail(user.getEmail(), langUtil.getProperty("st.mail.passwordReset"),
                langUtil.getProperty("st.mail.passwordResetNewPassword", password));
        userRepository.updateUserPassword(user.getId(), password);
    }

}
