import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalProgramming {
private static DecimalFormat decimalFormat=new DecimalFormat("#.##");

     static void printNameMostItemSold(String path) throws IOException {

        Stream<String> stream= Files.lines(Path.of("branches",path+".csv")); Set<Item> itemSet=stream.map(e->e.split(","))
                .map(FunctionalProgramming::mapToModel)
                .collect(Collectors.toSet());
        Map<String,Integer> nameMostItems=itemSet.stream()
                .collect(Collectors.groupingBy(Item::getItemName,Collectors.summingInt(Item::getUnitSold)));
        String item=nameMostItems.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("The most item sold in "+path.toUpperCase()+": "+item);
    }

     static void printMonthMostItemSold(Stream<String> streams) {
        Set<Item> itemSet=streams.map(e->e.split(","))
                .map(FunctionalProgramming::mapToModel)
                .collect(Collectors.toSet());
        Map<String,Integer> monthMostItems=itemSet.stream()
                .collect(Collectors.groupingBy(Item::getOrderDate,Collectors.summingInt(Item::getUnitSold)));
        String month=monthMostItems.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("\nMonth with the most number of unit sold: "+month);
    }

     static void printMostSoldItem2012(Stream<String> streams) {
        Set<Item> itemSet=streams.map(e->e.split(","))
                .filter(w->w[1].contains("2012"))
                .map(FunctionalProgramming::mapToModel)
                .collect(Collectors.toSet());
        Map<String,Integer> mostItem2012=itemSet.stream()
                .collect(Collectors.groupingBy(Item::getItemName,Collectors.summingInt(Item::getUnitSold)));
        String item=mostItem2012.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("\nThe most item sold in 2012 was: "+item);
    }

     static void printMonthFruitsAreSoldMost(Stream<String> streams) {
        Set<Item> itemSet=streams.map(e->e.split(","))
                .filter(w->w[0].equals("Fruits"))
                .map(FunctionalProgramming::mapToModel)
                .collect(Collectors.toSet());
        Map<String, Integer> fruit=itemSet.stream()
                .collect(Collectors.groupingBy((Item::getOrderDate),Collectors.summingInt(Item::getUnitSold)));
        String month=fruit.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println("\nMonth where \"Fruits\" are sold the most: "+month);
    }

    static void printTotalSalesFor2016(Stream<String> streams) {
        double totalSales2016=streams
                .map(e->e.split(","))
                .filter(e->e[1].contains("2016"))
                .mapToDouble(e->(Double.parseDouble(e[2]))*(Double.parseDouble(e[3]))).sum();
        System.out.println("Total sales from all branches for 2016: "+decimalFormat.format(new BigDecimal(totalSales2016)));
    }

    static void printTotalSalesAllBranches(Stream<String> streams){
        double totalSalesAllBranches=streams
                .map(e->e.split(","))
                .mapToDouble(e->Double.parseDouble(e[2])*Double.parseDouble(e[3])).sum();
        System.out.println("\nTotal sales from all branches: "+ decimalFormat.format(new BigDecimal(totalSalesAllBranches)));
    }

    static void printSortedItems(Stream<String> streams) {
        Set<String> items = new TreeSet<>(streams.map(e->e.split(",")[0])
                .collect(Collectors.toSet()));
        items.forEach(System.out::println);
    }

    static void printTotalSales(String path) throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get("branches",path+".csv"));
        double totalSales=stringStream
                .map(e->e.split(","))
                .mapToDouble(e-> Double.parseDouble(e[2]) * Double.parseDouble(e[3])).sum();
        System.out.println("Total sales in "+path.toUpperCase()+": "+ decimalFormat.format(new BigDecimal (totalSales)));
    }

    static Stream<String> stringStream()throws IOException{
        return Files.walk(Path.of("branches"))
                .filter(path -> Files.isRegularFile(path))
                .flatMap(FunctionalProgramming::streamLines);
    }
    static Stream<String> streamLines(Path path){
        try{
            return Files.lines(path);
        }catch (IOException e){
            e.printStackTrace(System.err);
            return Stream.empty();
        }
    }
     static Item mapToModel(String[] e){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        Item item= new Item();
        item.setItemName(e[0]);
        item.setOrderDate(LocalDate.parse((e[1]),formatter));
        item.setUnitSold(Integer.parseInt(e[2]));
        item.setUnitPrice(new BigDecimal(e[3]));
        return item;
    }
}
