package com.greencrane.filter;

import com.greencrane.utils.FilterUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityFilterTest {

    private static final String CORRECT_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjoicGF5bG9hZCIsImlhdCI6MTU0MjY0OTc4OSwiaXNzIjoiaXNzdWVyIiwic3ViIjoidG9rZW4ifQ.PuJbD1d8JP7fe_QCAv9n8qj_n-jNKWTdsYAkPVcJ7fE";
    private static final String NOT_CORRECT_TOKEN = "FVSJFVW^^TR(*WR&GWF&V&^CD*&C%ZF&*C^VC*^S%C&";
    private static final String NOT_CORRECT_TOKEN_2 = "FVSJFVW^^TR(*WR&GWF&V&^CD*&C%ZF&*C^VC*^S%C&.fssabklfg3rqw970tqw87tqw87dq7bwd.ygasfoayfgauwt23423432fy2fywaooaw";

    @Autowired
    FilterUtils filterUtils;

    @Test
    public void SecurityFilterTest() throws Exception {
        Assert.assertEquals(true, filterUtils.isTokenValid(CORRECT_TOKEN));
        Assert.assertEquals(false, filterUtils.isTokenValid(NOT_CORRECT_TOKEN));
        Assert.assertEquals(false, filterUtils.isTokenValid(NOT_CORRECT_TOKEN_2));
    }
}
