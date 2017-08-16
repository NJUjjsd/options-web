package com.jjsd.options.service.stub;

import com.jjsd.options.entity.KInfo;
import com.jjsd.options.entity.KInfoSimple;
import com.jjsd.options.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by a297 on 17/8/15.
 */
@Service
public class MarketServiceStub implements MarketService{


    static ArrayList<KInfoSimple> simple;

    @Override
    public ArrayList<KInfoSimple> getWeekK() {
        return null;
    }

    @Override
    public ArrayList<KInfoSimple> getMonthK() {
        return null;
    }

    @Override
    public ArrayList<KInfoSimple> getDayK() {
        return null;
    }
}
