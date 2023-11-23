import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Lambdas {
	
	public static void upperCaseAll(List<String> list) {
		list.replaceAll(s -> s.toUpperCase(Locale.ROOT));
	}
	
	public static Map<String, Integer> occurences(List<String> list) {
		var map = new HashMap<String, Integer>();
		list.forEach(s -> map.merge(s, 1, Integer::sum));
		return map;
	}
	
	public static void main(String[] args) {
		var list = new ArrayList<String>(List.of("Hello", "world", "you stink", "foo", "foo"));
		upperCaseAll(list);
		System.out.println(occurences(list));
		var newList = List.of(new Actor("bob", "de niro"), new Actor("willy", "cat"), new Actor("bob", "l'éponge"));
		var map = Actor.actorGroupBy(newList, Actor::lastName);
		System.out.println(map);
	}
	
}
