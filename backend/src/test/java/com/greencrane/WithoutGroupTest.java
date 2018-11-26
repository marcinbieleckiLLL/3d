package com.greencrane;


import com.greencrane.consts.OfferType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithoutGroupTest {

    @Test
    public void offerTypeTest() {
        Assert.assertEquals(OfferType.find("fork_lift", 1), OfferType.FORK_LIFT);
        Assert.assertEquals(OfferType.find("beton", 1), OfferType.ALL);
        Assert.assertEquals(OfferType.find("fork_lift", 2), OfferType.ALL);
    }
}
