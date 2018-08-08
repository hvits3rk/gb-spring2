package com.romantupikov.gbspring;

import com.romantupikov.gbspring.security.entity.User;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class GbspringApplicationTests {

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
