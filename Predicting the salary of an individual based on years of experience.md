## Introduction

Linear regression is a supervised machine learning technique that helps us to predict the value of a variable based on the value of another variable. The variable to be predicted is called the `dependent` variable while the variable used for predicting other variables is called the `independent variable`. It uses one or more independent variables to estimate the coefficients of the linear equation. Linear regression generates a straight line that minimizes the differences between the predicted and the expected output values. 

In this article, we will be implementing a linear regression model that predicts the salary of an individual based on their years of experience using Java and GridDB. 

## Write the Data into GridDB

The data to be used shows the years of experience and salaries for different individuals. 

We will store the data in GridDB as it offers many benefits such as fast query performance. Let us first import the Java libraries to help us accomplish this:

```java
import com.toshiba.mwcloud.gs.Collection;
import com.toshiba.mwcloud.gs.GSException;
import com.toshiba.mwcloud.gs.GridStore;
import com.toshiba.mwcloud.gs.GridStoreFactory;
import com.toshiba.mwcloud.gs.Query;
import com.toshiba.mwcloud.gs.RowKey;
import com.toshiba.mwcloud.gs.RowSet;
import java.util.*;
```

GridDB organizes data into containers, and each container can be represented as a static class in Java. Let us create a static class in Java to represent the container where the data will be stored:

```java
static class SalaryData {
	@RowKey int id;
        double yearsExperience;
	double salary;
     }
```

We have created a GridDB container and given it the name `SalaryData`. See it as an SQL table with 3 columns. 

To write the data into GridDB, we should first establish a connection to the database. This requires us to create a `Properties` file from the `java.util` package and pass our GridDB credentials using the `key:value` syntax:

```java
Properties props = new Properties();
props.setProperty("notificationMember", "127.0.1.1:10001");
props.setProperty("clusterName", "myCluster");
props.setProperty("user", "admin");
props.setProperty("password", "admin");
GridStore store = GridStoreFactory.getInstance().getGridStore(props);
```

We will be using the `store` variable to interact with the database. 

Now that we are connected, we can store the data in GridDB. Let us first define the data rows:

```java
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
```

Let's select the `SalaryData` container where the data is to be stored:

```java
Collection<String, SalaryData> sd= store.putCollection("SalaryData", SalaryData.class);
```

We can now call the `put()` function to help us flush the data into the database:

```java
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
```

## Retrieve the Data

We should now retrieve the data from GridDB and use it to fit a machine learning model. We will write a TQL query that selects and returns all the data stored in the `SalaryData` container:

```java
while(rs.hasNext()) {
SalaryData sd1 = rs.next();
x=sd1.yearsExperience;
y=sd1.salary;


System.out.println(x +" "+ y);
double[][] d= {{x},{y}};
data=d.clone();

}
```

The `select *` is a TQL query that fetches all the data stored in the `SalaryData` container. The data has then been stored in an array. 

## Create Weka Instances

We now want to use the Weka machine learning library to fit a linear regression model. We have to convert our data into Weka instances. We will first create attributes for the dataset and store them in a FastVector data structure. Next, we will create Weka instances from the dataset. 

Let's first create the data structures for storing the attributes and the instances:

```java
int numInstances = data[0].length;
FastVector atts = new FastVector();
List<Instance> instances = new ArrayList<Instance>();
```

We can now create a`for` loop and use it to iterate over the data items while populating the FastVector data structure with the attributes:

```java
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
```

We can use the `Instances` class of Weka to create instances and store them in an Instance variable named `newDataset`. 

```java
Instances newDataset = new Instances("Dataset", atts, instances.size());
```

## Build a Linear Regression Model

The data instances are now ready, hence, we can use them to build a machine learning model. Let's first import the necessary libraries from Weka:

```java
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
```

Let us set the class attribute for the dataset:

```java
newDataset.setClassIndex(1);
```

Next, we use the `LinearRegression()` function of the Weka library to build a Linear Regression classifier:

```java
for(Instance inst : instances)
newDataset.add(inst);
Classifier classifier = new weka.classifiers.functions.LinearRegression();

classifier.buildClassifier(newDataset);
```

We now have a linear regression model. 


## Make a Prediction

Let's use our linear regression model to predict the salary of a person based on their years of experience. We will use the last instance of our dataset to make the prediction:

```java
Instance pd = newDataset.lastInstance();
double value = classifier.classifyInstance(pd);               	
		System.out.println(value);
```

## Execute the Model

To run the model, first download the Weka API from the following URL:

```
http://www.java2s.com/Code/Jar/w/weka.htm
```
Choose Weka version 3.7.0. 

Set the class paths for the `gridstore.jar` and `weka-3-7-0.jar` files by running the following commands on the terminal:

```
export CLASSPATH=$CLASSPATH:/usr/share/java/gridstore.jar
```

```
export CLASSPATH=$CLASSPATH:/mnt/c/Users/user/Desktop/weka-3.7.0.jar
```

The above commands may change depending on the location of your files. 

Compile your `.java` file by running the following command:

```
javac Salary.java
```

Run the generated .class file using the following command:

```
java Salary
```

The model predicted a salary of 109431.0 for the person.   





