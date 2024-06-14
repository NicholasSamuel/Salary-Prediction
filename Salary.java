/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.toshiba.mwcloud.gs.Collection;
import com.toshiba.mwcloud.gs.GSException;
import com.toshiba.mwcloud.gs.GridStore;
import com.toshiba.mwcloud.gs.GridStoreFactory;
import com.toshiba.mwcloud.gs.Query;
import com.toshiba.mwcloud.gs.RowKey;
import com.toshiba.mwcloud.gs.RowSet;
import java.util.*;


import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;

/**
 *
 * @author user
 */
public class Salary {
   
   
      static class SalaryData {
	@RowKey int id;
        double yearsExperience;
	double salary;
     }
    
    
        // TODO code application logic here
     
   
public static void main(String[] args) {   
    
    try{

Properties props = new Properties();
props.setProperty("notificationMember", "127.0.1.1:10001");
props.setProperty("clusterName", "myCluster");
props.setProperty("user", "admin");
props.setProperty("password", "admin");


GridStore store = GridStoreFactory.getInstance().getGridStore(props);
                
SalaryData  row1 = new SalaryData();
row1.id=1;
row1.yearsExperience=1.1;
row1.salary=39343.00;

SalaryData  row2 = new SalaryData();
row2.id=2;
row2.yearsExperience=1.3;
row2.salary=46205.00;

SalaryData  row3 = new SalaryData();
row3.id=3;
row3.yearsExperience=1.5;
row3.salary=37731.00;

SalaryData  row4 = new SalaryData();
row4.id=4;
row4.yearsExperience=2.0;
row4.salary=43525.00;

SalaryData  row5 = new SalaryData();
row5.id=5;
row5.yearsExperience=2.2;
row5.salary=39891.00;

SalaryData  row6 = new SalaryData();
row6.id=6;
row6.yearsExperience=2.9;
row6.salary=56642.00;

SalaryData  row7 = new SalaryData();
row7.id=7;
row7.yearsExperience=3.0;
row7.salary=60150.00;

SalaryData  row8 = new SalaryData();
row8.id=8;
row8.yearsExperience=3.2;
row8.salary=54445.00;

SalaryData  row9 = new SalaryData();
row9.id=9;
row9.yearsExperience=3.2;
row9.salary=64445.00;

SalaryData  row10 = new SalaryData();
row10.id=10;
row10.yearsExperience=3.7;
row10.salary=57189.00;

SalaryData  row11 = new SalaryData();
row11.id=11;
row11.yearsExperience=3.9;
row11.salary=63218.00;

SalaryData  row12 = new SalaryData();
row12.id=12;
row12.yearsExperience=4.0;
row12.salary=55794.00;


SalaryData  row13 = new SalaryData();
row13.id=13;
row13.yearsExperience=4.0;
row13.salary=56957.00;

SalaryData  row14 = new SalaryData();
row14.id=14;
row14.yearsExperience=4.1;
row14.salary=57081.00;

SalaryData  row15 = new SalaryData();
row15.id=15;
row15.yearsExperience=4.5;
row15.salary=61111.00;

SalaryData  row16 = new SalaryData();
row16.id=16;
row16.yearsExperience=4.9;
row16.salary=67938.00;

SalaryData  row17 = new SalaryData();
row17.id=17;
row17.yearsExperience=5.1;
row17.salary=66029.00;

SalaryData  row18 = new SalaryData();
row18.id=18;
row18.yearsExperience=5.3;
row18.salary=83088.00;

SalaryData  row19 = new SalaryData();
row19.id=19;
row19.yearsExperience=5.9;
row19.salary=81363.00;

SalaryData  row20 = new SalaryData();
row20.id=20;
row20.yearsExperience=6.0;
row20.salary=93940.00;

SalaryData  row21 = new SalaryData();
row21.id=21;
row21.yearsExperience=6.8;
row21.salary=91738.00;

SalaryData  row22 = new SalaryData();
row22.id=22;
row22.yearsExperience=7.1;
row22.salary=98273.00;

SalaryData  row23 = new SalaryData();
row23.id=23;
row23.yearsExperience=7.9;
row23.salary=101302.00;

SalaryData  row24 = new SalaryData();
row24.id=24;
row24.yearsExperience=8.2;
row24.salary=113812.00;

SalaryData  row25 = new SalaryData();
row25.id=25;
row25.yearsExperience=8.7;
row25.salary=109431.00;

Collection<String, SalaryData> sd= store.putCollection("SalaryData", SalaryData.class);
sd.put(row1);
sd.put(row2);
sd.put(row3);
sd.put(row4);
sd.put(row5);
sd.put(row6);
sd.put(row7);
sd.put(row8);
sd.put(row9);
sd.put(row10);
sd.put(row11);
sd.put(row12);
sd.put(row13);
sd.put(row14);
sd.put(row15);
sd.put(row16);
sd.put(row17);
sd.put(row18);
sd.put(row19);
sd.put(row20);
sd.put(row21);
sd.put(row22);
sd.put(row23);
sd.put(row24);
sd.put(row25);




Query<SalaryData> query = sd.query("select *");
RowSet<SalaryData> rs = query.fetch(false);
double x=0;
double y=0;
double[][] data = {{x},{y}};

System.out.println("Training dataset:");

while(rs.hasNext()) {
SalaryData sd1 = rs.next();
x=sd1.yearsExperience;
y=sd1.salary;


System.out.println(x +" "+ y);
double[][] d= {{x},{y}};
data=d.clone();

}
   

int numInstances = data[0].length;
                   FastVector atts = new FastVector();
                         
                   List<Instance> instances = new ArrayList<Instance>();
                         
                         for(int dim = 0; dim < 2; dim++)
    {
        Attribute current = new Attribute("Attribute" + dim, dim);

        if(dim == 0)
        {
            for(int obj = 0; obj < numInstances; obj++)
            {
                instances.add(new SparseInstance(numInstances));
            }
        }

        for(int obj = 0; obj < numInstances; obj++)
        {
            instances.get(obj).setValue(current, data[dim][obj]);
            
        }
        atts.addElement(current);
    }
         
        


    
  
    Instances newDataset = new Instances("Dataset", atts, instances.size());
        

newDataset.setClassIndex(1);
for(Instance inst : instances)
newDataset.add(inst);

        


 
       try{
Classifier classifier = new weka.classifiers.functions.LinearRegression();

classifier.buildClassifier(newDataset);
         
                
                Instance pd = newDataset.lastInstance();
                double value = classifier.classifyInstance(pd);
                
		
		System.out.println("The predicted value is: "+ value);
        } catch (Exception ex) {
        Logger.getLogger(SalaryData.class.getName()).log(Level.SEVERE, null, ex);
    }
       
		
 

  //}  close while
     
}
   
catch (GSException e) {
                        System.out.println("An error occurred when creating the container.");
                        e.printStackTrace();
                        System.exit(1);
}
}
}
