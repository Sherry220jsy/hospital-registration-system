package com.sherry.controller.common;


import com.sherry.constant.JwtClaimsConstant;
import com.sherry.constant.UserTypeConstant;
import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Doctor;
import com.sherry.entity.Patient;
import com.sherry.properties.JwtProperties;
import com.sherry.result.Result;
import com.sherry.service.DoctorService;
import com.sherry.service.PatientService;
import com.sherry.utils.JwtUtil;
import com.sherry.vo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/login")
public class CommonController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {

        log.info("员工登录：{}", userLoginDTO);
        UserLoginVO userLoginVO = null;
        if (userLoginDTO.getUserType().equals(UserTypeConstant.DOCTOR)) {
            Doctor doctor = doctorService.login(userLoginDTO);
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.EMP_ID, doctor.getId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);

            userLoginVO=UserLoginVO.builder()
                    .id(doctor.getId())
                    .userName(doctor.getUsername())
                    .name(doctor.getName())
                    .token(token)
                    .build();
        } else if (userLoginDTO.getUserType().equals(UserTypeConstant.PATIENT)) {
            Patient patient = patientService.login(userLoginDTO);
            //登录成功后，生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put(JwtClaimsConstant.EMP_ID, patient.getId());
            String token = JwtUtil.createJWT(
                    jwtProperties.getAdminSecretKey(),
                    jwtProperties.getAdminTtl(),
                    claims);
            userLoginVO=UserLoginVO.builder()
                    .id(patient.getId())
                    .userName(patient.getUsername())
                    .name(patient.getName())
                    .token(token)
                    .build();

        }
        return Result.success(userLoginVO);


    }

}