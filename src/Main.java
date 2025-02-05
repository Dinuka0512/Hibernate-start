import config.FactoryConfiguration;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.cfgxml.spi.CfgXmlAccessService;

public class Main {
    public static FactoryConfiguration factory = FactoryConfiguration.getInstance();;
    public static void main(String[] args) {
//        Customer customer1 = new Customer(2, "Kamal", "Bandaragama", "0789871713");
//        Customer customer2 = new Customer(3, "Nimal", "Panadura", "0712345678");
//        Customer customer3 = new Customer(4, "Sunil", "Kalutara", "0756789123");
//        Customer customer4 = new Customer(5, "Amal", "Moratuwa", "0765432189");
//        Customer customer5 = new Customer(6, "Rohan", "Galle", "0778912345");
//        Customer customer6 = new Customer(7, "Saman", "Matara", "0701122334");
//
//        saveCustomer(customer1);
//        saveCustomer(customer2);
//        saveCustomer(customer3);
//        saveCustomer(customer4);
//        saveCustomer(customer5);
//        saveCustomer(customer6);
//
//        Customer custId = getCustomerAllDetailsById(3);
//        System.out.println(custId);
//
//        System.out.println(deleteCustomer(3) == true? "Deleted":"Delete Failed");

        System.out.println(update(new Customer(4,"A","A","A"),4) == true? "Updated" : "Update Failed");
    }

    public static boolean saveCustomer(Customer customer){
        Session session = factory.getSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }

    public static Customer getCustomerAllDetailsById(int id){
        Session session = factory.getSession();
        Customer customer = session.get(Customer.class,id);
        return customer;
    }

    public static boolean deleteCustomer(int id){
        Session session = factory.getSession();
        try{
            //HERE GET THE CUSTOMER DETAILS OBJ
            Customer customer = getCustomerAllDetailsById(id);

            //HERE DELETE THE CUSTOMER OBJ
            //BECOUSE THERE HAVENT ANY CLASS WE HAVE SAID SO THATS Y WE HAVE GETH THE CUSTOMER OBJ
            Transaction transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    public static boolean update(Customer customer, int id){
        Session session = factory.getSession();
        try{
            Customer custTemp = getCustomerAllDetailsById(id);
            Transaction transaction = session.beginTransaction();

            custTemp.setName(customer.getName());
            custTemp.setEmail(customer.getEmail());
            custTemp.setPhone(customer.getPhone());

            deleteCustomer(custTemp.getId());
            saveCustomer(custTemp);

            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
}