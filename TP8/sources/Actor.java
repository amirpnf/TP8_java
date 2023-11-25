import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public record Actor(String firstName, String lastName) {
	public Actor {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
	}
	
	public static Map<String, List<Actor>> actorGroupByFirstName(List<Actor> list) {
		var newMap = new HashMap<String, List<Actor>>();
		list.forEach(s -> newMap.computeIfAbsent(s.firstName, k -> new ArrayList<Actor>()).add(s));
		return newMap;
	}
	
	public static Map<String, List<Actor>> actorGroupBy(List<Actor> list, Function<Actor, String> function) {
		var newMap = new HashMap<String, List<Actor>>();
		list.forEach(actor -> newMap.computeIfAbsent(function.apply(actor), s -> new ArrayList<Actor>()).add(actor));
		return newMap;
	}
	
	public static <T, U> Map<U, List<T>> GroupByGeneric(List<T> list, Function<T, U> function) {
		var newMap = new HashMap<U, List<T>>();
		list.forEach(element -> newMap.computeIfAbsent(function.apply(element), s -> new ArrayList<T>()).add(element));
		return newMap;
	}
}
