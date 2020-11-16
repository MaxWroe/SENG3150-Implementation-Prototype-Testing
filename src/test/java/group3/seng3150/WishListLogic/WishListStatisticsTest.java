package group3.seng3150.WishListLogic;

import group3.seng3150.AccountController;
import group3.seng3150.entities.WishListEntry;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import java.util.List;
import java.util.Random;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class WishListStatisticsTest extends TestCase {
    @Autowired EntityManager em;

    @Rule public ErrorCollector collector = new ErrorCollector();
    @Autowired Filter springSecurityFilterChain;
    @Autowired WebApplicationContext ctx;
    
    @Autowired AccountController accountController;
    
    MockMvc mockMvc;

    WishListStatistics wishListStatistics;

    String[] countryCodes = {"A","ABW","AFG","AIA","ALA","ALB","AND"};
    String[] users = {"bobrox@gmail.com","julieiscool@hotmail.com", "henrytheman@gmail.com", "Kaylasweet@gmail.com", "jacthebat@gmail.com"};
    int[] countryCodeOccurences = new int[7];

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(springSecurityFilterChain).build();

        wishListStatistics = new WishListStatistics(em);

        for (int c : countryCodeOccurences) {
            c = 0;
        }
    }

    @Test
    @WithMockUser
    public void testGetStats() throws Exception {

        for (int i = 0; i < 5; i++) {

            for (int j = 0; j < 7-i; j++) {
                countryCodeOccurences[j]++;
                MvcResult result = mockMvc.perform(post("/wishList")
                        .param("countryCode", countryCodes[j]).with(user(users[i]).roles("CUSTOMER")))
                        .andReturn();
                assertEquals(result.getResponse().getStatus(), 200);
            }
        }

        List<CountryStat> countryStatList = wishListStatistics.getStats();

        for (int i = 0; i < 7; i++) {
            for (CountryStat c: countryStatList) {
                if(c.getCountryCode3().equals(countryCodes[i])){
                    assertEquals(c.getPopularity(), countryCodeOccurences[i]);
                }
            }
        }

        int current = 999;
        for (CountryStat c: countryStatList) {
            assertTrue(c.getPopularity() <= current);
            current = c.getPopularity();
        }
    }
}