package com.sinvon.goldfoilfontapi.service;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author : sinvon
 * @since :  2024/12/8 上午1:12
 */
public interface GoldFoilService {
    public File createGoldFoilImage(String text);
}
