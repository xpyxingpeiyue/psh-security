package com.psh.security.browser.dao;

import com.psh.security.browser.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by peiyue.xing on 2019/8/5 15:42
 *
 * @author peiyue.xing
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
