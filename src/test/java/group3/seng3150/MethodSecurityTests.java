package group3.seng3150;

import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

public interface MethodSecurityTests {

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
