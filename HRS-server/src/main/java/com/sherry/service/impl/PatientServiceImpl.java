package com.sherry.service.impl;

import com.sherry.constant.MessageConstant;
import com.sherry.dto.PatientDTO;
import com.sherry.dto.PatientUpdateDTO;
import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Patient;
import com.sherry.exception.PasswordErrorException;
import com.sherry.exception.AccountNotFoundException;
import com.sherry.mapper.PatientMapper;
import com.sherry.service.PatientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;


@Service
public class PatientServiceImpl implements PatientService {


    @Autowired
    private PatientMapper patientMapper;

    /**
     * 患者登录
     * @param userLoginDTO
     * @return
     */
    public Patient login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Patient patient = patientMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (patient == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        }

        //密码比对
        //对输入的用户密码进行MD5加密处理
        password = DigestUtils.md5DigestAsHex((username+password).getBytes());

        if (!password.equals(patient.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return patient;
    }

    /**
     * 注册患者账号
     * @param patientDTO
     */
    public void save(PatientDTO patientDTO) {

        Patient patient = new Patient();
        BeanUtils.copyProperties(patientDTO,patient);
        //初始化失约次数
        patient.setNoShowCount(0);

        //存入加密后的密码
        String username = patient.getUsername();
        String password = patient.getPassword();
        password = DigestUtils.md5DigestAsHex((username+password).getBytes());
        patient.setPassword(password);

        //添加患者到数据库
        patientMapper.insert(patient);

    }


    /**
     * 医生修改患者信息
     * @param patientUpdateDTO
     */
    public void updatePatientInfo(PatientUpdateDTO patientUpdateDTO) {
        Patient patient = new Patient();
        BeanUtils.copyProperties(patientUpdateDTO,patient);
        patientMapper.update(patient);
    }
}
