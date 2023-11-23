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
}
