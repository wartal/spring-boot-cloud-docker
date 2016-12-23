package com.wartal;

import com.wartal.entity.TrendHistoryEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by lwartalski on 21/12/2016.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DataJpaTest
public class TwitterServiceImplIntegrationTest {

    @Autowired
    private TwitterServiceImpl twitterService;

    @Test
    @Sql(value = "/historyData.sql")
    public void shouldFindAllHistoryDataSortDescTest() {
        final List<TrendHistoryEntity> list = twitterService.findAllHistoryData();

        Assert.assertEquals(2, list.size());
        Assert.assertEquals("The Netherlands", list.get(0).getCountry());
        Assert.assertEquals("Poland", list.get(1).getCountry());
    }

    @Test
    @Sql(value = "/historyData.sql")
    public void shouldFindHistoryById() {
        final TrendHistoryEntity entity = twitterService.findHistoryById(Long.valueOf(1));
        Assert.assertNotNull(entity);
        Assert.assertEquals("Poland", entity.getCountry());
    }
}