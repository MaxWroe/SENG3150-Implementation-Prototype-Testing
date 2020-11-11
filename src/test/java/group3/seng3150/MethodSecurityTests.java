package group3.seng3150;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/group3/seng3150/SpringDispatcher-servlet.xml"})
public interface MethodSecurityTests {

    @Before
    void setup();
    @Test
     void testMappings() throws Exception;
    @Test
    @WithMockUser("bobrox@gmail.com")
    void testMappings_Customer() throws Exception;
    @Test
    @WithMockUser("julieiscool@hotmail.com")
    void testMappings_Agent() throws Exception;
    @Test
    @WithMockUser("Kaylasweet@gmail.com")
    void testMappings_FlightPub() throws Exception;
    @Test
    @WithMockUser("jacthebat@gmail.com")
    void testMappings_Admin() throws Exception;
}
