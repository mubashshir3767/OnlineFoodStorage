package com.example.onlinefoodstorage.utils;

import com.example.onlinefoodstorage.exceptions.NotFoundException;

import java.util.Optional;

public class OptionalEntityUtil {
    public static <T> T getIfPresent(Optional<T> optionalT){
        if (optionalT.isPresent()){
            return optionalT.get();
        }
        throw new NotFoundException("THE DATA COULD NOT FIND");
    }
}
