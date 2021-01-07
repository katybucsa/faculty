import domain.MessageTask;
import domain.Strategy;
import runner.DelayTaskRunner;
import runner.PrinterTaskRunner;
import runner.StrategyTaskRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestDelayTaskRunner {

    public static void main(String[] args) {
        MessageTask[] mtasks=new MessageTask[5];
        LocalDateTime data=LocalDateTime.parse("02-03-2010 12:23", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        for(int i=0;i<5;i++)
            mtasks[i]=new MessageTask("mesaj"+i,"from"+i,"to"+i,data,String.valueOf(i),"description"+i);
        Strategy s=Strategy.valueOf(args[0]);
        StrategyTaskRunner str=new StrategyTaskRunner(s);
        for(int i=0;i<5;i++)
            str.addTask(mtasks[i]);
        str.executeAll();

        for(int i=0;i<5;i++)
            str.addTask(mtasks[i]);
        DelayTaskRunner dtr=new DelayTaskRunner(str);
        dtr.executeAll();

        for(int i=0;i<5;i++)
            str.addTask(mtasks[i]);
        PrinterTaskRunner ptr=new PrinterTaskRunner(dtr);
        ptr.executeAll();
    }
}
