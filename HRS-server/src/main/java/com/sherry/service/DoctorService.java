package com.sherry.service;

import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Doctor;

public interface DoctorService {

    /**
     * 医生登录
     * @param userLoginDTO
     * @return
     */
    Doctor login(UserLoginDTO userLoginDTO);
}
