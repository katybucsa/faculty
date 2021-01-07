package AB;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class InfiniteStreams {
    public static void main(String args[]) {
       /* Stream.iterate(0, n->n+2)
                .peek(num -> System.out.println("Peeked at:"+num))
                .limit(5)
                .forEach(System.out::println);*/


       /* Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .mapToInt(e->{
                    System.out.println(e);
                    return e.length();})
                .sorted()
                .peek(e -> System.out.println("Mapped value: " + e))
                .limit(3)
                .forEach(e-> System.out.println(e));
        System.out.println("abc".charAt(0));*/


       List<String> list = new ArrayList<>();
        for(char a='d';a>='a';a--){
            for(char b='d';b>='a';b--){
                for(char c='a';c<='d';c++){
                    list.add(""+a+b+c);
                }
            }
        }

        list.stream()
                .filter(x->x.charAt(1)==x.charAt(2))
                .map(x->"_"+x.charAt(1)+x.charAt(2))
                .sorted((x,y)->(x.charAt(1)-y.charAt(1)))
                .distinct()
                .forEach(System.out::println);
    }
}