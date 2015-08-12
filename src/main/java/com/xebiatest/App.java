package com.xebiatest;

import com.xebiatest.io.XmlReader;
import com.xebiatest.io.YakOutput;
import com.xebiatest.model.LabYak;
import com.xebiatest.service.YakService;
import com.xebiatest.service.YakServiceImpl;
import com.xebiatest.store.YakYield;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        File input = new File(args[0]);
        int elapsedTimeInDays = Integer.parseInt(args[1]);
        ArrayList<LabYak> yakList = new XmlReader(input).read();


        YakService service = new YakServiceImpl();
        for(LabYak yak : yakList){
            service.calculateAndSaveYieldForDay(yak, elapsedTimeInDays);
        }
        YakYield totalYakYield = service.getTotalYakYield(elapsedTimeInDays);
        show(elapsedTimeInDays,totalYakYield,yakList);
        //new App().process(yakList,elapsedTimeInDays);

    }

//    public YakOutput process(List<LabYak> yakList, int elapsedTimeInDays) {
//        double totalMilkLiters = 0;
//        int totalSkins = 0;
//        for (LabYak yak : yakList) {
//            totalMilkLiters += yak.getTotalMilkQuantity(elapsedTimeInDays);
//            totalSkins += yak.getSkinCount(elapsedTimeInDays);
//        }
//        show(elapsedTimeInDays,totalMilkLiters,totalSkins,yakList);
//        return new YakOutput(totalMilkLiters,totalSkins);
//    }
    public static  void show(int forDays, YakYield yield, List<LabYak> yakList){
        System.out.println("T = "+forDays+"\n\n");

        System.out.println("In Stock:");
        System.out.println("\t\t"+yield.getMilk()+" liters of milk");
        System.out.println("\t\t"+yield.getSkin()+" skins of wool");
        System.out.println("Herd:\n\n");
        for(LabYak yak : yakList){
            System.out.println(yak.display(forDays));
        }
    }
}
