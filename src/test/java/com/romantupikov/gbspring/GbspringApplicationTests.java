package com.romantupikov.gbspring;

import com.romantupikov.gbspring.security.entity.Role;
import com.romantupikov.gbspring.security.entity.User;
import com.romantupikov.gbspring.security.repository.RoleRepository;
import com.romantupikov.gbspring.security.repository.UserRepository;
import com.romantupikov.gbspring.security.service.RoleService;
import com.romantupikov.gbspring.security.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GbspringApplicationTests {

    private MockMvc mockMvc;

    private String userName = "admin";
    private String password = "password";


    private User user;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        this.userRepository.deleteAllInBatch();
        this.roleRepository.deleteAllInBatch();

        Role role = roleService.createRoleIfNotFound("ROLE_ADMIN");

        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);

        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        this.user = userService.register(user);

    }

    @Test
    public void shouldReturnIndexPage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to YAS!")));
    }

    @Test
    public void formAuthenticationFail() throws Exception {
        this.mockMvc.perform(formLogin("/login").user(userName).password(password))
                .andExpect(unauthenticated());
    }

    @Test
    public void formAuthenticationPassed() throws Exception {
        this.user.setEnabled(true);
        userRepository.save(this.user);

        this.mockMvc.perform(formLogin("/login").user(userName).password(password))
                .andExpect(redirectedUrl("/"))
                .andExpect(authenticated());
    }

    @Test
    public void spelTest() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        ExpressionParser ep = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        assertThat(user.getUsername())
                .isEqualToIgnoringCase(ep.parseExpression("username").getValue(context, String.class));

        ep.parseExpression("password").setValue(context, "new_password");

        assertThat(user.getPassword()).isEqualToIgnoringCase("new_password");
    }

    @Test
    public void passwordEncoderTest() {
        BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
        BCryptPasswordEncoder encoder2 = new BCryptPasswordEncoder();

        String password = "password";

        String encodedPassword = encoder1.encode(password);

        assertThat(encoder1.matches(password, encodedPassword)).isTrue();
        assertThat(encoder2.matches(password, encodedPassword)).isTrue();
        assertThat(encoder1.matches(password, "$2a$10$X7LF71X.PejJt6CkOVXDCOsKLpAM23KkGdXue.ES8T2O3YdxZ.o3S"))
                .isTrue();
    }

}
