package seed.core.motan.support;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * BasicServiceCondition
 * 
 * @author 	alanwei
 * @since 	2016-09-16
 */
public class BasicServiceConfigCondition implements Condition {

	/**
	 * match [motan.basicservice.exportPort, motan.basicservice.export] config property
	 * 
	 * @see org.springframework.context.annotation.Condition#matches(org.springframework.context.annotation.ConditionContext, org.springframework.core.type.AnnotatedTypeMetadata)
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment env = context.getEnvironment();
		return (!StringUtils.isEmpty(env.getProperty("motan.basicservice.exportPort"))
				|| !StringUtils.isEmpty(env.getProperty("motan.basicservice.export")));
	}

}