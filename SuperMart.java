
import java.util.*;
import java.io.*;
class data{
    protected String [] product = {"ORANGE","APPLE","PINEAPPLE","BANANA","KIWI","NOTEBOOK","PENCIL BOX","PEN","ERASER","SHARPENER","PIZZA","BURGER","NOODLES","FRENCH FRIES","VADAPAV","PHONE","LAPTOP","WATCH","TV","AC","COCA-COLA","APPY FIZZ","ORANGE JUICE","COLD COFFEE","BUTTER-MILK"};
    protected String [] products = {"ORANGES","APPLES","PINEAPPLES","BANANAS","KIWIS","NOTEBOOKS","PENCIL BOXES","PENS","ERASERS","SHARPENERS","PIZZAS","BURGERS","NOODLES","FRENCH FRIES","VADAPAVS","PHONES","LAPTOPS","WATCHES","TVs","ACs","COCA-COLA","APPY FIZZ","ORANGE JUICES","COLD COFFEES","BUTTER-MILK"};
    protected String [] files = {"orange.txt","apple.txt","pineapple.txt","banana.txt","kiwi.txt","notebook.txt","boxofpencil.txt","pen.txt","eraser.txt","sharpener.txt","pizza.txt","burger.txt","noodles.txt","fries.txt","vadapav.txt","phone.txt","laptop.txt","watch.txt","tv.txt","ac.txt","cocacola.txt","fizz.txt","juice.txt","coffee.txt","buttermilk.txt"};
    protected int [] price ={20,23,70,10,100,100,70,10,5,4,120,45,100,80,40,30000,100000,18500,150000,125000,40,20,30,50,15};
    protected int [] bought={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0} ;
}
class Market extends data {
    Market() {
        for(int i=0;i<25;i++){
            File f= new File(files[i]);
            if(!f.exists()){
                Write(files[i],50);
            }
        }
        System.out.println("---------------------------------");
        System.out.println("Welcome to KRISHNA SUPERMART");
    }
    public void FrontMenu() {
        System.out.println("---------------------------------");
        System.out.println("ENTER 0 to exit\n      1 to enter MAINMENU");
        System.out.println("---------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc = new Scanner(System.in);
        try {
            int c = sc.nextInt();
            if (c == 1) MainMenu();
            else bill();
        }
        catch(Exception e){
            bill();
        }
    }
    public void MainMenu() {
        System.out.println("---------------------------------");
        System.out.println("Press 0 to return FRONTMENU");
        System.out.println("      1 to buy FRUITS");
        System.out.println("      2 to buy STATIONARY PRODUCTS");
        System.out.println("      3 to buy SNACKS");
        System.out.println("      4 to buy ELECTRICAL GADGETS");
        System.out.println("      5 to buy REFRESHMENTS");
        System.out.println("      or any other number to exit");
        System.out.println("---------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc = new Scanner(System.in);
        try {
            int num = sc.nextInt();
            if (num == 0) FrontMenu();
            else if (num <= 5) Menu((num - 1) * 5);
            else bill();
        }
        catch (Exception e){
            bill();
        }
    }
    public void Menu(int num) {
        Formatter f = new Formatter();
        System.out.println("--------------------------------------");
        f.format("%15s %15s\n", "PRODUCT NAME", "PRICE(in Rs)");
        f.format("--------------------------------------\n");
        for (int i = num; i < num + 5; i++) {
            f.format("%15s %8s\n", product[i], price[i]);
        }
        System.out.print(f);
        System.out.println("--------------------------------------");
        System.out.print("Press ");
        for(int i=num;i<num+6;i++){
            if(i==num) System.out.format("%d to return MainMenu\n",i%5);
            else if(i<num+5) System.out.format("      %d to buy %s\n",i%5,product[i-1]);
            else if(i==num+5) System.out.format("      %d to buy %s\n",5,product[i-1]);
        }
        System.out.println("      or any other number to exit");
        System.out.println("--------------------------------------");
        System.out.print("Enter your choice here: ");
        Scanner sc=new Scanner(System.in);
        int p=sc.nextInt();
        if(p==0) MainMenu();
        else if(p<=5) shop(num+p-1);
        else bill();
    }
    public void shop(int num){
        System.out.println("--------------------------------------");
        System.out.print("Enter the quantity of product: ");
        Scanner sc= new Scanner(System.in);
        int q=sc.nextInt();
        System.out.println("--------------------------------------");
        int av=0;
        File myFile=new File(files[num]);
        try {
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()){
                String line = reader.next();
                av=Integer.parseInt(line);
            }
            reader.close();
        }
        catch (Exception e){
            av=0;
        }
        if(av==0){
            System.out.format("SORRY, We are out of %s\n",products[num]);
        }
        else if(q>av){
            System.out.format("We only have %d %s\n",av,products[num]);
            System.out.println("--------------------------------------");
            System.out.println("Press 0 if you don't want to buy");
            System.out.format("      1 if you want to buy remaining %s\n",products[num]);
            System.out.println("--------------------------------------");
            System.out.print("Enter your choice here: ");
            int choice=sc.nextInt();
            if(choice==1){
                System.out.println("--------------------------------------");
                System.out.println("Your desired product has been added to the cart.");
                bought[num]+=av;
                Write(files[num],0);
            }
        }
        else{
            System.out.println("Your desired product has been added to the cart.");
            bought[num]+=q;
            Write(files[num],av-q);
        }
        System.out.println("--------------------------------------");
        System.out.println("Press 0 to return MainMenu");
        System.out.println("      or any other number to exit");
        System.out.println("--------------------------------------");
        System.out.print("Enter your choice here: ");
        try {
            int c = sc.nextInt();
            if (c == 0) MainMenu();
            else bill();
        }
        catch(Exception e){
            bill();
        }
    }
    public void bill() {
        System.out.println("----------------------------------------------------");
        System.out.println("THANK YOU FOR VISITING OUR STORE");
        System.out.println("----------------------------------------------------");
        System.out.println("YOUR BILL IS DISPLAYED BELOW");
        System.out.println("----------------------------------------------------");
        Formatter f= new Formatter();
        f.format("%15s %10s %10s %10s\n","PRODUCT NAME","PRICE","BOUGHT","NET");
        f.format("----------------------------------------------------\n");
        long total=0;
        for(int i=0;i<25;i++){
            if(bought[i]>0) {f.format("%15s %10s %10s %10s\n",product[i],price[i],bought[i],price[i]*bought[i]);total+= (long) price[i] *bought[i];}
        }
        f.format("----------------------------------------------------\n");
        f.format("TAX = %2f\n",total*0.18f);
        f.format("----------------------------------------------------\n");
        f.format("TOTAL = %2f\n",total*1.18f);
        System.out.println(f);
    }
    public void Write(String fileName,int n){
        FileWriter fw=null;
        try{
            String s=String.valueOf(n);
            fw=new FileWriter(fileName);
            fw.write(s);
            fw.close();
        }
        catch(Exception e){
            System.out.println("--------------------------------------");
            System.out.println("Some error occurred\nPlease try again");
        }
    }
}
public class SuperMart {
    public static void main(String[] args) {
    Market customer= new Market();
    customer.FrontMenu();
    }
}