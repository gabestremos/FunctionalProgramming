import java.io.IOException;

public class Main extends FunctionalProgramming{

    public static void main(String[] args) throws IOException {

        printSortedItems(stringStream());
        System.out.println();
        printTotalSales("cebu");
        printTotalSales("manila");
        printTotalSales("davao");
        printTotalSalesAllBranches(stringStream());
        System.out.println();
        printTotalSalesFor2016(stringStream());
        printMonthFruitsAreSoldMost(stringStream());
        printMostSoldItem2012(stringStream());
        printMonthMostItemSold(stringStream());
        System.out.println();
        printNameMostItemSold("manila");
        printNameMostItemSold("cebu");
        printNameMostItemSold("davao");
    }

}