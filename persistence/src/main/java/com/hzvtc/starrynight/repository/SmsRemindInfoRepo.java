package com.hzvtc.starrynight.repository;

import com.hzvtc.starrynight.entity.SmsRemind;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 短信待发提醒表CDUQ
 * @Author: fhn
 * @Date: 2018/12/14 16:36
 * @Version: 1.0
 */
public interface SmsRemindInfoRepo extends JpaRepository<SmsRemind, Long> {

}
