package jlu.demo.predicate;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Eks på validering af filnavne med positiv  / negativlister. Lige en rettelse
 * 
 * @author JLU
 *
 */
public class FilenameValidator {
	
	
	public static Predicate<String> isIncluded(String includeExpression) {
		if (includeExpression != null && !includeExpression.isEmpty()) {
			return f -> Pattern.compile(includeExpression).matcher(f).matches();
		} else {
			return f -> true;
		}
	}

	public static Predicate<String> isNotExcluded(String excludeExpression) {
		if (excludeExpression != null && !excludeExpression.isEmpty()) {
			return f -> !Pattern.compile(excludeExpression).matcher(f).matches();
		} else {
			return f -> true;
		}
	}

	public static BiFunction<List<String>, Predicate<String>, List<String>> filterWithPredicate = new BiFunction<List<String>, Predicate<String>, List<String>>() {
		@Override
		public List<String> apply(List<String> filenames, Predicate<String> predicate) {
			return filenames.stream().filter(Objects::nonNull).filter(predicate).collect(Collectors.<String>toList());
		}
	};

	public static BiFunction<List<String>, String, List<String>> filterWithPredicateIsIncluded = (filenames,
			expression) -> filenames.stream().filter(Objects::nonNull).filter(isIncluded(expression))
					.collect(Collectors.<String>toList());

	public static BiFunction<List<String>, String, List<String>> filterWithPredicateIsNotExcluded = (filenames,
			expression) -> filenames.stream().filter(Objects::nonNull).filter(isNotExcluded(expression))
					.collect(Collectors.<String>toList());

}
