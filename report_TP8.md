# TP8 java - Amirhossein Pouyanfar - 262575 - Group 2 Mr. Forax

## Exercise 1

On souhaite écrire, dans une classe Lambdas, une méthode statique upperCaseAll qui prend en paramètre une liste de chaînes de caractères et met chaque chaîne en majusucules. 

1. Rappeler comment on met une chaîne de caractères en majusucules, indépendamment de la langue dans laquelle l'OS est configuré. 

**Answer**:
In order to change every character of a String to uppercase, we can use the `toUpperCase` method of the String class, regardless of the language
in which the OS is figured.

2. On va utiliser la méthode List.replaceAll pour mettre toutes les chaînes de caractères en majuscules. Quelle est l'interface fonctionnelle utilisée par la méthode replaceAll ? 

**Answer**: 

The functional interface used by `replaceAll` method of the `List` interface, is **`UnaryOperator`**.


3. À quel type de fonction cela correspond-il ? Autrement dit, que prend la fonction en argument et que renvoie-t-elle ?

**Answer** : UnaryOperator is in fact : `(T) -> T` where T is the type of the operand and result of the operator.
In our case it should take a String, and return String.

4. Sachant que l'on appelle replaceAll avec une liste de String, quel est le type des paramètres de la lambda et quel est son type de retour ? 

**Answer** : In our case, the lambda's parameter type should be String, as well as its return type.

5. Here's the `upperCaseAll` method:
```java
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Lambdas {
	
  public static void upperCaseAll(List<String> list) {
  	list.replaceAll(s -> s.toUpperCase(Locale.ROOT));
  }
  
  public static void main(String[] args) {
  	var list = new ArrayList<String>(List.of("Hello", "world", "you stink"));
  	upperCaseAll(list);
  	System.out.println(list);
  }
}
```

## Exercise 2

 On souhaite calculer le nombre d'occurences de chaque chaîne de caractères dans une liste de chaînes de caractères. Par exemple, avec la liste ["foo", "bar", "foo"], la méthode occurences va renvoyer {"foo" = 2, "bar" = 1}.

1. Quelle est le type du paramètre de la méthode occurences ?
Quelle est le type de retour de la méthode occurences ? 

**Answer** : The `Occurences` method takes a List of Strings as its parameter, and 
returns a Map<key, value> where key is a String and value is its number of appearances in the List.

2. Quelle est l'implantation que l'on doit choisir ici ? 

**Answer** : We would better use a `HashMap<String, Integer>`.

3. On veut parcourir la liste avec la méthode forEach. Quelle interface fonctionnelle prend-elle en paramètre ? Quel est le type fonction correspondant ? Ici, quels sont les types des paramètres / de retour de la lambda que vous allez utliser ?

**Answer** : The functional interface that we should be using is `Consumer`.
The function would be in this shape : `public HashMap<String, Integer> occurences`.
The lambda that we'll be using is  `Consumer : (String) -> void`

4. Pour compter le nombre d'occurences, on va utiiser la méthode merge de la structure de données que vous avez choisi de renvoyer. Quelle interface fonctionnelle prend-elle en paramètre ? Quel est le type fonction correspondant ? Ici, quels sont les types des paramètres / de retour de la lambda que vous allez utliser ? 

**Answer** : `merge` is a method of `Map` interface. It takes three parameters :
- **key** : a key in the map
- **newValue** : its new value
- **biFunction** : a `BiFunction`

The functional interface that we'll be using would be `BiFunction`, who takes two parameters and returns one.
We are going to use a lambda in this form : `(v1, v2) -> v1 + v2`

5. Écrire le code de occurences, toujours dans la classe Lambdas. Écrire le code de occurences, toujours dans la classe Lambdas. 

**Answer** :

```java
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
  	list.forEach(s -> map.merge(s, 1, (v1, v2) -> v1 + v2));
  	return map;
  }
  
  public static void main(String[] args) {
  	var list = new ArrayList<String>(List.of("Hello", "world", "you stink", "foo", "foo"));
  	upperCaseAll(list);
  	System.out.println(occurences(list));
  }
}
```

6. On peut noter qu'il existe une méthode statique sum dans la classe java.lang.Integer qui fait la somme de deux valeurs, on peut donc l'utiliser sous forme de method reference à la place de la lambda, lors de l'appel à merge. Modifier le code pour l’utiliser (garder la précédente version en commentaires). 

**Answer** : 
Here's the new face of our merge method :

```java
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
  }
}
```

## Exercise 3

 On souhaite pouvoir grouper des acteurs par leurs prénoms (firstName), avec les acteurs définis ainsi :
```java
   public record Actor(String firstName, String lastName) {
    public Actor {
      Objects.requireNonNull(firstName);
      Objects.requireNonNull(lastName);
    }
  }
```    

On va pour cela écrire une méthode actorGroupByFirstName qui prend en paramètre une liste d'acteurs, par exemple [Actor("bob", "de niro"), Actor("willy", "cat"), Actor("bob", "cat")] et renvoie une Map qui, pour un prénom, contient une liste de tous les acteurs ayant ce prénom. Avec notre exemple de liste, cela donne :

    {
       "bob" = [Actor("bob", "de niro"), Actor("bob", "cat")],
       "willy" = [Actor("willy", "cat")]
    }
    

1. Quel est le type de paramètre de actorGroupByFirstName ? Quel est le type de retour de actorGroupByFirstName ? 

**Answer** : It takes a List of Actors and it returns a Map<String, List<Actor>>

2. Rappeler comment marche la méthode Map.computeIfAbsent. Son second paramètre est une interface fonctionnelle, à quel type de fonction correspond-elle ? Expliquer à quoi correspondent le premier paramètre et le second paramètre de Map.computeIfAbsent, puis comment on peut l'utiliser pour grouper les acteurs selon leur prénom. 

**Answer** : The `computeIfAbsent` function has three parameters:

- K : key
- mappingFunction : Function<? super K,? extends V> mappingFunction>

This function tries attempts to compute the value of a key, if this key isn't already associated with a value in the map. Unless returns null.
Mapping function should be a lambda of `Function` functional interface.

We use this function as follows :
First, we create a new empty HashMap. For each key present in the Old map, we call
map.computeIfAbsent(key, function) with this lambda : (String) -> new ArrayList<Actor>.add(String)

3. Dans notre cas, quel doit être le type de la lambda passée en second paramètre de computeIfAbsent ?

**Answer** : In our case, The `computeIfAbsent` takes a `Function` lambda.

4. Écrire la méthode actorGroupByFirstName()

**Answer** : Here's the `actorGroupByFirstName()` method :

```java
public static Map<String, List<Actor>> actorGroupByFirstName(List<Actor> list) {
  var newMap = new HashMap<String, List<Actor>>();
  list.forEach(s -> newMap.computeIfAbsent(s.firstName, k -> new ArrayList<Actor>()).add(s));
  return newMap;
}
```

5. Si on veut maintenant grouper les acteurs par rapport à leur nom (lastName) au lieu du prénom, on va écrire à peu près le même code. On veut généraliser le code en écrivant une méthode actorGroupBy. qui prend en paramètre une liste d'acteurs ainsi qu'une fonction qui, étant donné un acteur, renvoie la valeur par laquelle il va être groupé. Et elle renvoie une Map des valeur par lesquelles on les groupe, associées aux listes d'acteurs groupés.
Par exemple, on va écrire :

```java
var group1 = actorGroupBy(actors, Actor::firstName)  // groupe par prénom
var group2 = actorGroupBy(actors, Actor::lastName)   // groupe par nom
```     

Quelle doit être le type fonction du second paramètre de actorGroupBy ?
Quelle est l'interface fonctionnelle correspondante ?
Quelle doit être le type du second paramètre de actorGroupBy ?
Écrire la méthode actorGroupBy. 

**Answer** : 

The function type of the second parameter of `actorGroupBy` should be a `Function`. It recieves an Actor and returns its first or lastname.
The corresponding functional interface is `Function`.
The second parameter has this type : `Function<Actor, String>`

Here's the actorGroupBy method :

```java
  public static Map<String, List<Actor>> actorGroupBy(List<Actor> list, Function<Actor, String> function) {
  	var newMap = new HashMap<String, List<Actor>>();
  	list.forEach(actor -> newMap.computeIfAbsent(function.apply(actor), s -> new ArrayList<Actor>()).add(actor));
  	return newMap;
  }
```

6. Optionnellement, on peut généraliser pour que la méthode ne fonctionne pas uniquement avec des acteurs, mais avec n'importe quel type.
Écrire la méthode groupBy qui prend en paramètre n'importe quel type de liste et n'importe quelle fonction.
Note : ici, on a besoin de deux paramètres de type : T qui est l'équivalent de Actor et U qui est l'équivalent de String, afin que cela fonctionne aussi si la clé pour grouper n'est pas de type String (par exemple, un autre type de composant du record). 

**Answer** : Here's the generic version of our `groupBy` function :

```java
public static <T, U> Map<U, List<T>> GroupByGeneric(List<T> list, Function<T, U> function) {
  var newMap = new HashMap<U, List<T>>();
  list.forEach(element -> newMap.computeIfAbsent(function.apply(element), s -> new ArrayList<T>()).add(element));
  return newMap;
}
```

