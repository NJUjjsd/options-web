package com.jjsd.options.util;

import net.sf.json.JSONObject;
import org.junit.Test;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

/**
 * Created by ${zrz} on 2017/9/11.
 */
public class ETFInfoUtilTest {
    @Test
    public void getETFTrade() throws Exception {
        assertNotNull(ETFInfoUtil.getETFTrade());
    }

}