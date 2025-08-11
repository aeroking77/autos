package com.mx.Autos.service;


import org.springframework.stereotype.Service;

import com.mx.Autos.dao.AutoDao;

@Service
public class AutoService {
    AutoDao dao;
    
    public AutoService(AutoDao autoDao) {
        dao = autoDao;
    }
}
