import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException {
        FunctionalProgramming fP=new FunctionalProgramming();
        fP.printSortedItems(fP.stringStream());
        System.out.println();
        fP.printTotalSales("cebu");
        fP.printTotalSales("manila");
        fP.printTotalSales("davao");
        fP.printTotalSalesAllBranches(fP.stringStream());
        System.out.println();
        fP.printTotalSalesFor2016(fP.stringStream());
        fP.printMonthFruitsAreSoldMost(fP.stringStream());
        fP.printMostSoldItem2012(fP.stringStream());
        fP.printMonthMostItemSold(fP.stringStream());
        System.out.println();
        fP.printNameMostItemSold("manila");
        fP.printNameMostItemSold("cebu");
        fP.printNameMostItemSold("davao");
    }

}