import java.util.*;

class discount{

    int discount_normal;
    int discount_prime;
    int discount_elite;
    discount(int disc_normal,int disc_prime,int disc_elite) {
        this.discount_normal=disc_normal;
        this.discount_prime=disc_prime;
        this.discount_elite=disc_elite;

    }

    static ArrayList<discount> discount_on_product=new ArrayList<discount>();


    public int getDiscount_normal() {
        return discount_normal;
    }

    public void setDiscount_normal(int discount_normal) {
        this.discount_normal = discount_normal;
    }

    public int getDiscount_prime() {
        return discount_prime;
    }

    public void setDiscount_prime(int discount_prime) {
        this.discount_prime = discount_prime;
    }

    public int getDiscount_elite() {
        return discount_elite;
    }

    public void setDiscount_elite(int discount_elite) {
        this.discount_elite = discount_elite;
    }
}

class customer implements check_password{
    private final String cust_name;
    private final int age;
    private final String email;
    private final String password;
    private String status;
    private double wallet;

    private int coupons;

    ArrayList<product> cart =new ArrayList<>();
    ArrayList<ArrayList<product>> deal_cart=new ArrayList<ArrayList<product>>();
    ArrayList<giveaway_deal> deal_id_for_add_deal_cart= new ArrayList<>();

    public customer(String cust_name, int age, String email, String password, String status,int account_bal) {
        this.cust_name=cust_name;
        this.age=age;
        this.email=email;
        this.password=password;
        this.status=status;
        this.wallet=account_bal;
    }

    String getcoust_name(){
        return this.cust_name;
    }
    int getAge(){
        return this.age;
    }
    String getEmail(){
        return this.email;
    }
    String getPassword(){
        return this.password;
    }

    double getAccount_bal(){
        return this.wallet;
    }
    void setWallet(double amount){
        double money=(float) this.wallet;
        money=money-amount;
        this.wallet=money;
    }
    void setWallet(int amount){
        double money=this.wallet;
        money=money+amount;
        this.wallet=money;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    String getStatus(){
        return this.status;
    }

    static ArrayList<customer> custm=new ArrayList<>();

    static void sign_up() {
        Scanner sc=new Scanner(System.in);
        String cust_name;
        int age;
        String email;
        String password;
        String status="Normal";
        int account_bal=1000;
        System.out.println("enter name: ");
        cust_name=sc.nextLine();
        System.out.println("enter age: ");
        age= sc.nextInt();
        System.out.println("enter email: ");
        sc.nextLine();
        email=sc.nextLine();
        System.out.println("enter passoword: ");
        password=sc.nextLine();
        customer cust1=new customer(cust_name,age,email,password,status,account_bal);
        custm.add(cust1);
        assignment2.enter_customer();
    }
    public static void log_in(){
        Scanner sc=new Scanner(System.in);
        String cust_name;
        String password;
        System.out.println("enter name: ");
        cust_name=sc.nextLine();
        System.out.println("enter passoword: ");
        password=sc.nextLine();
        for(int i=0;i<custm.size();i++){
            if(custm.get(i).cust_name.equals(cust_name) && custm.get(i).password.equals(password)){
                System.out.println("Successfully login");
                System.out.println();
                customer_menue(cust_name);
            }
        }
    }
    @Override
    public void password() {
        log_in();
    }

    private static void customer_menue(String cust_name){
        Scanner sc=new Scanner(System.in);
        int choose;
        System.out.println("Welcome "+cust_name+" !!");
        System.out.println("""
                            1) browse products
                            2) browse deals
                            3) add a product to cart
                            4) add products in deal to cart
                            5) view coupons
                            6) check account balance
                            7) view cart
                            8) empty cart
                            9) checkout cart
                            10) upgrade customer status
                            11) Add amount to wallet
                            12) back""");
        choose= sc.nextInt();
        if(choose==1){
            browse_product(cust_name);
        } else if (choose==2){
            browse_deals(cust_name);
        } else if (choose==3) {
            add_product_to_cart(cust_name);
        } else if (choose==4) {
            add_dealProduct_to_cart(cust_name);
        }else if (choose==5) {
            view_coupons(cust_name);
        } else if (choose==6) {
            check_account_balance(cust_name);
        } else if (choose==7) {
            view_cart(cust_name);
        } else if (choose==8) {
            empty_cart(cust_name);
        } else if (choose==9) {
            checkout_cart(cust_name);
        } else if (choose==10) {
            upgrade_customer_status(cust_name);
        } else if (choose==11) {
            Add_money_to_wallet(cust_name);
        } else if (choose==12){
            assignment2.enter_customer();


        }

    }


    private static void browse_product(String cust_name) {
        for(int i=0;i<ADMIN.c.size();i++){
            for(int j=0;j<ADMIN.c.get(i).p.size();j++){
                System.out.println("product name: "+ADMIN.c.get(i).p.get(j).getPname()+"\n" +
                        "Product ID: " +ADMIN.c.get(i).p.get(j).getPid()+"\n" +
                        "Product Price: "+ADMIN.c.get(i).p.get(j).getPrice()+"\n" +
                        "Product Specification: "+ADMIN.c.get(i).p.get(j).getSpecifications());
                System.out.println();
            }
            System.out.println();
        }customer_menue(cust_name);
    }
    private static void browse_deals(String cust_name) {
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        if(ADMIN.giveaway_deal.isEmpty()){
            System.out.println("There are no deals available");
            customer_menue(cust_name);
        }else{
            System.out.println("produc which are included in giveaway deal are: ");
            for(int i=0;i<ADMIN.giveaway_deal.size();i++){
                System.out.println("ID of giveaway deal is: "+ ADMIN.d.get(i).deal_id);
                for (int j=0; j<ADMIN.giveaway_deal.get(i).size();j++){
                    System.out.println("product name: " + ADMIN.giveaway_deal.get(i).get(j).getPname() +"\n" +
                            "product Id: "+ ADMIN.giveaway_deal.get(i).get(j).getPid()+"\n" +
                            "product price: "+ADMIN.giveaway_deal.get(i).get(j).getPrice()+"\n" +
                            "product specifications: "+ADMIN.giveaway_deal.get(i).get(j).getSpecifications());
                    System.out.println();
                }
                System.out.println("the giveaway deal price for these product for normal is: "+ ADMIN.d.get(i).getNormal_deal_price());
                System.out.println("the giveaway deal price for these product for prime is: "+ ADMIN.d.get(i).getPrime_deal_price());
                System.out.println("the giveaway deal price for these product for elite is: "+ ADMIN.d.get(i).getElite_deal_price());



                System.out.println();
            }
            customer_menue(cust_name);
        }


    }

    private static void add_product_to_cart(String cust_name){
        Scanner sc=new Scanner(System.in);
        float id;
        int quantity;
        System.out.println("enter the product id of the product and the quantity you want to add in you car:");
        id= sc.nextFloat();
        quantity=sc.nextInt();
        int a=0;
        for (int i=0;i<product.product_list.size();i++){
            if(product.product_list.get(i).getPid()==id){
                a=i;

            }
        }int b=0;
        for (int i=0;i<custm.size();i++){
            if(custm.get(i).cust_name.equals(cust_name)){
                b=i;
            }
        }
        product.product_list.get(a).setQuantity(quantity);
        custm.get(b).cart.add(product.product_list.get(a));
        System.out.println("Successfully added");
        customer_menue(cust_name);
    }
    private static void add_dealProduct_to_cart(String cust_name) {
        Scanner sc = new Scanner(System.in);
        int deal_id;
        System.out.println("enter the deal id which you want to add in cart: ");
        deal_id = sc.nextInt();
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        int z = 0;
        for (int i = 0; i < ADMIN.d.size(); i++) {
            if (ADMIN.d.get(i).getDeal_id() == deal_id) {
                z = i;
            }
        }
        custm.get(a).deal_cart.add(ADMIN.giveaway_deal.get(z));
        custm.get(a).deal_id_for_add_deal_cart.add(ADMIN.d.get(z));
        System.out.println("Successfully added");
        customer_menue(cust_name);
    }

    ArrayList<ArrayList<Integer>> view_coupons =new ArrayList<ArrayList<Integer>>();
    private static void view_coupons(String cust_name) {
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }if(custm.get(a).getStatus().equals("Prime")){
            if(custm.get(a).prime_coupons.isEmpty()){
                System.out.println("No coupons available");
            }else{
                for (int i=0;i<custm.get(a).prime_coupons.size();i++){
                    System.out.println("Coupons which are provide to you are :"+custm.get(a).prime_coupons.get(i));
                }
            }
        }else if (custm.get(a).getStatus().equals("Elite")){
            if(custm.get(a).Elite_coupons.isEmpty()){
                System.out.println("No coupons available");
            }else{
                for (int i=0;i<custm.get(a).Elite_coupons.size();i++){
                    System.out.println("Coupons which are provide to you are :"+custm.get(a).Elite_coupons.get(i));
                }
            }
        }
        customer_menue(cust_name);
    }

    private static void check_account_balance(String cust_name){
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        System.out.println("Current Account balance is: Rs "+ (float)custm.get(a).getAccount_bal());
        customer_menue(cust_name);
    }
    private static void view_cart(String cust_name) {
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        if (custm.get(a).cart.isEmpty() && custm.get(a).deal_cart.isEmpty()) {
            System.out.println("your cart is empty");
            customer_menue(cust_name);
        } else {
            if(custm.get(a).getStatus().equals("Normal")){
                for(int i=0;i<product.product_list.size();i++){
                    for (int j=0;j<custm.get(a).cart.size();j++){
                        if(product.product_list.get(i).getPid()==custm.get(a).cart.get(j).getPid()){
                            float final_price_of_product=custm.get(a).cart.get(j).getPrice();
                            System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                    "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                    "product Price: " + custm.get(a).cart.get(j).getQuantity()*final_price_of_product + "\n" +
                                    "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                    "quantity: " + custm.get(a).cart.get(j).getQuantity());
                            System.out.println();

                        }

                    }
                }
            }
            else if(custm.get(a).getStatus().equals("Prime")){
                for(int i=0;i<product.product_list.size();i++){
                    for (int j=0;j<custm.get(a).cart.size();j++){
                        if(product.product_list.get(i).getPid()==custm.get(a).cart.get(j).getPid()){
                            float final_price_of_product=custm.get(a).cart.get(j).getPrice();
                            System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                    "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                    "product Price: " + custm.get(a).cart.get(j).getQuantity()*final_price_of_product + "\n" +
                                    "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                    "quantity: " + custm.get(a).cart.get(j).getQuantity());
                            System.out.println();

                        }

                    }
                }
            }else if(custm.get(a).getStatus().equals("Elite")){
                for(int i=0;i<product.product_list.size();i++){
                    for (int j=0;j<custm.get(a).cart.size();j++){
                        if(product.product_list.get(i).getPid()==custm.get(a).cart.get(j).getPid()){
                            float final_price_of_product=custm.get(a).cart.get(j).getPrice();
                            System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                    "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                    "product Price: " + custm.get(a).cart.get(j).getQuantity()*final_price_of_product + "\n" +
                                    "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                    "quantity: " + custm.get(a).cart.get(j).getQuantity());
                            System.out.println();

                        }

                    }
                }

            }if(!custm.get(a).deal_cart.isEmpty()) {
                System.out.println("deal product in your cart is below with its name and combine price");
                for (int i = 0; i < custm.get(a).deal_id_for_add_deal_cart.size(); i++) {
                    if (custm.get(a).getStatus().equals("Normal")) {
                        System.out.println("product Id: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getDeal_id() + "\n" +
                                "deal_price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getNormal_deal_price());
                    } else if (custm.get(a).getStatus().equals("Prime")) {
                        System.out.println("product Id: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getDeal_id() + "\n" +
                                "deal_price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getPrime_deal_price());
                    } else if (custm.get(a).getStatus().equals("Elite")) {
                        System.out.println("product Id: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getDeal_id() + "\n" +
                                "deal_price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getElite_deal_price());
                    }
                    for (int j = 0; j < custm.get(a).deal_cart.get(i).size(); j++) {
                        System.out.println("product name: " + custm.get(a).deal_cart.get(i).get(j).getPname());
                    }
                    System.out.println();
                }
                customer_menue(cust_name);
            }else{
                System.out.println("No!! deal product in your cart");
                customer_menue(cust_name);
            }
        }
    }

    private static void empty_cart(String cust_name) {
        int a=0;
        for(int i=0;i<custm.size();i++){
            if(custm.get(i).cust_name.equals(cust_name)){
                a=i;
            }
        }
        custm.get(a).cart.clear();
        custm.get(a).deal_cart.clear();
        System.out.println("cart successfully emptied");
        customer_menue(cust_name);
    }

    private static void Add_money_to_wallet(String cust_name) {
        Scanner sc =new Scanner(System.in);
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        int amount;
        System.out.print("Enter the amount to be added: ");
        amount= sc.nextInt();
        custm.get(a).setWallet(amount);
        System.out.println("Money successfully added");
        customer_menue(cust_name);
    }

    ArrayList<Integer> prime_coupons=new ArrayList<Integer>();
    ArrayList<Integer> Elite_coupons=new ArrayList<Integer>();


    private static void make_paymet_Normal(String cust_name,int sum_of_original_price_for_Normal,float sum_of_final_price_of_product_Normal) {
        Scanner sc =new Scanner(System.in);
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        String permission;
        if(custm.get(a).getStatus().equals("Normal")){
            double delivery = (100 + (0.05 * sum_of_original_price_for_Normal));
            System.out.println("Delivery Charges Appied on the order are: " + (float) delivery);
            double total_cost = delivery + sum_of_final_price_of_product_Normal;
            System.out.println("total cost of your order(including delivery charges)== " + (float) total_cost);
            System.out.print("Do You want to make payment: ");
            permission= sc.next();
            if(permission.equals("yes")){
                if (total_cost > custm.get(a).getAccount_bal()) {
                    System.out.println("insufficient Balance");
                } else {
                    custm.get(a).setWallet(total_cost);
                    System.out.println("Yout order has been placed successfully!!");
                    System.out.println("your order will be delivered with in 7 to 10 days");
                    custm.get(a).cart.clear();
                    custm.get(a).deal_cart.clear();
                }
            }else{
                customer_menue(cust_name);
            }
        }
    }
    private static void make_paymet_Prime(String cust_name,int sum_of_original_price_for_Prime,float sum_of_final_price_of_product_Prime) {
        Scanner sc =new Scanner(System.in);
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        String permission;
        double delivery = (float) (100 + (0.02 * sum_of_original_price_for_Prime));
        System.out.println("Delivery Charges Appied on the order are: " + (float) delivery);
        double total_cost = (float) (delivery + sum_of_final_price_of_product_Prime);
        System.out.println("total cost of your order(including delivery charges)== " + (float) total_cost);
        System.out.print("Do You want to make payment: ");
        permission= sc.next();
        if(permission.equals("yes")){
            if (total_cost > custm.get(a).getAccount_bal()) {
                System.out.println("insufficient Balance");
            } else {
                custm.get(a).setWallet(total_cost);
                System.out.println("Yout order has been placed successfully!!");
                custm.get(a).cart.clear();
                custm.get(a).deal_cart.clear();
                System.out.println("your order will be delivered with in 3 to 6 days");
                if (!custm.get(a).prime_coupons.isEmpty()) {
                    custm.get(a).prime_coupons.clear();
                }
                if (total_cost > 5000) {
                    Random rand = new Random();
                    int rand_int1 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int1;
                    custm.get(a).prime_coupons.add(rand_int1);
                    int rand_int2 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int2;
                    custm.get(a).prime_coupons.add(rand_int2);
                    custm.get(a).view_coupons.add(custm.get(a).prime_coupons);
                }
            }
        }else{
            customer_menue(cust_name);
        }
    }

    private static void make_paymet_Elite(String cust_name, float sum_of_final_price_of_product_Elite) {
        Scanner sc =new Scanner(System.in);
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        String permission;
        double delivery = 100;
        System.out.println("Delivery Charges Appied on the order are: " + delivery);
        double total_cost = delivery + sum_of_final_price_of_product_Elite;
        System.out.println("total cost of your order(including delivery charges)== " + (float) total_cost);
        System.out.print("Do You want to make payment: ");
        permission= sc.next();
        if(permission.equals("yes")){
            if (total_cost > custm.get(a).getAccount_bal()) {
                System.out.println("insufficient Balance");
            } else {
                Random random_product = new Random();
                custm.get(a).setWallet(total_cost);
                System.out.println("Yout order has been placed successfully!!");
                System.out.println();
                custm.get(a).cart.clear();
                custm.get(a).deal_cart.clear();
                int index = 0;
                for (int i = 0; i < product.product_list.size(); i++) {
                    index = random_product.nextInt(product.product_list.size());
                }
                System.out.println("you got a free product as free from us: " + "\n" +
                        "produc name: " + product.product_list.get(index).getPname() + "\n" +
                        "worth price: " + product.product_list.get(index).getPrice());
                System.out.println("your order will be delivered within 2 days");
                if (!custm.get(a).Elite_coupons.isEmpty()) {
                    custm.get(a).Elite_coupons.clear();
                }
                if (total_cost > 5000) {
                    Random rand = new Random();
                    int rand_int1 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int1;
                    custm.get(a).Elite_coupons.add(rand_int1);
                    int rand_int2 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int2;
                    custm.get(a).Elite_coupons.add(rand_int2);
                    int rand_int3 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int3;
                    custm.get(a).Elite_coupons.add(rand_int3);
                    int rand_int4 = 5 + rand.nextInt(16 - 5);
                    custm.get(a).coupons = rand_int4;
                    custm.get(a).Elite_coupons.add(rand_int4);
                    custm.get(a).view_coupons.add(custm.get(a).Elite_coupons);
                    System.out.println();
                }
            }
        }else{
            customer_menue(cust_name);
        }
    }


    private static void checkout_cart(String cust_name) {
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        int sum_of_original_price_for_Normal = 0;
        float sum_of_final_price_of_product_Normal = 0;
        int sum_of_original_price_for_Prime = 0;
        float sum_of_final_price_of_product_Prime = 0;
        float sum_of_final_price_of_product_Elite = 0;
        System.out.println("Proceed to checkout!! Details: ");
        if (custm.get(a).getStatus().equals("Normal")) {
            for (int i = 0; i < product.product_list.size(); i++) {
                for (int j = 0; j < custm.get(a).cart.size(); j++) {
                    if (product.product_list.get(i).getPid() == custm.get(a).cart.get(j).getPid()) {
                        int original_normal = custm.get(a).cart.get(j).getQuantity() * (custm.get(a).cart.get(j).getPrice());
                        sum_of_original_price_for_Normal = sum_of_original_price_for_Normal + original_normal;
                        float final_price_of_product_for_Normal = custm.get(a).cart.get(j).getQuantity() * (custm.get(a).cart.get(j).getPrice() - (custm.get(a).cart.get(j).getPrice() * ((float) product.product_list.get(i).getDiscount_normal() / 100)));
                        sum_of_final_price_of_product_Normal = sum_of_final_price_of_product_Normal + final_price_of_product_for_Normal;
                        System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                "product Price: " + final_price_of_product_for_Normal + "\n" +
                                "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                "quantity: " + custm.get(a).cart.get(j).getQuantity());
                        System.out.println();
                    }
                }
            }
            System.out.println("Deal products in your cart given below: ");
            for (int i = 0; i < custm.get(a).deal_id_for_add_deal_cart.size(); i++) {
                System.out.println("product Id: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getDeal_id());
                for (int j = 0; j < custm.get(a).deal_cart.get(i).size(); j++) {
                    System.out.println("product name: " + custm.get(a).deal_cart.get(i).get(j).getPname());
                }
                System.out.println("deal price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getNormal_deal_price());
                System.out.println();
                sum_of_original_price_for_Normal = sum_of_original_price_for_Normal + custm.get(a).deal_id_for_add_deal_cart.get(i).getNormal_deal_price();
                sum_of_final_price_of_product_Normal = sum_of_final_price_of_product_Normal + custm.get(a).deal_id_for_add_deal_cart.get(i).getNormal_deal_price();

            }
            make_paymet_Normal(cust_name, sum_of_original_price_for_Normal, sum_of_final_price_of_product_Normal);
        } else if (custm.get(a).getStatus().equals("Prime")) {
            for (int i = 0; i < product.product_list.size(); i++) {
                for (int j = 0; j < custm.get(a).cart.size(); j++) {
                    if (product.product_list.get(i).getPid() == custm.get(a).cart.get(j).getPid()) {
                        int original = custm.get(a).cart.get(j).getQuantity() * (custm.get(a).cart.get(j).getPrice());
                        sum_of_original_price_for_Prime = sum_of_original_price_for_Prime + original;
                        int max_coupons = 0;
                        if (!custm.get(a).prime_coupons.isEmpty()) {
                            max_coupons = Collections.max(custm.get(a).prime_coupons);
                        }
                        int discount_applies_on_product = Math.max(max_coupons, product.product_list.get(i).getDiscount_prime());
                        float final_price_of_product = custm.get(a).cart.get(j).getQuantity() * (custm.get(a).cart.get(j).getPrice() - (custm.get(a).cart.get(j).getPrice() * ((float) discount_applies_on_product / 100)));
                        sum_of_final_price_of_product_Prime = sum_of_final_price_of_product_Prime + final_price_of_product;
                        System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                "product Price: " + final_price_of_product + "\n" +
                                "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                "quantity: " + custm.get(a).cart.get(j).getQuantity());
                        System.out.println();

                    }

                }
            }
        } else if (custm.get(a).getStatus().equals("Elite")) {
            for (int i = 0; i < product.product_list.size(); i++) {
                for (int j = 0; j < custm.get(a).cart.size(); j++) {
                    if (product.product_list.get(i).getPid() == custm.get(a).cart.get(j).getPid()) {
                        int max_coupons = 0;
                        if (!custm.get(a).Elite_coupons.isEmpty()) {
                            max_coupons = Collections.max(custm.get(a).Elite_coupons);
                        }
                        int discount_applies_on_product = Math.max(max_coupons, product.product_list.get(i).getDiscount_elite());
                        float final_price_of_product = custm.get(a).cart.get(j).getQuantity() * (custm.get(a).cart.get(j).getPrice() - (custm.get(a).cart.get(j).getPrice() * ((float) discount_applies_on_product / 100)));
                        sum_of_final_price_of_product_Elite = sum_of_final_price_of_product_Elite + final_price_of_product;
                        System.out.println("product name: " + custm.get(a).cart.get(j).getPname() + "\n" +
                                "product Id: " + custm.get(a).cart.get(j).getPid() + "\n" +
                                "product Price: " + final_price_of_product + "\n" +
                                "product specification: " + custm.get(a).cart.get(j).getSpecifications() + "\n" +
                                "quantity: " + custm.get(a).cart.get(j).getQuantity());
                        System.out.println();

                    }

                }
            }

        }
        if (!custm.get(a).getStatus().equals("Normal")) {
            for (int i = 0; i < custm.get(a).deal_id_for_add_deal_cart.size(); i++) {
                System.out.println("product Id: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getDeal_id());
                for (int j = 0; j < custm.get(a).deal_cart.get(i).size(); j++) {
                    System.out.println("product name: " + custm.get(a).deal_cart.get(i).get(j).getPname());
                }
                if (custm.get(a).getStatus().equals("Prime")) {
                    System.out.println("deal price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getPrime_deal_price());
                    sum_of_original_price_for_Prime = sum_of_original_price_for_Prime + custm.get(a).deal_id_for_add_deal_cart.get(i).getPrime_deal_price();
                    sum_of_final_price_of_product_Prime = sum_of_final_price_of_product_Prime + custm.get(a).deal_id_for_add_deal_cart.get(i).getPrime_deal_price();
                } else if (custm.get(a).getStatus().equals("Elite")) {
                    System.out.println("deal price: " + custm.get(a).deal_id_for_add_deal_cart.get(i).getElite_deal_price());
                    sum_of_final_price_of_product_Elite = sum_of_final_price_of_product_Elite + custm.get(a).deal_id_for_add_deal_cart.get(i).getElite_deal_price();
                }
                System.out.println();
            }
        }
        if (custm.get(a).getStatus().equals("Prime")) {
            make_paymet_Prime(cust_name, sum_of_original_price_for_Prime, sum_of_final_price_of_product_Prime);
        } else if (custm.get(a).getStatus().equals("Elite")) {
            make_paymet_Elite(cust_name, sum_of_final_price_of_product_Elite);
        }
        customer_menue(cust_name);
    }

    private static void upgrade_customer_status(String cust_name){
        Scanner sc =new Scanner(System.in);
        int a = 0;
        for (int i = 0; i < custm.size(); i++) {
            if (custm.get(i).cust_name.equals(cust_name)) {
                a = i;
            }
        }
        String upgrade_status;
        System.out.println("Current Status: "+custm.get(a).getStatus());
        upgrade_status=sc.next();
        if(upgrade_status.equals("Elite")){
            custm.get(a).setStatus("Elite");
            System.out.println(" Status updated to :"+ custm.get(a).getStatus());
            custm.get(a).setWallet(300.00);
            System.out.println();
        }
        else if(upgrade_status.equals("Prime")){
            custm.get(a).setStatus("Prime");
            System.out.println(" Status updated to :"+ custm.get(a).getStatus());
            custm.get(a).setWallet(200.00);
            System.out.println();
        }
        customer_menue(cust_name);
    }



}
class catogary {
    private final int cid;
    private final String cname;
    public ArrayList<product> p=new ArrayList<product>();
    String getcat_name() {
        return this.cname;
    }

    int getcat_id() {
        return this.cid;
    }

    catogary(String cname, int cid) {
        this.cname = cname;
        this.cid = cid;
    }
}

class product extends discount{
    final String pname;
    private final float pid;
    private int price;
    int quantity;
    private final String specifications;


    static ArrayList<product> product_list=new ArrayList<>();
    int getPrice() {
        return this.price;
    }

    float getPid() {
        return this.pid;
    }
    int getQuantity(){
        return this.quantity;
    }
    void setQuantity(int quantity){
        this.quantity=quantity;
    }
    String getPname() {
        return this.pname;
    }
    String getSpecifications(){
        return this.specifications;
    }

    product(String name, int id, String pname, float pid, int price, String specifications, int quantity,int disc_normal,int disc_prime,int disc_elite){
        super(disc_normal,disc_prime,disc_elite);
        this.pname=pname;
        this.pid=pid;
        this.price=price;
        this.specifications=specifications;
        this.quantity=quantity;

    }
}
class giveaway_deal{
    int deal_id;
    int normal_deal_price;
    int prime_deal_price;
    int elite_deal_price;

    int getDeal_id(){
        return this.deal_id;
    }
    int getNormal_deal_price(){
        return this.normal_deal_price;
    }
    int getPrime_deal_price(){
        return this.prime_deal_price;
    }
    int getElite_deal_price(){
        return this.elite_deal_price;
    }
    giveaway_deal(int did,int normal_dprice,int prime_dprice,int elite_dprice){
        this.deal_id=did;
        this.normal_deal_price=normal_dprice;
        this.prime_deal_price=prime_dprice;
        this.elite_deal_price=elite_dprice;

    }
}



class ADMIN implements check_password{
    private final String username = "saad";
    private final int password= 2021068;
    float giveaway_deal_id;
    int giveaway_deal_price;

    String getusername() {
        return this.username;
    }

    public static ArrayList<catogary> c=new ArrayList<catogary>();

    void admin_options() {
        Scanner sc = new Scanner(System.in);
        int choose;
        System.out.println("""
                Welcome saad!!!!
                    Please choose any one of the following actions:
                    1) Add category
                    2) Delete category
                    3) Add Product
                    4) Delete Product
                    5) Set Discount on Product
                    6) Add giveaway deal
                    7) Back""");
        choose = sc.nextInt();
        if (choose == 1) {
            this.add_catogary();
        } else if (choose == 2) {
            this.delete_categary();

        } else if (choose == 3) {
            this.add_product();

        } else if (choose == 4) {
            this.delete_product();

        } else if (choose == 5) {
            set_discount_product();

        } else if (choose == 6) {
            this.add_giveaway_deal();

        } else if (choose == 7) {
            assignment2.enter_application();
        }
    }

    private void add_catogary() {
        Scanner sc = new Scanner(System.in);
        int cat_id;
        String cname;
        System.out.println("enter catogary id: ");
        cat_id=sc.nextInt();
        for(int i=0;i<c.size();i++){
            if(c.get(i).getcat_id()==cat_id){
                System.out.println("catogary is already taken enter another ID");
                return;
            }
        }
        sc.nextLine();
        System.out.println("enter the catogary name");
        cname=sc.nextLine();
        catogary c1=new catogary(cname,cat_id);
        c.add(c1);
        System.out.println("Add product");
        System.out.print("product name: ");
        String pname=sc.nextLine();
        System.out.print("product ID: ");
        float pid=sc.nextFloat();
        int price;
        System.out.print("Enter the price: ");
        price=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the specification of the product: ");
        String specification=sc.nextLine();
        int quantity=1;
        product p1=new product(cname,cat_id,pname,pid,price,specification,quantity,0,0,0);
        c1.p.add(p1);
        product.product_list.add(p1);

        admin_options();
    }

    private  void add_product(){
        Scanner sc =new Scanner(System.in);
        int a=0;
        int cat_id;
        System.out.println("Enter catogary Id in which you want to add product: ");
        cat_id=sc.nextInt();
        for (int i=0;i<c.size();i++){
            if(c.get(i).getcat_id()==cat_id){
                a=i;
            }
        }

        sc.nextLine();
//        System.out.println(cato.get(a).getcat_name());
        System.out.println("Add a product:");
        System.out.print("product name: ");
        String pname=sc.nextLine();
        System.out.print("product ID: ");
        float pid=sc.nextFloat();
        for(int i=0;i<product.product_list.size();i++){
            if(product.product_list.get(i).getPid()==pid){
                System.out.println("Product Id is already taken enter another id");
                return;
            }

        }int quantity=1;
        int price;
        System.out.print("Enter the price: ");
        price=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter the specification of the product: ");
        String specification=sc.nextLine();
        String cname=c.get(a).getcat_name();
        product p2=new product(cname,cat_id,pname,pid,price,specification, quantity,0,0,0);
        c.get(a).p.add(p2);
        product.product_list.add(p2);
        admin_options();
    }

    private  void delete_categary() {
        Scanner sc =new Scanner(System.in);
        int a=0;
        int cat_id;
        System.out.println("Enter catogary ID which you want to delete: ");
        cat_id=sc.nextInt();
        for (int i=0;i<c.size();i++){
            if(c.get(i).getcat_id()==cat_id){
                a=i;
            }
        }
        c.remove(a);
        System.out.println("Successfully removed the catogary");
        admin_options();
    }
    private  void delete_product(){
        Scanner sc =new Scanner(System.in);
        int a=0;
        String cat_name;
        System.out.println("Enter catogary name from which you want to delete product: ");
        cat_name=sc.nextLine();
        for (int i=0;i<c.size();i++){
            if(c.get(i).getcat_name().equals(cat_name)){
                a=i;
            }
        }
        float pid;
        System.out.println("enter the product Id which you want to delete");
        pid= sc.nextFloat();
        int b = 0;
        for (int i=0;i<c.get(a).p.size();i++){
            if(c.get(a).p.get(i).getPid() == pid){
                b=i;
            }
        }
        c.get(a).p.remove(b);
        product.product_list.remove(b);
        System.out.println("successfully removed the product");
        admin_options();
    }
    void set_discount_product(){
        Scanner sc = new Scanner(System.in);
        float pid;
        float id;
        System.out.println("enter the catogary id of which the product belongs: ");
        id= sc.nextFloat();
        int b=0;
        for(int i=0;i<ADMIN.c.size();i++){
            if(ADMIN.c.get(i).getcat_id()==id){
                b=i;
            }
        }
        System.out.println("Dear Admin give the Product ID you want to add discount for");
        System.out.println("Enter the Product ID :");
        pid=sc.nextFloat();
        int a=0;
        for(int i=0;i<product.product_list.size();i++) {
            if (product.product_list.get(i).getPid() == pid) {
                a = i;
            }
        }
        int disc_normal;
        int disc_prime;
        int disc_elite;
        System.out.println("Enter discount for Normal,Prime and Elite customers respectively (in % terms)");
        disc_normal=sc.nextInt();
        disc_prime=sc.nextInt();
        disc_elite=sc.nextInt();
        discount discount1=new discount(disc_normal,disc_prime,disc_elite);
        product.discount_on_product.add(discount1);

        product.product_list.get(a).setDiscount_normal(disc_normal);
        product.product_list.get(a).setDiscount_prime(disc_prime);
        product.product_list.get(a).setDiscount_elite(disc_elite);

        admin_options();



    }

    static ArrayList<ArrayList<product>>giveaway_deal= new ArrayList<ArrayList<product>>();
    static ArrayList<giveaway_deal> d=new ArrayList<>();
    private  void add_giveaway_deal() {
        ArrayList<product> deal=new ArrayList<>();
        Scanner sc =new Scanner(System.in);
        System.out.println("Dear Admin give the Product IDs that you want to combine and giveaway a deal for");
        float ID1;
        float ID2;
        System.out.println("enter first product ID");
        ID1=sc.nextFloat();
        System.out.println("enter second product ID");
        ID2= sc.nextFloat();
        int a=0;int b=0;
        for(int i=0;i<product.product_list.size();i++) {
            if (product.product_list.get(i).getPid() == ID1) {
                a = i;
            }
        }
        deal.add(product.product_list.get(a));
        for(int i=0;i<product.product_list.size();i++) {
            if (product.product_list.get(i).getPid() == ID2) {
                b = i;
            }
        }deal.add(product.product_list.get(b));
        giveaway_deal.add(deal);
        int normal_deal_price;
        int prime_deal_price;
        int elite_deal_price;
        int deal_id;
        System.out.println("enter the deal Id");
        deal_id=sc.nextInt();
        System.out.println("Enter the combined price(Should be less than their combined price): ");
        System.out.print("for normal: ");
        normal_deal_price= sc.nextInt();
        System.out.print("for Prime: ");
        prime_deal_price=sc.nextInt();
        System.out.print("for Elite: ");
        elite_deal_price= sc.nextInt();
        giveaway_deal deal1=new giveaway_deal(deal_id,normal_deal_price,prime_deal_price,elite_deal_price);
        d.add(deal1);
        admin_options();
    }

    @Override
    public void password() {
        Scanner sc =new Scanner(System.in);
        String username;
        int password;
        System.out.println("""
                    Dear Admin,
                        Please enter your username and password""");
        username = sc.next();
        password = sc.nextInt();
        if (this.getusername().equals(username) && password == 2021068) {
            this.admin_options();
        } else {
            System.out.println("you entered wrong username and passoword. Pls enter again!!!");
            assignment2.enter_application();
        }
    }
}


public class assignment2 {
    static void enter_application() {
        Scanner sc = new Scanner(System.in);
        int choose;
        System.out.println("""
                WELCOME TO FLIPZON
                    1) Enter as Admin
                    2) Explore the Product Catalog
                    3) Show Available Deals
                    4) Enter as Customer
                    5) Exit the Application""");
        choose = sc.nextInt();
        if (choose == 1) {
            ADMIN a1 = new ADMIN();
            a1.password();
        } else if (choose == 2) {
            product_catalog();
        } else if (choose == 3) {
            show_available_deal();
        } else if (choose == 4) {
            enter_customer();
        } else if (choose == 5) {
            System.out.println("Thankyou for usingFlipZone!!");
        }
    }

    static void product_catalog() {
        for (int i = 0; i < ADMIN.c.size(); i++) {
            for (int j = 0; j < ADMIN.c.get(i).p.size(); j++) {
                System.out.println("product name: " + ADMIN.c.get(i).p.get(j).getPname() + "\n" +
                        "Product ID: " + ADMIN.c.get(i).p.get(j).getPid() + "\n" +
                        "Product Price: " + ADMIN.c.get(i).p.get(j).getPrice() + "\n" +
                        "Product Specification: " + ADMIN.c.get(i).p.get(j).getSpecifications());
                System.out.println();
            }
            System.out.println();
        }
        enter_application();
    }

    static void show_available_deal() {
        if (ADMIN.giveaway_deal.isEmpty()) {
            System.out.println("no deal present");
            enter_application();
        } else {
            System.out.println("produc which are included in giveaway deal are: ");
            for (int i = 0; i < ADMIN.giveaway_deal.size(); i++) {
                System.out.println("ID of giveaway deal is: " + ADMIN.d.get(i).deal_id);
                for (int j = 0; j < ADMIN.giveaway_deal.get(i).size(); j++) {
                    System.out.println("product name: " + ADMIN.giveaway_deal.get(i).get(j).getPname() + "\n" +
                            "product Id: " + ADMIN.giveaway_deal.get(i).get(j).getPid() + "\n" +
                            "product price: " + ADMIN.giveaway_deal.get(i).get(j).getPrice() + "\n" +
                            "product specifications: " + ADMIN.giveaway_deal.get(i).get(j).getSpecifications());
                    System.out.println();
                }
                System.out.println("the giveaway deal price for these product for normal is: " + ADMIN.d.get(i).getNormal_deal_price());
                System.out.println("the giveaway deal price for these product for prime is: " + ADMIN.d.get(i).getPrime_deal_price());
                System.out.println("the giveaway deal price for these product for elite is: " + ADMIN.d.get(i).getElite_deal_price());

                System.out.println();
            }enter_application();
        }
    }

    static void enter_customer() {
        Scanner sc = new Scanner(System.in);
        int choose;
        System.out.println("""
                1) Sign up
                2) Log in
                3) Back""");
        choose = sc.nextInt();
        if (choose == 1) {
            customer.sign_up();
        } else if (choose == 2) {
            customer.log_in();
        } else if (choose == 3) {
            enter_application();
        }
    }

    public static void main(String[] args) {
        enter_application();

    }

}