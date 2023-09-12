package com.sherry.service.impl;

import com.sherry.constant.MessageConstant;
import com.sherry.dto.DoctorDTO;
import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Doctor;
import com.sherry.entity.Patient;
import com.sherry.exception.AccountNotFoundException;
import com.sherry.exception.PasswordErrorException;
import com.sherry.mapper.DoctorMapper;
import com.sherry.service.DoctorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorMapper doctorMapper;
    /**
     * 医生登录
     * @param userLoginDTO
     * @return
     */
    public Doctor login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Doctor  doctor = doctorMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (doctor == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);

        }

        //密码比对
        //对输入的用户密码进行MD5加密处理
        password = DigestUtils.md5DigestAsHex((username+password).getBytes());

        if (!password.equals(doctor.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return doctor;
    }

    /**
     * 注册医生账号
     * @param doctorDTO
     */
    public void save(DoctorDTO doctorDTO) {

        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO,doctor);
        doctor.setModelId(0L);

        //存入加密后的密码
        String username = doctor.getUsername();
        String password = doctor.getPassword();
        password = DigestUtils.md5DigestAsHex((username+password).getBytes());
        doctor.setPassword(password);

        //添加患者到数据库
        doctorMapper.insert(doctor);

    }

    /**
     * 根据id查询医生
     * @param id
     * @return
     */
    public Doctor getById(Long id) {
        Doctor doctor=doctorMapper.getById(id);
        doctor.setPassword("******");
        return doctor;
    }

    /**
     * 修改医生信息
     * @param doctorDTO
     */
    public void update(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        BeanUtils.copyProperties(doctorDTO,doctor);
        doctor.setModelId(null);
        doctorMapper.update(doctor);
    }
}
