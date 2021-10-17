import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Test {

	
	public static void main(String[] args) {
		Chrono a = new Chrono("2");
		Chrono b = new Chrono("3");
		Chrono c = new Chrono("2");
		Chrono d = new Chrono("4");
		ArrayList<Chrono> list = new ArrayList<Chrono>();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		List<String> list2 = list.stream().distinct()
                .collect(Collectors.mapping(Chrono::getId, Collectors.toList()));
		List<String> listWithoutDuplicates = new ArrayList<>(new LinkedHashSet<>(list2));
		System.out.print(listWithoutDuplicates.toString());
	}

}

