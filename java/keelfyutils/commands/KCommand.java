package keelfyutils.commands;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author keelfy
 * @created 6 июл. 2017 г.
 */
@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface KCommand {

	String usage() default "";

	boolean onlyByPlayer() default true;
}
