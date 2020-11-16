package group3.seng3150.recommendationLogic;

import group3.seng3150.DefaultController;
import group3.seng3150.entities.UserAccount;
import org.junit.Before;
import org.junit.Test;
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
import java.util.Objects;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public class RecommendationGeneratorTest  {
    @Autowired WebApplicationContext ctx;

    @Autowired EntityManager em;

    @Autowired Filter springSecurityFilterChain;

    RecommendationGenerator recommendationGenerator;

    MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(springSecurityFilterChain).build();
        recommendationGenerator = new RecommendationGenerator(em);
    }

    @Test
    @WithMockUser
    public void testGetRecommendations() {
        UserAccount userAccount = (UserAccount) em.createQuery("select u from UserAccount u where u.email='bobrox@gmail.com'").getSingleResult();

        //Do test as business user

        //Do test as non-business user
    }
}